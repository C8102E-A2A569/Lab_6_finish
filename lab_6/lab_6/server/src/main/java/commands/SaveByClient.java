package commands;

import collection.CollectionManager;
import data.StudyGroup;

import java.io.IOException;

public class SaveByClient extends Command {
    private final CollectionManager collectionManager;

    /**
     * @param collectionManager - Collection to work on
     */
    public SaveByClient(CollectionManager collectionManager) {
        super("save_by_client", "Saves collection to file by client call");
        this.collectionManager = collectionManager;
    }

    /**
     * @return FINISHED, because there's no way to exit from program
     * @throws IOException
     */
    @Override
    public String apply(String argument, StudyGroup studyGroup) throws IOException {
        collectionManager.save();
        return "";
    }
}
