package com.thedavidnguyen.tictactoe;

enum CellState {
    X, O, EMPTY
}

enum GameState {
    X, O, DRAW, IN_PROGRESS
}

public class TicTacToe {
    private CellState[][] board; // true if player X
    GameState state;

    public TicTacToe() {
        this.board = new CellState[3][3];
        this.state = GameState.IN_PROGRESS;
        initBoard();
    }

    public void reset() {
        initBoard();
        state = GameState.IN_PROGRESS;
    }

    public GameState getState() {
        return state;
    }

    private void initBoard() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                board[i][j] = CellState.EMPTY;
            }
        }
    }

    private boolean isOver() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i][j] != CellState.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private GameState winner() {
        // straight line across
        if((board[0][0] == CellState.X && 
                board[0][1] == CellState.X && 
                board[0][2] == CellState.X )
                || (board[1][0]  == CellState.X && 
                board[1][1]  == CellState.X && 
                board[1][2] == CellState.X )
                || (board[2][0]  == CellState.X && 
                board[2][1]  == CellState.X && 
                board[2][2] == CellState.X )) {
            return GameState.X;
        } else if ((board[0][0]  == CellState.O &&
                board[0][1]  == CellState.O &&
                board[0][2] == CellState.O )
                || (board[1][0]  == CellState.O &&
                board[1][1]  == CellState.O &&
                board[1][2] == CellState.O )
                || (board[2][0]  == CellState.O &&
                board[2][1]  == CellState.O &&
                board[2][2] == CellState.O )) {
            return GameState.O;
        }
        // straight line down
        if((board[0][0] == CellState.X && board[1][0] == CellState.X && board[2][0] == CellState.X)
                || (board[0][1] == CellState.X && board[1][1] == CellState.X && board[2][2] == CellState.X)
                || (board[0][2] == CellState.X && board[1][2] == CellState.X && board[2][2] == CellState.X)) {
            return GameState.X;
        } else if ((board[0][0] == CellState.O && board[1][0] == CellState.O && board[2][0] == CellState.O)
                || (board[0][1] == CellState.O && board[1][1] == CellState.O && board[2][2] == CellState.O)
                || (board[0][2] == CellState.O && board[1][2] == CellState.O && board[2][2] == CellState.O)) {
            return GameState.O;
        }
        // diagonals
        if((board[0][0] == CellState.X&& board[1][1] == CellState.X&& board[2][2]== CellState.X)
                || (board[0][2]== CellState.X && board[1][1]== CellState.X && board[2][0]== CellState.X)) {
            return GameState.X;
        } else if ((board[0][0] == CellState.O && board[1][1] == CellState.O && board[2][2]== CellState.O)
                || (board[0][2]== CellState.O && board[1][1] == CellState.O && board[2][0]== CellState.O)) {
            return GameState.O;
        }

        if(isOver()) {
            return GameState.DRAW;
        } else {
            return GameState.IN_PROGRESS;
        }
    }

    public GameState updateBoard(boolean playerX, int x, int y) {
        this.board[x][y] = playerX ? CellState.X : CellState.O;

        state = winner();

        return state;
    }

}
