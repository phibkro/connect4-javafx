package tictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

public class Controller {
  private Game game;

  @FXML
  private FlowPane board;
  @FXML
  private Text output;
  @FXML
  private TextField input;

  public Controller() {
    this.game = new Game();
  }

  @FXML
  private void initialize() {
    this.output.setText(String.format(
        "Player %c make your move!",
        this.game.getCurrentPlayer().toChar()));
    // Create game Buttons programmatically
    Button[] tiles = new Button[BoardState.BOARD_SIZE];
    for (int i = 0; i < tiles.length; i++) {
      Button tile = new Button();
      tiles[i] = tile;

      tile.setText("[   ]");
      tile.setPrefSize(
          board.getPrefWidth() / BoardState.SIDE_SIZE,
          board.getPrefWidth() / BoardState.SIDE_SIZE);

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
    tile.setText(String.format(
        "%c",
        this.game.getCurrentPlayer().toChar()));
    System.out.println("pressed");
  }

  @FXML
  void handleSubmit(ActionEvent event) {
    this.output.setText(input.getText());
  }

  @FXML
  void handleTilePress(ActionEvent event) {
    EventTarget target = event.getTarget();
    this.output.setText(target.toString());
  }
}
