package com.example.xo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
//import android.content.SharedPreferences;
import android.os.Bundle;
//import android.preference.PreferenceManager;
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

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView drawscore,playerscore;
    private Button b0,b1,b2,b3,b4,b5,b6,b7,b8;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        try {
            xo();
        }
        catch (Exception ie){
            finish();
        }
    }
    public void xo() {
        b0 = (Button) findViewById(R.id.b0);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        final int[] xx = {100,100,100,100,100,100,100,100,100};
        final int[] oo = {100,100,100,100,100,100,100,100,100};
        final int[] aa = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        final int[] dop= {0, 1, 2, 3, 4, 5, 6, 7, 8};
        final int[] avoid={0,0,0,0,0,0,0,0,0,0,0,0};
        final int[] winordraw={0};
        final int[] matcheswon={0};
        final int[] matchesloss={0};
        final int[] matchesdraw={0};
        final int[] n={0};
        n[0]=0;
        drawscore = (TextView) findViewById(R.id.drawscore);
        playerscore = (TextView) findViewById(R.id.playerscore);
        final Button[] all = {b0, b1, b2, b3, b4, b5, b6, b7, b8};
        Random raom = new Random();
        int mn = raom.nextInt(2);
        System.out.println(mn);
        if (mn==1){
            Random ran = new Random();
            int m = ran.nextInt(all.length);
            System.out.println("comp play first  "+m);
            Button v = all[m];
            v.setText("O");
            aa[m]++;
            oo[m]=aa[m];
        }
        b0.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View view) {
                                      if (aa[0] == 0) {
                                          b0.setText("X");
                                          dop[0]++;
                                          ai(aa,oo,xx,dop,avoid,winordraw, matcheswon, matchesloss, matchesdraw,n);
                                          res(xx,oo,aa,dop,winordraw,matcheswon,matchesloss,matchesdraw,n);
                                          if (aa[0]==1){
                                              return;
                                          }
                                          Random random = new Random();
                                          int n = random.nextInt(all.length);
                                          System.out.println(n);
                                          Button v = all[n];
                                          if (aa[n] == n) {
                                              if (v == b0) {
                                                  dop[0]--;
                                                  onClick(b0);
                                              } else {
                                                  v.setText("O");
                                              }
                                              aa[0]++;
                                              aa[n]++;
                                              xx[0]=aa[0];
                                              oo[n]=aa[n];
                                          } else {
                                              dop[0]--;
                                              onClick(b0);
                                          }
                                      }
                                      res(xx,oo,aa,dop,winordraw,matcheswon,matchesloss,matchesdraw,n);
                                      ai(aa,oo,xx,dop,avoid,winordraw, matcheswon, matchesloss, matchesdraw,n);
                                  }
                              }
        );
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aa[1] == 1) {
                    b1.setText("X");
                    dop[1]++;
                    ai(aa,oo,xx,dop,avoid,winordraw, matcheswon, matchesloss, matchesdraw,n);
                    res(xx,oo,aa,dop,winordraw,matcheswon,matchesloss,matchesdraw,n);
                    if (aa[1]==2){
                        return;
                    }
                    Random random = new Random();
                    int n = random.nextInt(all.length);
                    System.out.println(n);
                    Button v = all[n];
                    if (aa[n] == n) {
                        if (v == b1) {
                            dop[1]--;
                            onClick(b1);
                        } else {
                            v.setText("O");
                        }
                        aa[1]++;
                        aa[n]++;
                        xx[1]=aa[1];
                        oo[n]=aa[n];
                    } else {
                        dop[1]--;
                        onClick(b1);
                    }
                }
                res(xx,oo,aa,dop,winordraw,matcheswon,matchesloss,matchesdraw,n);
                ai(aa,oo,xx,dop,avoid,winordraw, matcheswon, matchesloss, matchesdraw,n);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aa[2] == 2) {
                    b2.setText("X");
                    dop[2]++;
                    ai(aa,oo,xx,dop,avoid,winordraw, matcheswon, matchesloss, matchesdraw,n);
                    res(xx,oo,aa,dop,winordraw,matcheswon,matchesloss,matchesdraw,n);
                    if (aa[2]==3){
                        return;
                    }
                    Random random = new Random();
                    int n = random.nextInt(all.length);
                    System.out.println(n);
                    Button v = all[n];
                    if (aa[n] == n) {
                        if (v == b2) {
                            dop[2]--;
                            onClick(b2);
                        } else {
                            v.setText("O");
                        }
                        aa[2]++;
                        aa[n]++;
                        xx[2]=aa[2];
                        oo[n]=aa[n];
                    } else {
                        dop[2]--;
                        onClick(b2);
                    }
                }
                res(xx,oo,aa,dop,winordraw,matcheswon,matchesloss,matchesdraw,n);
                ai(aa,oo,xx,dop,avoid,winordraw, matcheswon, matchesloss, matchesdraw,n);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (aa[3] == 3) {
                    b3.setText("X");
                    dop[3]++;
                    ai(aa,oo,xx,dop,avoid,winordraw, matcheswon, matchesloss, matchesdraw,n);
                    res(xx,oo,aa,dop,winordraw,matcheswon,matchesloss,matchesdraw,n);
                    if (aa[3]==4){
                        return;
                    }
                    Random random = new Random();
                    int n = random.nextInt(all.length);
                    System.out.println(n);
                    Button v = all[n];
                    if (aa[n] == n) {
                        if (v == b3) {
                            dop[3]--;
                            onClick(b3);
                        } else {
                            v.setText("O");
                        }
                        aa[n]++;
                        aa[3]++;
                        xx[3]=aa[3];
                        oo[n]=aa[n];
                    } else {
                        dop[3]--;
                        onClick(b3);
                    }
                }
                res(xx,oo,aa,dop,winordraw,matcheswon,matchesloss,matchesdraw,n);
                ai(aa,oo,xx,dop,avoid,winordraw, matcheswon, matchesloss, matchesdraw,n);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aa[4] == 4) {
                    b4.setText("X");
                    dop[4]++;
                    ai(aa,oo,xx,dop,avoid,winordraw, matcheswon, matchesloss, matchesdraw,n);
                    res(xx,oo,aa,dop,winordraw,matcheswon,matchesloss,matchesdraw,n);
                    if (aa[4]==5){
                        return;
                    }
                    Random random = new Random();
                    int n = random.nextInt(all.length);
                    System.out.println(n);
                    Button v = all[n];
                    if (aa[n] == n) {
                        if (v == b4) {
                            dop[4]--;
                            onClick(b4);
                        } else {
                            v.setText("O");
                        }
                        aa[n]++;
                        aa[4]++;
                        xx[4]=aa[4];
                        oo[n]=aa[n];
                    } else {
                        dop[4]--;
                        onClick(b4);
                    }
                }
                res(xx,oo,aa,dop,winordraw,matcheswon,matchesloss,matchesdraw,n);
                ai(aa,oo,xx,dop,avoid,winordraw,matcheswon,matchesloss,matchesdraw,n);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aa[5] == 5) {
                    b5.setText("X");
                    dop[5]++;
                    ai(aa,oo,xx,dop,avoid,winordraw, matcheswon, matchesloss, matchesdraw,n);
                    res(xx,oo,aa,dop,winordraw,matcheswon,matchesloss,matchesdraw,n);
                    if (aa[5]==6){
                        return;
                    }
                    Random random = new Random();
                    int n = random.nextInt(all.length);
                    System.out.println(n);
                    Button v = all[n];
                    if (aa[n] == n) {
                        if (v == b5) {
                            dop[5]--;
                            onClick(b5);
                        } else {
                            v.setText("O");
                        }
                        aa[5]++;
                        aa[n]++;
                        xx[5]=aa[5];
                        oo[n]=aa[n];
                    } else {
                        dop[5]--;
                        onClick(b5);
                    }
                }
                res(xx,oo,aa,dop,winordraw,matcheswon,matchesloss,matchesdraw,n);
                ai(aa,oo,xx,dop,avoid,winordraw, matcheswon, matchesloss, matchesdraw,n);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aa[6] == 6) {
                    b6.setText("X");
                    dop[6]++;
                    ai(aa,oo,xx,dop,avoid,winordraw, matcheswon, matchesloss, matchesdraw,n);
                    res(xx,oo,aa,dop,winordraw,matcheswon,matchesloss,matchesdraw,n);
                    if (aa[6]==7){
                        return;
                    }
                    Random random = new Random();
                    int n = random.nextInt(all.length);
                    System.out.println(n);
                    Button v = all[n];
                    if (aa[n] == n) {
                        if (v == b6) {
                            dop[6]--;
                            onClick(b6);
                        } else {
                            v.setText("O");
                        }
                        aa[6]++;
                        aa[n]++;
                        xx[6]=aa[6];
                        oo[n]=aa[n];
                    } else {
                        dop[6]--;
                        onClick(b6);
                    }
                }
                res(xx,oo,aa,dop,winordraw,matcheswon,matchesloss,matchesdraw,n);
                ai(aa,oo,xx,dop,avoid,winordraw, matcheswon, matchesloss, matchesdraw,n);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aa[7] == 7) {
                    b7.setText("X");
                    dop[7]++;
                    ai(aa,oo,xx,dop,avoid,winordraw, matcheswon, matchesloss, matchesdraw,n);
                    res(xx,oo,aa,dop,winordraw,matcheswon,matchesloss,matchesdraw,n);
                    if (aa[7]==8){
                        return;
                    }
                    Random random = new Random();
                    int n = random.nextInt(all.length);
                    System.out.println(n);
                    Button v = all[n];
                    if (aa[n] == n) {
                        if (v == b7) {
                            dop[7]--;
                            onClick(b7);
                        } else {
                            v.setText("O");
                        }
                        aa[7]++;
                        aa[n]++;
                        xx[7]=aa[7];
                        oo[n]=aa[n];
                    } else {
                        dop[7]--;
                        onClick(b7);
                    }
                }
                res(xx,oo,aa,dop,winordraw,matcheswon,matchesloss,matchesdraw,n);
                ai(aa,oo,xx,dop,avoid,winordraw, matcheswon, matchesloss, matchesdraw,n);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (aa[8] == 8) {
                    b8.setText("X");
                    dop[8]++;
                    ai(aa,oo,xx,dop,avoid,winordraw, matcheswon, matchesloss, matchesdraw,n);
                    res(xx,oo,aa,dop,winordraw,matcheswon,matchesloss,matchesdraw,n);
                    if (aa[8]==9){
                        return;
                    }
                    Random random = new Random();
                    int n = random.nextInt(all.length);
                    System.out.println(n);
                    Button v = all[n];
                    if (aa[n] == n) {
                        if (v == b8) {
                            dop[8]--;
                            onClick(b8);
                        } else {
                            v.setText("O");
                        }
                        aa[8]++;
                        aa[n]++;
                        xx[8]=aa[8];
                        oo[n]=aa[n];
                    } else {
                        dop[8]--;
                        onClick(b8);
                    }
                }
                res(xx,oo,aa,dop,winordraw,matcheswon,matchesloss,matchesdraw,n);
                ai(aa,oo,xx,dop,avoid,winordraw, matcheswon, matchesloss, matchesdraw,n);
            }
        });
    }

    public void res(int[] xx, int[] oo, int[] aa, int[] dop, int[] winordraw, int[] matcheswon, int[] matchesloss, int[] matchesdraw,int[] n) {
        if (xx[0] + xx[1] + xx[2] == 7) {
            System.out.println("001");winordraw[0]=1;matcheswon[0]=1;
            win(matcheswon,matchesloss,matchesdraw,n); }
        else if (xx[3] + xx[4] + xx[5] == 15) {
            System.out.println("002");winordraw[0]=1;matcheswon[0]=1;
            win(matcheswon,matchesloss,matchesdraw,n); }
        else if (xx[6] + xx[7] + xx[8] == 24) {
            System.out.println("003");winordraw[0]=1;matcheswon[0]=1;
            win(matcheswon,matchesloss,matchesdraw,n);}
        else if (xx[0] + xx[3] + xx[6] == 12) {
            System.out.println("004");winordraw[0]=1;matcheswon[0]=1;
            win(matcheswon,matchesloss,matchesdraw,n);}
        else if (xx[1] + xx[4] + xx[7] == 15) {
            System.out.println("005");winordraw[0]=1;matcheswon[0]=1;
            win(matcheswon,matchesloss,matchesdraw,n);}
        else if (xx[2] + xx[5] + xx[8] == 18) {
            System.out.println("006");winordraw[0]=1;matcheswon[0]=1;
            win(matcheswon,matchesloss,matchesdraw,n);}
        else if (xx[0] + xx[4] + xx[8] == 15) {
            System.out.println("007");winordraw[0]=1;matcheswon[0]=1;
            win(matcheswon,matchesloss,matchesdraw,n);}
        else if (xx[2] + xx[4] + xx[6] == 15) {
            System.out.println("008");winordraw[0]=1;matcheswon[0]=1;
            win(matcheswon,matchesloss,matchesdraw,n);}
        else if (oo[0] + oo[1] + oo[2] == 7) {
            System.out.println("001");winordraw[0]=1;matchesloss[0]=1;
            loss(matcheswon,matchesloss,matchesdraw,n);}
        else if (oo[3] + oo[4] + oo[5] == 15) {
            System.out.println("002");winordraw[0]=1;matchesloss[0]=1;
            loss(matcheswon,matchesloss,matchesdraw,n);}
        else if (oo[6] + oo[7] + oo[8] == 24) {
            System.out.println("003");winordraw[0]=1;matchesloss[0]=1;
            loss(matcheswon,matchesloss,matchesdraw,n);}
        else if (oo[0] + oo[3] + oo[6] == 12) {
            System.out.println("004");winordraw[0]=1;matchesloss[0]=1;
            loss(matcheswon,matchesloss,matchesdraw,n);}
        else if (oo[1] + oo[4] + oo[7] == 15) {
            System.out.println("005");winordraw[0]=1;matchesloss[0]=1;
            loss(matcheswon,matchesloss,matchesdraw,n);}
        else if (oo[2] + oo[5] + oo[8] == 18) {
            System.out.println("006");winordraw[0]=1;matchesloss[0]=1;
            loss(matcheswon,matchesloss,matchesdraw,n);}
        else if (oo[0] + oo[4] + oo[8] == 15) {
            System.out.println("007");winordraw[0]=1;matchesloss[0]=1;
            loss(matcheswon,matchesloss,matchesdraw,n);}
        else if (oo[2] + oo[4] + oo[6] == 15) {
            System.out.println("008");winordraw[0]=1;matchesloss[0]=1;
            loss(matcheswon,matchesloss,matchesdraw,n);}
    }
    public void ai(int[] aa, int[] oo, int[] xx, int[] dop, int[] avoid, int[] winordraw, int[] matcheswon, int[] matchesloss, int[] matchesdraw,int[] n){
        final Button[] all = {b0, b1, b2, b3, b4, b5, b6, b7, b8};
        System.out.println("checking");
        if (avoid[0]==0 && avoid[1]==0 && avoid[2]==0){
            System.out.println("initial");
            for (int i=0;i<7;i=i+3){
                if (aa[dop[i]]==i+2 && aa[dop[i+2]-2]==i+2){
                    System.out.println("co-incidence");
                }
                else if (dop[i]+dop[i+2]+dop[i+1]==i+i+i+6){
                    System.out.println("zzz1");
                    Button v=all[i+1];
                    v.setText("X");
                    winordraw[0]=1;
                    aa[i+1]++;
                    matcheswon[0]=1;
                    win(matcheswon,matchesloss,matchesdraw,n);
                }
                else if (dop[i]+dop[i+2]==i+i+4){
                    System.out.println("z1");
                    Button v=all[i+1];
                    v.setText("o");
                    aa[i+1]++;
                    oo[i+1]=aa[i+1];
                    if (aa[i]==i){
                        aa[i]++;
                        xx[i]=aa[i];
                    }
                    else{
                        aa[i+2]++;
                        xx[i+2]=aa[i+2];
                    }avoid[0]=1;return;
                }}
            System.out.println("initial 1");
            for (int i=0;i<7;i=i+3){
                System.out.println(i);
                try{
                    if (aa[dop[i+1]-2]==i+1 && aa[dop[i+2]-3]==i+1){
                        System.out.println("no....."+i);
                        System.out.println("co-incidence");
                    }
                    else if (dop[i]+dop[i+2]+dop[i+1]==i+i+i+6){
                        System.out.println("zzz2");
                        Button v=all[i];
                        v.setText("X");
                        winordraw[0]=1;
                        aa[i]++;
                        matcheswon[0]=1;
                        win(matcheswon,matchesloss,matchesdraw,n);
                    }
                    else if (dop[i+1]+dop[i+2]==i+i+5){
                        System.out.println("z2");
                        Button v=all[i];
                        v.setText("o");
                        aa[i]++;
                        oo[i]=aa[i];
                        if (aa[i+1]==i+1){
                            aa[i+1]++;
                            xx[i+1]=aa[i+1];
                        }
                        else{
                            aa[i+2]++;
                            xx[i+2]=aa[i+2];
                        }avoid[1]=1;return;
                    }}
                catch (ArrayIndexOutOfBoundsException exc){
                    System.out.println("exception occred");
                    continue;
                }
            }
            System.out.println("initial 2");
            for (int i=0;i<7;i=i+3){
                if ((aa[dop[i]+1]==i+3 && aa[dop[i+1]]==i+3)){
                    System.out.println("no....."+i);
                    System.out.println("co-incidence");
                }
                else if (dop[i]+dop[i+2]+dop[i+1]==i+i+i+6){
                    System.out.println("zzz3");
                    Button v=all[i+2];
                    v.setText("X");
                    winordraw[0]=1;
                    aa[i+2]++;
                    matcheswon[0]=1;
                    win(matcheswon,matchesloss,matchesdraw,n);
                }
                else if (dop[i]+dop[i+1]==i+i+3){
                    System.out.println("z3");
                    Button v=all[i+2];
                    v.setText("o");
                    aa[i+2]++;
                    oo[i+2]=aa[i+2];
                    if (aa[i]==i){
                        aa[i]++;
                        xx[i]=aa[i];
                    }
                    else{
                        aa[i+1]++;
                        xx[i+1]=aa[i+1];
                    }avoid[2]=1;return;
                }}}
        if (avoid[3]==0 && avoid[4]==0 && avoid[5]==0){
            System.out.println("initial 3");
            for (int i=0;i<3;i++){
                if ((aa[dop[i]+2]==i+4 && aa[dop[i+6]-4]==i+4)){
                    System.out.println("no....."+i);
                    System.out.println("co-incidence");
                }
                else if (dop[i]+dop[i+3]+dop[i+6]==i+i+i+12){
                    System.out.println("zzz4");
                    Button v=all[i+3];
                    v.setText("X");
                    winordraw[0]=1;
                    aa[i+3]++;
                    matcheswon[0]=1;
                    win(matcheswon,matchesloss,matchesdraw,n);
                }
                else if (dop[i]+dop[i+6]==i+i+8){
                    System.out.println("z4");
                    Button v=all[i+3];
                    v.setText("o");
                    aa[i+3]++;
                    oo[i+3]=aa[i+3];
                    if (aa[i]==i){
                        aa[i]++;
                        xx[i]=aa[i];
                    }
                    else{
                        aa[i+6]++;
                        xx[i+6]=aa[i+6];
                    }avoid[3]=1;return;
                } }
            System.out.println("initial 4");
            for (int i=0;i<3;i++){
                try{
                    if ((aa[dop[i+3]-4]==i+1 && aa[dop[i+6]-7]==i+1)){
                        System.out.println("no....."+i);
                        System.out.println("co-incidence");
                    }
                    else if (dop[i]+dop[i+3]+dop[i+6]==i+i+i+12){
                        System.out.println("zzz5");
                        Button v=all[i];
                        v.setText("X");
                        winordraw[0]=1;
                        aa[i]++;
                        matcheswon[0]=1;
                        win(matcheswon,matchesloss,matchesdraw,n);
                    }
                    else if (dop[i+3]+dop[i+6]==i+i+11){
                        System.out.println("z5");
                        Button v=all[i];
                        v.setText("o");
                        aa[i]++;
                        oo[i]=aa[i];
                        if (aa[i+3]==i+3){
                            aa[i+3]++;
                            xx[i+3]=aa[i+3];
                        }
                        else{
                            aa[i+6]++;
                            xx[i+6]=aa[i+6];
                        }avoid[4]=1;return;
                    } }
                catch (ArrayIndexOutOfBoundsException exc){
                    System.out.println("exception occred");
                    continue;}
            }
            System.out.println("initial 5");
            for (int i=0;i<3;i++){
                if (aa[dop[i]+5]==i+7 && aa[dop[i+3]+2]==i+7){
                    System.out.println("no....."+i);
                    System.out.println("co-incidence");
                }
                else if (dop[i]+dop[i+3]+dop[i+6]==i+i+i+12){
                    System.out.println("zzz6");
                    Button v=all[i+6];
                    v.setText("X");
                    winordraw[0]=1;
                    aa[i+6]++;
                    matcheswon[0]=1;
                    win(matcheswon,matchesloss,matchesdraw,n);
                }
                else if (dop[i]+dop[i+3]==i+i+5){
                    System.out.println("zsix");
                    Button v=all[i+6];
                    v.setText("o");
                    aa[i+6]++;
                    oo[i+6]=aa[i+6];
                    if (aa[i]==i){
                        aa[i]++;
                        xx[i]=aa[i];
                    }
                    else{
                        aa[i+3]++;
                        xx[i+3]=aa[i+3];
                    }avoid[5]=1;return;
                } }}
        if (avoid[6]==0){
            System.out.println("initial 6");
            for (int i=0;i<1;i++){
                if (aa[dop[i]+3]==i+5 && aa[dop[i+8]-5]==i+5){
                    System.out.println("no....."+i);
                    System.out.println("co-incidence");
                }
                else if (dop[i]+dop[i+4]+dop[i+8]==i+i+i+15){
                    System.out.println("zzz7");
                    Button v=all[i+4];
                    v.setText("X");
                    winordraw[0]=1;
                    aa[i+4]++;
                    matcheswon[0]=1;
                    win(matcheswon,matchesloss,matchesdraw,n);
                }
                else if (dop[i]+dop[i+8]==i+i+10){
                    System.out.println("z7");
                    Button v=all[i+4];
                    v.setText("o");
                    aa[i+4]++;
                    oo[i+4]=aa[i+4];
                    if (aa[i]==i){
                        aa[i]++;
                        xx[i]=aa[i];
                    }
                    else{
                        aa[i+8]++;
                        xx[i+8]=aa[i+8];
                    }avoid[6]=1;return;
                } }}
        if (avoid[7]==0){
            System.out.println("initial 7");
            for (int i=0;i<1;i++){
                try{
                    if (aa[dop[i+4]-5]==i+1 && aa[dop[i+8]-9]==i+1){
                        System.out.println("co-incidence");
                    }
                    else if (dop[i]+dop[i+4]+dop[i+8]==i+i+i+15){
                        System.out.println("zzz8");
                        Button v=all[i];
                        v.setText("X");
                        winordraw[0]=1;
                        aa[i]++;
                        matcheswon[0]=1;
                        win(matcheswon,matchesloss,matchesdraw,n);
                    }
                    else if (dop[i+4]+dop[i+8]==14){
                        System.out.println("z8");
                        Button v=all[i];
                        v.setText("o");
                        aa[i]++;
                        oo[i]=aa[i];
                        if (aa[i+4]==i+4){
                            aa[i+4]++;
                            xx[i+4]=aa[i+4];
                        }
                        else{
                            aa[i+8]++;
                            xx[i+8]=aa[i+8];
                        }avoid[7]=1;return;
                    } }
                catch (ArrayIndexOutOfBoundsException exc){
                    System.out.println("exception occred");
                    continue;}
            }}
        if (avoid[8]==0){
            System.out.println("initial 8");
            for (int i=0;i<1;i++){
                if ((aa[dop[i]+7]==i+9 && aa[dop[i+4]+3]==i+9)){
                    System.out.println("co-incidence");
                }
                else if (dop[i]+dop[i+4]+dop[i+8]==i+i+i+15){
                    System.out.println("zzz9");
                    Button v=all[i+8];
                    v.setText("X");
                    winordraw[0]=1;
                    aa[i+8]++;
                    matcheswon[0]=1;
                    win(matcheswon,matchesloss,matchesdraw,n);
                }
                else if (dop[i]+dop[i+4]==6){
                    System.out.println("znine");
                    Button v=all[i+8];
                    v.setText("o");
                    aa[i+8]++;
                    oo[i+8]=aa[i+8];
                    if (aa[i]==i){
                        aa[i]++;
                        xx[i]=aa[i];
                    }
                    else{
                        aa[i+4]++;
                        xx[i+4]=aa[i+4];
                    }avoid[8]=1;return;
                } }}
        if (avoid[9]==0){
            System.out.println("initial 9");
            for (int i=2;i<3;i++) {
                if (aa[dop[i]+1]==i+3 && aa[dop[i+4]-3]==i+3){
                    System.out.println("co-incidence");
                }
                else if (dop[i]+dop[i+2]+dop[i+4]==i+i+i+9){
                    System.out.println("zzz10");
                    Button v=all[i+2];
                    v.setText("X");
                    winordraw[0]=1;
                    aa[i+2]++;
                    matcheswon[0]=1;
                    win(matcheswon,matchesloss,matchesdraw,n);
                }
                else if (dop[i]+dop[i+4]==10){
                    System.out.println("zten");
                    Button v=all[i+2];
                    v.setText("o");
                    aa[i+2]++;
                    oo[i+2]=aa[i+2];
                    if (aa[i]==i){
                        aa[i]++;
                        xx[i]=aa[i];
                    }
                    else{
                        aa[i+4]++;
                        xx[i+4]=aa[i+4];
                    }avoid[9]=1;return;
                } }}
        if (avoid[10]==0){
            System.out.println("initial 10");
            for (int i=2;i<3;i++){
                try{
                    if (aa[dop[i+2]-3]==i+1 && aa[dop[i+4]-5]==i+1){
                        System.out.println("co-incidence");
                    }
                    else if (dop[i]+dop[i+2]+dop[i+4]==i+i+i+9){
                        System.out.println("zzz11");
                        Button v=all[i];
                        v.setText("X");
                        winordraw[0]=1;
                        aa[i]++;
                        matcheswon[0]=1;
                        win(matcheswon,matchesloss,matchesdraw,n);
                    }
                    else if (dop[i+2]+dop[i+4]==12){
                        System.out.println("z11");
                        Button v=all[i];
                        v.setText("o");
                        aa[i]++;
                        oo[i]=aa[i];
                        if (aa[i+2]==i+2){
                            aa[i+2]++;
                            xx[i+2]=aa[i+2];
                        }
                        else{
                            aa[i+4]++;
                            xx[i+4]=aa[i+4];
                        }avoid[10]=1;return;
                    } }
                catch (ArrayIndexOutOfBoundsException exc){
                    System.out.println("exception occred");
                    continue;}
            }}
        if (avoid[11]==0){
            System.out.println("initial 11");
            for (int i=2;i<3;i++){
                if (aa[dop[i]+3]==i+5 && aa[dop[i+2]+1]==i+5){
                    System.out.println("co-incidence");
                }
                else if (dop[i]+dop[i+2]+dop[i+4]==i+i+i+9){
                    System.out.println("zzz12");
                    Button v=all[i+4];
                    v.setText("X");
                    winordraw[0]=1;
                    aa[i+4]++;
                    matcheswon[0]=1;
                    win(matcheswon,matchesloss,matchesdraw,n);
                }
                else if (dop[i]+dop[i+2]==8){
                    System.out.println("z12");
                    Button v=all[i+4];
                    v.setText("o");
                    aa[i+4]++;
                    oo[i+4]=aa[i+4];
                    if (aa[i]==i){
                        aa[i]++;
                        xx[i]=aa[i];
                    }
                    else{
                        aa[i+2]++;
                        xx[i+2]=aa[i+2];
                    }avoid[11]=1;return;
                } }}
        System.out.println("atlast");
        if (true){
            int i;
            int count = 0;
            System.out.println(aa);
            for (i=0;i<9;i++){
                if (dop[i]==i+1) {
                    count++;
                    System.out.println("con is..."+count);
                }}
            if (count==5){
                for (i=0;i<9;i++){
                    if (dop[i]==i+1) {
                        if (aa[i]!=i+1){
                            aa[i]=i+1;
                            xx[i]=i+1;
                        }
                    }}
                res(xx,oo,aa,dop,winordraw,matcheswon,matchesloss,matchesdraw,n);
                if (winordraw[0]==0){
                    System.out.println("Draw it");
                    matchesdraw[0]=1;
                    Toast toast=Toast.makeText(getApplicationContext(),"Try double side attack to Win ",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM,0,0);
                    toast.show();
                    playerscore.setText("Draw");
                    drawscore.setText("Draw");
                    final int matchewon=matcheswon[0];
                    final int matcheloss=matchesloss[0];
                    final int matchedraw=matchesdraw[0];
                    openactivity(matchewon,matcheloss,matchedraw,n);}
            }
        }
        if (true){
            int i;
            int count = 0;
            System.out.println(aa);
            for (i=0;i<9;i++){
                if (oo[i]==i+1) {
                    count++;
                    System.out.println("connnn is..."+count);
                }}
            if (count==5){
                for (i=0;i<9;i++){
                    if (oo[i]==i+1) {
                        if (aa[i]!=i+1){
                            aa[i]=i+1;
                            oo[i]=i+1;
                        }
                    }}
                res(xx,oo,aa,dop,winordraw,matcheswon,matchesloss,matchesdraw,n);
                if (winordraw[0]==0){
                    System.out.println("Draw it");
                    matchesdraw[0]=1;
                    Toast toast=Toast.makeText(getApplicationContext(),"Better luck next time",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM,0,0);
                    toast.show();
                    playerscore.setText("Draw");
                    drawscore.setText("Draw");
                    final int matchewon=matcheswon[0];
                    final int matcheloss=matchesloss[0];
                    final int matchedraw=matchesdraw[0];
                    openactivity(matchewon,matcheloss,matchedraw,n);}
            }
        }
    }
    public void openactivity(final int matchewon, final int matcheloss, final int matchedraw, final int[] n){
        if (n[0]==0){
            System.out.println("i am in openactivity");
            Intent intent=new Intent(this,Main2Activity.class);
            //Intent tent=new Intent(this,Main7Activity.class);
            //Bundle bundle=new Bundle();
            //matchewon=999999;
            //bundle.putInt("matcheswon",matchewon);
            //bundle.putInt("matchesloss",matcheloss);
            //bundle.putInt("matchesdraw",matchedraw);
            //tent.putExtras(bundle);
            //-----------------------------------------------------------------------------------------
            //SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
            //SharedPreferences.Editor editor=sharedPreferences.edit();
            //editor.putInt("matcheswon",matchewon);
            //editor.putInt("matchesloss",matcheloss);
            //editor.putInt("matchesdraw",matchedraw);
            //editor.apply();
            firebaseAuth=FirebaseAuth.getInstance();
            firebaseDatabase=FirebaseDatabase.getInstance();
            final DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getUid());
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (n[0]==0){
                    profileloader profile_loader=dataSnapshot.getValue(profileloader.class);
                    String name=profile_loader.getName();
                    String gmail=profile_loader.getGmail();
                    int wincontfromdatabase =Integer.parseInt(profile_loader.getMatwon());
                    int losscontfromdatabase =Integer.parseInt(profile_loader.getMatloss());
                    int drawcontfromdatabase =Integer.parseInt(profile_loader.getMatdraw());
                    int win=wincontfromdatabase+ matchewon;
                    int loss=losscontfromdatabase+ matcheloss;
                    int draw=drawcontfromdatabase+ matchedraw;
                    String maw= String.valueOf(win);
                    System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww"+maw);
                    String mal=String.valueOf(loss);
                    System.out.println("llllllllllllllllllllllllllllllllllllllllllllllllllllllll"+mal);
                    String mad=String.valueOf(draw);
                    System.out.println("ddddddddddddddddddddddddddddddddddddddddddddddddddddddd"+mad);
                    profileloader profile__loader=new profileloader(name,gmail,maw,mal,mad);
                    databaseReference.setValue(profile__loader);
                    n[0]=1;
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast toast=Toast.makeText(MainActivity.this,databaseError.getCode(),Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM,0,0);toast.show();
                }
            });
            startActivity(intent);
        }}
    public void win(int[] matcheswon,int[] matchesloss,int[] matchesdraw,int[] n){
        System.out.println("i am in win");
        playerscore.setText("Win");
        drawscore.setText("Loss");
        Toast toast=Toast.makeText(getApplicationContext(),"Winner Winner Chicken Dinner",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM,0,0);
        toast.show();
        final int matchewon=matcheswon[0];
        final int matcheloss=matchesloss[0];
        final int matchedraw=matchesdraw[0];
        openactivity(matchewon,matcheloss,matchedraw,n);
    }
    public void loss(int[] matcheswon,int[] matchesloss,int[] matchesdraw,int[] n){
        System.out.println("i am in loss");
        playerscore.setText("Loss");
        drawscore.setText("Win");
        Toast toast=Toast.makeText(getApplicationContext(),"Rest in Peace",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM,0,0);
        toast.show();
        final int matchewon=matcheswon[0];
        final int matcheloss=matchesloss[0];
        final int matchedraw=matchesdraw[0];
        openactivity(matchewon,matcheloss,matchedraw,n);
    }
}
//Toast toast=Toast.makeText(getApplicationContext()," ",Toast.LENGTH_LONG);
//      toast.setGravity(Gravity.BOTTOM,0,0);
//            toast.show();
