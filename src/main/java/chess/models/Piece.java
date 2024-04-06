package chess.models;

public enum Piece {
  WhitePawn('P'),
  WhiteKnight('N'),
  WhiteBishop('B'),
  WhiteRook('R'),
  WhiteQueen('Q'),
  WhiteKing('K'),
  BlackPawn('p'),
  BlackKnight('n'),
  BlackBishop('b'),
  BlackRook('r'),
  BlackQueen('q'),
  BlackKing('k');

  private Character letter;

  Piece(Character letter) {
    this.letter = letter;
  }
}
