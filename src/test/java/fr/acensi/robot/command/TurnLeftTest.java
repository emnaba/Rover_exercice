package fr.acensi.robot.command;

import fr.acensi.robot.model.Plateau;
import fr.acensi.robot.model.Position;
import fr.acensi.robot.model.Rover;
import fr.acensi.robot.states.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TurnLeftTest {

    Position position;
    Plateau plateau;

    @Before
    public void init() {
        position = new Position(2, 3);
        plateau = new Plateau(10, 10);
    }

    @Test
    public void testLeftNorthOk() {
        //given
        RoverDirectionState northDirection = new NorthDirection();
        Rover rover = new Rover(position, northDirection, plateau);
        Command turnLeft = new TurnLeftCommand();
        turnLeft.setRobot(rover);
        //when
        turnLeft.execute();
        //then
        Assert.assertEquals("WestDirection", rover.getDirection().getClass().getSimpleName());
    }

    @Test
    public void testLeftWestOk() {
        //given
        RoverDirectionState westDirection = new WestDirection();
        Rover rover = new Rover(position, westDirection, plateau);
        Command turnLeft = new TurnLeftCommand();
        turnLeft.setRobot(rover);
        //when
        turnLeft.execute();
        //then
        Assert.assertEquals("SouthDirection", rover.getDirection().getClass().getSimpleName());
    }

    @Test
    public void testLeftEastOk() {
        //given
        RoverDirectionState eastDirection = new EastDirection();
        Rover rover = new Rover(position, eastDirection, plateau);
        Command turnLeft = new TurnLeftCommand();
        turnLeft.setRobot(rover);
        //when
        turnLeft.execute();
        //then
        Assert.assertEquals("NorthDirection", rover.getDirection().getClass().getSimpleName());
    }

    @Test
    public void testRightSouthOk() {
        //given
        RoverDirectionState southDirection = new SouthDirection();
        Rover rover = new Rover(position, southDirection, plateau);
        Command turnLeft = new TurnLeftCommand();
        turnLeft.setRobot(rover);
        //when
        turnLeft.execute();
        //then
        Assert.assertEquals("EastDirection", rover.getDirection().getClass().getSimpleName());
    }


}
