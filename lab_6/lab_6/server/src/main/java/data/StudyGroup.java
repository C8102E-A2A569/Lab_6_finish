package data;

import java.util.Date;
import java.util.Objects;

/**
 * StudyGroup data-class
 */
public class StudyGroup implements Validatable, Comparable<StudyGroup> {
    private static int cur_id = 1;
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long studentsCount; //Значение поля должно быть больше 0, Поле может быть null
    private FormOfEducation formOfEducation; //Поле не может быть null
    private Semester semesterEnum; //Поле не может быть null
    private Person groupAdmin; //Поле не может быть null

    public StudyGroup(String name, Coordinates coordinates, Long studentsCount,
                      FormOfEducation formOfEducation, Semester semesterEnum, Person groupAdmin, Date creationDate) {
        this.id = cur_id;
        ++cur_id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }


    @Override
    public int compareTo(StudyGroup o) {
        if (this == o) {
            return 0;
        }
        if (o == null) {
            return 1;
        }
        if (Objects.equals(studentsCount, o.getStudentsCount())) {
            if (name.length() > o.getName().length()) {
                return 1;
            }
            return -1;
        } else if (studentsCount > o.getStudentsCount()) {
            return 1;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "ID: " + id + '\n' +
                name + " at " + coordinates.toString() + " created " + creationDate.toString() + ".\n" +
                "Students: " + studentsCount.toString() + '\n' +
                EnumParser.formOfEducationToText.get(formOfEducation) + " form of education.\n" +
                EnumParser.semesterToText.get(semesterEnum) + " semester.\n" +
                "Group admin:\n" + groupAdmin.toString();
    }

    @Override
    public boolean validate() {
        return name != null && !name.isEmpty() &&
                coordinates != null && coordinates.validate() &&
                (studentsCount == null || studentsCount > 0) &&
                formOfEducation != null && semesterEnum != null &&
                groupAdmin != null && groupAdmin.validate();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setStudentsCount(Long studentsCount) {
        this.studentsCount = studentsCount;
    }

    public void setFormOfEducation(FormOfEducation formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    public void setSemesterEnum(Semester semesterEnum) {
        this.semesterEnum = semesterEnum;
    }

    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Long getStudentsCount() {
        return studentsCount;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }
}
