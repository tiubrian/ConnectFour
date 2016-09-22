package graphics;

//import com.sun.java.swing.action.NewAction;
import connectfour.GUIRunner;
import listeners.NewGameListener;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Brian on 16/3/24.
 */
public class NewGameFrame extends JFrame{
    private GUIRunner runner;
    private JButton cancel = new JButton("Cancel");
    private JButton newGame = new JButton("New game");

    // JLabels
    JLabel p1 = new JLabel("Red:    ");
    JLabel player1Comp = new JLabel("Red computer player   ");
    JLabel p2 = new JLabel("Black:  ");
    JLabel player2Comp = new JLabel("Black computer player ");

    // JSwitch
    JCheckBox computer1 = new JCheckBox();
    JCheckBox computer2 = new JCheckBox();


    // JPanels
    JPanel player1P = new JPanel(new FlowLayout());
    JPanel player1ChoiceP = new JPanel(new FlowLayout());
    JPanel player2P = new JPanel(new FlowLayout());
    JPanel player2ChoiceP = new JPanel(new FlowLayout());
    JPanel buttons = new JPanel(new FlowLayout());


    // Constructor
    public NewGameFrame(GUIRunner runner) {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        this.runner = runner;
        this.setSize(300, 150);

       // player1P.add(p1);
        player1ChoiceP.add(player1Comp); player1ChoiceP.add(computer1);

        //player2P.add(p2);
        player2ChoiceP.add(player2Comp); player2ChoiceP.add(computer2);

        buttons.add(newGame);
        buttons.add(cancel);

        //this.add(player1P);
        this.add(player1ChoiceP);

       // this.add(player2P);
        this.add(player2ChoiceP);

        this.add(buttons);

        addListeners();

        this.repaint();
        this.validate();
        this.setVisible(true);
    }

    private void addListeners() {
        cancel.addActionListener(new NewGameListener(runner,this,"cancel"));
        newGame.addActionListener(new NewGameListener(runner,this,"new"));
        computer1.addActionListener(new NewGameListener(runner,this,"redcomp"));
        computer2.addActionListener(new NewGameListener(runner,this, "blackcomp"));
    }


    public JCheckBox getComputer1() {
        return computer1;
    }

    public void setComputer1(JCheckBox computer1) {
        this.computer1 = computer1;
    }

    public JCheckBox getComputer2() {
        return computer2;
    }

    public void setComputer2(JCheckBox computer2) {
        this.computer2 = computer2;
    }
}
