package commands;

import collection.CollectionManager;
import data.StudyGroup;

import java.io.IOException;

/**
 * Implements same-named command
 */
public class RemoveById extends Command {
    private final CollectionManager collectionManager;

    /**
     * @param collectionManager - Collection to work on
     */
    public RemoveById(CollectionManager collectionManager) {
        super("remove_by_id", "remove_by_id id. Removes element with equal id");
        this.collectionManager = collectionManager;
    }

    /**
     * @return FINISHED, because there's no way to exit from program
     * @throws IOException
     */
    @Override
    public String apply(String argument, StudyGroup studyGroup) throws IOException {
        int id = Integer.parseInt(argument);
        for (int i = 0; i < collectionManager.getCollection().size(); ++i) {
            if (collectionManager.getCollection().get(i).getId() == id) {
                collectionManager.getCollection().remove(i);
                return "Removed!";
            }
        }
        return "There's no such element!";
    }
}
