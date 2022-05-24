package com.example.shopmoon.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.shopmoon.Model.ProducerType;
import com.example.shopmoon.Model.ProductDetail;
import com.example.shopmoon.MuaHangActivity;
import com.example.shopmoon.R;
import com.example.shopmoon.SearchProducerActivity;
import com.example.shopmoon.Server.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProducerTypeAdapter extends RecyclerView.Adapter<KHUNGNHIN>{

    Context context;
    ArrayList<ProducerType>data=new ArrayList<>();
ViewFlipper viewFlipper;
    public ProducerTypeAdapter(Context context, ArrayList<ProducerType> data) {
        this.context = context;
        this.data = data;

    }

    @NonNull
    @Override
    public KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(context).inflate(R.layout.producertype_layout,null);
        return new KHUNGNHIN(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNGNHIN holder, int position) {
       ProducerType cd = data.get(position);
        holder.tenchude.setText(cd.proProducerName);
       Picasso.get().load(Server.urlhinhanh+cd.proProducerPhoto).into(holder.hinhchude);

        holder.hinhchude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, SearchProducerActivity.class);
                intent.putExtra("proProducer",cd.proProducer);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

class KHUNGNHIN extends RecyclerView.ViewHolder {
    TextView tenchude;
    ImageView hinhchude;

    public KHUNGNHIN(@NonNull View itemView) {
        super(itemView);
        tenchude=itemView.findViewById(R.id.tenchude);
        hinhchude=itemView.findViewById(R.id.hinhchude);

    }

}
