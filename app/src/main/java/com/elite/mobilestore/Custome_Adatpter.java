package com.elite.mobilestore;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Custome_Adatpter extends RecyclerView.Adapter<Custome_Adatpter.MyViewHolder> {

    public static ArrayList<HashMap<String, String>> name2;
    Context context;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public Custome_Adatpter(MainActivity mainActivity, ArrayList<HashMap<String, String>> name2) {
        this.context = mainActivity;
        this.name2 = name2;
    }


    @NonNull
    @Override
    public Custome_Adatpter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layoutView = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.onecard, null);
        MyViewHolder rcv = new MyViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull Custome_Adatpter.MyViewHolder myViewHolder, int position) {

        myViewHolder.Name.setText(name2.get(position).get("phonename"));
        myViewHolder.Price.setText("₹ " + name2.get(position).get("colorblack32"));

        String res = name2.get(position).get("large");
        int id = context.getResources().getIdentifier(res, "drawable", context.getPackageName());
        myViewHolder.img.setImageResource(id);
    }

    @Override
    public int getItemCount() {
        return name2.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name;
        public TextView Price;
        public ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.Name);
            Price = itemView.findViewById(R.id.Price);
            img = itemView.findViewById(R.id.img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
