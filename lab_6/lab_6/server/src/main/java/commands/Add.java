package commands;

import collection.CollectionManager;
import data.StudyGroup;

import java.io.IOException;

/**
 * Implements same-named command
 */
public class Add extends Command {

    private final CollectionManager collectionManager;

    /**
     * @param collectionManager - Collection to work on
     */
    public Add(CollectionManager collectionManager) {
        super("add", "add {element}. Adds new study group to collection");
        this.collectionManager = collectionManager;
    }


    /**
     * @return FINISHED, because there's no way to exit from program
     * @throws IOException
     */
    @Override
    public String apply(String argument, StudyGroup studyGroup) throws IOException {
        collectionManager.getCollection().add(studyGroup);
        return "";
    }
}
