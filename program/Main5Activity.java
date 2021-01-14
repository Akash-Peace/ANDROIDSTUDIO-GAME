package com.example.xo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main5Activity extends AppCompatActivity {
    private EditText lname,lpass;
    private Button bll,newreg,dice;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotpass;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        lname=(EditText) findViewById(R.id.lname);
        lpass=(EditText) findViewById(R.id.lpass);
        bll=(Button) findViewById(R.id.bl2);
        dice=(Button) findViewById(R.id.dice);
        newreg=(Button) findViewById(R.id.newreg);
        forgotpass=(TextView)findViewById(R.id.forgotpass);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        //FirebaseUser candidate=firebaseAuth.getCurrentUser();
        //if (candidate!=null){
        //    finish();
        //    startActivity(new Intent(this,Main3Activity.class));
        //}
        newreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main5Activity.this,Main4Activity.class));
            }
        });
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main5Activity.this,Main6Activity.class));
            }
        });
        bll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fillall()){
                validate();}
            }
        });
    }
    public void validate(){
        progressDialog.setMessage("Verifying . . .");
        progressDialog.show();
        String llname=lname.getText().toString();
        String llpass=lpass.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(llname,llpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    System.out.println("processing.......");
                    Toast toast=Toast.makeText(Main5Activity.this,"WELCOME !",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM,0,0);
                    toast.show();
                    System.out.println("processed.......");
                    startActivity(new Intent(Main5Activity.this,Main3Activity.class));
                    System.out.println("completed.......");
                }else {
                    progressDialog.dismiss();
                    Toast toast=Toast.makeText(Main5Activity.this,"Login Failed",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM,0,0);
                    toast.show();
                }
            }
        });
    }
    public boolean fillall(){
        Boolean res=false;
        String namecheck=lname.getText().toString();
        String passcheck=lpass.getText().toString();
        if (namecheck.isEmpty()||passcheck.isEmpty()){
            Toast toast=Toast.makeText(this,"Please fill all Details",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM,0,0);
            toast.show();
        }else {
            res=true;
        }
        return res;
    }
}