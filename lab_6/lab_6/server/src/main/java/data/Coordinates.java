package data;

import java.io.Serializable;

/**
 * Coordinates data-class
 */
public class Coordinates implements Validatable, Serializable {
    private static final long serialVersionUID = 123456789L;
    private Long x; //Поле не может быть null
    private long y; //Максимальное значение поля: 30
    
    public Coordinates(Long x, long y) {
        this.x = x;
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    @Override
    public String toString() {
        return "{" + x.toString() + ";" + y + "}";
    }

    @Override
    public boolean validate() {
        return (x != null && y <= 30);
    }
}
