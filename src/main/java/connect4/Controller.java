package connect4;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import connect4.models.Game;
import connect4.models.Tile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class Controller implements Initializable {
  private static final int TILE_SIZE = 100;
  private Game game;

  // Menu elements
  @FXML
  private Button btnStartGame;
  @FXML
  private Button btnLoadGame;

  // Game elements
  @FXML
  private HBox board;

  private Pane[] columns = new Pane[Game.COLUMN_COUNT];

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.game = new Game();

    // Create and bind board
    {
      // Recontextualize attributes with relevant names

      // Bind gridShape
      this.board.getChildren().clear();

      // Create gridShape
      {
        final double COLUMN_WIDTH = TILE_SIZE;
        final double COLUMN_HEIGHT = TILE_SIZE * Game.COLUMN_SIZE;
        for (int i = 0; i < Game.COLUMN_COUNT; i++) {
          StackPane stackPane = new StackPane();
          stackPane.setPrefWidth(COLUMN_WIDTH);
          stackPane.setPrefHeight(COLUMN_HEIGHT);

          VBox vBox = new VBox();
          vBox.setPrefWidth(COLUMN_WIDTH);
          vBox.setPrefHeight(COLUMN_HEIGHT);
          vBox.setRotate(180);

          Button btn = new Button();
          btn.setPrefWidth(COLUMN_WIDTH);
          btn.setPrefHeight(COLUMN_HEIGHT);
          btn.setOpacity(0.50);
          btn.setDefaultButton(true);

          this.board.getChildren().add(stackPane);
          stackPane.getChildren().addAll(vBox, btn);
          final int x = i;
          this.columns[x] = vBox;

          btn.setOnAction(e -> {
            if (this.game.isMoveLegal(x)) {
              final Tile currentPlayer = this.game.getCurrentPlayer();
              // Update game
              this.game.makeMove(x);

              // Update ui
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
              this.columns[x].getChildren().add(circle);

              // Update handlers
              if (!this.game.isMoveLegal(x)) {
                btn.setDisable(true);
                btn.setOnAction(null);
              }
            } else {
              System.err.println("wtf");
            }
          });
        }
      }

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
