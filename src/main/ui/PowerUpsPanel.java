package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;

public class PowerUpsPanel extends JFrame {

    Game game;
    private JPanel jlabelPanel = new JPanel();

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

    public void addContent(JPanel panel) {
        for (String s : game.getPowerUpManager().getLog()) {
            JLabel label = new JLabel(s);
            label.setHorizontalAlignment(JLabel.CENTER);
            panel.add(label);
//            jlabelPanel.add(label);
        }
    }
}
