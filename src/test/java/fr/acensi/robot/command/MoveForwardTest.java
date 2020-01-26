package fr.acensi.robot.command;

import fr.acensi.robot.exception.RoverOutOfBoundsException;
import fr.acensi.robot.model.Plateau;
import fr.acensi.robot.model.Position;
import fr.acensi.robot.model.Rover;
import fr.acensi.robot.states.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoveForwardTest {
    Position position;
    Plateau plateau;

    @Before
    public void init() {
        position = new Position(2, 3);
        plateau = new Plateau(10, 10);

    }

    @Test
    public void testMoveForwardNorth() {
        //given
        RoverDirectionState northDirection = new NorthDirection();
        Rover rover = new Rover(position, northDirection, plateau);
        Command move = new MoveForwardCommand();
        move.setRobot(rover);
        //when
        move.execute();
        //then
        Assert.assertEquals(2, rover.getPosition().getX());
        Assert.assertEquals(4, rover.getPosition().getY());
    }

    @Test(expected = RoverOutOfBoundsException.class)
    public void testMoveForwardNorthOutOfBounds() {
        //given
        RoverDirectionState northDirection = new NorthDirection();
        position.setY(10);
        Rover rover = new Rover(position, northDirection, plateau);
        Command move = new MoveForwardCommand();
        move.setRobot(rover);
        //when
        move.execute();
        //then
        //exception
    }

    @Test
    public void testMoveForwardSouth() {
        //given
        RoverDirectionState southDirection = new SouthDirection();
        Rover rover = new Rover(position, southDirection, plateau);
        Command move = new MoveForwardCommand();
        move.setRobot(rover);
        //when
        move.execute();
        //then
        Assert.assertEquals(2, rover.getPosition().getX());
        Assert.assertEquals(2, rover.getPosition().getY());
    }

    @Test
    public void testMoveForwardEast() {
        //given
        RoverDirectionState eastDirection = new EastDirection();
        Rover rover = new Rover(position, eastDirection, plateau);
        Command move = new MoveForwardCommand();
        move.setRobot(rover);
        //when
        move.execute();
        //then
        Assert.assertEquals(3, rover.getPosition().getX());
        Assert.assertEquals(3, rover.getPosition().getY());
    }

    @Test
    public void testMoveForwardWest() {
        //given
        RoverDirectionState westDirection = new WestDirection();
        Rover rover = new Rover(position, westDirection, plateau);
        Command move = new MoveForwardCommand();
        move.setRobot(rover);
        //when
        move.execute();
        //then
        Assert.assertEquals(1, rover.getPosition().getX());
        Assert.assertEquals(3, rover.getPosition().getY());
    }


}
