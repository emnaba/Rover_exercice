package fr.acensi.robot.states;

public enum DirectionEnum {
    //north
    N(new NorthDirection()),
    //south
    S(new SouthDirection()),
    //east
    E(new EastDirection()),
    //west
    W(new WestDirection());

    private RoverDirectionState directionState;

    DirectionEnum(RoverDirectionState directionState) {
        this.directionState = directionState;
    }

    public RoverDirectionState getDirection() {
        return directionState;
    }
}
