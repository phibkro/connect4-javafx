package connect4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connect4.models.Game;
import connect4.models.Tile;

public class GameTest {
    private Game game;

    @BeforeEach
    public void setUp() {
        this.game = new Game();
    }

    @Test
    public void testGetCurrentPlayer() {
        assertEquals(Tile.Player, this.game.getCurrentPlayer());
    }

    @Test
    public void testIsValidMove() {
        assertTrue(this.game.isValidMove(0));
        assertTrue(this.game.isValidMove(1));
        assertTrue(this.game.isValidMove(2));
        assertTrue(this.game.isValidMove(3));
        assertTrue(this.game.isValidMove(4));
        assertTrue(this.game.isValidMove(5));
        assertTrue(this.game.isValidMove(6));
    }
}