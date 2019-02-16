/*
Tic Tac Toe Android Project

mainActivity.java handles the text input and start button on the main menu

@Author Aden Mariyappa
@Date: 2/8/19
 */

package edu.quinnipiac.ser210.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    // when the mainActivity is oped, display
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // when the button is clicked, send the text in the edit text to the ttt activity
    public void onSendMessage(View view){
        Intent intent = new Intent(this,tictactoeActivity.class);
        EditText nameIn = findViewById(R.id.nameEdit);
        String name = nameIn.getText().toString();
        intent.putExtra("name", name);
        startActivity(intent);
    }
}
