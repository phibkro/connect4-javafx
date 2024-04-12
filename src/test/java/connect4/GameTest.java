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
    @Test
    public void CurrentPlayer_StartsAs_Player() {
        assertEquals(Token.Player, this.game.getCurrentPlayer());
    }

    @Test
    public void CurrentPlayer_ShouldAlternate_OnMove() throws Exception {
        Token currentPlayer = this.game.getCurrentPlayer();

        if (currentPlayer != Token.Player && currentPlayer != Token.Opponent) {
            throw new Exception("Starting `currentPlayer` must be either Player or Opponent.");
        }

        for (int x = 0; x < 7; x++) {
            assertEquals(currentPlayer, this.game.getCurrentPlayer());
            this.game.makeMove(x);

            currentPlayer = (currentPlayer == Token.Player) ? Token.Opponent : Token.Player;
        }
    }

    @Test
    public void getCurrentPlayer_ReturnsNone_IfGameOver() {
        // Create stalemate
        for (int row = 0; row < Game.HEIGHT / 2; row++) {
            this.game.makeMove(Game.WIDTH / 2);
            for (int column = 0; column < Game.WIDTH / 2; column++) {
                this.game.makeMove(column);
                this.game.makeMove(column);
                this.game.makeMove(Game.WIDTH - 1 - column);
                this.game.makeMove(Game.WIDTH - 1 - column);
            }
            this.game.makeMove(Game.WIDTH / 2);
        }

        assertEquals(Token.None, this.game.getCurrentPlayer());
    }

    // Test these together since their behavior is equivalent
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
    public void Move_IsIllegal_IfGameOver() throws Exception {
        this.game.makeMove(0);
        this.game.makeMove(1);
        this.game.makeMove(0);
        this.game.makeMove(1);
        this.game.makeMove(0);
        this.game.makeMove(1);
        this.game.makeMove(0);

        assertFalse(this.game.isLegalMove(0));

        assertThrows(IllegalStateException.class, () -> {
            this.game.makeMove(0);
        });
    }

    @Test
    public void Move_ShouldNotMutateGameState_IfIllegal() throws Exception {
        Game gameCopy = new Game();

        game.makeMove(0);
        gameCopy.makeMove(0);

        game.makeMove(2);
        gameCopy.makeMove(2);

        assertThrows(IllegalArgumentException.class, () -> game.makeMove(-1));

        assertTrue(game.extractMoveHistory().equals(gameCopy.extractMoveHistory()));
        assertTrue(game.getCurrentPlayer().equals(gameCopy.getCurrentPlayer()));
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
    public void Game_IsOver_IfWon() throws Exception {
        this.game.makeMove(0);
        this.game.makeMove(1);
        this.game.makeMove(0);
        this.game.makeMove(1);
        this.game.makeMove(0);
        this.game.makeMove(1);
        this.game.makeMove(0);

        assertTrue(this.game.isGameOver());
    }

    @Test
    public void Game_IsOver_IfBoardIsFull() throws Exception {
        // Create stalemate
        for (int row = 0; row < Game.HEIGHT / 2; row++) {
            this.game.makeMove(Game.WIDTH / 2);
            for (int column = 0; column < Game.WIDTH / 2; column++) {
                this.game.makeMove(column);
                this.game.makeMove(column);
                this.game.makeMove(Game.WIDTH - 1 - column);
                this.game.makeMove(Game.WIDTH - 1 - column);
            }
            this.game.makeMove(Game.WIDTH / 2);
        }

        assertTrue(this.game.isGameOver());
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
    public void getWinner_ReturnsNone_IfStaleMate() throws Exception {
        // Create stalemate
        for (int row = 0; row < Game.HEIGHT / 2; row++) {
            this.game.makeMove(Game.WIDTH / 2);
            for (int column = 0; column < Game.WIDTH / 2; column++) {
                this.game.makeMove(column);
                this.game.makeMove(column);
                this.game.makeMove(Game.WIDTH - 1 - column);
                this.game.makeMove(Game.WIDTH - 1 - column);
            }
            this.game.makeMove(Game.WIDTH / 2);
        }

        assertEquals(Token.None, this.game.getWinner());
    }

    @Test
    public void extractMoveHistory_ReturnsCorrectMoveHistory() {
        String moveSequence = "0102600";
        moveSequence.chars().forEach(c -> {
            this.game.makeMove(c - 48);
        });
        assertEquals(moveSequence, this.game.extractMoveHistory());
    }
}