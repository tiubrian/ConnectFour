package ai;

import connectfour.ConnectFour;
import graphics.Board;
import graphics.Cell;
import org.omg.PortableInterceptor.INACTIVE;
import state.ComputerTurn;
import state.GameOver;
import state.HumanTurn;
import state.Turn;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Brian on 16/3/24.
 */
public class ComputerPlayer {
    Turn.ConnectFourColor color;
    Turn.ConnectFourColor opposite;
    Cell[][] squares;
    private int[][] evaluationTable =   {{3, 4, 5,  7,  5,  4, 3},
                                         {4, 6, 8,  10, 8,  6, 4},
                                         {5, 8, 11, 13, 11, 8, 5},
                                         {5, 8, 11, 13, 11, 8, 5},
                                         {4, 6, 8,  10, 8,  6, 4},
                                         {3, 4, 5,  7,  5,  4, 3}};
    int depth = 8;
    int moveNumber = 0;

    // Constructor
    public ComputerPlayer(Turn.ConnectFourColor color, Cell[][] squares) {
        System.out.println("constructor call");
        if(color == Turn.ConnectFourColor.red) {
            this.color = Turn.ConnectFourColor.red;
            this.opposite = Turn.ConnectFourColor.black;
        } else {
            this.color = Turn.ConnectFourColor.black;
            this.opposite = Turn.ConnectFourColor.red;
        }
        this.squares = squares;
        //getPossibleMoves();
    }




    public ArrayList<Integer> getPossibleMoves() {
        ArrayList<Integer> moves = new ArrayList<>();

        for(int i = 0; i < 7; i++) {
            if(squares[0][i].getValue() == null) {
                moves.add(i);
            }
        }
        return moves;
    }

    public int getMove() {
        ArrayList<Integer> moves = getPossibleMoves();


        // switch(0) {
        switch(turnNumber()) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4: depth = 8; break;
            case 5:
            case 6:
            case 7:
            case 8: //depth = 9; break;
            case 9:
            case 10:

            case 11:
            case 12: depth = 8; break;
            case 13:
            case 14:
            case 15:
            case 16: depth = 9; break;
            case 17:
            case 18:
            case 19:
            case 20: depth = 11; break;

            case 21:
            case 22:
            case 23:
            case 24: depth = 15; break;
            case 25:
            case 26:
            case 27:
            case 28:
            case 29: depth = 19; break;
            case 30:

            case 31:
            case 32: depth = 21; break;
            case 33:
            case 34:
            case 35:
            case 36: depth =22; break;
            case 37:
            case 38:
            case 39:
            case 40:

            case 41:
            case 42://depth = 30;

        }
        int best = findBest(moves);

        System.out.println("depth = " + depth);
        System.out.println("turn  = " + turnNumber());


        if(best == -1) {
            Collections.shuffle(moves);
            return moves.get(0);
        } else {
            return best;
        }

    }

    private int turnNumber() {
        int turnNumber = 0;
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 7; j++) {
                if(squares[i][j].getValue() != null) {
                    turnNumber++;
                }
            }
        }
        return turnNumber;
    }

    private int findBest(ArrayList<Integer> moves) {
        int move = -1;
        double score = Integer.MIN_VALUE;
        double alpha = Integer.MIN_VALUE;
        double beta = Integer.MAX_VALUE;

        if(moves.size() == -1){
            return -1;
        }

        Collections.shuffle(moves);

        Turn.ConnectFourColor otherPlayer;
        if(color == Turn.ConnectFourColor.black) {
            otherPlayer = Turn.ConnectFourColor.red;
        } else {
            otherPlayer = Turn.ConnectFourColor.black;
        }
        for(Integer i : moves) {
            // Make move
            Cell tempMove = findSquare(i);
            tempMove.setValue(color);

            // Evaluate move
            if(win(color,tempMove.getRow(), tempMove.getCol())) {
                tempMove.setValue(null);
                return i;
            }

            double value = alphaBeta(otherPlayer, alpha, beta, depth);
            if(value > score) {
                score = value;
                move = i;
            }



            // undo move
            tempMove.setValue(null);
        }

       if(score == -1) {
            Collections.shuffle(moves);
            move = moves.get(0);
        }

        //System.out.println(evaluate(color));

        return move;
    }


    private double alphaBeta(Turn.ConnectFourColor turn, double alpha, double beta, int depth) {
        depth--;
        if(depth == 0) {
            return evaluate(color);
        }
        ArrayList<Integer> possibleMoves = getPossibleMoves();
        if(possibleMoves.size() == 0) {
            return evaluate(color);
        }
        if(turn == color) {
            for(Integer m : possibleMoves) {
                Cell tempMove = findSquare(m);
                tempMove.setValue(turn);

                if(win(turn,tempMove.getRow(), tempMove.getCol())) {
                    tempMove.setValue(null);
                    return 10000;
                }

                Turn.ConnectFourColor otherPlayer;
                if(turn == Turn.ConnectFourColor.black) {
                    otherPlayer = Turn.ConnectFourColor.red;
                } else {
                    otherPlayer = Turn.ConnectFourColor.black;
                }
                double score = alphaBeta(otherPlayer, alpha, beta, depth);
                if(score > alpha) {
                    alpha = score;
                }
                if(alpha >= beta) {
                    tempMove.setValue(null);
                    return alpha;
                }
                tempMove.setValue(null);
            }
            return alpha;
        } else {
            for(Integer m : possibleMoves) {
                Cell tempMove = findSquare(m);
                tempMove.setValue(turn);

                if(win(turn,tempMove.getRow(), tempMove.getCol())) {
                    tempMove.setValue(null);
                    return -10000;
                }

                Turn.ConnectFourColor otherPlayer;
                if(turn == Turn.ConnectFourColor.black) {
                    otherPlayer = Turn.ConnectFourColor.red;
                } else {
                    otherPlayer = Turn.ConnectFourColor.black;
                }
                double score = alphaBeta(otherPlayer, alpha, beta, depth);
                if(score < beta) {
                    beta = score;
                }
                if(alpha >= beta) {
                    tempMove.setValue(null);
                    return beta;
                }
                tempMove.setValue(null);
            }
            return beta;
        }
    }



    private double min(int depth) {
        depth--;
        if(depth == 0) {
           return evaluate(color);
        }

        Turn.ConnectFourColor thisTurnColor;
        if(color == Turn.ConnectFourColor.red) {
            thisTurnColor = Turn.ConnectFourColor.black;
        } else {
            thisTurnColor = Turn.ConnectFourColor.red;
        }
        ArrayList<Integer> possibleMoves = getPossibleMoves();
        double score = 0;
        for(Integer i : possibleMoves) {
            // Make move
            Cell tempMove = findSquare(i);
            tempMove.setValue(thisTurnColor);


            // Evaluate move
            int x = tempMove.getRow();
            int y = tempMove.getCol();
            if(checkForWin(thisTurnColor, x, y)) {
                tempMove.setValue(null);
                return -1000;
            }

            double value = max(depth);

            if(value < score) {
                score = value;
            }


            // undo move
            tempMove.setValue(null);
        }

        return score;
    }

    private double max(int depth) {
        depth--;
        if(depth == 0) {
           return evaluate(opposite);
        }

        Turn.ConnectFourColor thisTurnColor;
        if(color == Turn.ConnectFourColor.red) {
            thisTurnColor = Turn.ConnectFourColor.black;
        } else {
            thisTurnColor = Turn.ConnectFourColor.red;
        }

        ArrayList<Integer> possibleMoves = getPossibleMoves();
        double score = 0;
        for(Integer i : possibleMoves) {
            // Make move
            Cell tempMove = findSquare(i);
            tempMove.setValue(thisTurnColor);


            // Evaluate move
            int x = tempMove.getRow();
            int y = tempMove.getCol();
            if(checkForWin(thisTurnColor, x, y)) {
                tempMove.setValue(null);
                return 1000;
            }

            double value = min(depth);
            if(value > score) {
                score = value;
            }


            // undo move
            tempMove.setValue(null);
        }

        return score;
    }





    private Cell findSquare(int column) {
        for(int i = 5; i >= 0; i--) {
            if(squares[i][column].getValue() == null) {
                return squares[i][column];
            }
        }
        System.out.println("Should not ever come here!");
        return null;
    }




    public boolean checkForWin(Turn.ConnectFourColor c, int row, int column) {

        if(win(c, row, column)) {
            if(c == Turn.ConnectFourColor.red) {
                //JOptionPane.showMessageDialog(null, "Red wins!");
                //currentTurn = new GameOver(null);
                return true;
            } else {
                //JOptionPane.showMessageDialog(null, "Black wins!");
                //currentTurn = new GameOver(null);

                return true;


            }

        }
        return false;

    }


    // Checking for win
    public boolean win(Turn.ConnectFourColor color, int row, int column) {
        //Cell[][] squares = board.getSquares();
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 7; j++) {
                String k = "";
                if(squares[i][j].getValue() == Turn.ConnectFourColor.red) {
                    k = "R";
                } else if(squares[i][j].getValue() == Turn.ConnectFourColor.black) {
                    k = "B";
                } else if(squares[i][j].getValue() == null) {
                    k = "O";
                }

                sb.append(k + " ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }


    // Evaluations
    private double evaluate(Turn.ConnectFourColor currentColor) {

//        double red = getScore(Turn.ConnectFourColor.red);
//        double black = getScore(Turn.ConnectFourColor.black);
//
//
//        if(currentColor == Turn.ConnectFourColor.red) {
//            return red - black;
//        } else {
//            return black - red;
//        }
        if(currentColor == Turn.ConnectFourColor.red) {
            return fastGetSore(Turn.ConnectFourColor.red, Turn.ConnectFourColor.black);
        } else {
            return fastGetSore(Turn.ConnectFourColor.black, Turn.ConnectFourColor.red);
        }

    }

    private boolean hasWin(Turn.ConnectFourColor currentColor) {
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 7; j++) {
                if(squares[i][j].getValue() == currentColor && win(currentColor, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Turn.ConnectFourColor whatIsHighest(int row) {
        Turn.ConnectFourColor highest = null;
        for(int i = 7; i >=0; i--) {
            if(squares[row][i].getValue() != null) {
                highest = squares[row][i].getValue();
            } else {
                break;
            }
        }
        return highest;
    }

    private double getScore(Turn.ConnectFourColor currentColor) {
        double score = 0;
        score += middleScore(currentColor);
        score += potentialWins(currentColor);
        return score;
    }

    private double fastGetSore(Turn.ConnectFourColor first, Turn.ConnectFourColor second) {
        double score = 0;
        boolean firstInARow = false;
        boolean secondInARow = false;
        boolean goNextColumn = false;
       // boolean rowDone= false;
        //boolean rowAlmost1 = false;
        //boolean rowAlmost2 = false;
        for(int j = 6; j >= 0; j--) {
            firstInARow = false;
            secondInARow = false;
            goNextColumn = false;
         //   rowAlmost1 = false;
          //  rowDone = false;
           // rowAlmost2 = false;
            for(int i = 5; i >= 0; i--) {
                Turn.ConnectFourColor tempSquare = squares[i][j].getValue();
                //////////////middle score begin
                if(tempSquare == first) {
                    score += evaluationTable[i][j];
//                    switch (j) {
//                        case 0:
//                        case 6: score += 2;
//                            break;
//                        case 1:
//                        case 5: score += 4;
//                            break;
//                        case 2:
//                        case 4: score += 6;
//                            break;
//                        case 3: score += 8;
//                            break;
//
//                    }
                } else if(tempSquare == second) {
                    score -= evaluationTable[i][j];

//                    switch (j) {
//                        case 0:
//                        case 6: score -= 2;
//                            break;
//                        case 1:
//                        case 5: score -= 4;
//                            break;
//                        case 2:
//                        case 4: score -= 6;
//                            break;
//                        case 3: score -= 8;
//                            break;
//
//                    }
                }
                //////////////middle score end

                /////////////potential win begin
                if(tempSquare == null) {
                    // First

                        // Make move
                        squares[i][j].setValue(first);

                        if (/*!rowDone && !rowAlmost2 && */win(first, i, j)) {
                            if (firstInARow) {
                                score += 500;
                                //rowDone = true;
                            }
                            if(first == Turn.ConnectFourColor.red && i%2==1 ||
                                    first == Turn.ConnectFourColor.black && i%2==0) {
                                score += 50;
                                score += ((i) * 20); // Down bonus
                                /*if(win(second, i,j)) {
                                    rowDone = true;
                                } else {
                                    rowAlmost1 = true;
                                }*/
                                //j++;
                               // goNextColumn = true;
                            }
                            firstInARow = true;
                        } else {
                            firstInARow = false;
                            /*if(rowAlmost1) {
                                rowDone = true;
                            }*/
                        }

                        // undo move
                        squares[i][j].setValue(null);


                    // Second

                        // Make move
                        squares[i][j].setValue(second);

                        if (/*!rowDone && */win(second, i, j)) {
                            if (secondInARow) {
                                score -= 500;
                                //rowDone = true;
                            }
                            if(second == Turn.ConnectFourColor.red && i%2==1 ||
                                    second == Turn.ConnectFourColor.black && i%2==0) {
                                score -= 50;
                                score -= ((i) * 20); // Down bonus
                                /*if(win(first,i,j)) {
                                    rowDone = true;
                                }else{
                                    rowAlmost2 = true;
                                }*/
                                //j++;
                                //goNextColumn = true;
                            }
                            secondInARow = true;
                        } else {
                            secondInARow = false;
                           /* if(rowAlmost2) {
                                rowDone = true;
                            }*/
                        }

                        // undo move
                        squares[i][j].setValue(null);
                    }
//                    if(goNextColumn) {
//                        j++;
//                    }


                /////////////potential win end
            }
        }

        return score;
    }

    private double potentialWins(Turn.ConnectFourColor currentColor) {
        double score = 0;

        boolean inARow = false;
        for(int j = 0; j < 7; j++) {
            inARow = false;
            for(int i = 0; i < 6 ; i++) {
                if(squares[i][j].getValue() == null) {
                    // Make move
                    squares[i][j].setValue(currentColor);

                    if(win(currentColor, i, j)) {
                        if(inARow) {
                            score += 500;
                        }
                        score += 50;
                        score += (i*8); // Down bonus
                        inARow = true;
                    } else {
                        inARow = false;
                    }

                    // undo move
                    squares[i][j].setValue(null);
                }
            }
        }

        return score;
    }

    private double middleScore(Turn.ConnectFourColor currentColor) {
        int willWin = 0;
        double score = 0;
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 7; j++) {
                if(checkForWin(currentColor, i,j)) {
                    willWin++;
                }

                if(squares[i][j].getValue() == currentColor) {
                    switch (j) {
                        case 0:
                        case 6: score += 2;
                            break;
                        case 1:
                        case 5: score += 4;
                            break;
                        case 2:
                        case 4: score += 6;
                            break;
                        case 3: score += 8;
                            break;

                    }
                }
            }
        }
        //score += willWin * 1000;
        return score;
    }


}
