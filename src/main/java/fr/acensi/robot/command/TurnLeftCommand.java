package fr.acensi.robot.command;

import fr.acensi.robot.model.Robot;

public class TurnLeftCommand implements Command {
    private Robot robot;

    public TurnLeftCommand(Robot robot) {
        this.robot = robot;
    }
    public TurnLeftCommand() {
    }

    public void execute() {
        this.robot.turnLeft();
    }

    public Robot getRobot() {
        return robot;
    }

    @Override
    public void setRobot(Robot robot) {
        this.robot = robot;
    }
}
