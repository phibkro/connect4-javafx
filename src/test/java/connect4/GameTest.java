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

    // Example for test naming: Functionality_ExpectedBehavior_StateUnderTest
    // or for unit testing: MethodName_ExpectedBehavior_StateUnderTest
    // Test game logic correct state handling
    @Test
    public void CurrentPlayer_StartsAs_Player() {
        assertEquals(Token.Player, this.game.getCurrentPlayer());
    }

    @Test
    public void CurrentPlayer_ShouldAlternate_OnMove() throws Exception {
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
    public void getCurrentPlayer_Throws_IfGameOver() {
        int x = 0;
        while (!this.game.isGameOver()) {
            if (this.game.isLegalMove(x)) {
                this.game.makeMove(x);
            }
            if (x == 7) {
                x = 0;
            } else {
                x++;
            }
        }
        assertThrows(IllegalStateException.class, () -> this.game.getCurrentPlayer());
    }

    // Test Game.isLegalMove & Game.makeMove
    // Test these together since their behavior is equivalent
    // When makeMove throws isLegalMove returns false
    @Test
    public void Move_IsIllegal_IfInputIsNegative() {
        assertFalse(this.game.isLegalMove(-1));
        assertThrows(IllegalArgumentException.class, () -> this.game.makeMove(-1));
    }

    @Test
    public void Move_IsIllegal_IfInputLargerThanWidth() {
        assertFalse(this.game.isLegalMove(Game.WIDTH + 1));
        assertThrows(IllegalArgumentException.class, () -> this.game.makeMove(Game.WIDTH + 1));
    }

    @Test
    public void Move_IsLegal_IfInputLessThanWidth() {
        int x = 0;
        while (x < Game.WIDTH) {
            assertTrue(this.game.isLegalMove(x));
            x++;
        }
    }

    @Test
    public void Move_IsIllegal_IfColumnFull() {
        for (int i = 0; i < Game.WIDTH; i++) {
            for (int _j = 0; _j < Game.HEIGHT; _j++) {
                this.game.makeMove(i);
            }
            final int x = i;
            assertFalse(this.game.isLegalMove(x));
            assertThrows(IllegalStateException.class, () -> {
                this.game.makeMove(x);
            });
        }
    }

    @Test
    public void Move_IsIllegal_IfGameOver() {
        // TODO
    }

    @Test
    public void Move_ShouldNotMutateGameState_IfIllegal() {
        // TODO
    }

    // Test game over states
    @Test
    public void Game_IsNotOver_IfPossibleToMove() {
        int x = 0;
        while (this.game.isLegalMove(x)) {
            assertFalse(this.game.isGameOver());
            x++;
        }
    }

    @Test
    public void Game_IsOver_IfWon() {
        // TODO
    }

    @Test
    public void Game_IsOver_IfBoardIsFull() {
    }

    @Test
    public void getWinner_Throws_IfGameNotOver() {
        assertThrows(IllegalStateException.class, () -> {
            this.game.getWinner();
        });
    }

    @Test
    public void getWinner_ReturnsWinner_IfGameOver() {
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
    public void getWinner_ReturnsNone_IfStaleMate() {
        // TODO
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