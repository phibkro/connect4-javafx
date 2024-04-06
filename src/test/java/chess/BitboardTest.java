package chess;

import org.junit.jupiter.api.Test;

import chess.models.Bitboard;
import chess.models.Piece;

public class BitboardTest {
  @Test
  public void testConstructor() {
    Bitboard board = new Bitboard();

    System.out.println(board.toString());
    for (Piece piece : Piece.values()) {
      System.out.println(piece.name());
      System.out.println(Long.toBinaryString(board.getPieceBitboards().get(piece)));
    }
  }
}
