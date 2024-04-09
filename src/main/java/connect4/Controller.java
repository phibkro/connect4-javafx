package connect4;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import connect4.models.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

public class Controller implements Initializable {
  private static final int TILE_SIZE = 100;
  private Game game;
  @FXML
  private Pane board;
  @FXML
  private Button btnStartGame;
  @FXML
  private Button btnLoadGame;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.game = new Game();

    // Create game view
    {
      // Create game Buttons programmatically
      Button[] tiles = new Button[this.game.getBoardSize()];
      for (int i = 0; i < tiles.length; i++) {
        Button tile = new Button();
        tiles[i] = tile;

        tile.setText("[   ]");
        tile.setPrefSize(TILE_SIZE, TILE_SIZE);
        tile.setId(String.format("tile%d", i));

        tile.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            System.out.println("hello");
          }
        });
      }
      this.board.getChildren().setAll(tiles);
    }
  }

  @FXML
  private void openFileDialog(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    File file = fileChooser.showOpenDialog(this.board.getScene().getWindow());

    // TODO
    // this.game = new Game(file);
    // or
    // this.game = new Game();
    // this.game.loadGame(file);
  }

  @FXML
  private void startNewGame(ActionEvent event) {
    this.game = new Game();
  }
}
