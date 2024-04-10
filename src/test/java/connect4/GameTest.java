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

    // Test game logic correct state handling
    @Test
    public void currentPlayerAlternates_onMove() throws Exception {
        Token evenPlayer = this.game.getCurrentPlayer();
        Token oddPlayer;
        switch (evenPlayer) {
            case Player:
                oddPlayer = Token.Opponent;
                break;
            case Opponent:
                oddPlayer = Token.Player;
                break;
            case None:
                throw new Exception(String.format("Starting player cannot be %s", Token.None.toString()));
            default:
                throw new Exception("Starting player cannot be Null");
        }

        int x = 0;
        while (this.game.isLegalMove(x)) {
            if (x % 2 == 0) {
                assertEquals(this.game.getCurrentPlayer(), evenPlayer);
            } else {
                assertEquals(this.game.getCurrentPlayer(), oddPlayer);
            }
            this.game.makeMove(x);
            if (x == 7) {
                x = 0;
            } else {
                x++;
            }
        }
    }

    // Unit test Game methods
    // .getCurrentPlayer()
    @Test
    public void currentPlayer_StartsAs_Player() {
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

    @Test
    public void testLoadMoveHistory() {
        this.game.makeMove(0);

        String moveHistory = this.game.extractMoveHistory();

        this.game = new Game();
        this.game.loadMoveHistory(moveHistory);

        assertEquals(Token.Opponent, this.game.getCurrentPlayer());
    }

    @Test
    public void testExtractMoveHistory() {
        this.game.makeMove(0);
        this.game.makeMove(1);
        this.game.makeMove(0);
        this.game.makeMove(2);
        this.game.makeMove(6);
        this.game.makeMove(0);
        this.game.makeMove(0);

        String moveHistory = this.game.extractMoveHistory();

        assertEquals("0102600", moveHistory);
    }
}