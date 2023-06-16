package commands;

import collection.CollectionManager;
import data.StudyGroup;

import java.io.IOException;

/**
 * Implements same-named command
 */
public class Show extends Command {

    private final CollectionManager collectionManager;

    /**
     * @param collectionManager - Collection to work on
     */
    public Show(CollectionManager collectionManager) {
        super("show", "Prints collection data");
        this.collectionManager = collectionManager;
    }

    /**
     * @return FINISHED, because there's no way to exit from program
     * @throws IOException
     */
    @Override
    public String apply(String argument, StudyGroup studyGroup) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (var sg : collectionManager.getCollection()) {
            sb.append(sg.toString()).append("\n\n");
        }
        return sb.toString();
    }
}
