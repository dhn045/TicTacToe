package com.thedavidnguyen.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<ImageView, Pair<Integer, Integer>> cellPos = new HashMap<>();
    private TicTacToe ticTacToe;
    private boolean xTurn = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCellPos();
        ticTacToe = new TicTacToe();
    }

    private void initCellPos() {
        ImageView cell00 = findViewById(R.id.imageViewCell0);
        ImageView cell01 = findViewById(R.id.imageViewCell1);
        ImageView cell02 = findViewById(R.id.imageViewCell2);
        ImageView cell10 = findViewById(R.id.imageViewCell3);
        ImageView cell11 = findViewById(R.id.imageViewCell4);
        ImageView cell12 = findViewById(R.id.imageViewCell5);
        ImageView cell20 = findViewById(R.id.imageViewCell6);
        ImageView cell21 = findViewById(R.id.imageViewCell7);
        ImageView cell22 = findViewById(R.id.imageViewCell8);

        cellPos.put(cell00, new Pair<Integer, Integer>(0, 0));
        cellPos.put(cell01, new Pair<Integer, Integer>(0, 1));
        cellPos.put(cell02, new Pair<Integer, Integer>(0, 2));
        cellPos.put(cell10, new Pair<Integer, Integer>(1, 0));
        cellPos.put(cell11, new Pair<Integer, Integer>(1, 1));
        cellPos.put(cell12, new Pair<Integer, Integer>(1, 2));
        cellPos.put(cell20, new Pair<Integer, Integer>(2, 0));
        cellPos.put(cell21, new Pair<Integer, Integer>(2, 1));
        cellPos.put(cell22, new Pair<Integer, Integer>(2, 2));
    }

    public void reset(View view) {
        ticTacToe.reset();

        ImageView cell00 = findViewById(R.id.imageViewCell0);
        ImageView cell01 = findViewById(R.id.imageViewCell1);
        ImageView cell02 = findViewById(R.id.imageViewCell2);
        ImageView cell10 = findViewById(R.id.imageViewCell3);
        ImageView cell11 = findViewById(R.id.imageViewCell4);
        ImageView cell12 = findViewById(R.id.imageViewCell5);
        ImageView cell20 = findViewById(R.id.imageViewCell6);
        ImageView cell21 = findViewById(R.id.imageViewCell7);
        ImageView cell22 = findViewById(R.id.imageViewCell8);

        cell00.setImageDrawable(null);
        cell01.setImageDrawable(null);
        cell02.setImageDrawable(null);
        cell10.setImageDrawable(null);
        cell11.setImageDrawable(null);
        cell12.setImageDrawable(null);
        cell20.setImageDrawable(null);
        cell21.setImageDrawable(null);
        cell22.setImageDrawable(null);

        Toast.makeText(getApplicationContext(), "Game reset", Toast.LENGTH_SHORT);

    }


    public void play(View view) {
        if(ticTacToe.getState() != GameState.IN_PROGRESS) {
            return;
        }

        ImageView imageView = (ImageView) view;
        Pair<Integer, Integer> p = cellPos.get(imageView);

        GameState state;
        // update only if it is empty and game is in progress
        if(imageView.getDrawable() == null) {
            state = ticTacToe.updateBoard(xTurn, p.first, p.second);
        } else {
            return;
        }

        Log.i("STATE", state.toString());

        if(xTurn) {
            imageView.setImageResource(R.drawable.x);
        } else {
            imageView.setImageResource(R.drawable.o);
        }
        xTurn = !xTurn;

        if(state != GameState.IN_PROGRESS) {
            switch(state) {
                case X:
                    Log.i("X", "WINNER");
                    Toast.makeText(getApplicationContext(), "X wins!", Toast.LENGTH_LONG).show();
                    break;
                case O:
                    Toast.makeText(getApplicationContext(), "O wins!", Toast.LENGTH_LONG).show();
                    break;
                case DRAW:
                    Toast.makeText(getApplicationContext(), "It's a draw!", Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    }

    // app:srcCompat="@android:drawable/ic_menu_close_clear_cancel"
    // app:srcCompat="@android:drawable/presence_invisible"
}
