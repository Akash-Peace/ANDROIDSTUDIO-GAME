package com.example.xo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Main6Activity extends AppCompatActivity {
    private EditText fpgmail;
    private Button brp;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        fpgmail=(EditText)findViewById(R.id.fpgmail);
        brp=(Button)findViewById(R.id.brp);
        firebaseAuth=FirebaseAuth.getInstance();
        brp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=fpgmail.getText().toString().trim();
                if (email.equals("")){
                    Toast toast=Toast.makeText(Main6Activity.this,"Enter registered Gmail ID",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM,0,0);
                    toast.show();
                }else {
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast toast=Toast.makeText(Main6Activity.this,"New Password sent - Gmail",Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.BOTTOM,0,0);
                                toast.show();
                                finish();
                                startActivity(new Intent(Main6Activity.this,Main5Activity.class));
                            }else {
                                Toast toast=Toast.makeText(Main6Activity.this,"Error in sending password!",Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.BOTTOM,0,0);
                                toast.show();
                            }
                        }
                    });
                }
            }
        });
    }
}
