package csv;

import data.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.Vector;

/**
 * Class provides saving and loading collection from csv file
 */
public class CSVManager {
    final private String filename;

    static SimpleDateFormat formatter = new SimpleDateFormat("EEE LLL dd HH:mm:ss zzz yyyy", Locale.US);

    public CSVManager(String filename) {
        this.filename = filename;
    }


    /**
     * @return Collection read from file
     * @throws IOException
     * @throws ParseException
     */
    public Vector<StudyGroup> readCollection() throws IOException, ParseException {
        FileReader fr = new FileReader(filename);
        var reader = new BufferedReader(fr);

        var data = new Vector<StudyGroup>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] separated = line.split(", ");
            data.add(new StudyGroup(
                    Objects.equals(separated[0], "null") ? null : separated[0],
                    new Coordinates(Objects.equals(separated[1], "null") ? null : Long.parseLong(separated[1]), Long.parseLong(separated[2])),
                    Objects.equals(separated[3], "null") ? null : Long.parseLong(separated[3]),
                    EnumParser.textToFormOfEducation.get(separated[4]),
                    EnumParser.textToSemester.get(separated[5]),
                    new Person(
                            Objects.equals(separated[6], "null") ? null : separated[6],
                            Double.parseDouble(separated[7]),
                            Double.parseDouble(separated[8]),
                            Objects.equals(separated[9], "null") ? null : separated[9],
                            new Location(
                                    Integer.parseInt(separated[10]),
                                    Objects.equals(separated[11], "null") ? null : Long.parseLong(separated[11]),
                                    Objects.equals(separated[12], "null") ? null : Integer.parseInt(separated[12]),
                                    Objects.equals(separated[13], "null") ? null : separated[13]
                            )
                    ),
                    formatter.parse(separated[14])
            ));
        }

        return data;
    }


    /**
     * @param collection Collection to write
     * @throws IOException
     */
    public void writeCollection(Vector<StudyGroup> collection) throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filename));
        StringBuilder builder = new StringBuilder();
        for (var sb: collection) {
            builder.append(sb.getName());
            builder.append(", ");
            builder.append(sb.getCoordinates().getX());
            builder.append(", ");
            builder.append(sb.getCoordinates().getY());
            builder.append(", ");
            builder.append(sb.getStudentsCount());
            builder.append(", ");
            builder.append(EnumParser.formOfEducationToText.get(sb.getFormOfEducation()));
            builder.append(", ");
            builder.append(EnumParser.semesterToText.get(sb.getSemesterEnum()));
            builder.append(", ");
            var ga = sb.getGroupAdmin();
            builder.append(ga.getName());
            builder.append(", ");
            builder.append(ga.getHeight());
            builder.append(", ");
            builder.append(ga.getWeight());
            builder.append(", ");
            builder.append(ga.getPassportID());
            builder.append(", ");
            builder.append(ga.getLocation().getX());
            builder.append(", ");
            builder.append(ga.getLocation().getY());
            builder.append(", ");
            builder.append(ga.getLocation().getZ());
            builder.append(", ");
            builder.append(ga.getLocation().getName());
            builder.append(", ");
            builder.append(sb.getCreationDate());
            builder.append('\n');
        }
        osw.write(builder.toString());
        osw.close();
    }
}
