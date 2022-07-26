package pl.wiluplwolf;

import javax.swing.*;
import java.awt.*;

/**
 * This is a class to build the button
 */
public class GameButton extends JButton{
    private final int i;

    /**
     * Constructor with two argument
     * @param i is number of button
     * @param frame is a main frame
     */
    GameButton(int i, JFrame frame){
        new JButton();
        this.i = i;
        this.addActionListener(new MakeAction(this, frame));
    }

    /**
     * Draw a circle in button
     */
    public void drawingCircle(){
        this.setText("O");
        this.setFont(new Font("Arial",Font.BOLD, 150));
        Game.doneMoves = Game.doneMoves + 1;
    }

    /**
     * Draw a cross in button
     */
    public void drawingCross(){
        this.setText("X");
        this.setFont(new Font("Arial",Font.BOLD, 150));
        Game.doneMoves = Game.doneMoves + 1;
    }

    /**
     * Getter
     * @return number of the button
     */
    public int getI() {
        return i;
    }
}
