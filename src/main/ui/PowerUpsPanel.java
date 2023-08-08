package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;

// Represents the display screen of all the power ups obtained so far
public class PowerUpsPanel extends JFrame {

    Game game;

    //Constructs a screen that gets its data of power ups from game
    public PowerUpsPanel(Game game) {
        super("Power Ups Obtained");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel container = new JPanel();
        JScrollPane scrPane = new JScrollPane(container);
        scrPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scrPane);
        container.setLayout(new GridLayout(0, 1));

        this.game = game;
        addContent(container);

//        add(jlabelPanel, BorderLayout.NORTH);

        setSize(400, 600);
        setLocationRelativeTo(null);
    }

    //Modifies: panel
    //Effects: adds the name of all the power ups obtains as JLabels to the Jpanel
    public void addContent(JPanel panel) {
        for (String s : game.getPowerUpManager().getLog()) {
            JLabel label = new JLabel(s);
            label.setHorizontalAlignment(JLabel.CENTER);
            panel.add(label);
        }
    }
}
