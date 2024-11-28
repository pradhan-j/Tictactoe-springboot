package com.example.tic_tac_toe.model;

import java.util.Arrays;

public class Game {
    private char[][] board;
    private char currentPlayer;
    private String status;

    public Game() {
        board = new char[3][3];
        for (char[] row : board) {
            Arrays.fill(row, ' ');
        }
        currentPlayer = 'X';  // X starts the game
        status = "Game in Progress";
    }

    public Game(char[][] board) {
        this.board = board;
    }

    public char[][] getBoard() {
        return board;
    }

    public String getStatus() {
        return status;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean makeMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') {
            return false;  // Invalid move
        }

        board[row][col] = currentPlayer;
        if (checkWinner()) {
            status = "Player " + currentPlayer + " wins!";
        } else if (isBoardFull()) {
            status = "It's a tie!";
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';  // Switch player
        }
        return true;
    }

    private boolean checkWinner() {
        // Check rows, columns, and diagonals for a winner
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
            (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }
}
