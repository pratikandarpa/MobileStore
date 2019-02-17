package com.elite.mobilestore;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static com.elite.mobilestore.Cartdata.Emptycard;
import static com.elite.mobilestore.Cartdata.listCart;

public class CartAdapter extends ArrayAdapter<Cart> {

    Context mCtx;
    int listLayoutRes;
    List<Cart> cartList;
    SQLiteDatabase mDatabase;

    int minteger = 1;
    static String text;
    static String a;

    public CartAdapter(Context mCtx, int listLayoutRes, List<Cart> cartList, SQLiteDatabase mDatabase) {
        super(mCtx, listLayoutRes, cartList);

        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.cartList = cartList;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        final View view = inflater.inflate(listLayoutRes, null);

        //getting employee of the specified position
        final Cart cart = cartList.get(position);

        TextView t1 = view.findViewById(R.id.t1);
        TextView t2 = view.findViewById(R.id.t2);
        TextView t3 = view.findViewById(R.id.t3);
        TextView t4 = view.findViewById(R.id.t4);
        final TextView t5 = view.findViewById(R.id.t5);
        final TextView t6 = view.findViewById(R.id.t6);
        ImageView dataimg = view.findViewById(R.id.dataimg);

        ImageView deleteitem = view.findViewById(R.id.delete);
        TextView integer_numbernew = view.findViewById(R.id.integer_numbernew);
        Button increasenew = view.findViewById(R.id.increasenew);
        Button decreasenew = view.findViewById(R.id.decreasenew);

        t1.setText(cart.getPname());
        t2.setText("₹ " + cart.getPprice());
        t3.setText(String.valueOf(cart.getPcolor()));
        t4.setText(cart.getPstorage());
        t5.setText("Quantity: " + cart.getPquantity());
        integer_numbernew.setText(cart.getPquantity());
        minteger = Integer.parseInt(cart.getPquantity());

        final int p = Integer.parseInt(cart.getPprice());
        int q = Integer.parseInt(cart.getPquantity());
        int TotalPrice = p * q;
        t6.setText("Total ₹ " + TotalPrice);

        String res = cart.getForimage();
        int id = mCtx.getResources().getIdentifier(res, "drawable", mCtx.getPackageName());
        dataimg.setImageResource(id);

        reloadcart();
//        final List<String> cartempty = new ArrayList<>();
//        Cursor cursor = mDatabase.rawQuery("SELECT * FROM cart", null);
//        while (cursor.moveToNext()) {
//            cartempty.add(cursor.getString(cursor.getColumnIndexOrThrow("Uid")));
//        }

        deleteitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String sql = "DELETE FROM cart WHERE id = ?";
                        mDatabase.execSQL(sql, new Integer[]{cart.getId()});

                        Cursor cursor = mDatabase.rawQuery("SELECT * FROM cart", null);
                        notifyDataSetChanged();
                        reloadcart();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        increasenew.setOnClickListener(new View.OnClickListener() {
            int mintegernew = Integer.parseInt(cart.getPquantity());

            @Override
            public void onClick(View v) {
                mintegernew = mintegernew + 1;
                t5.setText("" + mintegernew);
                text = t5.getText().toString();
                String sql = "UPDATE cart \n" +
                        "SET quantity = ? \n" +
                        "WHERE id = ?;\n";
                mDatabase.execSQL(sql, new String[]{text, String.valueOf(cart.getId())});
                //Toast.makeText(mCtx, "Quantity Updated", Toast.LENGTH_SHORT).show();
                reloadcart();
            }
        });

        decreasenew.setOnClickListener(new View.OnClickListener() {
            int mintegernew = Integer.parseInt(cart.getPquantity());

            @Override
            public void onClick(View v) {
                if (!(mintegernew == 1)) {
                    mintegernew = mintegernew - 1;
                }
                t5.setText("" + mintegernew);
                text = t5.getText().toString();
                String sql = "UPDATE cart \n" +
                        "SET quantity = ? \n" +
                        "WHERE id = ?;\n";
                mDatabase.execSQL(sql, new String[]{text, String.valueOf(cart.getId())});
                reloadcart();
            }
        });
        return view;
    }

    private void reloadcart() {
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM cart", null);
        //cartList.clear();
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
        notifyDataSetChanged();
    }
}