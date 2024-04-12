package connect4;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class FileHandlerTest {
  Path path;
  File file;

  String testString = new String("hello world");
  String validMoveHistory = "01234";
  String invalidStateMoveHistory = "000000000";
  String outOfBoundsMoveHistory = "013769";

  @TempDir
  Path tempDir;

  @BeforeEach
  public void setUp() {
    try {
      this.path = tempDir.resolve("testfile.txt");
    } catch (InvalidPathException e) {
      System.err.println(
          "error creating temporary test file in " +
              this.getClass().getSimpleName());
    }
    this.file = path.toFile();
  }

  @Test
  public void saveFile_WritesString() {
    FileHandler fileHandler = new FileHandler(this.file);
    assertDoesNotThrow(() -> fileHandler.saveFile(this.testString));
  }

  // Failing test as we do not know how to mock an fxml view
  @Test
  public void LoadsGame_With_ValidMoveHistory() throws Exception {
    FileHandler fileHandler = new FileHandler(this.file);
    assertDoesNotThrow(() -> fileHandler.saveFile(this.validMoveHistory));
    assertDoesNotThrow(() -> fileHandler.loadGame(new Controller()));
  }

  // Test shouldn't pass as mock controller is incorrect
  @Test
  public void loadGame_Throws_WithInvalidMoveHistory() {
    FileHandler fileHandler = new FileHandler(this.file);
    assertDoesNotThrow(() -> fileHandler.saveFile(this.invalidStateMoveHistory));
    Controller controller = new Controller();
    assertThrows(IOException.class, () -> fileHandler.loadGame(controller));
    assertDoesNotThrow(() -> fileHandler.saveFile(this.outOfBoundsMoveHistory));
    assertThrows(IOException.class, () -> fileHandler.loadGame(controller));
  }
}
