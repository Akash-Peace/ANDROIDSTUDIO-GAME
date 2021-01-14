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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main8Activity extends AppCompatActivity {
    private EditText editname,editgmail;
    private Button bedit3,profile2;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        profile2=(Button)findViewById(R.id.imagebutton);
        editname=(EditText) findViewById(R.id.editname);
        editgmail=(EditText) findViewById(R.id.editgmail);
        bedit3=(Button) findViewById(R.id.bedit3);
        final String[] mathwon = new String[1];
        final String[] mathloss = new String[1];
        final String[] mathdraw = new String[1];
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase= FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                profileloader profileloader=dataSnapshot.getValue(profileloader.class);
                editname.setText(profileloader.getName());
                editgmail.setText(profileloader.getGmail());
                mathwon[0] =profileloader.getMatwon();
                mathloss[0] =profileloader.getMatloss();
                mathdraw[0] =profileloader.getMatdraw();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast toast=Toast.makeText(Main8Activity.this,databaseError.getCode(),Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM,0,0);toast.show();
            }
        });
        bedit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent=getIntent();
                //Bundle extras = intent.getExtras();
                //final String matcheswon=extras.getString("matcheswon");
                //final String matchesloss=extras.getString("matchesloss");
                //final String matchesdraw=extras.getString("matchesdraw");
                String name=editname.getText().toString();
                String gmail=editgmail.getText().toString();
                profileloader profile_loader=new profileloader(name,gmail, mathwon[0], mathloss[0], mathdraw[0]);
                databaseReference.setValue(profile_loader);
                finish();
            }
        });
    }
}
