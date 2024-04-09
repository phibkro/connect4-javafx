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
        assertTrue(this.game.isMoveLegal(0));
        assertTrue(this.game.isMoveLegal(1));
        assertTrue(this.game.isMoveLegal(2));
        assertTrue(this.game.isMoveLegal(3));
        assertTrue(this.game.isMoveLegal(4));
        assertTrue(this.game.isMoveLegal(5));
        assertTrue(this.game.isMoveLegal(6));
    }
}