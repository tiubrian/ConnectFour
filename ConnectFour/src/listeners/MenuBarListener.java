package listeners;

import graphics.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Brian on 16/3/24.
 */
public class MenuBarListener implements ActionListener {
    private String command;
    private Board board;

    // Constructor
    public MenuBarListener(Board board, String command) {
        this.board = board;
        this.command = command;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (command) {
            case "new":board.newGame(); break;
            case "options": board.options(); break;
            case "exit": board.exit(); break;
        }
    }
}
