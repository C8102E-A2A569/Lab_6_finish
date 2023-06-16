import collection.CollectionManager;
import commands.*;
import exchanger.Listener;
import executer.Executor;


public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid number of arguments!");
            return;
        }
        try {
            CollectionManager collectionManager = new CollectionManager(args[0]);
            collectionManager.sort();

            if (!collectionManager.validate()) {
                System.out.println("Not valid data in file!");
                return;
            }
            var commandManager = new CommandManager() {{
                addCommand(new Info(collectionManager));
                addCommand(new Show(collectionManager));
                addCommand(new Add(collectionManager));
                addCommand(new Update(collectionManager));
                addCommand(new RemoveById(collectionManager));
                addCommand(new Clear(collectionManager));
                addCommand(new SaveByClient(collectionManager));
                addCommand(new InsertAtIndex(collectionManager));
                addCommand(new Shuffle(collectionManager));
                addCommand(new RemoveGreater(collectionManager));
                addCommand(new SumOfStudentsCount(collectionManager));
                addCommand(new MaxById(collectionManager));
                addCommand(new CountByFormOfEducation(collectionManager));
            }};

            Listener listener = new Listener(commandManager);
            Thread handleCommandsThread = new Thread(listener::handleCommands);
            handleCommandsThread.start();

            Executor executor = new Executor(new Save(collectionManager));
            Thread interactiveRunThread = new Thread(executor::interactiveRun);
            interactiveRunThread.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}