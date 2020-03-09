package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.drm.DrmStore;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class PlyVsComp extends AppCompatActivity {

    int[] tokenSet={0,0,0,0,0,0,0,0,0};
    int[][] win={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int chance=0;
    int result=0;
    int zWin=0;
    int cWin=0;
    int tokenPlayer=0;
    int tokenComputer=0;
    int count=0;

    public int gameState(int[] board){
        for (int i = 0; i < 8; i++) {
            if (board[win[i][0]] == board[win[i][1]] && board[win[i][2]] == board[win[i][1]] && board[win[i][0]] != 0) {
                if (board[win[i][0]]==1)
                    return 1;
                else
                    return 2;
            }
        }
        return -1;
    }

    public boolean isFull(int[] board) {
        for(int i=0;i<9;i++) {
            if(board[i]==0)
                return false;
        }
        return true;
    }

    public class Data{
        int value;
        int pos;
    }

    public Data maximum(List<Data> arr){
        Data max=new Data();
        max.value=Integer.MIN_VALUE;
        for (int i=0;i<arr.size();i++){
            if(max.value<arr.get(i).value)
                max= arr.get(i);
        }
        return max;
    }

    public Data minimum(List<Data> arr){
        Data min=new Data();
        min.value=Integer.MAX_VALUE;
        for (int i=0;i<arr.size();i++){
            if(min.value>arr.get(i).value)
                min= arr.get(i);
        }
        return min;
    }

    public void compPlay() {
        int pos;
        if(count>=1 && count!=3){
            pos = minMax(tokenSet, 0, true);
            count++;
           // System.out.println("AI");
        }
        else {
            List<Integer> vacant=new ArrayList<>();
            for(int i=0;i<9;i++){
                if(tokenSet[i]==0)
                    vacant.add(i);
            }
            pos=vacant.get((int)(Math.random()*vacant.size()));
            count++;
            //System.out.println("Random");
        }
        if(pos>=0&&!isFull(tokenSet)) {
            ImageView token = (ImageView) findViewById(R.id.IV1 + pos);
            token.setImageResource(tokenComputer);
            tokenSet[pos] = 2;
            chance = 0;
        }
    }

    public int minMax(int[] board,int depth,boolean flag) {
        int state = gameState(board);
        if(state==1)
            return depth-10;
        else if(state==2)
            return 10-depth;
        else if(isFull(board))
            return 0;
        else{
            List<Data> values=new ArrayList<>();
            int[] newBoard;
            for(int i=0;i<9;i++){
                if(board[i]==0) {
                    newBoard = board.clone();
                    if(flag)
                        newBoard[i]=2;
                    else
                        newBoard[i]=1;
                    Data data=new Data();
                    data.value=minMax(newBoard,depth+1,!flag);
                    data.pos=i;
                    values.add(data);
                }
            }
            if(flag){
                Data max=maximum(values);
                if(depth==0) {
                    return max.pos;
                }
                else
                    return max.value;
            }
            else{
                Data min=minimum(values);
                return min.value;
            }
        }
    }

    public void play(View view){
        ImageView token =(ImageView) view;
        if(result==0) {
            if (chance == 0) {
                if (tokenSet[Integer.parseInt(token.getTag().toString())] == 0) {
                    token.setImageResource(tokenPlayer);
                    tokenSet[Integer.parseInt(token.getTag().toString())] = 1;
                    chance = 1;
                    compPlay();
                }
            }
            if(gameState(tokenSet)==1) {
                result=1;
                if(tokenPlayer==R.drawable.cross)
                    crossWin();
                else
                    zeroWin();
            }
            else if(gameState(tokenSet)==2){
                result=1;
                if(tokenComputer==R.drawable.cross)
                    crossWin();
                else
                    zeroWin();
            }
        }
    }

    public void setToken(View view){
        ImageView token=(ImageView) view;
        ImageView grid=findViewById(R.id.Grid);
        grid.setAlpha(1f);
        if(Integer.parseInt(token.getTag().toString())==1){
            tokenPlayer=R.drawable.cross;
            tokenComputer=R.drawable.zero;
        }
        else {
            tokenPlayer=R.drawable.zero;
            tokenComputer=R.drawable.cross;
        }
        ConstraintLayout layout=findViewById(R.id.choose);
        layout.setVisibility(View.INVISIBLE);
    }

    public void mode(View view){
        Button button=(Button)view;
        TextView text=findViewById(R.id.textView);
        button.setText("Basic");
        button.setBackgroundColor(Color.parseColor("#3DC4F3"));
        reset(view);
        if(Integer.parseInt(button.getTag().toString())==0){
            button.setText("Normal");
            button.setBackgroundColor(Color.parseColor("#3DC4F3"));
            count=4;
            text.setText("Impossible Mode");
            text.setTextColor(Color.parseColor("#FE605D"));
            button.setTag(4);
        }
        else{
            button.setText("Impossible");
            button.setBackgroundColor(Color.parseColor("#FE605D"));
            count=0;
            text.setText("Normal Mode");
            text.setTextColor(Color.parseColor("#3DC4F3"));
            button.setTag(0);
        }
    }

    public void zeroWin(){
        TextView zScore=findViewById(R.id.zScore);
        LinearLayout layout = (LinearLayout) findViewById(R.id.win);
        TextView winMessage = (TextView) findViewById(R.id.winMessage);
        winMessage.setText(R.string.winZero);
        layout.setVisibility(View.VISIBLE);
        layout.setBackgroundColor(Color.parseColor("#3DC4F3"));
        zWin++;
        zScore.setText(Integer.toString(zWin));
    }

    public void crossWin(){
        TextView cScore=findViewById(R.id.cScore);
        LinearLayout layout = (LinearLayout) findViewById(R.id.win);
        TextView winMessage = (TextView) findViewById(R.id.winMessage);
        layout.setVisibility(View.VISIBLE);
        layout.setBackgroundColor(Color.parseColor("#FE605D"));
        winMessage.setText(R.string.winCross);
        cWin++;
        cScore.setText(Integer.toString(cWin));
    }

    public void reset(View view)
    {
        chance=0;
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
        setContentView(R.layout.activity_plyvscomp);
        ImageView grid=findViewById(R.id.Grid);
        grid.setAlpha(0.2f);
    }
}
