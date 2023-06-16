package commands;

import collection.CollectionManager;
import data.EnumParser;
import data.StudyGroup;

import java.io.IOException;

/**
 * Implements same-named command
 */
public class CountByFormOfEducation extends Command {
    private final CollectionManager collectionManager;

    /**
     * @param collectionManager - Collection to work on
     */
    public CountByFormOfEducation(CollectionManager collectionManager) {
        super("count_by_form_of_education", "Counts number of groups with some form of education");
        this.collectionManager = collectionManager;
    }

    /**
     * @return FINISHED, because there's no way to exit from program
     * @throws IOException
     */
    @Override
    public String apply(String argument, StudyGroup studyGroup) throws IOException {
        var formOfEducation = EnumParser.textToFormOfEducation.get(argument);
        int counter = 0;
        for (var sg : collectionManager.getCollection()) {
            if (sg.getFormOfEducation() == formOfEducation) {
                ++counter;
            }
        }

        return String.valueOf(counter);
    }
}
