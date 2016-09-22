package listeners;

import connectfour.GUIRunner;
import graphics.NewGameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Brian on 16/3/24.
 */
public class NewGameListener implements ActionListener {
    GUIRunner runner;
    NewGameFrame newGame;
    String command;

    // Constructor
    public NewGameListener(GUIRunner runner, NewGameFrame newGame, String command) {
        this.runner = runner;
        this.newGame = newGame;
        this.command = command;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (command) {
            case "new":
                if(newGame.getComputer1().isSelected()) {
                    runner.setType1(GUIRunner.TurnType.computerred);
                } else {
                    runner.setType1(GUIRunner.TurnType.humanred);

                }

                if(newGame.getComputer2().isSelected()) {
                    runner.setType2(GUIRunner.TurnType.computerblack);
                } else {
                    runner.setType2(GUIRunner.TurnType.humanblack);

                }
                newGame.dispose();
                runner.newGame();
            break;
            case "cancel": newGame.dispose(); break;
            case "redcomp": break;
            case "blackcomp": break;
        }
    }
}
