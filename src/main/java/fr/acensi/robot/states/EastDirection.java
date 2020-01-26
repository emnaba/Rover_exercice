package fr.acensi.robot.states;

import fr.acensi.robot.exception.RoverOutOfBoundsException;
import fr.acensi.robot.model.Position;
import fr.acensi.robot.model.Rover;

public class EastDirection implements RoverDirectionState {

    public void move(Rover rover) {
        Position actualPosition = rover.getPosition();
        int actualPositionX = actualPosition.getX();
        if (actualPositionX == rover.getPlateau().getLength()) {
            throw new RoverOutOfBoundsException();
        }
        actualPosition.setX(actualPositionX + 1);
    }

    public void turnRight(Rover rover) {
        rover.setDirection(new SouthDirection());
    }

    public void turnLeft(Rover rover) {
        rover.setDirection(new NorthDirection());
    }


}
