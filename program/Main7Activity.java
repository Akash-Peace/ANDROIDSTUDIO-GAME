package com.example.xo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main7Activity extends AppCompatActivity {
    private TextView gmailretrieve,nameretrieve,mw,ml,md;
    private Button bedit,changepassword;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        gmailretrieve=(TextView)findViewById(R.id.gmailretrieve);
        nameretrieve=(TextView)findViewById(R.id.nameretrieve);
        mw=(TextView)findViewById(R.id.matcheswon);
        ml=(TextView)findViewById(R.id.matchesloss);
        md=(TextView)findViewById(R.id.matchesdraw);
        bedit=(Button)findViewById(R.id.bedit);
        changepassword=(Button)findViewById(R.id.bedit2);

        /*int no=getIntent().getExtras().getInt("matcheswon");
        System.out.println(no);
        System.out.println(no);
        System.out.println(no);
        System.out.println(no);
        System.out.println(no);
        System.out.println(no);
        System.out.println(no);
        System.out.println(no);
        System.out.println(no);
        System.out.println(no);
        System.out.println(no);
        System.out.println(no);
        System.out.println(no);
        System.out.println(no);
        System.out.println(no);*/
        //SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        //final String matcheswon=sharedPreferences.getString("matcheswon","");
        //final String matchesloss=sharedPreferences.getString("matcheswon","");
        //final String matchesdraw=sharedPreferences.getString("matcheswon","");
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                profileloader profile_loader=dataSnapshot.getValue(profileloader.class);
                nameretrieve.setText("Name : " + profile_loader.getName());
                gmailretrieve.setText("Gmail : " + profile_loader.getGmail());
                //int wincontfromdatabase =Integer.parseInt(profileloader.getMatwon());
                //int losscontfromdatabase =Integer.parseInt(profileloader.getMatloss());
                //int drawcontfromdatabase =Integer.parseInt(profileloader.getMatdraw());
                //int addingwincont =Integer.parseInt(matcheswon);
                //int addinglosscont =Integer.parseInt(matchesloss);
                //int addingdrawcont =Integer.parseInt(matchesdraw);
                //int win=wincontfromdatabase+addingwincont;
                //int loss=losscontfromdatabase+addinglosscont;
                //int draw=drawcontfromdatabase+addingdrawcont;
                mw.setText("Matches WON : "+profile_loader.getMatwon());
                ml.setText("Matches LOSS : "+profile_loader.getMatloss());
                md.setText("Matches DRAW : "+profile_loader.getMatdraw());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast toast=Toast.makeText(Main7Activity.this,databaseError.getCode(),Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM,0,0);toast.show();
            }
        });
        bedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main7Activity.this,Main8Activity.class));
            }
        });
        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main7Activity.this,Main9Activity.class));
            }
        });
    }
}