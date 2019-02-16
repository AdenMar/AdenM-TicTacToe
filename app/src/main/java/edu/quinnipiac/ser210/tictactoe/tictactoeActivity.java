/*
Tic Tac Toe Android Project

tictactoeActivity.java handles the tictactoe activity and all game related actions

@Author Aden Mariyappa
@Date: 2/8/19
 */

package edu.quinnipiac.ser210.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;

public class tictactoeActivity extends Activity implements ITicTacToe{

    public static int HUMAN_PLAYER = 1, COMPUTER_PLAYER = 2;

    private static final int ROWS = 3, COLS = 3; // number of rows and columns
    private int[][] board = new int[ROWS][COLS]; // game board in 2D array
    private static int PLAY_COUNT;
    int currentState = ITicTacToe.PLAYING; // sets the state to playing based on the interface
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);

        clearBoard();

        // inputs the name into the game text view
        name = getIntent().getStringExtra("name");
        TextView playerName = findViewById(R.id.TTT_Game_Text);
        playerName.setText("It's Tic Tac Toe Time " + name);
    }

    public void playerMove(View view){
        int location = idToNum(view.getId()); // converts button id to an input-able location

        if (currentState == ITicTacToe.PLAYING) { // if computer has won, skip
            setMove(HUMAN_PLAYER, location); // player turn
            winCheck();
            if (currentState == ITicTacToe.PLAYING) { // if the user has won, skip
                setMove(COMPUTER_PLAYER, getComputerMove()); // computer turn
                winCheck();
            }
        }
    }

    // separated rom the playerMove so it is used twice
    public void winCheck(){
        currentState = checkForWinner(); // checks/sets the state of the game

        if (currentState == ITicTacToe.CROSS_WON) {
            Toast.makeText(getApplicationContext(), "'X' won! Bye!", Toast.LENGTH_LONG).show(); // toast showing x won
            returnToMenu();
        } else if (currentState == ITicTacToe.NOUGHT_WON) {
            Toast.makeText(getApplicationContext(), "'O' won! Bye!", Toast.LENGTH_LONG).show(); // toast showing o won
            returnToMenu();
        } else if (currentState == ITicTacToe.TIE) {
            Toast.makeText(getApplicationContext(), "It's a TIE! Bye!", Toast.LENGTH_LONG).show(); // toast showing tie
            returnToMenu();
        }
    }

    public void returnToMenu(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent); // returns to main menu
    }

    // takes the button id and converts it into a location
    public int idToNum(int id){
        switch(id){
            case R.id.button0:
                return 0;
            case R.id.button1:
                return 1;
            case R.id.button2:
                return 2;
            case R.id.button3:
                return 3;
            case R.id.button4:
                return 4;
            case R.id.button5:
                return 5;
            case R.id.button6:
                return 6;
            case R.id.button7:
                return 7;
            case R.id.button8:
                return 8;
        }
        return 0;
    }

    @Override
    public void clearBoard() {
        // sets all button back to blank
        findViewById(R.id.button0).setBackgroundResource(R.drawable.blankspace);;
        findViewById(R.id.button1).setBackgroundResource(R.drawable.blankspace);;
        findViewById(R.id.button2).setBackgroundResource(R.drawable.blankspace);;
        findViewById(R.id.button3).setBackgroundResource(R.drawable.blankspace);;
        findViewById(R.id.button4).setBackgroundResource(R.drawable.blankspace);;
        findViewById(R.id.button5).setBackgroundResource(R.drawable.blankspace);;
        findViewById(R.id.button6).setBackgroundResource(R.drawable.blankspace);;
        findViewById(R.id.button7).setBackgroundResource(R.drawable.blankspace);;
        findViewById(R.id.button8).setBackgroundResource(R.drawable.blankspace);;

        // sets all space back to empty
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = EMPTY;
            }
        }
        PLAY_COUNT = 0; // sets turn back to 0
    }

    @Override
    public void setMove(int player, int location) {
        board[location / 3][location % 3] = player; // sets move
        display(player,location); // displays symbol in space

        PLAY_COUNT++; // increases turnS
    }

    // random unused number
    @Override
    public int getComputerMove() {
        int move = (int) (Math.random() * 9);

        while (board[move / 3][move % 3] != EMPTY) {
            move = (int) (Math.random() * 9);
        }
        return move;
    }

    // checks all states for win
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

    // changes button image at a specific location
    public void display(int player, int location){
        switch(location){
            case 0:
                findViewById(R.id.button0).setBackgroundResource(cell(player));
                break;
            case 1:
                findViewById(R.id.button1).setBackgroundResource(cell(player));
                break;
            case 2:
                findViewById(R.id.button2).setBackgroundResource(cell(player));
                break;
            case 3:
                findViewById(R.id.button3).setBackgroundResource(cell(player));
                break;
            case 4:
                findViewById(R.id.button4).setBackgroundResource(cell(player));
                break;
            case 5:
                findViewById(R.id.button5).setBackgroundResource(cell(player));
                break;
            case 6:
                findViewById(R.id.button6).setBackgroundResource(cell(player));
                break;
            case 7:
                findViewById(R.id.button7).setBackgroundResource(cell(player));
                break;
            case 8:
                findViewById(R.id.button8).setBackgroundResource(cell(player));
                break;
        }
    }

    // determines the image in the button
    public int cell(int content) {
        switch (content) {
            case EMPTY:
                return R.drawable.blankspace;
            case NOUGHT:
                return R.drawable.nought;
            case CROSS:
                return R.drawable.cross;
        }
        return R.drawable.blankspace;
    }

    // returns to main menu
    public void onQuit(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    // resets the board
    public void onRestart(View view){
        clearBoard();
    }
}