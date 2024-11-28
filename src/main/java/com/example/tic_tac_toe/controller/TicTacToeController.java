package com.example.tic_tac_toe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class TicTacToeController {

    private char[][] board = new char[3][3];
    private char currentPlayer = 'X';
    private boolean gameEnded = false;

    public TicTacToeController() {
        resetBoard();
    }

    @GetMapping("/board")
    public String displayBoard() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current Board:\n");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j] == '\0' ? "-" : board[i][j]);
                if (j < 2) sb.append(" | ");
            }
            sb.append("\n");
            if (i < 2) sb.append("---------\n");
        }
        return sb.toString() + (gameEnded ? "\nGame Over!" : "\nPlayer " + currentPlayer + "'s turn.");
    }

    @PostMapping("/move")
    public String makeMove(@RequestParam int row, @RequestParam int col) {
        if (gameEnded) {
            return "Game is over! Reset to play again.";
        }

        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != '\0') {
            return "Invalid move. Try again.";
        }

        board[row][col] = currentPlayer;
        if (checkWin()) {
            gameEnded = true;
            return displayBoard() + "\nPlayer " + currentPlayer + " wins!";
        }

        if (isBoardFull()) {
            gameEnded = true;
            return displayBoard() + "\nIt's a draw!";
        }

        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        return displayBoard();
    }

    @PostMapping("/reset")
    public String resetGame() {
        resetBoard();
        gameEnded = false;
        currentPlayer = 'X';
        return "Game reset!\n" + displayBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '\0';
            }
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) return true;
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) return true;
        }
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
               (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\0') return false;
            }
        }
        return true;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }
}
