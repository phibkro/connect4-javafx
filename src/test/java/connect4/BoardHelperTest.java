package connect4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
    public void testGetRow() {
        BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);

        Token[] row = boardHelper.getRow(0);
        assertEquals(7, row.length);
    }

    @Test
    public void testGetColumn() {
        BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);

        Token[] column = boardHelper.getColumn(0);
        assertEquals(6, column.length);
    }

    @Test
    public void testIsNInARow() {
        BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);

        boolean isFourInARow = boardHelper.isNInARow(0, 0, 4);
        boolean isFourInARow2 = boardHelper.isNInARow(0, 1, 4);
        boolean isFiveInARow = boardHelper.isNInARow(0, 0, 5);

        assertTrue(isFourInARow);
        assertFalse(isFourInARow2);
        assertFalse(isFiveInARow);
    }

    @Test
    public void getTileTest() {
        BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);

        Token tile = boardHelper.getToken(0, 0);
        assertEquals(Token.Player, tile);
    }

    @Test
    public void translateTest() {
        BoardHelper boardHelper = new BoardHelper(Arrays.copyOf(this.board, this.board.length), Game.WIDTH);

        assertEquals(0, boardHelper.translate(0, 0));
        assertEquals(1, boardHelper.translate(0, 1));
        assertEquals(7, boardHelper.translate(1, 0));
        assertEquals(8, boardHelper.translate(1, 1));
        assertEquals(14, boardHelper.translate(2, 0));
        assertEquals(15, boardHelper.translate(2, 1));
    }
}