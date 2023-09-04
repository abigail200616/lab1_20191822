package com.example.lab1_20191822_abigail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String titulo= "APPSIoT- Lab 1";
    private TextView titleTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleTextView = findViewById(R.id.textView);
        registerForContextMenu(titleTextView);

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        getMenuInflater().inflate(R.menu.menucontext2, menu);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        return super.onContextItemSelected(item);
    }

    public void juego(View view){
        Intent intent = new Intent(this , MainActivity2.class);
        startActivity(intent);
    }
}