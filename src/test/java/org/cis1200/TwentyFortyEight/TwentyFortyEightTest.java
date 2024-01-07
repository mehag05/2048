package org.cis1200.TwentyFortyEight;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TwentyFortyEightTest {

    @Test
    public void testInitialization() {
        TwentyFortyEight game = new TwentyFortyEight();
        assertEquals(0, game.getScore());
        assertFalse(game.gameIsOver());
    }

    @Test
    public void testRandomTile() {
        TwentyFortyEight game = new TwentyFortyEight();
        game.randomTile();
        boolean tileAdded = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (game.board[i][j].getValue() == 2) {
                    tileAdded = true;
                    break;
                }
            }
        }
        assertTrue(tileAdded);
    }

    @Test
    public void testHandleUpBasicSlide() {
        TwentyFortyEight game = new TwentyFortyEight();
        game.initBoard();
        game.board[3][0] = new Tile(2);

        game.handleUp();

        assertEquals(2, game.board[0][0].getValue());
        assertEquals(0, game.board[3][0].getValue());
    }

    @Test
    public void testHandleDownBasicSlide() {
        TwentyFortyEight game = new TwentyFortyEight();
        game.initBoard();
        game.board[0][0] = new Tile(2);

        game.handleDown();

        assertEquals(0, game.board[0][0].getValue());
        assertEquals(2, game.board[3][0].getValue());
    }

    @Test
    public void testHandleLeftBasicSlide() {
        TwentyFortyEight game = new TwentyFortyEight();
        game.initBoard();
        game.board[0][1] = new Tile(2);

        game.handleLeft();

        assertEquals(2, game.board[0][0].getValue());
        assertEquals(0, game.board[0][1].getValue());
    }

    @Test
    public void testHandleRightBasicSlide() {
        TwentyFortyEight game = new TwentyFortyEight();
        game.initBoard();
        game.board[0][0] = new Tile(2);

        game.handleRight();

        assertEquals(0, game.board[0][0].getValue());
        assertEquals(2, game.board[0][3].getValue());
    }

    @Test
    public void testHandleUpMergeTiles() {
        TwentyFortyEight game = new TwentyFortyEight();
        game.initBoard();
        game.board[0][0] = new Tile(2);
        game.board[1][0] = new Tile(2);

        game.handleUp();

        // Fix this part in the code
        assertEquals(4, game.board[0][0].getValue());
        assertEquals(0, game.board[1][0].getValue());
        assertEquals(0, game.board[2][0].getValue());
        assertEquals(0, game.board[3][0].getValue());
    }

    @Test
    public void testHandleDownMergeTiles() {
        TwentyFortyEight game = new TwentyFortyEight();
        game.initBoard();
        game.board[2][0] = new Tile(2);
        game.board[3][0] = new Tile(2);

        game.handleDown();

        // Fix this part in the code
        assertEquals(0, game.board[0][0].getValue());
        assertEquals(0, game.board[1][0].getValue());
        assertEquals(0, game.board[2][0].getValue());
        assertEquals(4, game.board[3][0].getValue());
    }

    @Test
    public void testCannotCombine() {
        TwentyFortyEight game = new TwentyFortyEight();
        game.board = new Tile[][]{
                {new Tile(2), new Tile(4), new Tile(2), new Tile(4)},
                {new Tile(4), new Tile(2), new Tile(4), new Tile(2)},
                {new Tile(2), new Tile(4), new Tile(2), new Tile(4)},
                {new Tile(4), new Tile(2), new Tile(4), new Tile(2)}};

        assertTrue(game.cannotCombine());
        game.board[0][0].setTile(4);
        assertFalse(game.cannotCombine());

    }

    @Test
    public void testGameIsOver() {
        TwentyFortyEight game = new TwentyFortyEight();
        game.board = new Tile[][]{
                {new Tile(2), new Tile(4), new Tile(2), new Tile(4)},
                {new Tile(4), new Tile(2), new Tile(4), new Tile(2)},
                {new Tile(2), new Tile(4), new Tile(2), new Tile(4)},
                {new Tile(4), new Tile(2), new Tile(4), new Tile(2)}};

        assertTrue(game.gameIsOver());
    }
}
