package connect4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
    public void isValidMove_Works() {
        for (int i = 0; i < Game.COLUMN_COUNT; i++) {
            assertTrue(this.game.isMoveLegal(i));
        }
        assertFalse(this.game.isMoveLegal(Game.COLUMN_COUNT + 1));
        assertFalse(this.game.isMoveLegal(-1));
    }
}