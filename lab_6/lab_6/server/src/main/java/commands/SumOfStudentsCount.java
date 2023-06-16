package commands;

import collection.CollectionManager;
import data.StudyGroup;

import java.io.IOException;

/**
 * Implements same-named command
 */
public class SumOfStudentsCount extends Command {
    private final CollectionManager collectionManager;

    /**
     * @param collectionManager - Collection to work on
     */
    public SumOfStudentsCount(CollectionManager collectionManager) {
        super("sum_of_students_count", "Sums all students count");
        this.collectionManager = collectionManager;
    }

    /**
     * @return FINISHED, because there's no way to exit from program
     * @throws IOException
     */
    @Override
    public String apply(String argument, StudyGroup studyGroup) throws IOException {
        long result = 0;
        for (var sg : collectionManager.getCollection()) {
            result += sg.getStudentsCount();
        }
        return "Sum of students count: " + result;
    }
}
