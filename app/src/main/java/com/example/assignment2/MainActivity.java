package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    EditText editTextText;
    EditText editTextTextPassword;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.loginButton);
        editTextText = findViewById(R.id.editTextText);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);

        sharedPreferences = getSharedPreferences("",MODE_PRIVATE);
        String name = sharedPreferences.getString("",null);
        if(name != null){
            Intent intent = new Intent(MainActivity.this,secondActivity.class);
            Toast.makeText(this, "login success",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo();
                Intent intent = new Intent(MainActivity.this,secondActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        saveInfo();
    }

    public void saveInfo(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("",editTextText.getText().toString());
        editor.putString("",editTextTextPassword.getText().toString());
        editor.apply();
    }
}