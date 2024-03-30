package tictactoe;

public class BoardState {
  static final int SIDE_SIZE = 3;
  static final int BOARD_SIZE = SIDE_SIZE * SIDE_SIZE;
  boolean[] xs = new boolean[BOARD_SIZE];
  boolean[] os = new boolean[BOARD_SIZE];

  BoardState() {
    this.xs = new boolean[] {
        false, false, false,
        false, false, false,
        false, false, false,
    };
    this.os = new boolean[] {
        false, false, false,
        false, false, false,
        false, false, false,
    };
  }

  BoardState(boolean[] xs, boolean[] os) throws IllegalArgumentException {
    if (xs.length != BOARD_SIZE || os.length != BOARD_SIZE) {
      throw new IllegalArgumentException();
    }
    for (int i = 0; i < os.length; i++) {
      if (xs[i] && os[i]) {
        throw new IllegalArgumentException();
      }
    }
    this.xs = xs;
    this.os = os;
  }
}
