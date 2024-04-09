package connect4.models;

import java.io.File;

public interface VectorGame {
  void makeMove(int column);

  boolean isValidMove(int column);

  boolean[] getValidMoves();

  Tile isWon();

  void loadGame(File file);

  File saveGame();
}
