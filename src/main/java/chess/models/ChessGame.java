package chess.models;

public class ChessGame {
  private Bitboard currentBoard;

  public ChessGame() {
    this.currentBoard = new Bitboard();
  }

  public Bitboard getCurrentBoard() {
    return currentBoard;
  }
}
