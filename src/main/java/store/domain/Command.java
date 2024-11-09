package store.domain;

import java.util.Arrays;

public enum Command {
    YES("Y",true),
    NO("N",false);

    private final String name;
    private final boolean flag;

    Command(String name, boolean flag) {
        this.name = name;
        this.flag = flag;
    }

    public static Command find(String findCommand) {
        return Arrays.stream(Command.values())
                .filter(command -> command.name.equals(findCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Command not found"));
    }

}
