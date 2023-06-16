package commands;

import collection.CollectionManager;
import data.StudyGroup;

import java.io.IOException;

/**
 * Implements same-named command
 */
public class Update extends Command {

    private final CollectionManager collectionManager;

    /**
     * @param collectionManager - Collection to work on
     */
    public Update(CollectionManager collectionManager) {
        super("update", "update id {element}. Updates element at index");
        this.collectionManager = collectionManager;
    }

    /**
     * @return FINISHED, because there's no way to exit from program
     * @throws IOException
     */
    @Override
    public String apply(String argument, StudyGroup studyGroup) throws IOException {
        int id = Integer.parseInt(argument);
        StudyGroup sg = null;
        for (var entry : collectionManager.getCollection()) {
            if (entry.getId() == id) {
                sg = entry;
                break;
            }
        }
        if (sg == null) {
            return "There's no such ID";
        }
        sg.setName(studyGroup.getName());
        sg.setCoordinates(studyGroup.getCoordinates());
        sg.setCreationDate(studyGroup.getCreationDate());
        sg.setStudentsCount(studyGroup.getStudentsCount());
        sg.setFormOfEducation(studyGroup.getFormOfEducation());
        sg.setSemesterEnum(studyGroup.getSemesterEnum());
        sg.setGroupAdmin(studyGroup.getGroupAdmin());
        return "";
    }
}
