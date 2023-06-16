package commands;

import collection.CollectionManager;
import data.StudyGroup;

import java.io.IOException;

/**
 * Implements same-named command
 */
public class Clear extends Command {
    private final CollectionManager collectionManager;

    /**
     * @param collectionManager - Collection to work on
     */
    public Clear(CollectionManager collectionManager) {
        super("clear", "Clears collection");
        this.collectionManager = collectionManager;
    }

    /**
     * @return FINISHED, because there's no way to exit from program
     * @throws IOException
     */
    @Override
    public String apply(String argument, StudyGroup studyGroup) throws IOException {
        collectionManager.getCollection().clear();
        return "";
    }
}
