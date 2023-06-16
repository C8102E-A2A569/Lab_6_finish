package exchanger;

import data.StudyGroup;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Class, that sends commands to the server
 */
public class Exchanger implements AutoCloseable {
    private static final int BUFFER_SIZE = 65536;
    private SocketChannel socketChannel;

    public Exchanger() {
        this.socketChannel = openConnection();
    }

    public String exchange(String commandName, String argument, StudyGroup studyGroup) {
        SerializableCommand serializableCommand = new SerializableCommand(
                commandName, argument, SerializableStudyGroup.fromStudyGroup(studyGroup), null
        );
        return exchange(serializableCommand).getAnswer();
    }

    private SerializableCommand exchange(SerializableCommand serializableCommand) {
        try {
            writeRequest(serializableCommand);
            return readResponse();
        } catch (IOException e) {
            System.out.println("Connection to the server lost. Trying to reopen...");
            reopenConnection();
            return exchange(serializableCommand);
        }
    }

    private void writeRequest(SerializableCommand request) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
        objectStream.writeObject(request);
        objectStream.flush();

        buffer.clear();
        buffer.put(byteStream.toByteArray());
        buffer.flip();

        while (buffer.hasRemaining()) {
            socketChannel.write(buffer);
        }
    }

    private SerializableCommand readResponse() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

        buffer.clear();
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        socketChannel.read(buffer);
        buffer.flip();
        byteStream.write(buffer.array(), buffer.position(), buffer.remaining());
        buffer.clear();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(byteStream.toByteArray());
        ObjectInputStream objectStream = new ObjectInputStream(inputStream);
        try {
            return (SerializableCommand) objectStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private SocketChannel openConnection() {
        SocketChannel socketChannel;
        while (true) {
            try {
                System.out.println("Trying to open socket connection...");
                socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8088));
                System.out.println("Connection successfully opened.");
                return socketChannel;
            } catch (ConnectException e) {
                System.out.println("Couldn't open connection. Retrying in 3s...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e1) {
                    throw new RuntimeException(e1);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void reopenConnection() {
        this.socketChannel = openConnection();
    }

    @Override
    public void close() throws Exception {
        socketChannel.close();
    }
}
