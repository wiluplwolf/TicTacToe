package pl.wiluplwolf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is a class to create ActionListener for the button
 */
public class MakeAction implements ActionListener{
    private final GameButton game;
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
        } else {
            game.drawingCircle();
            Game.boardInt[game.getI()/3][game.getI()%3] = 1;
            //helpDraw(); //Uncomment if you would like to see board in console after each move.
        }

        if(checkGame(Game.playingPlayer)){
            changePlayer(Game.playingPlayer);
            restartGame();
        } else {
            if(checkDraw() > 0) changePlayer(Game.playingPlayer);
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
     * @param player the player
     * @return true
     */
    private boolean checkGame(int player){
        for (int i = 0; i < 3; i++) {
            if(Game.boardInt[i][0] == player && Game.boardInt[i][1] == player && Game.boardInt[i][2] == player){
                lockButtons();
                addPoint(player);
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if(Game.boardInt[0][i] == player && Game.boardInt[1][i] == player && Game.boardInt[2][i] == player){
                lockButtons();
                addPoint(player);
                return true;
            }
        }

        if(Game.boardInt[0][0] == player && Game.boardInt[1][1] == player && Game.boardInt[2][2] == player) {
            lockButtons();
            addPoint(player);
            return true;
        }

        if(Game.boardInt[0][2] == player && Game.boardInt[1][1] == player && Game.boardInt[2][0] == player) {
            lockButtons();
            addPoint(player);
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
        int s;
        if(i == 0){
            s = Integer.parseInt(Game.jLabels[1].getText());
            Game.jLabels[1].setText(++s + "");
        } else {
            s = Integer.parseInt(Game.jLabels[3].getText());
            Game.jLabels[3].setText(++s + "");
        }
    }

    /**
     * Check if the game end with draw
     */
    private int checkDraw(){
        if(Game.doneMoves >= 9){
            JOptionPane.showMessageDialog(null, "Draw", "Draw", JOptionPane.INFORMATION_MESSAGE);
            changePlayer(Game.playingPlayer);
            restartGame();
            return 0;
        }
        return 1;
    }

    /**
     * Restarting the game
     */
    private void restartGame(){
        Game.doneMoves = 0;
        frame.dispose();
        frame = new Game(Game.jLabels, Game.playingPlayer);
    }

    /**
     * Change player to next one
     * @param player current player
     */
    private void changePlayer(int player){
        if(player == 0){
            Game.playingPlayer = 1;
        } else if(player == 1){
            Game.playingPlayer = 0;
        }
    }
}
