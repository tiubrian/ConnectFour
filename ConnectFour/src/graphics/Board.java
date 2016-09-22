package graphics;

import connectfour.GUIRunner;
import listeners.MenuBarListener;
import state.ComputerTurn;
import state.HumanTurn;
import state.Turn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 * Created by Brian on 16/3/24.
 */
public class Board extends JFrame {
    private final int cellSize = 100;

    public static Cell[][] squares = new Cell[6][7];
    private DownButton[] buttons = new DownButton[7];
    private GUIRunner runner;
    private JMenuBar theMenu;
    private NewGameFrame newGame;


    // Constructor
    public Board(GUIRunner runner) {
       // this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Connect Four");

        this.runner = runner;

        this.setSize(cellSize * 7, cellSize * 7);
        this.setLayout(new GridLayout(7, 7));

        addTopButtonArrows();
        addSquares();

        setMenu();

        this.repaint();
        this.validate();
        this.setVisible(true);
    }

    private void setMenu() {
        theMenu = new JMenuBar();
        this.setJMenuBar(theMenu);

        JMenu file = new JMenu("File");

        // Menu items
        JMenuItem newGame = new JMenuItem("New game");
        //JMenuItem gameOptions = new JMenuItem("Game options");
        JMenuItem exit = new JMenuItem("Exit");

        // Action listeners
        newGame.addActionListener(new MenuBarListener(this, "new"));
       // gameOptions.addActionListener(new MenuBarListener(this, "options"));
        exit.addActionListener(new MenuBarListener(this, "exit"));

        file.add(newGame);
       // file.add(gameOptions);
        file.add(exit);

        theMenu.add(file);

/*        // Options
        JMenu options = new JMenu("Options");
        JMenuItem reverse = new JMenuItem(("Reverse the board"));
        reverse.addActionListener(new MenuBarListener(this, "reverse"));*/


       // options.add(reverse);

        //theMenu.add(options);
    }

    // Add the arrows at the top
    private void addTopButtonArrows() {


    }

    // Creates and adds Cells to the square array and to the board
    private void addSquares() {
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                if(i == 0) {
                    this.add(new DownButton(j, this));
                } else {
                    Cell c = new Cell(i-1,j);
                    squares[i-1][j] = c;
                    this.add(c);
                }
            }
        }
    }


    int tempRow = 0;
    int tempCol = 0;
    public void fall(int column) {
        if(runner.getTurnColor() == null) {
            return;
        }

        squares[tempRow][tempCol].setNormal();

        int highest = findHighest(column);
        squares[highest][column].setSet();
        tempRow = highest;
        tempCol = column;
        if(runner.getCurrentTurn() instanceof HumanTurn) {
            if (highest != -1) {
                if (runner.getTurnColor() == Turn.ConnectFourColor.red) {
                    squares[highest][column].setRed();
                    squares[highest][column].setValue(Turn.ConnectFourColor.red);
                } else {
                    squares[highest][column].setBlack();
                    squares[highest][column].setValue(Turn.ConnectFourColor.black);
                }
                this.validate();
                this.repaint();

                runner.checkForWin(highest, column);
                runner.switchTurn();
            }

        } else if(runner.getCurrentTurn() instanceof ComputerTurn) {
            if (runner.getTurnColor() == Turn.ConnectFourColor.red) {
                squares[highest][column].setRed();
                squares[highest][column].setValue(Turn.ConnectFourColor.red);
            } else {
                squares[highest][column].setBlack();
                squares[highest][column].setValue(Turn.ConnectFourColor.black);
            }

            this.validate();
            this.repaint();

            runner.checkForWin(highest, column);
            runner.switchTurn();

        }


    }

    private int findHighest(int column) {
        for(int i = 5; i >= 0; i--) {
            if(squares[i][column].getValue() == null) {
                return i;
            }
        }
        return -1;
    }

    public Cell[][] getSquares() {
        return squares;
    }


    // Action listeners
    public void newGame() {
        newGame = new NewGameFrame(runner);
    }

    public void options() {

    }

    public void exit() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
