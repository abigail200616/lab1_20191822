package com.example.lab1_20191822_abigail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    private String[] letras;
    private Random random;
    private String actual;
    private TextView[] charView;
    private LinearLayout letraL;
    private Teclado adapter;
    private GridView gridView;
    private int numC;
    private int numChar;
    private ImageView[]parts;
    private int sizeParts=6;
    private int currPart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        letras=getResources().getStringArray(R.array.letras);
        letraL=findViewById(R.id.letra);
        gridView=findViewById(R.id.letras);
        random = new Random();
        parts=new ImageView[sizeParts];
        parts[0]=findViewById(R.id.head);
        parts[1]=findViewById(R.id.body);
        parts[2]=findViewById(R.id.brazoD);
        parts[3]=findViewById(R.id.brazoI);
        parts[4]=findViewById(R.id.piernaD);
        parts[5]=findViewById(R.id.piernaI);

        playGame();
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucontext2, menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return super.onContextItemSelected(item);

    }

    private void playGame(){
        String nuevaP = letras[random.nextInt(letras.length)];

        while(nuevaP.equals(actual))nuevaP=letras[random.nextInt(letras.length)];
        actual= nuevaP;
        charView = new TextView[actual.length()];

        letraL.removeAllViews();
        for(int i=0; i<actual.length(); i++){
            charView[i] = new TextView(this);
            charView[i].setText(""+actual.charAt(i));
            charView[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            charView[i].setGravity(Gravity.CENTER);
            charView[i].setTextColor(Color.WHITE);
            charView[i].setBackgroundResource(R.drawable.letter_bg);
            letraL.addView(charView[i]);
        }

        adapter = new Teclado(this);
        gridView.setAdapter(adapter);
        numC=0;
        currPart=0;
        numChar=actual.length();

        for(int i=0; i<sizeParts; i++){
            parts[i].setVisibility(View.INVISIBLE);
        }
    }

    public void letterPressed(View view){
        String letter = ((TextView)view).getText().toString();
        char letterChar=letter.charAt(0);

        view.setEnabled(false);

        boolean correct=false;
        for(int i=0; i<actual.length(); i++){
            if (actual.charAt(i)==letterChar){
                correct=true;
                numC++;
                charView[i].setTextColor(Color.BLACK);
            }
        }

        if(correct){
            if(numC==numChar){
                disableButtons();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Ganó");
                builder.setMessage("Terminó en \n\n La respuesta era " + actual);
                builder.setPositiveButton("Nuevo Juego", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity2.this.playGame();
                    }
                });
                builder.show();
            }
        } else if (currPart<sizeParts) {
            parts[currPart].setVisibility(View.VISIBLE);
            currPart++;
        }else{
            disableButtons();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Perdió");
            builder.setMessage("Terminó en \n\n La respuesta era " + actual);
            builder.setPositiveButton("Nuevo Juego", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity2.this.playGame();
                }
            });
            builder.show();
        }
    }

    public void disableButtons(){
        for(int i =0; i<gridView.getChildCount();i++){
            gridView.getChildAt(i).setEnabled(false);
        }
    }


}