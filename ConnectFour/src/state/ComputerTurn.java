package state;

import ai.ComputerPlayer;
import graphics.Board;
import graphics.Cell;

import java.util.ArrayList;
import java.util.jar.Pack200;

/**
 * Created by Brian on 16/3/24.
 */
public class ComputerTurn extends Turn {

    Board board;
    ComputerPlayer ai;

    public ComputerTurn(ConnectFourColor color, Board board) {
        super(color);
        this.board = board;
        ai = new ComputerPlayer(color, board.getSquares());
        //makeMove();
    }

    public void makeMove() {
        int column = ai.getMove();

        board.fall(column);


    }
}
