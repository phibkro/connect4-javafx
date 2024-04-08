package connect4.mvc;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import connect4.models.*;

public class Controller {
  private Game game;

  @FXML
  private FlowPane board;

  public Controller() {
    this.game = new Game();
  }

  public Controller(Game game) {
    this.game = game;
  }

  @FXML
  private void initialize() {
    // Create game Buttons programmatically
    Button[] tiles = new Button[this.game.getBoardSize()];
    for (int i = 0; i < tiles.length; i++) {
      Button tile = new Button();
      tiles[i] = tile;

      tile.setText("[   ]");
      tile.setPrefSize(
          this.game.getTileSize(),
          this.game.getTileSize());

      tile.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          fireClick(tile);
          tile.setOnAction(null);
        }
      });
    }
    this.board.getChildren().setAll(tiles);
  }

  void fireClick(Button tile) {
    System.out.println("pressed");
  }

}
