package tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Game {
  List<BoardState> history;
  Player currentPlayer;

  public static void main(String[] args) {
    System.out.println("Would you like to play a game? y/n:");
    String input = System.console().readLine();
    System.out.println(input);
  }

  Game() {
    this.history = new ArrayList<>();
    this.history.add(new BoardState());
    this.currentPlayer = Player.X;
  }

  public boolean isMoveValid(int tileIndex) {
    if (tileIndex < 1 || 9 < tileIndex) {
      return false;
    }
    boolean[] occupiedTiles = this.getCurrentBoardState().getOccupiedTiles();
    if (occupiedTiles[tileIndex]) {
      return false;
    }
    return true;
  }

  public void makeMove(int tileIndex) throws IllegalArgumentException {
    if (!this.isMoveValid(tileIndex)) {
      throw new IllegalArgumentException();
    }
    BoardState previousBoardState = this.getCurrentBoardState();
    history.add(this.getCurrentBoardState());
  }

  public BoardState getCurrentBoardState() {
    return new BoardState(history.getLast());
  }

  public Player getCurrentPlayer() {
    return this.currentPlayer;
  }
}
