package com.elite.mobilestore;

public class Cart {

     int id;
    String Uid, pname, pprice, pcolor, pstorage, pquantity;
    String forimage = "large";

    public Cart(int id, String Uid, String pname, String pprice, String pcolor, String pstorage, String pquantity, String forimage) {

        this.id=id;
        this.Uid = Uid;
        this.pname=pname;
        this.pprice=pprice;
        this.pcolor=pcolor;
        this.pstorage=pstorage;
        this.pquantity=pquantity;
        this.forimage=forimage;
    }

    public int getId() {
        return id;
    }

    public String getPname() {
        return pname;
    }

    public String getPprice() {
        return pprice;
    }

    public String getPcolor() {
        return pcolor;
    }

    public String getPstorage() {
        return pstorage;
    }

    public String getPquantity() {
        return pquantity;
    }

    public String getForimage() {
        return forimage;
    }

    public String getUid() {
        return Uid;
    }

    public void setForimage(String forimage) {
        this.forimage = forimage;
    }
}
