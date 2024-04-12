package connect4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import connect4.models.Game;

public class FileHandler {
  private File file;

  FileHandler(File file) {
    this.file = file;
  }

  public void saveGame(Game game) throws IOException, FileNotFoundException, SecurityException {
    try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
      String moveHistory = game.extractMoveHistory();
      fileOutputStream.write(moveHistory.getBytes());
    }
  }
}
