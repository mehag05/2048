package org.cis1200.TwentyFortyEight;
import java.io.*;
import java.util.LinkedList;

public class TwentyFortyEight {

    Tile[][] board;
    private boolean gameOver;
    private LinkedList<Tile[][]> moves = new LinkedList<>();

    /**
     * Constructor sets up game state.
     */
    public TwentyFortyEight() {
        reset();
    }
    // Creating a board of zeroes to avoid null exceptions
    public void initBoard() {
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                board[r][c] = new Tile(0);
            }
        }
    }
    public int getScore() {
        int score = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                score += board[i][j].getValue();
            }
        }
        return score;
    }

    // attempting to handle the action when the up arrow is pressed
    public void randomTile() {
        int randomRow, randomColumn;
        do {
            randomRow = (int) (Math.random() * 4);
            randomColumn = (int) (Math.random() * 4);
        } while (board[randomRow][randomColumn].getValue() != 0);
        board[randomRow][randomColumn] = new Tile(2);
    }

    public void handleUp() {
        // Move everything up
        for (int c = 0; c < 4; c++) {
            for (int r = 3; r > 0; r--) {
                if (board[r][c].getValue() != 0 && board[r - 1][c].getValue() == 0) {
                    board[r - 1][c].setTile(board[r][c].getValue());
                    board[r][c].setTile(0);
                }
            }
        }
        // Merge matching elements that are vertically stacked
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 3; r++) {
                if (board[r][c].getValue() == board[r + 1][c].getValue()) {
                    board[r][c].setTile(2 * board[r][c].getValue());
                    board[r + 1][c].setTile(0);
                }
            }
        }
        // Move everything up again to handle gaps when merging happens
        for (int c = 0; c < 4; c++) {
            for (int r = 3; r > 0; r--) {
                if (board[r][c].getValue() != 0 && board[r - 1][c].getValue() == 0) {
                    board[r - 1][c].setTile(board[r][c].getValue());
                    board[r][c].setTile(0);
                }
            }
        }
        // Add a new 2 randomly in an empty slot
        randomTile();
    }

    // attempting to handle the action when the up arrow is pressed
    public void handleDown() {
        // Move everything down
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 3; r++) {
                if (board[r][c].getValue() != 0 && board[r + 1][c].getValue() == 0) {
                    board[r + 1][c].setTile(board[r][c].getValue());
                    board[r][c].setTile(0);
                }
            }
        }
        // Merge matching elements that are vertically stacked
        for (int c = 0; c < 4; c++) {
            for (int r = 3; r > 0; r--) {
                if (board[r][c].getValue() == board[r - 1][c].getValue()) {
                    board[r][c].setTile(2 * board[r][c].getValue());
                    board[r - 1][c].setTile(0);
                }
            }
        }
        // Move everything down again
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 3; r++) {
                if (board[r][c].getValue() != 0 && board[r + 1][c].getValue() == 0) {
                    board[r + 1][c].setTile(board[r][c].getValue());
                    board[r][c].setTile(0);
                }
            }
        }
        // Add a new 2 randomly in an empty slot
        randomTile();
    }
    public void handleLeft() {
        // Move everything to the left
        for (int r = 3; r >= 0; r--) {
            for (int c = 3; c >= 1; c--) {
                if (board[r][c].getValue() != 0 && board[r][c - 1].getValue() == 0) {
                    board[r][c - 1].setTile(board[r][c].getValue());
                    board[r][c].setTile(0);
                }
            }
        }

        // Merge matching elements that are horizontally stacked
        for (int r = 3; r >= 0; r--) {
            for (int c = 3; c >= 1; c--) {
                if (board[r][c].getValue() == board[r][c - 1].getValue()) {
                    board[r][c - 1].setTile(2 * board[r][c].getValue());
                    board[r][c].setTile(0);
                }
            }
        }

        // Move everything to the left again
        for (int r = 3; r >= 0; r--) {
            for (int c = 3; c >= 1; c--) {
                if (board[r][c].getValue() != 0 && board[r][c - 1].getValue() == 0) {
                    board[r][c - 1].setTile(board[r][c].getValue());
                    board[r][c].setTile(0);
                }
            }
        }

        // Add a new 2 randomly in an empty slot
        randomTile();
    }
    public void handleRight() {
        // Move everything to the right
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c].getValue() != 0 && board[r][c + 1].getValue() == 0) {
                    board[r][c + 1].setTile(board[r][c].getValue());
                    board[r][c].setTile(0);
                }
            }
        }

        // Merge matching elements that are horizontally stacked
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c].getValue() == board[r][c + 1].getValue()) {
                    board[r][c + 1].setTile(2 * board[r][c + 1].getValue());
                    board[r][c].setTile(0);
                }
            }
        }

        // Move everything to the right again
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c].getValue() != 0 && board[r][c + 1].getValue() == 0) {
                    board[r][c + 1].setTile(board[r][c].getValue());
                    board[r][c].setTile(0);
                }
            }
        }

        // Add a new 2 randomly in an empty slot
        randomTile();
    }
    // Helper for game over
    public boolean cannotCombine() {
        // Check horizontally
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c].getValue() == board[r][c + 1].getValue()) {
                    return false;
                }
            }
        }

        // Check vertically
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 4; c++) {
                if (board[r][c].getValue() == board[r + 1][c].getValue()) {
                    return false;
                }
            }
        }

        return true;
    }
    // Checks if game over
    public boolean gameIsOver() {
        gameOver = true;
        for (int r = 0; r < 4; r++) {  // Adjusted loop bounds
            for (Tile t : board[r]) {
                if (t.getValue() == 0) {
                    gameOver = false;
                    break;
                }
            }
        }
        return gameOver && cannotCombine();
    }

    public void addMove() {
        moves.addFirst(copyBoard(board));
    }
    private Tile[][] copyBoard(Tile[][] original) {
        Tile[][] copy = new Tile[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            for (int j = 0; j < original[i].length; j++) {
                copy[i][j] = new Tile(original[i][j].getValue());
            }
        }
        return copy;
    }
    public void undo() {
        if (!moves.isEmpty()) {
            moves.removeFirst();
            board = moves.getFirst();
        }
    }
    public void clearMoves() {
        moves.clear();
    }

    /**
     * printGameState prints the current game state
     * for debugging.
     */
    public void printGameState() {
        System.out.println("Board State:");

        // Print column headers
        System.out.print("  ");
        for (int j = 0; j < 4; j++) {
            System.out.print("  " + j + "  ");
        }
        System.out.println();

        for (int i = 0; i < 4; i++) {
            // Print row header
            System.out.print(i + " ");

            for (int j = 0; j < 4; j++) {
                System.out.print("| " + board[i][j].getValue() + " |");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * reset (re-)sets the game state to start a new game.
     */
    public void reset() {
        board = new Tile[4][4];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[j][i] = new Tile(0);
            }
        }
        gameOver = false;
    }

    public void saveProgress(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Tile[][] b: moves) {
                for (int r = 0; r < 4; r++) {
                    for (int c = 0; c < 4; c++) {
                        writer.write(Integer.toString(b[r][c].getValue()));
                        if (c < 3) {
                            writer.write(",");
                        }
                    }
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public void reloadProgress(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            moves.clear();
            Tile[][] loadedBoard = new Tile[4][4];
            String line;
            int row = 0;
            // While stream is nonempty
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                for (int col = 0; col < 4; col++) {
                    loadedBoard[row][col] = new Tile(Integer.valueOf(values[col].trim()));
                }
                row++;
                // If we have read a 4x4 grid, add loadedBoard to
                // moves so we can undo after reloading
                if (row == 4) {
                    moves.addLast(copyBoard(loadedBoard));
                    loadedBoard = new Tile[4][4];  // Reset loadedBoard
                    row = 0;  // Reset row counter
                }
            }
            board = moves.getFirst();
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
    public static void main(String[] args) {
        TwentyFortyEight t = new TwentyFortyEight();
    }
}
