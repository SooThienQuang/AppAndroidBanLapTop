package com.example.shopmoon.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopmoon.DetailsProductActivity;
import com.example.shopmoon.Model.Product;
import com.example.shopmoon.MuaHangActivity;
import com.example.shopmoon.R;
import com.example.shopmoon.Server.Server;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.KHUNGNHINPRODUCT>{
    Context context;
    ArrayList<Product> data=new ArrayList<>();

    public ProductAdapter(Context context, ArrayList<Product> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public KHUNGNHINPRODUCT onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.product_layout,null);
        return new KHUNGNHINPRODUCT(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNGNHINPRODUCT holder, int position) {
        Product cd = data.get(position);
        holder.tensanpham.setText(cd.proName);
        holder.giasanpham.setText("Giá sản phẩm :"+cd.proPrice+"  VND");
        Picasso.get().load(Server.urlhinhanh+cd.proPhoto).into(holder.hinhsanpham);
        holder.hinhsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailsProductActivity.class);
                intent.putExtra("proID",cd.proID);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class KHUNGNHINPRODUCT extends RecyclerView.ViewHolder {
        TextView  tensanpham;
        ImageView hinhsanpham;
        TextView giasanpham;

        public KHUNGNHINPRODUCT(@NonNull View itemView) {
            super(itemView);
            tensanpham=itemView.findViewById(R.id.tvnameproduct);
            hinhsanpham=itemView.findViewById(R.id.imgProduct);
            giasanpham=itemView.findViewById(R.id.tvPriceProduct);

        }

    }
}
