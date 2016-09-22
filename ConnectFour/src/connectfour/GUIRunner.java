package connectfour;

import graphics.Board;
import graphics.Cell;
import state.ComputerTurn;
import state.GameOver;
import state.HumanTurn;
import state.Turn;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Brian on 16/3/24.
 */
public class GUIRunner {
    private Board board;
    private Turn currentTurn;
    private TurnType type1 = TurnType.humanred;
    private TurnType type2 = TurnType.humanblack;


// Constructor
    public GUIRunner() {
        newGame();

    }

    public void checkForWin(int row, int column) {

       if(win(currentTurn.getColor(), row, column)) {
           if(currentTurn.getColor() == Turn.ConnectFourColor.red) {
               JOptionPane.showMessageDialog(null, "Red wins!");
               currentTurn = new GameOver(null);
           } else {
               JOptionPane.showMessageDialog(null, "Black wins!");
               currentTurn = new GameOver(null);


           }

       }


    }

    public boolean win(Turn.ConnectFourColor color, int row, int column) {
        Cell[][] squares = board.getSquares();
        int count = 0;

        // Check vertical
        for(int i = 0; i < 6; i++) {
            if(squares[i][column].getValue() == color) {
                count++;
            } else {
                count = 0;
            }
            if(count == 4) {
                return true;
            }
        }

        // Check horizontal
        count = 0;
        for(int i = 0; i < 7; i++) {
            if(squares[row][i].getValue() == color) {
                count++;
            } else {
                count = 0;
            }
            if(count == 4) {
                return true;
            }
        }

        // Check upper left lower right diagonal
        count = 0;
        Cell starting = getStartingDiagonal1(squares, row, column);
        int startRow = starting.getRow();
        int startCol = starting.getCol();

        for(int i = 0; i < 7; i++) {
            if(startRow+i < 6 && startCol+i < 7) {
                if(squares[startRow+i][startCol+i].getValue() == color) {
                    count++;
                    if(count == 4) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            } else {
                break;
            }
        }

        // Check lower left upper right diagonal
        count = 0;
        starting = getStartingDiagonal2(squares, row, column);
        startRow = starting.getRow();
        startCol = starting.getCol();

        //System.out.println(startRow + " " + startCol);

        for(int i = 0; i < 7; i++) {


            if(startRow-i >= 0 && startCol+i < 7) {
                if(squares[startRow-i][startCol+i].getValue() == color) {
                    count++;
                    if(count == 4) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            } else {
                break;
            }
        }


        return false;
    }

    public Cell getStartingDiagonal1(Cell[][] squares, int row, int column) {
        while(true) {
            if(row == 0 || column == 0) {
                return squares[row][column];
            } else {
                row--;
                column--;
            }
        }

    }

    public Cell getStartingDiagonal2(Cell[][] squares,int row, int column) {
        while(true) {
            if(row == 5 || column == 0) {
                return squares[row][column];
            } else {
                row++;
                column--;
            }
        }
    }


    public void switchTurn() {
        if(currentTurn.getColor() == null) {
            return;
        }
        if(currentTurn.getColor() == Turn.ConnectFourColor.red) {
            if(type2 == TurnType.humanblack) {
                currentTurn = new HumanTurn(Turn.ConnectFourColor.black);
            } else {
                currentTurn = new ComputerTurn(Turn.ConnectFourColor.black, board);
                currentTurn.makeMove();
            }
        } else {

            if(type1 == TurnType.humanred) {
                currentTurn = new HumanTurn(Turn.ConnectFourColor.red);
            } else {
                currentTurn = new ComputerTurn(Turn.ConnectFourColor.red, board);
                currentTurn.makeMove();
            }
        }
    }

    public Turn.ConnectFourColor getTurnColor() {
        return currentTurn.getColor();
    }

    public void newGame() {
        if(board != null) {
            board.dispose();
        }
        board = new Board(this);

        switch(type1) {
            case humanred: currentTurn = new HumanTurn(Turn.ConnectFourColor.red); break;
            case humanblack: currentTurn = new HumanTurn(Turn.ConnectFourColor.black); break;
            case computerred: currentTurn = new ComputerTurn(Turn.ConnectFourColor.red,board); currentTurn.makeMove(); break;
            case computerblack: currentTurn = new ComputerTurn(Turn.ConnectFourColor.black,board); currentTurn.makeMove(); break;

        }
    }

    public Turn getCurrentTurn() {
        return currentTurn;
    }

    public TurnType getType1() {
        return type1;
    }

    public TurnType getType2() {
        return type2;
    }

    public void setType1(TurnType type1) {
        this.type1 = type1;
    }

    public void setType2(TurnType type2) {
        this.type2 = type2;
    }

    public enum TurnType {
        humanred, humanblack, computerred, computerblack
    }
}
