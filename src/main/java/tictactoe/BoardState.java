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

  BoardState(BoardState boardState) {
    this.xs = boardState.xs;
    this.os = boardState.os;
  }

  public boolean[] getOccupiedTiles() {
    boolean[] board = new boolean[BOARD_SIZE];
    for (int i = 0; i < board.length; i++) {
      board[i] = this.xs[i] || this.os[i];
    }
    return board;
  }

  public boolean isWon() {
    if (xs[0] && xs[1] && xs[2]) {
      return true;
    }
    if (os[0] && os[1] && os[2]) {
      return true;
    }
    if (xs[3] && xs[4] && xs[5]) {
      return true;
    }
    if (os[3] && os[4] && os[5]) {
      return true;
    }
    if (xs[6] && xs[7] && xs[8]) {
      return true;
    }
    if (os[6] && os[7] && os[8]) {
      return true;
    }

    if (xs[0] && xs[3] && xs[6]) {
      return true;
    }
    if (os[0] && os[3] && os[6]) {
      return true;
    }
    if (xs[1] && xs[4] && xs[7]) {
      return true;
    }
    if (os[1] && os[4] && os[7]) {
      return true;
    }
    if (xs[2] && xs[5] && xs[8]) {
      return true;
    }
    if (os[2] && os[5] && os[8]) {
      return true;
    }

    if (xs[0] && xs[4] && xs[9]) {
      return true;
    }
    if (os[0] && os[4] && os[9]) {
      return true;
    }
    if (xs[2] && xs[4] && xs[6]) {
      return true;
    }
    if (os[2] && os[4] && os[6]) {
      return true;
    }

    return false;
  }
}
