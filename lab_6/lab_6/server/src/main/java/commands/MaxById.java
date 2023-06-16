package commands;

import collection.CollectionManager;
import data.StudyGroup;

import java.io.IOException;

/**
 * Implements same-named command
 */
public class MaxById extends Command {
    private final CollectionManager collectionManager;

    /**
     * @param collectionManager - Collection to work on
     */
    public MaxById(CollectionManager collectionManager) {
        super("max_by_id", "Prints object with max ID");
        this.collectionManager = collectionManager;
    }

    /**
     * @return FINISHED, because there's no way to exit from program
     * @throws IOException
     */
    @Override
    public String apply(String argument, StudyGroup studyGroup) throws IOException {
        StudyGroup maxGroup = null;
        for (var sg : collectionManager.getCollection()) {
            if (maxGroup == null || sg.getId() > maxGroup.getId()) {
                maxGroup = sg;
            }
        }
        if (maxGroup == null) {
            return "Collection is empty!";
        } else {
            return maxGroup.toString();
        }
    }
}
