package com.example.imccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.imccalculator.models.UserData;

public class MainActivity extends AppCompatActivity {

    private ContentController contentController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.contentController = new ContentController(this);
        UserData userData = contentController.getUserData();
        if(userData == null){

        }
        else {

        }
    }

    public void proximaTela(View view){
        Intent intent = new Intent(this, informacoes.class);
        startActivity(intent);
    }

    
}
