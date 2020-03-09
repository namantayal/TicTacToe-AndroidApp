package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

   private FirebaseAuth mAuth;

    public void signUp(View view){
        Intent SignUp= new Intent(getApplicationContext(),SignUp.class);
        startActivity(SignUp);
    }

    public  void googleLogin(View view){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
