package com.example.xo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main9Activity extends AppCompatActivity {
    private EditText changepassword;
    private Button bchangepassword;
    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        changepassword=(EditText)findViewById(R.id.changepassword);
        bchangepassword=(Button)findViewById(R.id.bchangepassword);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        bchangepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newpassword=changepassword.getText().toString();
                firebaseUser.updatePassword(newpassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast toast=Toast.makeText(Main9Activity.this,"Password Changed!",Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.BOTTOM,0,0);
                            toast.show();
                            finish();
                        }
                        else {
                            Toast toast=Toast.makeText(Main9Activity.this,"Edited Profile Gmail not registered!",Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.BOTTOM,0,0);
                            toast.show();
                            finish();
                        }
                    }
                });
            }
        });
    }
}
