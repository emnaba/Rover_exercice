package fr.acensi.robot.model;

import fr.acensi.robot.states.RoverDirectionState;

public class Rover implements Robot {

    private Position position;

    private RoverDirectionState direction;

    private Plateau plateau;

    public Rover(Position position, RoverDirectionState direction, Plateau plateau) {
        this.position = position;
        this.direction = direction;
        this.plateau = plateau;
    }

    public synchronized void move() {
        direction.move(this);
    }

    public synchronized void turnRight() {
        direction.turnRight(this);
    }

    public synchronized void turnLeft() {
        direction.turnRight(this);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public RoverDirectionState getDirection() {
        return direction;
    }

    public void setDirection(RoverDirectionState direction) {
        this.direction = direction;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

}
