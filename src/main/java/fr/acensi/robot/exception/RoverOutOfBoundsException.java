package fr.acensi.robot.exception;

public class RoverOutOfBoundsException extends RuntimeException {
    public RoverOutOfBoundsException() {
        super("The rover could not exceed the plateau's limit");
    }
}
