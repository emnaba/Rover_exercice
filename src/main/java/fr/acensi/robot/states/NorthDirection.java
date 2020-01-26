package fr.acensi.robot.states;

import fr.acensi.robot.exception.RoverOutOfBoundsException;
import fr.acensi.robot.model.Position;
import fr.acensi.robot.model.Rover;

public class NorthDirection implements RoverDirectionState {

    public void move(Rover rover) {
        Position actualPosition = rover.getPosition();
        int actualPositionY = actualPosition.getY();
        if (actualPositionY == rover.getPlateau().getWidth()) {
            throw new RoverOutOfBoundsException();
        }
        actualPosition.setY(actualPositionY + 1);
    }

    public void turnRight(Rover rover) {
        rover.setDirection(new EastDirection());
    }

    public void turnLeft(Rover rover) {
        rover.setDirection(new WestDirection());
    }
}
