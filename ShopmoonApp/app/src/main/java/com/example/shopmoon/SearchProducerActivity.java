package com.example.shopmoon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.shopmoon.Adapter.ProducerTypeAdapter;
import com.example.shopmoon.Adapter.ProductAdapter;
import com.example.shopmoon.Model.ProducerType;
import com.example.shopmoon.Model.Product;
import com.example.shopmoon.Server.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchProducerActivity extends AppCompatActivity {
    ArrayList<Product> dataproduct=new ArrayList<>();


    //khai bao adapter
    ProductAdapter productAdapter;
    ImageView back;
    //anh xa
    RecyclerView rvproduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_producer);
        //anh xa
        back=findViewById(R.id.backhome2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SearchProducerActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        rvproduct=findViewById(R.id.rvsearchproducer);
        Intent intent=getIntent();
        String ma=intent.getStringExtra("proProducer");
        Toast.makeText(this,""+ma,Toast.LENGTH_SHORT).show();

        productAdapter=new ProductAdapter(this,dataproduct);
        rvproduct.setAdapter(productAdapter);
        rvproduct.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        LoadProduct(ma);
    }
    public void LoadProduct(String ma)
    {
        String url= Server.urlSearchProducer+"?id="+ma;
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        Response.Listener<JSONArray>thanhcong=new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject productObject=response.getJSONObject(i);
                        Product cd=new Product(productObject.getString("proID"),productObject.getString("proName"),productObject.getString("proPrice"),productObject.getString("proPhoto"),productObject.getString("proProducer"),productObject.getString("proQuantity"));
                        dataproduct.add(cd);
                    }
                    catch (JSONException e)
                    {
                        Toast.makeText(getApplicationContext(),"loi"+e.toString(),Toast.LENGTH_SHORT).show();
                    }

                }
                //cap nhat lai danh sach
                productAdapter.notifyDataSetChanged();
            }
        };

        Response.ErrorListener thatbai=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"that bai"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url,thanhcong,thatbai);
        requestQueue.add(jsonArrayRequest);
    }
}