package com.elite.mobilestore;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.elite.mobilestore.Cartdata.Emptycard;
import static com.elite.mobilestore.Cartdata.foremptycart;
import static com.elite.mobilestore.Cartdata.listCart;
import static com.elite.mobilestore.MainActivity.EXTRA_DES;
import static com.elite.mobilestore.MainActivity.EXTRA_IMG;
import static com.elite.mobilestore.MainActivity.EXTRA_NAME;
import static com.elite.mobilestore.MainActivity.EXTRA_PRICE;
import static com.elite.mobilestore.MainActivity.EXTRA_colorblack128;
import static com.elite.mobilestore.MainActivity.EXTRA_colorblack32;
import static com.elite.mobilestore.MainActivity.EXTRA_colorblack64;
import static com.elite.mobilestore.MainActivity.EXTRA_colorgold128;
import static com.elite.mobilestore.MainActivity.EXTRA_colorgold32;
import static com.elite.mobilestore.MainActivity.EXTRA_colorgold64;
import static com.elite.mobilestore.MainActivity.EXTRA_colorgray128;
import static com.elite.mobilestore.MainActivity.EXTRA_colorgray32;
import static com.elite.mobilestore.MainActivity.EXTRA_colorgray64;
import static com.elite.mobilestore.MainActivity.EXTRA_uID;

public class DetailActivity extends AppCompatActivity {

    CardView card1, card2, card3, card4, card5, card6, card7, card8, card9;
    String Price, NAME;
    String Uid;
    String temp_color = "Black";
    String temp_storage = "32GB";
    static ImageView favone, favtwo;
    Button cart, cardata;
    int minteger = 1;
    Button plus, minus;
    String getquantity = "1";
    public static final String DATABSE_NAME = "mydatabse";
    SQLiteDatabase mDatabase;
    public static int IMG;
    public static List<String> a, b, c;


    public String uid;
    public String pcolor;
    public String pname;
    public String pprice;
    public String pstorage;
    public String pquantity;
    public String forimage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//        createTable();

        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        card5 = findViewById(R.id.card5);
        card6 = findViewById(R.id.card6);
        card7 = findViewById(R.id.card7);
        card8 = findViewById(R.id.card8);
        card9 = findViewById(R.id.card9);

        favone = findViewById(R.id.favone);
        favtwo = findViewById(R.id.favtwo);

        favone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //favone.setVisibility(View.GONE);
                //favtwo.setVisibility(View.VISIBLE);
            }
        });

        favtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //favone.setVisibility(View.VISIBLE);
                //favtwo.setVisibility(View.GONE);
            }
        });

        //Database Section

        mDatabase = openOrCreateDatabase(DATABSE_NAME, MODE_PRIVATE, null);
        createTable();
        cart = findViewById(R.id.cart);
        cardata = findViewById(R.id.cartdata);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkincart(Uid)) {
                        dataupdate();
                    favone.setVisibility(View.GONE);
                    favtwo.setVisibility(View.VISIBLE);
                } else {
                    addCart();
                    favone.setVisibility(View.GONE);
                    favtwo.setVisibility(View.VISIBLE);
                }
            }
        });

        cardata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailActivity.this, Cartdata.class);
                startActivity(i);
            }
        });

        //Quantity plus and minus
        plus = findViewById(R.id.increase);
        minus = findViewById(R.id.decrease);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseInteger();
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseInteger();
            }
        });

        Intent intent = getIntent();

        Uid = intent.getStringExtra(EXTRA_uID);
        Log.i("data", "Mobile ID" + Uid);
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM cart", null);
        a = new ArrayList<>();
        while (cursor.moveToNext()) {
            a.add(cursor.getString(cursor.getColumnIndexOrThrow("Uid")));
//            Log.i("data","CursorData"+a);
        }
//        Log.i("data","BCursorData"+a);
        if (a.contains(Uid)) {
            favone.setVisibility(View.GONE);
            favtwo.setVisibility(View.VISIBLE);
        }

        NAME = intent.getStringExtra(EXTRA_NAME);
        final String PRICE = intent.getStringExtra(EXTRA_PRICE);
        IMG = intent.getIntExtra(EXTRA_IMG, 0);

        final String des = intent.getStringExtra(EXTRA_DES);
        final String colorblack32 = intent.getStringExtra(EXTRA_colorblack32);
        final String colorblack64 = intent.getStringExtra(EXTRA_colorblack64);
        final String colorblack128 = intent.getStringExtra(EXTRA_colorblack128);

        final String colorgray32 = intent.getStringExtra(EXTRA_colorgray32);
        final String colorgray64 = intent.getStringExtra(EXTRA_colorgray64);
        final String colorgray128 = intent.getStringExtra(EXTRA_colorgray128);

        final String colorgold32 = intent.getStringExtra(EXTRA_colorgold32);
        final String colorgold64 = intent.getStringExtra(EXTRA_colorgold64);
        final String colorgold128 = intent.getStringExtra(EXTRA_colorgold128);

        ImageView imageView = findViewById(R.id.Detail_Img);
        TextView name = findViewById(R.id.Detail_Name);
        final TextView price = findViewById(R.id.Detail_Price);
        final TextView color = findViewById(R.id.Color);
        final TextView storage = findViewById(R.id.Storage);

        name.setText(NAME);
        price.setText("₹ " + PRICE);
        Price = PRICE;
        imageView.setImageResource(IMG);

        TextView desc = findViewById(R.id.des);
        desc.setText(des);

        if (colorblack32.equals("null")) {
            card1.setVisibility(View.GONE);
        }
        if (colorgray32.equals("null")) {
            card2.setVisibility(View.GONE);
        }
        if (colorgold32.equals("null")) {
            card3.setVisibility(View.GONE);
        }

        if (colorblack64.equals("null")) {
            card4.setVisibility(View.GONE);
        }
        if (colorgray64.equals("null")) {
            card5.setVisibility(View.GONE);
        }
        if (colorgold64.equals("null")) {
            card6.setVisibility(View.GONE);
        }

        if (colorblack128.equals("null")) {
            card7.setVisibility(View.GONE);
        }
        if (colorgray128.equals("null")) {
            card8.setVisibility(View.GONE);
        }
        if (colorgold128.equals("null")) {
            card9.setVisibility(View.GONE);
        }

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price.setText("₹ " + colorblack32);
                Price = colorblack32;
                temp_color = "Black";
                temp_storage = "32GB";
                color.setText(temp_color);
                storage.setText(temp_storage);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price.setText("₹ " + colorgray32);
                Price = colorgray32;
                temp_color = "Gray";
                temp_storage = "32GB";
                color.setText(temp_color);
                storage.setText(temp_storage);
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price.setText("₹ " + colorgold32);
                Price = colorgold32;
                temp_color = "Gold";
                temp_storage = "32GB";
                color.setText(temp_color);
                storage.setText(temp_storage);
            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price.setText("₹ " + colorblack64);
                Price = colorblack64;
                temp_color = "Black";
                temp_storage = "64GB";
                color.setText(temp_color);
                storage.setText(temp_storage);
            }
        });

        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price.setText("₹ " + colorgray64);
                Price = colorgray64;
                temp_color = "Gray";
                temp_storage = "64GB";
                color.setText(temp_color);
                storage.setText(temp_storage);
            }
        });

        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price.setText("₹ " + colorgold64);
                Price = colorgold64;
                temp_color = "Gold";
                temp_storage = "64GB";
                color.setText(temp_color);
                storage.setText(temp_storage);
            }
        });

        card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price.setText("₹ " + colorblack128);
                Price = colorblack128;
                temp_color = "Black";
                temp_storage = "128GB";
                color.setText(temp_color);
                storage.setText(temp_storage);
            }
        });

        card8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price.setText("₹ " + colorgray128);
                Price = colorgray128;
                temp_color = "Gray";
                temp_storage = "128GB";
                color.setText(temp_color);
                storage.setText(temp_storage);
            }
        });

        card9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price.setText("₹ " + colorgold128);
                Price = colorgold128;
                temp_color = "Gold";
                temp_storage = "128GB";
                color.setText(temp_color);
                storage.setText(temp_storage);
            }
        });

        //Log.i("data", "\nName " + NAME + "\nPrice " + Price + "\nColor " + temp_color + "\nStorage " + temp_storage);
    }

    public void createTable() {
        mDatabase.execSQL("CREATE TABLE IF NOT EXISTS cart (\n" +
                "id INTEGER NOT NULL CONSTRAINT cart_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "Uid varchar (200) NOT NULL,\n" +
                "phonename varchar (200) NOT NULL,\n" +
                "price varchar (200) NOT NULL,\n" +
                "color varchar (200) NOT NULL,\n" +
                "storage varchar (200) NOT NULL,\n" +
                "quantity varchar (200) NOT NULL,\n" +
                "img varchar (200) NOT NULL\n" +
                ");");
    }

    public void addCart() {
        uid = Uid;
        pcolor = temp_color;
        pname = NAME;
        pprice = Price;
        pstorage = temp_storage;
        pquantity = getquantity;
        forimage = String.valueOf(IMG);

        String insertSQL = "INSERT INTO cart(Uid,phonename,price,color,storage,quantity,img)" +
                "VALUES(?,?,?,?,?,?,?)";

        mDatabase.execSQL(insertSQL, new String[]{uid, pname, pprice, pcolor, pstorage, pquantity, forimage});
        Toast.makeText(DetailActivity.this, "Product is Added", Toast.LENGTH_SHORT).show();
        //Log.i("data","\nUid " + uid + "\nName " + pname + "\nPrice " + pprice + "\nColor " + pcolor + "\nStorage " + pstorage + "\nQuantity " + pquantity+ "\nImage " + forimage);
    }

    public boolean checkincart(String uid) {
        Boolean cart = false;

        Log.i("data", "Mobile ID" + Uid);
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM cart", null);
        b = new ArrayList<>();
        while (cursor.moveToNext()) {
            b.add(cursor.getString(cursor.getColumnIndexOrThrow("Uid")));
        }
        if (b.contains(uid)){
            cart = true;
        }
        return cart;
    }

    public void dataupdate() {

        pcolor = temp_color;
        pprice = Price;
        pstorage = temp_storage;
        pquantity = getquantity;

        String sql = "UPDATE cart \n" +
                "SET price = ?,color = ?,storage = ?,quantity = ? \n" +
                "WHERE Uid = ?;\n";
            mDatabase.execSQL(sql, new String[]{pprice, pcolor, pstorage, pquantity,Uid});
            Toast.makeText(DetailActivity.this, "Product Updated", Toast.LENGTH_SHORT).show();
    }

    public void increaseInteger() {
        minteger = minteger + 1;
        display(minteger);
    }

    public void decreaseInteger() {

        if (!(minteger == 0)) {
            minteger = minteger - 1;
        }
        display(minteger);
    }

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.integer_number);
        displayInteger.setText("" + number);
        getquantity = displayInteger.getText().toString();
    }

    @Override
    public void onRestart() {
        super.onRestart();

        Cursor cursor = mDatabase.rawQuery("SELECT * FROM cart", null);
        c = new ArrayList<>();
        while (cursor.moveToNext()) {
            c.add(cursor.getString(cursor.getColumnIndexOrThrow("Uid")));
        }
        if (!c.contains(Uid)) {
            Log.i("data","OnRestart "+c);
            favone.setVisibility(View.VISIBLE);
            favtwo.setVisibility(View.GONE);
        }
    }
}
