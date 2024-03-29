package data;

import java.io.Serializable;

/**
 * Location data-class
 */
public class Location implements Validatable, Serializable {
    private static final long serialVersionUID = 123456789L;
    private int x;
    private Long y; //Поле не может быть null
    private Integer z; //Поле не может быть null
    private String name; //Строка не может быть пустой, Поле может быть null


    public Location(int x, Long y, Integer z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    public Integer getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ": {" + x + ";" + y.toString() + ";" + z.toString() + "}";
    }

    @Override
    public boolean validate() {
        return (y != null && z != null && (name == null || !name.isEmpty()));
    }
}
