package com.example.xo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity {
    private Button play,score,ex,ib;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        play = (Button) findViewById(R.id.play);
        score = (Button) findViewById(R.id.score);
        ex = (Button) findViewById(R.id.ex);
        ib=(Button) findViewById(R.id.imagebutton);
        bttons();}
        public void bttons(){
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go();
            }
        });
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                points();
            }
        });
        ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ext();
            }
        });
    }
    public void go(){
        Intent intent=new Intent(Main3Activity.this,MainActivity.class);
        startActivity(intent);
    }
    public void points(){
        Intent intent=new Intent(Main3Activity.this,Main7Activity.class);
        startActivity(intent);
    }
    public void ext(){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
