package collection;

import csv.CSVManager;
import data.StudyGroup;
import data.Validatable;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Vector;

/**
 * Implements collection
 */
public class CollectionManager implements Validatable {
    private Vector<StudyGroup> collection;
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;

    private CSVManager manager;

    /**
     * @param file csv file to load data
     * @throws IOException
     * @throws ParseException
     */
    public CollectionManager(String file) throws IOException, ParseException {
        manager = new CSVManager(file);
        collection = manager.readCollection();
        lastInitTime = LocalDateTime.now();
    }

    /**
     * @return true if all collection entries are valid
     */
    @Override
    public boolean validate() {
        for (var sg : collection) {
            if (!sg.validate()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return String with applied toString() for all collection entries
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var sg : collection) {
            sb.append(sg.toString());
            sb.append("\n\n");
        }
        return sb.toString();
    }


    /**
     * Saves collection to file given in constructor
     * @throws IOException
     */
    public void save() throws IOException {
        manager.writeCollection(collection);
        lastSaveTime = LocalDateTime.now();
    }


    /**
     * @param collection Collection to set
     */
    public void setCollection(Vector<StudyGroup> collection) {
        this.collection = collection;
    }


    /**
     * @return Collection
     */
    public Vector<StudyGroup> getCollection() {
        return collection;
    }

    /**
     * @return Last time of initialization
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    /**
     * @return Last time collection has been saved
     */
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    /**
     * Sorts collection
     */
    public void sort() {
        collection.sort(StudyGroup::compareTo);
    }
}
