package com.example.shopmoon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.shopmoon.Adapter.CartAdapter;
import com.example.shopmoon.Model.Cart_class;
import com.example.shopmoon.Server.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MuaHangActivity extends AppCompatActivity {

    ArrayList<Cart_class> datacart=new ArrayList<>();
    CartAdapter cartAdapter;
    RecyclerView rvorder;
    ImageView back;
    SharedPreferences luutru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mua_hang);
        rvorder=findViewById(R.id.rvorderpayment);
        back=findViewById(R.id.orderbackhome);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MuaHangActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        cartAdapter=new CartAdapter(getApplicationContext(),datacart);
        rvorder.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        rvorder.setAdapter(cartAdapter);
        luutru= getSharedPreferences("savefile", Context.MODE_PRIVATE);
        int cusID= Integer.parseInt( luutru.getString("us","0"));
        LoadCart(cusID);
    }
    public void LoadCart(int cusID)
    {
        String url= Server.urlgetorder+"?cusID="+cusID;
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        Response.Listener<JSONArray>thanhcong=new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject productObject=response.getJSONObject(i);
                        Cart_class cd=new Cart_class(productObject.getString("orderID"),productObject.getString("proID"),productObject.getString("proPhoto"),productObject.getString("proName"),productObject.getString("cusID"),productObject.getString("proPrice"),productObject.getString("proQuantity"),productObject.getString("proMoney"));
                        //Toast.makeText(getApplicationContext(),productObject.getString("orderID"),Toast.LENGTH_SHORT).show();
                      datacart.add(cd);

                    }
                    catch (JSONException e)
                    {
                        Toast.makeText(getApplicationContext(),"loi"+e.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
               cartAdapter.notifyDataSetChanged();
            }
        };

        Response.ErrorListener thatbai=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"that bai:" +error.toString(),Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url,thanhcong,thatbai);
        requestQueue.add(jsonArrayRequest);
    }
}