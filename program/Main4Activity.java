package com.example.xo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main4Activity extends AppCompatActivity {
    private EditText name,gmail,pass;
    private Button bl,profile;
    private Spinner spinner;
    private FirebaseAuth fb;
    String namefb,passfb,gmailfb,matchwon,matchloss,matchdraw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        loginviews();
        fb=FirebaseAuth.getInstance();
        String[] caution={"Enter more than 5 digit numeric values :)"};
        spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(Main4Activity.this,android.R.layout.simple_spinner_item,caution);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }
    public void loginviews(){
        profile=(Button) findViewById(R.id.imagebutton);
        name=(EditText) findViewById(R.id.name);
        gmail=(EditText) findViewById(R.id.gmail);
        pass=(EditText) findViewById(R.id.pass);
        bl=(Button) findViewById(R.id.bl);
        bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    namefb=name.getText().toString().trim();
                    gmailfb=gmail.getText().toString().trim();
                    matchwon="0";
                    matchloss="0";
                    matchdraw="0";
                    passfb=pass.getText().toString().trim();
                    fb.createUserWithEmailAndPassword(gmailfb,passfb).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                sendprofiletodatabase();
                                System.out.println("processing 1.......");
                                Toast toast = Toast.makeText(Main4Activity.this, "Registration Done", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.BOTTOM, 0, 0);
                                toast.show();
                                System.out.println("processed 1.......");
                                startActivity(new Intent(Main4Activity.this, Main3Activity.class));
                                System.out.println("completed 1.......");
                            } else {
                                Toast toast = Toast.makeText(Main4Activity.this, "Registration failed", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.BOTTOM, 0, 0);
                                toast.show();
                            }}});}}});}
    public boolean validate(){
        Boolean res=false;
        String namecheck=name.getText().toString();
        String passcheck=pass.getText().toString();
        String gmailcheck=gmail.getText().toString();
        if (namecheck.isEmpty()||passcheck.isEmpty()||gmailcheck.isEmpty()){
            Toast toast=Toast.makeText(this,"Please fill all Details",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM,0,0);
            toast.show();
        }else {
            res=true;
        }
        return res;
    }
    public void sendprofiletodatabase(){
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference ref=firebaseDatabase.getReference(fb.getUid());
        profileloader profile_loader=new profileloader(namefb,gmailfb,matchwon,matchloss,matchdraw);
        ref.setValue(profile_loader);
    }
}

