package commands;

import data.StudyGroup;

import java.io.IOException;
import java.util.Objects;


/**
 * Describe Command interface. All commands have to have 'apply()' method
 */
public abstract class Command {
    private final String name;
    private final String description;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract String apply(String argument, StudyGroup studyGroup) throws IOException;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Command command = (Command) other;
        return Objects.equals(name, command.name) && Objects.equals(description, command.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
