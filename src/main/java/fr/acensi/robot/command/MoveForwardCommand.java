package fr.acensi.robot.command;

import fr.acensi.robot.model.Robot;

public class MoveForwardCommand implements Command {
    private Robot robot;

    public MoveForwardCommand() {
    }

    public MoveForwardCommand(Robot robot) {
        this.robot = robot;
    }

    public void execute() {
        this.robot.move();
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }
}
