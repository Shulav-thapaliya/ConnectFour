package com.example.connectfour;

public class GameBoard {

    private int[][] board;
    private int rows;
    private int cols;

    public GameBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new int[rows][cols];
    }

    public boolean placePiece(int col, int playerId) {
        for (int i = rows - 1; i >= 0; i--) {
            if (board[i][col] == 0) {
                board[i][col] = playerId;
                return true;
            }
        }
        return false;
    }

    public boolean checkForWin(int playerId) {
        // Horizontal check
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols - 3; col++) {
                if (this.board[row][col] == playerId && this.board[row][col + 1] == playerId && this.board[row][col + 2] == playerId && this.board[row][col + 3] == playerId) {
                    return true;
                }
            }
        }
        // Vertical check
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows - 3; row++) {
                if (this.board[row][col] == playerId && this.board[row + 1][col] == playerId && this.board[row + 2][col] == playerId && this.board[row + 3][col] == playerId) {
                    return true;
                }
            }
        }
        // Ascending Diagonal check
        for (int row = 3; row < rows; row++) {
            for (int col = 0; col < cols - 3; col++) {
                if (this.board[row][col] == playerId && this.board[row - 1][col + 1] == playerId && this.board[row - 2][col + 2] == playerId && this.board[row - 3][col + 3] == playerId) {
                    return true;
                }
            }
        }
        // Descending Diagonal check
        for (int row = 0; row < rows - 3; row++) {
            for (int col = 0; col < cols - 3; col++) {
                if (this.board[row][col] == playerId && this.board[row + 1][col + 1] == playerId && this.board[row + 2][col + 2] == playerId && this.board[row + 3][col + 3] == playerId) {
                    return true;
                }
            }
        }
        return false;
    }

    public int[][] getBoard() {
        return board;
    }
}
