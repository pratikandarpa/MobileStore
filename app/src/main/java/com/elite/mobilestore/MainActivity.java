package com.elite.mobilestore;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements Custome_Adatpter.OnItemClickListener {

    private static final String TAG = "data";
    TextView t1;
    ArrayList<HashMap<String, String>> name2 = new ArrayList<>();
    String image_file, name, des, smallthum, large, colorb, colorg, colorgold, colorblack32, colorgray32, colorgold32, colorblack64, colorgray64, colorgold64, colorblack128, colorgray128, colorgold128;
    ArrayList<HashMap<String, String>> getdata = new ArrayList<>();
    public RecyclerView recyclerView;
    private Custome_Adatpter adapter;

    public static final String EXTRA_uID = "uID";
    public static final String EXTRA_IMG = "IMG";
    public static final String EXTRA_NAME = "NAME";
    public static final String EXTRA_PRICE = "PRICE";
    public static final String EXTRA_DES = "EXTRA_DES";

    public static final String EXTRA_BLACK32 = "BLACK32";
    public static final String EXTRA_GRAY64 = "GRAY64";
    public static final String EXTRA_GOLD128 = "GOLD128";

    public static final String EXTRA_colorblack32 = "colorblack32";
    public static final String EXTRA_colorblack64 = "colorblack64";
    public static final String EXTRA_colorblack128 = "colorblack128";

    public static final String EXTRA_colorgray32 = "colorgray32";
    public static final String EXTRA_colorgray64 = "colorgray64";
    public static final String EXTRA_colorgray128 = "colorgray128";

    public static final String EXTRA_colorgold32 = "colorgold32";
    public static final String EXTRA_colorgold64 = "colorgold64";
    public static final String EXTRA_colorgold128 = "colorgold128";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getdata = getData();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
//        layoutManager = new LinearLayoutManager(ImageActivity.this);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new Custome_Adatpter(MainActivity.this, name2);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(MainActivity.this);
    }

    public ArrayList<HashMap<String, String>> getData() {
        try {
            String jsonFileContent = readFile("phone.json");
            JSONObject jsonObject = new JSONObject(jsonFileContent);
            JSONArray data = jsonObject.getJSONArray("data");

            for (int i = 0; i < data.length(); i++) {

                JSONObject jsonObj = data.getJSONObject(i);
                JSONArray fullimage = jsonObj.getJSONArray("fullimage");
                JSONArray colorcode = jsonObj.getJSONArray("colorcode");
                JSONArray size = jsonObj.getJSONArray("size");

                for (int j = 0; j < fullimage.length(); j++) {

                    JSONObject jsonObj2 = fullimage.getJSONObject(j);
                    smallthum = jsonObj2.getString("smallthum");
                    large = jsonObj2.getString("large");

                    JSONObject jsonObj3 = colorcode.getJSONObject(j);
                    colorb = jsonObj3.getString("colorb");
                    colorg = jsonObj3.getString("colorg");
                    colorgold = jsonObj3.getString("colorgold");
                }

                for (int j = 0; j < size.length(); j++) {

                    JSONObject jsonObj4 = size.getJSONObject(j);
                    JSONArray size32 = jsonObj4.getJSONArray("size32");
                    JSONArray size64 = jsonObj4.getJSONArray("size64");
                    JSONArray size128 = jsonObj4.getJSONArray("size128");

                    for (int k = 0; k < size32.length(); k++) ;
                    {


                        JSONObject jsonObj5 = size32.getJSONObject(j);
                        colorblack32 = jsonObj5.getString("colorblack");
                        colorgray32 = jsonObj5.getString("colorgray");
                        colorgold32 = jsonObj5.getString("colorgold");

                        JSONObject jsonObj6 = size64.getJSONObject(j);
                        colorblack64 = jsonObj6.getString("colorblack");
                        colorgray64 = jsonObj6.getString("colorgray");
                        colorgold64 = jsonObj6.getString("colorgold");

                        JSONObject jsonObj7 = size128.getJSONObject(j);
                        colorblack128 = jsonObj7.getString("colorblack");
                        colorgray128 = jsonObj7.getString("colorgray");
                        colorgold128 = jsonObj7.getString("colorgold");

                    }
                }


                Integer id = jsonObj.getInt("id");
                name = jsonObj.getString("name");
                des = jsonObj.getString("des");
                image_file = jsonObj.getString("image_file");
//                Log.i(TAG, "ID: " + id);
//                Log.i(TAG, "Phone Name: " + name);
//                Log.i(TAG, "Description: " + des);
//
//                Log.i(TAG, "Smallthum: " + smallthum);
//                Log.i(TAG, "Large: " + large);
//
//                Log.i(TAG, "ColorB: " + colorb);
//                Log.i(TAG, "ColorG: " + colorg);
//                Log.i(TAG, "ColorGold: " + colorgold);
//
//                Log.i(TAG, "ColorBlack32: " + colorblack32);
//                Log.i(TAG, "ColorBlack64: " + colorblack64);
//                Log.i(TAG, "ColorBlack128: " + colorblack128);
//
//                Log.i(TAG, "ColorGray32: " + colorgray32);
//                Log.i(TAG, "ColorGray64: " + colorgray64);
//                Log.i(TAG, "ColorGray128: " + colorgray128);
//
//                Log.i(TAG, "ColorGold32: " + colorgold32);
//                Log.i(TAG, "ColorGold64: " + colorgold64);
//                Log.i(TAG, "ColorGold128: " + colorgold128);
                // Log.i(TAG, "ColorGold128: " + image_file);
                HashMap<String, String> abcd = new HashMap<>();

                abcd.put("id", String.valueOf(id));
                abcd.put("phonename", name);
                abcd.put("image_file", image_file);
                abcd.put("description", des);
                abcd.put("smallthum", smallthum);
                abcd.put("large", large);

                abcd.put("colorb", colorb);
                abcd.put("colorg", colorg);
                abcd.put("colorgold", colorgold);

                abcd.put("colorblack32", colorblack32);
                abcd.put("colorblack64", colorblack64);
                abcd.put("colorblack128", colorblack128);

                abcd.put("colorgray32", colorgray32);
                abcd.put("colorgray64", colorgray64);
                abcd.put("colorgray128", colorgray128);

                abcd.put("colorgold32", colorgold32);
                abcd.put("colorgold64", colorgold64);
                abcd.put("colorgold128", colorgold128);

                name2.add(abcd);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return name2;
    }


    public String readFile(String fileName) throws IOException {
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(getAssets().open(fileName), "UTF-8"));

        String content = "";
        String line;
        while ((line = reader.readLine()) != null) {
            content = content + line;
        }
        return content;
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);

        String uid = name2.get(position).get("id");
        String name = name2.get(position).get("phonename");
        String price = name2.get(position).get("colorblack32");
        String description = name2.get(position).get("description");

        String res = name2.get(position).get("large");
        int id = getResources().getIdentifier(res, "drawable", getPackageName());

        detailIntent.putExtra(EXTRA_uID, uid);
        detailIntent.putExtra(EXTRA_NAME, name);
        detailIntent.putExtra(EXTRA_PRICE, price);
        detailIntent.putExtra(EXTRA_IMG, id);
        detailIntent.putExtra(EXTRA_DES, description);

        String black32 = name2.get(position).get("colorblack32");
        String gray64 = name2.get(position).get("colorgray64");
        String gold128 = name2.get(position).get("colorgold128");

        detailIntent.putExtra(EXTRA_BLACK32, black32);
        detailIntent.putExtra(EXTRA_GRAY64, gray64);
        detailIntent.putExtra(EXTRA_GOLD128, gold128);


        String colorblack32 = name2.get(position).get("colorblack32");
        String colorblack64 = name2.get(position).get("colorblack64");
        String colorblack128 = name2.get(position).get("colorblack128");

        String colorgray32 = name2.get(position).get("colorgray32");
        String colorgray64 = name2.get(position).get("colorgray64");
        String colorgray128 = name2.get(position).get("colorgray128");

        String colorgold32 = name2.get(position).get("colorgold32");
        String colorgold64 = name2.get(position).get("colorgold64");
        String colorgold128 = name2.get(position).get("colorgold128");

        detailIntent.putExtra(EXTRA_colorblack32, colorblack32);
        detailIntent.putExtra(EXTRA_colorblack64, colorblack64);
        detailIntent.putExtra(EXTRA_colorblack128, colorblack128);

        detailIntent.putExtra(EXTRA_colorgray32, colorgray32);
        detailIntent.putExtra(EXTRA_colorgray64, colorgray64);
        detailIntent.putExtra(EXTRA_colorgray128, colorgray128);

        detailIntent.putExtra(EXTRA_colorgold32, colorgold32);
        detailIntent.putExtra(EXTRA_colorgold64, colorgold64);
        detailIntent.putExtra(EXTRA_colorgold128, colorgold128);


        startActivity(detailIntent);
    }
}
