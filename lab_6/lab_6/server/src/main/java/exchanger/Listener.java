package exchanger;

import commands.Command;
import commands.CommandManager;
import data.StudyGroup;

import java.io.*;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Listens to the commands from client
 */
public class Listener {
    private static final int BUFFER_SIZE = 65536;
    private final CommandManager commandManager;

    private SerializableCommand serializableCommandAnswer;

    public Listener(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public String exchange(SerializableCommand serializableCommand) throws IOException {
        SerializableStudyGroup serializableStudyGroup = serializableCommand.getStudyGroup();
        String argument = serializableCommand.getArgument();
        StudyGroup studyGroup = null;
        if (serializableStudyGroup != null) {
            studyGroup = serializableStudyGroup.toStudyGroup();
        }
        Command command = commandManager.getCommands().get(serializableCommand.getName());
        if (command == null) {
            return "There is no such command!";
        }
        return command.apply(argument, studyGroup);
    }

    public void handleCommands() {
        try {
            Selector selector = Selector.open();
            ServerSocketChannel socket = ServerSocketChannel.open();
            ServerSocket serverSocket = socket.socket();
            System.out.println("Server is started...");
            serverSocket.bind(new InetSocketAddress("localhost", 8088));
            socket.configureBlocking(false);
            int ops = socket.validOps();
            socket.register(selector, ops, null);
            while (true) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> i = selectedKeys.iterator();
                while (i.hasNext()) {
                    SelectionKey key = i.next();
                    if (key.isAcceptable()) {
                        handleAccept(socket, selector);
                    } else if (key.isReadable()) {
                        handleRead(selector, key);
                    } else if (key.isWritable()) {
                        handleWrite(selector, key);
                    }
                    i.remove();
                }
            }
        } catch (BindException e) {
            System.out.println("The port is busy. Try changing the port");
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("Something went wrong when starting server");
            System.exit(-1);
        }
    }

    private static void handleAccept(ServerSocketChannel mySocket, Selector selector) throws IOException {
        System.out.println("Connection accepted");
        SocketChannel client = mySocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }

    private void handleRead(Selector selector, SelectionKey key) {
        System.out.println("Reading command...");
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
        try {
            byteBuffer.clear();
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            socketChannel.read(byteBuffer);
            byteBuffer.flip();
            byteStream.write(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining());
            byteBuffer.clear();

            ByteArrayInputStream inputStream = new ByteArrayInputStream(byteStream.toByteArray());
            ObjectInputStream objectStream = new ObjectInputStream(inputStream);
            SerializableCommand request = (SerializableCommand) objectStream.readObject();
            this.serializableCommandAnswer = new SerializableCommand(
                    null, null, null, exchange(request)
            );
            socketChannel.register(selector, SelectionKey.OP_WRITE);
        } catch (IOException | ClassNotFoundException e) {
            try {
                socketChannel.close();
            } catch (IOException e1) {
                System.out.println("Something went wrong when handling reading");
            }
            System.out.println("Client disconnected");
        }
    }

    private void handleWrite(Selector selector, SelectionKey key) {
        System.out.println("Writing answer...");
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
            objectStream.writeObject(this.serializableCommandAnswer);
            objectStream.flush();

            buffer.clear();
            buffer.put(byteStream.toByteArray());
            buffer.flip();

            while (buffer.hasRemaining()) {
                client.write(buffer);
            }
            client.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            try {
                client.close();
            } catch (IOException e1) {
                System.out.println("Something went wrong when handling reading");
            }
            System.out.println("Client disconnected");
        }
    }
}

//java -jar name.jar
// для каждо