package chess.models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Bitboard {
  public static final int BOARD_LENGTH = 8;
  public static final int BOARD_SIZE = BOARD_LENGTH * BOARD_LENGTH;
  static final Piece[][] STANDARD_BOARD = {
      { Piece.BlackRook, Piece.BlackKnight, Piece.BlackBishop, Piece.BlackQueen, Piece.BlackKing, Piece.BlackBishop,
          Piece.BlackKnight, Piece.BlackRook },
      { Piece.BlackPawn, Piece.BlackPawn, Piece.BlackPawn, Piece.BlackPawn, Piece.BlackPawn, Piece.BlackPawn,
          Piece.BlackPawn, Piece.BlackPawn },
      { null, null, null, null, null, null, null, null },
      { null, null, null, null, null, null, null, null },
      { null, null, null, null, null, null, null, null },
      { null, null, null, null, null, null, null, null },
      { Piece.WhitePawn, Piece.WhitePawn, Piece.WhitePawn, Piece.WhitePawn, Piece.WhitePawn, Piece.WhitePawn,
          Piece.WhitePawn, Piece.WhitePawn },
      { Piece.WhiteRook, Piece.WhiteKnight, Piece.WhiteBishop, Piece.WhiteQueen, Piece.WhiteKing, Piece.WhiteBishop,
          Piece.WhiteKnight, Piece.WhiteRook },
  };
  private Map<Piece, Long> pieceBitboards = new HashMap<>();
  /*
   * long whitePawns = 0L;
   * long whiteKnights = 0L;
   * long whiteBishops = 0L;
   * long whiteRooks = 0L;
   * long whiteQueens = 0L;
   * long whiteKing = 0L;
   * 
   * long blackPawns = 0L;
   * long blackKnights = 0L;
   * long blackBishops = 0L;
   * long blackRooks = 0L;
   * long blackQueens = 0L;
   * long blackKing = 0L;
   */

  public Bitboard() {
    for (Piece piece : Piece.values()) {
      this.pieceBitboards.put(piece, 0L);
    }
    loadBitboardFromArray(STANDARD_BOARD);
  }

  public Bitboard(Piece[][] chessBoard) {
    for (Piece piece : Piece.values()) {
      this.pieceBitboards.put(piece, 0L);
    }
    loadBitboardFromArray(chessBoard);
  }

  public void loadBitboardFromArray(Piece[][] chessBoard) throws IllegalArgumentException {
    if (chessBoard.length != BOARD_LENGTH) {
      throw new IllegalArgumentException(String.format("Chessboard must have %d rows", BOARD_LENGTH));
    }
    for (Piece[] row : chessBoard) {
      if (row.length != BOARD_LENGTH) {
        throw new IllegalArgumentException(String.format("Rows must have %d characters", BOARD_LENGTH));
      }
      /*
       * for (Piece letter : row) {
       * if (!Arrays.asList(Piece.values()).contains(letter)) {
       * throw new
       * IllegalArgumentException("Characters must be valid piece characters");
       * }
       * }
       */
    }
    String binary;
    for (int i = 0; i < BOARD_SIZE; i++) {
      binary = "0000000000000000000000000000000000000000000000000000000000000000";
      binary = binary.substring(i + 1) + "1" + binary.substring(0, i);
      Piece piece = chessBoard[i / BOARD_LENGTH][i % BOARD_LENGTH];
      if (Arrays.asList(Piece.values()).contains(piece)) {
        this.pieceBitboards.put(
            Piece.valueOf(piece.toString()),
            this.pieceBitboards.get(piece) + convertBinaryStringToBitboard(binary));
      }
    }
  }

  @Override
  public String toString() {
    String chessBoard[][] = new String[BOARD_LENGTH][BOARD_LENGTH];
    for (int i = 0; i < BOARD_SIZE; i++) {
      chessBoard[i / BOARD_LENGTH][i % BOARD_LENGTH] = " ";
    }
    for (int i = 0; i < BOARD_SIZE; i++) {
      if (((this.pieceBitboards.get(Piece.WhitePawn) >> i) & 1) == 1) {
        chessBoard[i / BOARD_LENGTH][i % BOARD_LENGTH] = "P";
      }
      if (((this.pieceBitboards.get(Piece.WhiteKnight) >> i) & 1) == 1) {
        chessBoard[i / BOARD_LENGTH][i % BOARD_LENGTH] = "N";
      }
      if (((this.pieceBitboards.get(Piece.WhiteBishop) >> i) & 1) == 1) {
        chessBoard[i / BOARD_LENGTH][i % BOARD_LENGTH] = "B";
      }
      if (((this.pieceBitboards.get(Piece.WhiteRook) >> i) & 1) == 1) {
        chessBoard[i / BOARD_LENGTH][i % BOARD_LENGTH] = "R";
      }
      if (((this.pieceBitboards.get(Piece.WhiteQueen) >> i) & 1) == 1) {
        chessBoard[i / BOARD_LENGTH][i % BOARD_LENGTH] = "Q";
      }
      if (((this.pieceBitboards.get(Piece.WhiteKing) >> i) & 1) == 1) {
        chessBoard[i / BOARD_LENGTH][i % BOARD_LENGTH] = "K";
      }
      if (((this.pieceBitboards.get(Piece.BlackPawn) >> i) & 1) == 1) {
        chessBoard[i / BOARD_LENGTH][i % BOARD_LENGTH] = "p";
      }
      if (((this.pieceBitboards.get(Piece.BlackKnight) >> i) & 1) == 1) {
        chessBoard[i / BOARD_LENGTH][i % BOARD_LENGTH] = "n";
      }
      if (((this.pieceBitboards.get(Piece.BlackBishop) >> i) & 1) == 1) {
        chessBoard[i / BOARD_LENGTH][i % BOARD_LENGTH] = "b";
      }
      if (((this.pieceBitboards.get(Piece.BlackRook) >> i) & 1) == 1) {
        chessBoard[i / BOARD_LENGTH][i % BOARD_LENGTH] = "r";
      }
      if (((this.pieceBitboards.get(Piece.BlackQueen) >> i) & 1) == 1) {
        chessBoard[i / BOARD_LENGTH][i % BOARD_LENGTH] = "q";
      }
      if (((this.pieceBitboards.get(Piece.BlackKing) >> i) & 1) == 1) {
        chessBoard[i / BOARD_LENGTH][i % BOARD_LENGTH] = "k";
      }
    }
    StringBuilder output = new StringBuilder();
    for (String[] row : chessBoard) {
      output.append(Arrays.toString(row));
      output.append("\n");
    }
    return output.toString();
  }

  private long convertBinaryStringToBitboard(String binary) {
    if (binary.charAt(0) == '0') {
      return Long.parseLong(binary, 2);
    } else {
      // if binary is negative
      return Long.parseLong("1" + binary.substring(2), 2) * 2;
    }
  }

  public Map<Piece, Long> getPieceBitboards() {
    return new HashMap<>(this.pieceBitboards);
  }
}
