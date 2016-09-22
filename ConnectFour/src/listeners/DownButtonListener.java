package listeners;

import graphics.Board;
import graphics.DownButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Brian on 16/3/24.
 */
public class DownButtonListener implements ActionListener {
    private Board board;
    private DownButton button;

    // Constructor
    public DownButtonListener(Board board, DownButton button) {
        this.board = board;
        this.button = button;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int column = button.getColumn();

        board.fall(column);

    }
}
