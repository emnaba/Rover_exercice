package fr.acensi.robot.command;

import fr.acensi.robot.model.Rover;

public enum CommandEnum {
    M(new MoveForwardCommand()),
    L(new TurnLeftCommand()),
    R(new TurnRightCommand());


    private Command command;
    private Rover rover;


    CommandEnum(Command command) {
        this.command = command;

    }

    public Command getCommand() {
        return this.command;
    }
}
