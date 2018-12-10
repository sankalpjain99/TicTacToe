package com.example.sankalpjain.tictactoe;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 for red and 1 for yellow
    int activePlayer=0;
    //2 means empty
    int[] gameState= {2,2,2,2,2,2,2,2,2};
    int[][] win = {{0,1,2}, {3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView title = findViewById(R.id.title);
        Typeface t = Typeface.createFromAsset(getAssets(), "fonts/JosefinSans-Bold.ttf");
        title.setTypeface(t);
    }

    public void dropIn(View view){
        ImageView counter = (ImageView)view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter]==2 && gameActive) {
            gameState[tappedCounter]=activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.red);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 0;
            }
            for(int []wp : win){
                if ((gameState[wp[0]]==gameState[wp[1]]) &&
                        (gameState[wp[1]]==gameState[wp[2]])&&
                        (gameState[wp[0]]!=2)){

                    gameActive=false;
                    String winner;
                    if (gameState[wp[0]]==0){
                        winner="Player one";
                    }
                    else{
                        winner="Player two";
                    }
                    System.out.println("Winnnnnn");
                    Toast t = Toast.makeText(getApplicationContext(), winner+" has won !", Toast.LENGTH_SHORT);
                    t.show();
                }
                else{
                    boolean gameOver=true;
                    for(int i : gameState){
                        if (i==2) gameOver=false;
                    }
                    if (gameOver){
                        Toast t = Toast.makeText(getApplicationContext(), "It's a DRAW", Toast.LENGTH_SHORT);
                        t.show();
                    }
                }
            }
        }
    }

    public void playAgain(View view){
        activePlayer=0;
        for (int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        gameActive=true;
        GridLayout gl = findViewById(R.id.board);
        for(int i=0;i<gl.getChildCount();i++){
            ((ImageView) gl.getChildAt(i)).setImageResource(0);
        }
    }
}
