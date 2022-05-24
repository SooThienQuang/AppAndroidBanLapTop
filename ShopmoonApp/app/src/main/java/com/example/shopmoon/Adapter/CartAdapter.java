package com.example.shopmoon.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shopmoon.DetailsProductActivity;
import com.example.shopmoon.LoginActivity;
import com.example.shopmoon.MainActivity;
import com.example.shopmoon.Model.Cart_class;
import com.example.shopmoon.Model.Product;
import com.example.shopmoon.Model.ProductDetail;
import com.example.shopmoon.R;
import com.example.shopmoon.SearchProducerActivity;
import com.example.shopmoon.Server.Server;
import com.example.shopmoon.View.Cart;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartAdapter extends RecyclerView.Adapter<KHUNG>{
    Context context;
    ArrayList<Cart_class> data=new ArrayList<>();
    RequestQueue requestQueue;
    public CartAdapter(Context context, ArrayList<Cart_class> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public KHUNG onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.cart_layout_item,null);
        return new KHUNG(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNG holder, int position) {
        Cart_class cd = data.get(position);
        requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        Picasso.get().load(Server.urlhinhanh+cd.proPhoto).into(holder.image);
        holder.gia.setText(cd.proPrice);
        holder.name.setText(cd.proName);
        holder.sl.setText(cd.proQuantity);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailsProductActivity.class);
                intent.putExtra("proID",cd.proID);
                context.startActivity(intent);
            }
        });
        holder.cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sll=Integer.parseInt(holder.sl.getText().toString())+1;
                holder.sl.setText(sll+"");
            }
        });
        holder.tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.sl.getText().toString().equals("1")) {
                    Toast.makeText(context.getApplicationContext(), "Lỗi số lượng", Toast.LENGTH_SHORT).show();
                }
                    else
                {
                    int sll=Integer.parseInt(holder.sl.getText().toString())-1;
                    holder.sl.setText(sll+"");
                }

            }
        });
        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest=new StringRequest(Request.Method.POST,Server.urldeletecart, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String s=response.trim();
                            Toast.makeText(context.getApplicationContext(), response,Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context.getApplicationContext(),"loi" +error+"",Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>params=new HashMap<String,String>();
                        params.put("cartID",cd.cartID.toString());
                        params.put("cusID",cd.cusID.toString());
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }

        });

        holder.luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest=new StringRequest(Request.Method.POST,Server.urlupdatecart, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String s=response.trim();
                        Toast.makeText(context.getApplicationContext(), response,Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context.getApplicationContext(),"loi" +error+"",Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>params=new HashMap<String,String>();
                        params.put("cartID",cd.cartID.toString());
                        params.put("cusID",cd.cusID.toString());
                        params.put("proQuantity",holder.sl.getText().toString());
                        int mm=Integer.parseInt(cd.proPrice)*Integer.parseInt(holder.sl.getText().toString());
                        params.put("proMoney",mm+"");
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}


class KHUNG extends RecyclerView.ViewHolder {
    ImageView image,cong ,tru,xoa,luu;
    TextView name, gia;
    EditText sl;

    public KHUNG(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.imagecart);
        name = itemView.findViewById(R.id.txtcartname);
        gia = itemView.findViewById(R.id.txtcartprice);
        sl=itemView.findViewById(R.id.txtcartsl);
        cong=itemView.findViewById(R.id.imcartcong);
        tru=itemView.findViewById(R.id.imcarttru);
        xoa=itemView.findViewById(R.id.btncartdelete);
        luu=itemView.findViewById(R.id.btnsave);
    }

}
