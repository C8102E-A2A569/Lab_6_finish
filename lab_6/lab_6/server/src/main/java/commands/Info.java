package commands;

import collection.CollectionManager;
import data.StudyGroup;

import java.io.IOException;

/**
 * Implements same-named command
 */
public class Info extends Command {
    private final CollectionManager collectionManager;

    /**
     * @param collectionManager - Collection to work on
     */
    public Info(CollectionManager collectionManager) {
        super("info", "Prints collection info");
        this.collectionManager = collectionManager;
    }

    /**
     * @return FINISHED, because there's no way to exit from program
     * @throws IOException
     */
    @Override
    public String apply(String argument, StudyGroup studyGroup) throws IOException {
        return
                "Collection size: " + collectionManager.getCollection().size() + '\n' +
                        "Initialized: " + collectionManager.getLastInitTime().toString() + '\n' +
                        "Saved: " + (collectionManager.getLastSaveTime() == null ? "not saved yet" : collectionManager.getLastSaveTime()) + '\n';
    }
}
