package fr.acensi.robot.command;

import fr.acensi.robot.model.Robot;

public class TurnRightCommand implements Command {
    private Robot robot;

    public TurnRightCommand(Robot robot) {
        this.robot = robot;
    }
    public TurnRightCommand() {

    }
    public void execute() {
        this.robot.turnRight();
    }

    public Robot getRobot() {
        return robot;
    }

    @Override
    public void setRobot(Robot robot) {
        this.robot = robot;
    }
}
