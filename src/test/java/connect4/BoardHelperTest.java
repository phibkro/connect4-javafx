package connect4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connect4.models.BoardHelper;
import connect4.models.Game;
import connect4.models.Token;

public class BoardHelperTest {
    private Token[] board = new Token[Game.SIZE];

    @BeforeEach
    public void setUp() {
        Arrays.fill(this.board, Token.None);

        BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);

        board[boardHelper.translate(0, 0)] = Token.Player;
        board[boardHelper.translate(0, 1)] = Token.Player;
        board[boardHelper.translate(0, 2)] = Token.Player;
        board[boardHelper.translate(0, 3)] = Token.Player;

        board[boardHelper.translate(2, 0)] = Token.Opponent;
        board[boardHelper.translate(2, 1)] = Token.Opponent;
        board[boardHelper.translate(2, 2)] = Token.Opponent;

        board[boardHelper.translate(5, 0)] = Token.Player;
        board[boardHelper.translate(5, 1)] = Token.Player;
    }

    @Test
    public void getRow_LengthSameAs_BoardWidth() {
        BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);
        Token[] row = boardHelper.getRow(0);
        assertEquals(7, row.length);
    }

    @Test
    public void getColumn_LengthSameAs_BoardColumn() {
        BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);
        Token[] column = boardHelper.getColumn(0);
        assertEquals(6, column.length);
    }

    @Test
    public void isNInARow_DetectsFourInARow_WhenSequenceExists() {
        BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);
        assertTrue(boardHelper.isNInARow(0, 0, 4));
    }

    @Test
    public void isNInARow_DoesNotDetectFourInARow_WhenSequenceDoesNotExists() {
        BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);
        assertFalse(boardHelper.isNInARow(0, 1, 4));
    }

    @Test
    public void isNInARow_DoesNotDetectMoreThan_FourInARowWhenCheckingForFour() {
        BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);
        assertFalse(boardHelper.isNInARow(0, 0, 5));
    }

    @Test
    public void isNInARow_ThrowsException_WhenStartingIndexOutOfBounds() {
        BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);
        assertThrows(IllegalArgumentException.class, () -> boardHelper.isNInARow(-1, 0, 4));
    }

    @Test
    public void isNInARow_ThrowsException_WhenNLessThanOne() {
        BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);
        assertThrows(IllegalArgumentException.class, () -> boardHelper.isNInARow(0, 0, 0));
    }

    @Test
    public void isNInARow_ReturnsTrue_WhenSequenceExists() {
        BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);
        assertTrue(boardHelper.isNInARow(4, 0, 4));
    }

    @Test
    public void isNInARow_ReturnsFalse_WhenSequenceDoesNotExists() {
        BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);
        assertFalse(boardHelper.isNInARow(5, 0, 4));
    }

    @Test
    public void getToken_ReturnsCorrectToken_AtGivenPosition() {
        BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);
        assertEquals(Token.Player, boardHelper.getToken(0, 0));
    }

    @Test
    public void translate_CoordinatesToIndex_AtTopLeft() {
        BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);
        assertEquals(0, boardHelper.translate(0, 0));
    }

    @Test
    public void translate_CoordinatesToIndex_AtTopRight() {
        BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);
        assertEquals(1, boardHelper.translate(0, 1));
    }

    @Test
    public void translate_CoordinatesToIndex_SecondRowFirstColumn() {
        BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);
        assertEquals(7, boardHelper.translate(1, 0));
    }

    @Test
    public void translate_CoordinatesToIndex_SecondRowSecondColumn() {
        BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);
        assertEquals(8, boardHelper.translate(1, 1));
    }

}