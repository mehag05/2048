package org.cis1200.TwentyFortyEight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class GameBoard extends JPanel {

    private TwentyFortyEight ttt; // model for the game
    private JLabel status; // current status text
    private JFrame frame;
    // Game score label
    private JLabel scoreLabel;
    public static final int BOARD_WIDTH = 400;
    public static final int BOARD_HEIGHT = 400;

    /**
     * Initializes the game board.
     */
    public GameBoard(JLabel statusInit, JLabel scoreLabel) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // store reference
        this.scoreLabel = scoreLabel;
        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        ttt = new TwentyFortyEight(); // initializes model for the game
        status = statusInit; // initializes the status JLabel
        /*
         * Listens for arrow presses. Updates the model, then updates the game
         * board based off of the updated model.
         */
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    ttt.handleUp();
                    repaint();
                    ttt.addMove();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    ttt.handleDown();
                    repaint();
                    ttt.addMove();
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    ttt.handleLeft();
                    repaint();
                    ttt.addMove();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    ttt.handleRight();
                    repaint();
                    ttt.addMove();
                }
                // Checks if game is over
                if (ttt.gameIsOver()) {
                    status.setText("You Lost");
                    JOptionPane.showMessageDialog(frame, "You lost",
                            "SORRY!", JOptionPane.PLAIN_MESSAGE);
                    reset();
                }
            }
            // No need to update this method
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        ttt.reset();
        ttt.clearMoves();
        repaint();
        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }
    /**
     * (Re-)sets the board to the last move.
     */
    public void undo() {
        ttt.undo();
        ttt.printGameState();
        repaint();
        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }
    public void initBoard() {
        ttt.initBoard(); // initialize the board
    }
    public void saveProgress() throws IOException {
        ttt.saveProgress("files/savedProgress.txt");
        ttt.printGameState();
        repaint();
        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }
    public void reloadProgress() throws IOException {
        ttt.reloadProgress("files/savedProgress.txt");
        ttt.printGameState();
        repaint();
        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    /**
     * Draws the game board.
     */

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellSize = 100;
        // draws grid
        for (int i = 1; i < 4; i++) {
            g.drawLine(i * cellSize, 0, i * cellSize, 400);
            g.drawLine(0, i * cellSize, 400, i * cellSize);
        }
        // draws tile values
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                g.drawRect(i * cellSize, j * cellSize, cellSize, cellSize);
                int tileValue = ttt.board[j][i].getValue();
                if (tileValue != 0) {
                    g.drawString(String.valueOf(tileValue),
                            i * cellSize + cellSize / 2, j * cellSize + cellSize / 2);
                }
            }
        }
        //score
        int boardScore = ttt.getScore();

        // Update the score label
        SwingUtilities.invokeLater(() -> {
            scoreLabel.setText("Score: " + boardScore);
        });
    }


    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
