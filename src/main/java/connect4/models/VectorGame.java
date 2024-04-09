package connect4.models;

import java.io.File;

public interface VectorGame {
  void makeMove(int column);

  boolean isMoveLegal(int column);

  boolean[] getLegalMoves();

  Tile isWon();

  void loadGame(File file);

  File saveGame();
}
