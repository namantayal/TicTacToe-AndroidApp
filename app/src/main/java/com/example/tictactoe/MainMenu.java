package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    public void basic(View view){
        Intent intent=new Intent(getApplicationContext(),BasicTTT.class);
        startActivity(intent);
    }
    public void advance(View view){
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
    public void playVsComp(View view){
        Intent intent=new Intent(getApplicationContext(),PlyVsComp.class);
        startActivity(intent);
    }

    public void online(View view){
        Intent login=new Intent(getApplicationContext(),Login.class);
        startActivity(login);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }
}
