package com.elite.mobilestore;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.DialogPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static com.elite.mobilestore.DetailActivity.DATABSE_NAME;
import static com.elite.mobilestore.MainActivity.EXTRA_IMG;

public class Cartdata extends AppCompatActivity {

    List<Cart> cartList;
    SQLiteDatabase mDatabase;
    public static ListView listCart;
    CartAdapter adapter;
    Context context;
    public static int IMG;
    public static LinearLayout Emptycard;
    public static List<String> foremptycart = new ArrayList<>();
    LinearLayout imgselected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartdata);

        listCart = findViewById(R.id.list_item);
        cartList = new ArrayList<>();

        mDatabase = openOrCreateDatabase(DATABSE_NAME, MODE_PRIVATE, null);
        loadcartdata();

        Intent intent = getIntent();
        IMG = intent.getIntExtra(EXTRA_IMG, 0);

        Emptycard = findViewById(R.id.Emptycard);

        Cursor cursor = mDatabase.rawQuery("SELECT * FROM cart", null);

        loadcartdata();

        while (cursor.moveToNext()) {
            foremptycart.add(cursor.getString(cursor.getColumnIndexOrThrow("Uid")));
        }
        if (foremptycart.size() != 0) {
            Emptycard.setVisibility(View.GONE);
            listCart.setVisibility(View.VISIBLE);
        }
        loadcartdata2();

        context = Cartdata.this;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.onecartdata, null);
        imgselected = view.findViewById(R.id.line);
        imgselected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO particullar image par click nathi thatu
                Log.i("data","1");
                Intent i = new Intent(context,DetailActivity.class);
                startActivity(i);
                Log.i("data","2");
            }
        });
    }

    public void loadcartdata() {
        String sql = "SELECT * FROM cart";
        Cursor cursor = mDatabase.rawQuery(sql, null);
//        cartList.clear();
        if (cursor.moveToFirst()) {
//            cartList.clear();
            do {
                cartList.add(new Cart(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)
                ));
            } while (cursor.moveToNext());
        }
//        else {
//            Emptycard.setVisibility(View.VISIBLE);
//            listCart.setVisibility(View.GONE);
//        }

        cursor.close();
        adapter = new CartAdapter(this, R.layout.onecartdata, cartList, mDatabase);
        //adding the adapter to listview
        listCart.setAdapter(adapter);

    }

    public void loadcartdata2() {
        String sql = "SELECT * FROM cart";
        Cursor cursor = mDatabase.rawQuery(sql, null);
        cartList.clear();
        if (cursor.moveToFirst()) {
            cartList.clear();
            do {
                cartList.add(new Cart(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)
                ));
            } while (cursor.moveToNext());
        } else {
            Emptycard.setVisibility(View.VISIBLE);
            listCart.setVisibility(View.GONE);
        }

        cursor.close();
        //adapter = new CartAdapter(this, R.layout.onecartdata, cartList, mDatabase);
        //adding the adapter to listview
        //listCart.setAdapter(adapter);

    }
}
