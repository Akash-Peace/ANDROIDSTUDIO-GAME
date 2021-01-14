package com.example.xo;

public class profileloader {
    public String name,gmail,matwon,matloss,matdraw;
    public profileloader(){

    }
    public profileloader(String name,String gmail,String matwon,String matloss,String matdraw){
        this.name=name;
        this.gmail=gmail;
        this.matwon=matwon;
        this.matloss=matloss;
        this.matdraw=matdraw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGmail() { return gmail; }

    public void setGmail(String gmail) { this.gmail = gmail; }

    public String getMatwon() { return matwon; }

    public void setGetMatwon(String matwon) { this.matwon =matwon; }

    public String getMatloss() {
        return matloss;
    }

    public void setMatloss(String matloss) { this.matloss = matloss; }

    public String getMatdraw() {
      return matdraw;
    }

    public void getMatdraw(String matdraw) { this.matdraw = matdraw; }}