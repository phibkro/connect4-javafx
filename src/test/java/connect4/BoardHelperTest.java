package connect4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connect4.models.BoardHelper;
import connect4.models.Tile;

public class BoardHelperTest {
    private Tile[] board;

    @BeforeEach
    public void setUp() {
        Tile[] board = new Tile[6 * 7];
        BoardHelper boardHelper = new BoardHelper(6, 7, board);

        for (int i = 0; i < 6 * 7; i++) {
            System.out.println(i);
            board[i] = Tile.Empty;
        }

        board[boardHelper.translate(0, 0)] = Tile.Player;
        board[boardHelper.translate(0, 1)] = Tile.Player;
        board[boardHelper.translate(0, 2)] = Tile.Player;
        board[boardHelper.translate(0, 3)] = Tile.Player;

        board[boardHelper.translate(2, 0)] = Tile.Opponent;
        board[boardHelper.translate(2, 1)] = Tile.Opponent;
        board[boardHelper.translate(2, 2)] = Tile.Opponent;

        board[boardHelper.translate(5, 0)] = Tile.Player;
        board[boardHelper.translate(5, 1)] = Tile.Player;

        this.board = board;

    }

    @Test
    public void testGetRow() {
        BoardHelper boardHelper = new BoardHelper(6, 7, this.board);

        Tile[] row = boardHelper.getRow(0);
        assertEquals(7, row.length);
    }

    @Test
    public void testGetColumn() {
        BoardHelper boardHelper = new BoardHelper(6, 7, this.board);

        Tile[] column = boardHelper.getColumn(0);
        assertEquals(6, column.length);
    }

    @Test
    public void testIsXSelfSameTilesInARowHorizontalOrDiagonal() {
        BoardHelper boardHelper = new BoardHelper(6, 7, this.board);

        boolean isPlayerFourTimesInARow = boardHelper.isXSelfSameTilesInARowHorizontalOrDiagonal(Tile.Player, 0, 0, 3);
        boolean isPlayerFourTimesInARow2 = boardHelper.isXSelfSameTilesInARowHorizontalOrDiagonal(Tile.Player, 1, 0, 3);
        boolean isPlayerFourTimesInARow3 = boardHelper.isXSelfSameTilesInARowHorizontalOrDiagonal(Tile.Player, 0, 0, 4);

        assertEquals(isPlayerFourTimesInARow, true);
        assertEquals(isPlayerFourTimesInARow2, false);
        assertEquals(isPlayerFourTimesInARow3, false);
    }

    @Test
    public void getTileTest() {
        BoardHelper boardHelper = new BoardHelper(6, 7, this.board);

        Tile tile = boardHelper.getTile(0, 0);
        assertEquals(Tile.Player, tile);
    }

    @Test
    public void translateTest() {
        BoardHelper boardHelper = new BoardHelper(6, 7, this.board);

        assertEquals(0, boardHelper.translate(0, 0));
        assertEquals(1, boardHelper.translate(0, 1));
        assertEquals(7, boardHelper.translate(1, 0));
        assertEquals(8, boardHelper.translate(1, 1));
        assertEquals(14, boardHelper.translate(2, 0));
        assertEquals(15, boardHelper.translate(2, 1));
    }
}