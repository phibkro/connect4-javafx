package chess;

import chess.models.Bitboard;
import chess.models.ChessGame;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

public class Controller {
  private ChessGame game;

  @FXML
  FlowPane board;

  public Controller() {
    this.game = new ChessGame();
  }

  @FXML
  private void initialize() {
    Button[] squares = new Button[Bitboard.BOARD_SIZE];
    for (int i = 0; i < squares.length; i++) {
      Button square = new Button();
      squares[i] = square;
    }
  }
}
