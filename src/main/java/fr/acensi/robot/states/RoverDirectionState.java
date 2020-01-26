package fr.acensi.robot.states;

import fr.acensi.robot.model.Rover;

public interface RoverDirectionState {
    void move(Rover rover);

    void turnRight(Rover rover);

    void turnLeft(Rover rover);

}
