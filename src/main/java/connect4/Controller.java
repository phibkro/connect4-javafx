package connect4;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import connect4.models.Game;
import connect4.models.Token;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class Controller implements Initializable {
  private static final int TILE_SIZE = 100;
  private Game game;

  // Menu elements
  @FXML
  private Text output;
  @FXML
  private Button btnStartGame;
  @FXML
  private Button btnLoadGame;

  // Game elements
  @FXML
  private HBox board;

  private VBox[] columns = new VBox[Game.WIDTH];
  private Button[] gameBtns = new Button[Game.WIDTH];

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.startNewGame(null);
  }

  private void resetBoard() {
    this.board.getChildren().clear();
    this.output.setText(String.format("%s, it is your turn!", this.game.getCurrentPlayer()));

    // Recontextualize attributes with relevant names
    final double COLUMN_WIDTH = TILE_SIZE;
    final double COLUMN_HEIGHT = TILE_SIZE * Game.HEIGHT;

    // Create board
    for (int i = 0; i < Game.WIDTH; i++) {
      StackPane stackPane = new StackPane();
      stackPane.setPrefWidth(COLUMN_WIDTH);
      stackPane.setPrefHeight(COLUMN_HEIGHT);

      // Configure visual vBox
      VBox vBox = new VBox();
      vBox.setPrefWidth(COLUMN_WIDTH);
      vBox.setPrefHeight(COLUMN_HEIGHT);
      // Flip it
      vBox.setRotate(180);

      // Configure interactive button element
      Button btn = new Button();
      btn.setPrefWidth(COLUMN_WIDTH);
      btn.setPrefHeight(COLUMN_HEIGHT);
      btn.setOpacity(0.50);

      // Add elements to app
      this.board.getChildren().add(stackPane);
      stackPane.getChildren().addAll(vBox, btn);

      // Store for later use
      this.columns[i] = vBox;
      this.gameBtns[i] = btn;

      // Copy index to use in event handler
      final int x = i;

      btn.setOnAction(e -> {
        this.makeMove(x);
      });
    }
  }

  private void makeMove(int column) {
    if (this.game.isLegalMove(column)) {
      // Copy current player before we make move
      final Token currentPlayer = this.game.getCurrentPlayer();

      // Only make move if move is legal
      this.game.makeMove(column);

      // Update ui
      this.output.setText(String.format("%s, it is your turn!", this.game.getCurrentPlayer()));
      Circle circle = new Circle(TILE_SIZE / 2);
      switch (currentPlayer) {
        case Player:
          circle.setFill(Color.PURPLE);
          break;
        case Opponent:
          circle.setFill(Color.ORANGE);
          break;
        default:
          System.err.println("This shouldn't happen");
          break;
      }
      // Place disk in app
      this.columns[column].getChildren().add(circle);

      // Disable btn and remove event handler if the next move is illegal
      if (!this.game.isLegalMove(column)) {
        this.gameBtns[column].setDisable(true);
        this.gameBtns[column].setOnAction(null);
      }

      if (this.game.isGameOver()) {
        this.handleGameOver(this.game.getWinner());
      }
    } else {
      // This shouldn't happen as the event handler is removed
      System.err.println("wtf");
    }
  }

  private void handleGameOver(Token winner) {
    // Disable all buttons
    for (Button btn : this.gameBtns) {
      btn.setDisable(true);
      btn.setOnAction(null);
    }
    // Display the winner
    switch (winner) {
      case None:
        this.output.setText("It's a stalemate!");
        break;
      case Player:
        this.output.setText("Player, you won!");
        break;
      case Opponent:
        this.output.setText("Opponent, you won!");
        break;
      default:
        this.output.setText("what");
        break;
    }
  }

  @FXML
  private void loadGame(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    File file = fileChooser.showOpenDialog(this.board.getScene().getWindow());
    if (file == null) {
      // User cancelled
      return;
    }
    Game newGame = new Game();
    try (FileReader reader = new FileReader(file)) {
      this.output.setText(String.format("Loading... %s", file.getPath()));

      // Validate file
      int c;
      // (reader.read() - 48) to convert from ASCII code points to int
      // (c > -1) because reader.read() returns -1 if there is no more to read
      while ((c = reader.read() - 48) > -1) {
        if (newGame.isLegalMove(c)) {
          newGame.makeMove(c);
        } else {
          throw new IOException("Error: Cannot load file. Chosen file may illegal moves.");
        }
      }
      try (FileReader newReader = new FileReader(file)) {
        // File validated, load game
        this.game = new Game();
        this.resetBoard();

        int d;
        while ((d = newReader.read() - 48) > -1) {
          this.makeMove(d);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      this.output.setText(e.getMessage());
    }

  }

  @FXML
  private void saveGame(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    File file = fileChooser.showSaveDialog(this.board.getScene().getWindow());
    if (file == null) {
      // User cancelled
      return;
    }
    FileHandler fileHandler = new FileHandler(file);
    try {
      fileHandler.saveGame(this.game);
      this.output.setText(String.format("Saved to %s", file.getPath()));
    } catch (Exception e) {
      e.printStackTrace();
      this.output.setText("Error: Could not save game.");
    }
  }

  @FXML
  private void startNewGame(ActionEvent event) {
    this.game = new Game();
    this.resetBoard();
  }
}
