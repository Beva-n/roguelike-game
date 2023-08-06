package ui;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {
    private final String name;
    private Color color = new Color(0, 128, 0);

    public CustomButton(String name) {
        int width = 48 * 2;
        int height = 48;
        this.name = name;
        //this.color = grey;

        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(color);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        g2.setColor(Color.white);
        g2.setFont(new Font("Serif", Font.BOLD, 15));
        g2.drawString(name, 10, getHeight() / 2 + 5);
    }

    public void setColor(Color color) {
        this.color = color;
    }


}
