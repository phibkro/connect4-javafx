package connect4.models;

import java.io.File;

public interface VectorGame {
  void makeMove(int column);

  boolean isMoveLegal(int column);

  boolean[] getLegalMoves();

  boolean isGameOver();

  Tile getWinner();

  void loadGame(File file);

  File saveGame();
}
