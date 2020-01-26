package fr.acensi.robot.model;

//immutable class
final public class Plateau {
    //max X
    final private int length;
    //max Y
    final private int width;

    public Plateau(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

}
