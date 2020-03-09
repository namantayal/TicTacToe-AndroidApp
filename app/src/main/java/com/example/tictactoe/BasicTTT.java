package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BasicTTT extends AppCompatActivity {

    int flag=0;
    int result=0;
    int zWin=0;
    int cWin=0;
    int[] tokenSet={0,0,0,0,0,0,0,0,0};
    int[][] win={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void play(View view){
        ImageView token =(ImageView) view;
        TextView cScore=findViewById(R.id.cScore);
        TextView zScore=findViewById(R.id.zScore);
        if(result==0) {
            if (flag == 0) {
                if (tokenSet[Integer.parseInt(token.getTag().toString())] == 0) {
                    token.setImageResource(R.drawable.cross);
                    tokenSet[Integer.parseInt(token.getTag().toString())] = 1;
                    flag = 1;
                }
            } else {
                if (tokenSet[Integer.parseInt(token.getTag().toString())] == 0) {
                    token.setImageResource(R.drawable.zero);
                    tokenSet[Integer.parseInt(token.getTag().toString())] = 2;
                    flag = 0;
                }
            }
            for (int i = 0; i < 8; i++) {
                if (tokenSet[win[i][0]] == tokenSet[win[i][1]] && tokenSet[win[i][2]] == tokenSet[win[i][1]] && tokenSet[win[i][0]] != 0) {
                    LinearLayout layout = (LinearLayout) findViewById(R.id.win);
                    layout.setVisibility(View.VISIBLE);
                    TextView winMessage = (TextView) findViewById(R.id.winMessage);
                    result = 1;
                    if (flag == 1) {

                        winMessage.setText(R.string.winCross);
                        layout.setBackgroundColor(Color.parseColor("#FE605D"));
                        cWin++;
                        cScore.setText(Integer.toString(cWin));
                    } else {
                        winMessage.setText(R.string.winZero);
                        layout.setBackgroundColor(Color.parseColor("#3DC4F3"));
                        zWin++;
                        zScore.setText(Integer.toString(zWin));
                    }
                }

            }
        }
    }

    public void reset(View view)
    {
        flag=0;
        for(int i=0;i<9;i++) {
            ImageView token = (ImageView) findViewById(R.id.IV1 + i);
            token.setImageResource(0);
            tokenSet[i]=0;
        }
        result=0;
        LinearLayout layout2 = (LinearLayout) findViewById(R.id.win);
        layout2.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_ttt);
    }
}
