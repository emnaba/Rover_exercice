package fr.acensi.robot.command;

import fr.acensi.robot.model.Robot;

public interface Command {

    public void execute();
    public void setRobot(Robot robot);
}


