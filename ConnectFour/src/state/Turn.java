package state;

import connectfour.ConnectFour;

/**
 * Created by Brian on 16/3/24.
 */
public class Turn {
    ConnectFourColor color;

    // Constructor
    public Turn(ConnectFourColor color) {
        this.color = color;
    }

    public void move() {

    }

    public ConnectFourColor getColor() {
        return color;
    }

    public void makeMove() {}

    public enum ConnectFourColor {
        red, black
    }
}
