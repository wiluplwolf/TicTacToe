package pl.wiluplwolf;

import javax.swing.*;
import java.awt.*;

/**
 *  This is a class to build the game
 */
public class Game extends JFrame {

    static int playingPlayer = 0;
    static int[][] boardInt = new int[3][3];
    static int doneMoves = 0;
    static GameButton[] buttons = new GameButton[9];
    static JLabel[] jLabels = new JLabel[4];
    private final JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private final JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private final JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private final JPanel panelGame = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private final JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.CENTER));

    /**
     * No-argument constructor
     */
    public Game(){
        this.setVisible(true);
        this.setTitle("Tic Tac Toe");
        this.setResizable(false);
        createLabels();
        gameBoard(this);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JOptionPane.showMessageDialog(null,startingPlayerName(playingPlayer),"Starting...",JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Constructor with one argument
     * @param jLabel is an array of JLabels
     */
    public Game(JLabel[] jLabel){
        this.setVisible(true);
        this.setTitle("Tic Tac Toe");
        this.setResizable(false);
        createLabels(jLabel);
        gameBoard(this);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JOptionPane.showMessageDialog(null,startingPlayerName(playingPlayer),"Starting...",JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This method is creating a GUI
     * @param frame is a main frame
     */
    private void gameBoard(JFrame frame){
        //Create buttons and fulfill 'boardInt'
        for (int i = 0; i < 9; i++) {
            buttons[i] = new GameButton(i, frame);
            buttons[i].setPreferredSize(new Dimension(200 , 200));
            addToPanel(i);
            boardInt[i/3][i%3] = 2;
        }

        //Add buttons to 'panelGame'
        panelGame.setLayout(new GridLayout(3,1));
        panelGame.add(panel1, BorderLayout.NORTH);
        panelGame.add(panel2, BorderLayout.CENTER);
        panelGame.add(panel3, BorderLayout.SOUTH);

        //Add both 'panelGame' and 'panelInfo' to main container
        this.getContentPane().add(panelGame, BorderLayout.NORTH);
        this.getContentPane().add(panelInfo, BorderLayout.SOUTH);
    }

    /**
     * Method for adding button to the right panel
     * @param i is number of button
     */
    private void addToPanel(int i){
        if(i <= 2){
            panel1.add(buttons[i]);
        }else if(i < 6){
            panel2.add(buttons[i]);
        }else {
            panel3.add(buttons[i]);
        }
    }

    /**
     * No-argument method needed to fulfill labels with names and scores
     */
    private void createLabels(){
        //Create labels with name and score for players
        jLabels[0] = new JLabel("Player X: ");
        jLabels[0].setFont(new Font("Arial", Font.BOLD , 30));
        jLabels[1] = new JLabel("0");
        jLabels[1].setFont(new Font("Arial", Font.BOLD , 30));
        jLabels[2] = new JLabel("   Player O: ");
        jLabels[2].setFont(new Font("Arial", Font.BOLD , 30));
        jLabels[3] = new JLabel("0");
        jLabels[3].setFont(new Font("Arial", Font.BOLD , 30));

        //Add labels to 'panelInfo'
        for (JLabel l: jLabels) {
            panelInfo.add(l);
        }
    }

    /**
     * Overloaded method createLabels for saving old labels
     * @param jLabels1 is an array of JLabels
     */
    private void createLabels(JLabel[] jLabels1){
        jLabels = jLabels1;

        //Add labels to 'panelInfo'
        for (JLabel l: jLabels) {
            panelInfo.add(l);
        }
    }

    private String startingPlayerName(int playingPlayer){
        if(playingPlayer == 0){
            return "Player X starts the game";
        } else {
            return "Player O starts the game";
        }
    }
}
