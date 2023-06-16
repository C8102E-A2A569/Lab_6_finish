package commands;

import collection.CollectionManager;
import data.StudyGroup;

import java.io.IOException;
import java.util.Vector;

/**
 * Implements same-named command
 */
public class RemoveGreater extends Command {
    private final CollectionManager collectionManager;

    /**
     * @param collectionManager - Collection to work on
     */
    public RemoveGreater(CollectionManager collectionManager) {
        super("remove_greater", "Removes the biggest element");
        this.collectionManager = collectionManager;
    }

    /**
     * @return FINISHED, because there's no way to exit from program
     * @throws IOException
     */
    @Override
    public String apply(String argument, StudyGroup studyGroup) throws IOException {
        Vector<StudyGroup> sifted = new Vector<>();
        for (var sg : collectionManager.getCollection()) {
            if (studyGroup.compareTo(sg) >= 0) {
                sifted.add(sg);
            }
        }

        collectionManager.setCollection(sifted);
        return "";
    }
}
