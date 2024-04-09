package connect4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connect4.models.Game;
import connect4.models.Token;

public class GameTest {
    private Game game;

    @BeforeEach
    public void setUp() {
        this.game = new Game();
    }

    @Test
    public void testGetCurrentPlayer() {
        assertEquals(Token.Player, this.game.getCurrentPlayer());
    }

    @Test
    public void testIsMoveLegal() {
        assertTrue(this.game.isLegalMove(0));
        assertTrue(this.game.isLegalMove(1));
        assertTrue(this.game.isLegalMove(2));
        assertTrue(this.game.isLegalMove(3));
        assertTrue(this.game.isLegalMove(4));
        assertTrue(this.game.isLegalMove(5));
        assertTrue(this.game.isLegalMove(6));

        assertFalse(this.game.isLegalMove(-1));
        assertFalse(this.game.isLegalMove(7));
    }

    @Test
    public void testMakeMove() {
        assertEquals(Token.Player, this.game.getCurrentPlayer());
        this.game.makeMove(0);
        assertEquals(Token.Opponent, this.game.getCurrentPlayer());
        this.game.makeMove(0);
        assertEquals(Token.Player, this.game.getCurrentPlayer());

        assertThrows(IllegalArgumentException.class, () -> {
            this.game.makeMove(-1);
        });

        this.game.makeMove(0);
        this.game.makeMove(0);
        this.game.makeMove(0);
        this.game.makeMove(0);

        assertThrows(IllegalArgumentException.class, () -> {
            this.game.makeMove(0);
        });
    }

    @Test
    public void testIsGameOver() {
        assertFalse(this.game.isGameOver());
    }

    @Test
    public void testGetWinner() {
        assertThrows(IllegalStateException.class, () -> {
            this.game.getWinner();
        });

        this.game.makeMove(0);
        this.game.makeMove(1);
        this.game.makeMove(0);
        this.game.makeMove(1);
        this.game.makeMove(0);
        this.game.makeMove(1);
        this.game.makeMove(0);

        assertEquals(Token.Player, this.game.getWinner());
    }
}