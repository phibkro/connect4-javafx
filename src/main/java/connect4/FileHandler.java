package connect4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import connect4.models.Game;

public class FileHandler {
  private File file;

  FileHandler(File file) {
    this.file = file;
  }

  public void saveFile(String text) throws IOException, FileNotFoundException, SecurityException {
    try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
      fileOutputStream.write(text.getBytes());
    }
  }

  public void loadGame(Controller controller) throws IOException {
    // Validate file
    try (FileReader reader = new FileReader(this.file)) {
      Game testGame = new Game();
      int move;
      // (reader.read() - 48) to convert from ASCII code points to int
      // (c > -1) because reader.read() returns -1 if there is no more to read
      while ((move = reader.read() - 48) > -1) {
        if (testGame.isLegalMove(move)) {
          testGame.makeMove(move);
        } else {
          throw new IOException("Error: Cannot load file. Chosen file may illegal moves.");
        }
      }
    }

    // Load game by making all the moves
    try (FileReader reader = new FileReader(this.file)) {
      // File validated, load game
      controller.startNewGame();

      int move;
      while ((move = reader.read() - 48) > -1) {
        controller.makeMove(move);
      }
    }
  }
}
