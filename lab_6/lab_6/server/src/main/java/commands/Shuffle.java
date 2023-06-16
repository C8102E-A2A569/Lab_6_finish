package commands;

import collection.CollectionManager;
import data.StudyGroup;

import java.io.IOException;
import java.util.Collections;

/**
 * Implements same-named command
 */
public class Shuffle extends Command {
    private final CollectionManager collectionManager;

    /**
     * @param collectionManager - Collection to work on
     */
    public Shuffle(CollectionManager collectionManager) {
        super("shuffle", "Shuffle elements in collection");
        this.collectionManager = collectionManager;
    }

    /**
     * @return FINISHED, because there's no way to exit from program
     * @throws IOException
     */
    @Override
    public String apply(String argument, StudyGroup studyGroup) throws IOException {
        Collections.shuffle(collectionManager.getCollection());
        return "";
    }
}
