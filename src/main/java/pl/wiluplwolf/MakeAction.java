package pl.wiluplwolf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is a class to create ActionListener for the button
 */
public class MakeAction implements ActionListener{
    private final GameButton game;
    private int scored = 0;
    private JFrame frame;

    /**
     * Constructor with two arguments
     * @param game the button
     * @param frame the main frame
     */
    MakeAction(GameButton game, JFrame frame){
        this.game = game;
        this.frame = frame;
    }

    /**
     * ActionListener for the button
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        game.setEnabled(false);

        if(Game.playingPlayer == 0){
            game.drawingCross();
            Game.boardInt[game.getI()/3][game.getI()%3] = 0;
            //helpDraw(); //Uncomment if you would like to see board in console after each move.

            if(checkGame(Game.playingPlayer)){
                //System.out.println("Win 0");
                restartGame();
            }
            checkDraw(scored);
            Game.playingPlayer = 1;
        } else {
            game.drawingCircle();
            Game.boardInt[game.getI()/3][game.getI()%3] = 1;
            //helpDraw(); //Uncomment if you would like to see board in console after each move.

            if(checkGame(Game.playingPlayer)){
                //System.out.println("Win 1");
                restartGame();
            }
            checkDraw(scored);
            Game.playingPlayer = 0;
        }
    }

    /**
     * Simple method drawing board in console
     * @since 1.0
     */
    private void helpDraw(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++) {
                System.out.print(Game.boardInt[i][j]);
            }
            System.out.println();
        }
        System.out.println("------");
    }

    /**
     * Method to checks if anyone has won
     * @param x the player
     * @return true
     */
    private boolean checkGame(int x){
        for (int i = 0; i < 3; i++) {
            if(Game.boardInt[i][0] == x && Game.boardInt[i][1] == x && Game.boardInt[i][2] == x){
                lockButtons();
                addPoint(x);
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if(Game.boardInt[0][i] == x && Game.boardInt[1][i] == x && Game.boardInt[2][i] == x){
                lockButtons();
                addPoint(x);
                return true;
            }
        }

        if(Game.boardInt[0][0] == x && Game.boardInt[1][1] == x && Game.boardInt[2][2] == x) {
            lockButtons();
            addPoint(x);
            return true;
        }

        if(Game.boardInt[0][2] == x && Game.boardInt[1][1] == x && Game.boardInt[2][0] == x) {
            lockButtons();
            addPoint(x);
            return true;
        }

        return false;
    }

    /**
     * Method to lock buttons
     */
    private void lockButtons(){
        for (GameButton game: Game.buttons) {
            game.setEnabled(false);
        }
    }

    /**
     * Method to add point to players
     * @param i the player
     */
    private void addPoint(int i){
        int s = 0;
        if(i == 0){
            s = Integer.parseInt(Game.jLabels[1].getText());
            Game.jLabels[1].setText(++s + "");
        } else {
            s = Integer.parseInt(Game.jLabels[3].getText());
            Game.jLabels[3].setText(++s + "");
        }
        scored++;
    }

    /**
     * Check if the game end with draw
     * @param scored information about that the point has been added
     */
    private void checkDraw(int scored){
        if(Game.doneMoves >= 9 && scored == 0){
            //System.out.println("Draw");
            JOptionPane.showMessageDialog(null, "Draw", "Draw", JOptionPane.INFORMATION_MESSAGE);
            restartGame();
        }
    }

    /**
     * Restarting the game
     */
    private void restartGame(){
        Game.doneMoves = 0;
        if(Game.playingPlayer == 0){
            Game.playingPlayer = 1;
        } else {
            Game.playingPlayer = 0;
        }
        frame.dispose();
        frame = new Game(Game.jLabels);
    }
}
