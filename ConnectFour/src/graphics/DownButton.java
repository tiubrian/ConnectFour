package graphics;

import listeners.DownButtonListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Brian on 16/3/24.
 */
public class DownButton extends JButton {
    private String image = "images/SmallDownArrow.png";
    private int column;
    private Board board;

    // Constructor
    public DownButton(int column, Board board) {
        this.column = column;
        this.board = board;

        setImage();
        this.setSize(50, 50);

        this.addActionListener(new DownButtonListener(board, this));
    }

    public void setImage() {
//        JLabel pic;
//
//        BufferedImage myPicture = null;
//        try {
//            myPicture = ImageIO.read(new File(image));
//        } catch (IOException e) {
//            System.out.println(image);
//            e.printStackTrace();
//        }
//        pic = new JLabel(new ImageIcon(myPicture));
//        this.add(pic);
        this.add(new JLabel("X"));

    }

    public int getColumn() {
        return column;
    }
}

