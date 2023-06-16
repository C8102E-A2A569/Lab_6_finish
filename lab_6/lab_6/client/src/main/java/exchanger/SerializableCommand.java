package exchanger;

import java.io.Serializable;

public class SerializableCommand implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private final String name;
    private final String argument;
    private final SerializableStudyGroup studyGroup;
    private final String answer;

    public SerializableCommand(String name, String argument, SerializableStudyGroup studyGroup, String answer) {
        this.name = name;
        this.argument = argument;
        this.studyGroup = studyGroup;
        this.answer = answer;
    }

    public String getName() {
        return name;
    }

    public String getArgument() {
        return argument;
    }

    public SerializableStudyGroup getStudyGroup() {
        return studyGroup;
    }

    public String getAnswer() {
        return answer;
    }
}
