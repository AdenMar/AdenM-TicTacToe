package edu.quinnipiac.ser210.tictactoe;

/**
 * TicTacToe class implements the interface
 * @author Aden Mariyappa
 * @date 1/25/19
 */

public class TicTacToe implements ITicTacToe {

    // The game board and the game status
    private static final int ROWS = 3, COLS = 3; // number of rows and columns
    private int[][] board = new int[ROWS][COLS]; // game board in 2D array
    private static int PLAY_COUNT;

    /**
     * clear board and set current player
     */
    public TicTacToe() {
        clearBoard();
        PLAY_COUNT = 0;
    }

    @Override
    public void clearBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    @Override
    public void setMove(int player, int location) {
        board[location / 3][location % 3] = player;

        PLAY_COUNT++;
    }

    @Override
    public int getComputerMove() {
        int move = (int) (Math.random() * 9);

        while (board[move / 3][move % 3] != EMPTY) {
            move = (int) (Math.random() * 9);
        }
        return move;
    }

    @Override
    public int checkForWinner() {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == board[i][1]) && (board[i][1] == board[i][2])) {
                if (board[i][0] == CROSS) {
                    return CROSS_WON;
                } else if (board[i][0] == NOUGHT) {
                    return NOUGHT_WON;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            if ((board[0][i] == board[1][i]) && (board[1][i] == board[2][i])) {
                if (board[0][i] == CROSS) {
                    return CROSS_WON;
                } else if (board[0][i] == NOUGHT) {
                    return NOUGHT_WON;
                }
            }
        }

        for (int i = 0; i < 3; i = i + 2) {
            if ((board[0][i] == board[1][1]) && (board[1][1] == board[2][2 - i])) {
                if (board[0][i] == CROSS) {
                    return CROSS_WON;
                } else if (board[0][i] == NOUGHT) {
                    return NOUGHT_WON;
                }
            }
        }

        if (PLAY_COUNT == 8){
            return TIE;
        }

        return PLAYING;
    }

    /**
     * Print the game board
     */
    public void printBoard() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                printCell(board[row][col]); // print each of the cells
                if (col != COLS - 1) {
                    System.out.print("|");   // print vertical partition
                }
            }
            System.out.println();
            if (row != ROWS - 1) {
                System.out.println("-----------"); // print horizontal partition
            }
        }
        System.out.println();
    }

    /**
     * Print a cell with the specified "content"
     *
     * @param content either CROSS, NOUGHT or EMPTY
     */
    public void printCell(int content) {
        switch (content) {
            case EMPTY:
                System.out.print("   ");
                break;
            case NOUGHT:
                System.out.print(" O ");
                break;
            case CROSS:
                System.out.print(" X ");
                break;
        }
    }

}
