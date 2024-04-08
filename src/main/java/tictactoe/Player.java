package tictactoe;

public enum Player {
  X('X'),
  O('O');

  private final char label;

  Player(char label) {
    this.label = label;
  }

  public char getValue() {
    return this.label;
  }

  public char toChar() {
    return this.label;
  }
}
