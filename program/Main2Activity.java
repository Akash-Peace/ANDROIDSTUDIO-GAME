package com.example.xo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private TextView drawscore2,playerscore2;
    private Button bb,br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int) (width*.7),(int) (height*.2));
        //playerscore2=(TextView)findViewById(R.id.playerscore2);
        //drawscore2=(TextView)findViewById(R.id.drawscore2);
        bb=(Button)findViewById(R.id.bb);
        br=(Button)findViewById(R.id.br);
        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starter();
            }
        });
        //Bundle bundle=getIntent().getExtras();
        //String ps=bundle.getString("ps");
        //playerscore2.setText(ps);
        //String ds=bundle.getString("ds");
        //drawscore2.setText(ds);
    }
    public void starter(){
        Intent intent=new Intent(this,Main3Activity.class);
        finish();
        startActivity(intent);}
    public void openActivity(){
        Intent intent=new Intent(this,MainActivity.class);
        finish();
        startActivity(intent);}
}
