package data;

import java.io.Serializable;

/**
 * Person data-class
 */
public class Person implements Validatable, Serializable {
    private static final long serialVersionUID = 123456789L;
    private String name; // Поле не может быть null, Строка не может быть пустой
    private double height; // Значение поля должно быть больше 0
    private double weight; // Значение поля должно быть больше 0
    private String passportID; // Длина строки не должна быть больше 31, Значение этого поля должно быть уникальным, Длина строки должна быть не меньше 8, Поле может быть null
    private Location location; // Поле может быть null

    public Person(String name, double height, double weight, String passportID, Location location) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.passportID = passportID;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getPassportID() {
        return passportID;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Name: " + name + '\n' +
                "Height: " + height + '\n' +
                "Weight: " + weight + '\n' +
                "Passport ID: " + passportID + '\n' +
                "Location: " + location.toString();
    }

    @Override
    public boolean validate() {
        return name != null && !name.isEmpty() && height > 0 && weight > 0
                && (passportID == null || 8 <= passportID.length() && passportID.length() <= 31)
                && location != null && location.validate();
    }
}

