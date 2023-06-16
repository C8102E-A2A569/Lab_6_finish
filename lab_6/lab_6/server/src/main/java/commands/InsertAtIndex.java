package commands;

import collection.CollectionManager;
import data.StudyGroup;

import java.io.IOException;

/**
 * Implements same-named command
 */
public class InsertAtIndex extends Command {

    private final CollectionManager collectionManager;

    /**
     * @param collectionManager - Collection to work on
     */
    public InsertAtIndex(CollectionManager collectionManager) {
        super("insert_at", "insert_at index {element}");
        this.collectionManager = collectionManager;
    }

    /**
     * @return FINISHED, because there's no way to exit from program
     * @throws IOException
     */
    @Override
    public String apply(String argument, StudyGroup studyGroup) throws IOException {
        var index = Integer.parseInt(argument);
        collectionManager.getCollection().insertElementAt(studyGroup, index);
        return "";
    }
}
