package com.elite.mobilestore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.elite.mobilestore.MainActivity.EXTRA_BLACK32;
import static com.elite.mobilestore.MainActivity.EXTRA_GOLD128;
import static com.elite.mobilestore.MainActivity.EXTRA_GRAY64;

public class DemoActivity extends AppCompatActivity {

    Button size32,size64,size128;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        //TODO Toolbar nu karvu kaik
        //TODO Highlight button karva jena par click thaya te


        size32 = findViewById(R.id.size32);
        size64 = findViewById(R.id.size64);
        size128 = findViewById(R.id.size128);

        Intent intent = getIntent();
        final String black32 = intent.getStringExtra(EXTRA_BLACK32);
        final String gray64 = intent.getStringExtra(EXTRA_GRAY64);
        final String gold128 = intent.getStringExtra(EXTRA_GOLD128);

        if (black32.equals("null")){
            size32.setVisibility(View.GONE);
        }
        if (gray64.equals("null")){
            size64.setVisibility(View.GONE);
        }
        if (gold128.equals("null")){
            size128.setVisibility(View.GONE);
        }

        size32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                price.setText(black32);
            }
        });

        size64.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                price.setText(gray64);
            }
        });

        size128.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                price.setText(gold128);
            }
        });
    }
}
