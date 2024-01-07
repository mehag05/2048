package org.cis1200.TwentyFortyEight;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.NoSuchElementException;

public class Run2048 implements Runnable {
    public void run() {
        // NOTE: the 'final' keyword denotes immutability even for local variables.

        // Top-level frame in which game components live
        final JFrame frame = new JFrame("2048");
        frame.setLocation(400, 400);
        //score board
        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("");
        status_panel.add(status);
        //instructions
        final String INSTRUCTIONS = ("WELCOME TO 2048!\n"
                + "2048 is a single-player sliding block puzzle game \n"
                + "designed by Italian web developer Gabriele Cirulli. \n"
                + "The game's objective is to slide numbered tiles on a grid \n"
                + "to combine them to create a tile with the number 2048.\n"
                + "upon starting you could choose whether or not you would \n"
                + "want to read in the saved progress from the last game \n"
                + "\nCONTROLS: \n"
                + "Use the arrow keys to move the tiles \n"
                + "Use the undo button to undo your last move \n"
                + "Use the save button to save the game progress \n"
                + "Use the reload button to load your saved progress \n"
                + "Use the reset button to reset the board");
        JOptionPane.showMessageDialog(frame, INSTRUCTIONS, "INSTRUCTIONS:",
                JOptionPane.PLAIN_MESSAGE);
        // score
        JLabel scoreLabel = new JLabel("Score" + "0");
        // Game board
        final GameBoard board = new GameBoard(status, scoreLabel);
        frame.add(board, BorderLayout.CENTER);
        // Initialize board
        board.initBoard();
        // Control panel of buttons
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Reset button
        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> board.reset());
        control_panel.add(reset);
        // Undo button
        final JButton undo = new JButton("Undo");
        undo.addActionListener(e -> {
            try {
                board.undo();
            } catch (NoSuchElementException ex) {
                // Display an error message if moves is empty
                JOptionPane.showMessageDialog(board, "No moves to undo",
                        "Error", JOptionPane.ERROR_MESSAGE);
                board.reset();
            }
        });
        control_panel.add(undo);
        // Save button
        final JButton save = new JButton("Save");
        save.addActionListener(e -> {
            try {
                board.saveProgress();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        control_panel.add(save);
        // Reload button
        final JButton reload = new JButton("Reload");
        reload.addActionListener(e -> {
            try {
                board.reloadProgress();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        control_panel.add(reload);
        // score
        control_panel.add(scoreLabel);
        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        // Start the game
        board.reset();
    }
}