package graphics;

import state.Turn;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by Brian on 16/3/24.
 */
public class Cell extends JPanel {
    JLabel picture;
    Turn.ConnectFourColor value = null;

    Color squareColor = new Color(91, 100, 88);

    private int row;
    private int col;

// Constructor
    public Cell(int row, int col) {
        this.setLayout(new FlowLayout());
        this.setBackground(squareColor);
        this.setBorder(new LineBorder(new Color(39, 9, 255), 10));

        this.row = row;
        this.col = col;


        this.setVisible(true);
    }

    public void setRed() {
        this.setBackground(Color.red);
        this.repaint();
        this.validate();
    }

    public void setBlack() {
        this.setBackground(Color.black);
        this.repaint();
        this.validate();
    }

    public Turn.ConnectFourColor getValue() {
        return value;
    }

    public void setNormal() {
        this.setBorder(new LineBorder(new Color(39, 9, 255), 10));
    }

    public void setSet() {
        this.setBorder(new LineBorder(new Color(82, 27, 100), 10));

    }

    public void setValue(Turn.ConnectFourColor value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
