package com.example.shopmoon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shopmoon.Adapter.ProductAdapter;
import com.example.shopmoon.Model.ProducerType;
import com.example.shopmoon.Model.Product;
import com.example.shopmoon.Model.ProductDetail;
import com.example.shopmoon.Server.Server;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailsProductActivity extends AppCompatActivity {
    ArrayList<Product> dataproduct=new ArrayList<>();

    ProductAdapter productAdapter;

    RecyclerView rvproduct;

    ViewFlipper viewFlipper;
    BottomNavigationView bottomNavigationView;
    TextView ten,gia,tt;
    Button b1;
    RequestQueue requestQueue;
    SharedPreferences luutru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_product);
        luutru= getSharedPreferences("savefile", Context.MODE_PRIVATE);
        viewFlipper=findViewById(R.id.viewfliperslideproductdetails);
        requestQueue= Volley.newRequestQueue(this);
        ten=findViewById(R.id.txttensanpham);
        gia=findViewById(R.id.txtgiasanpham);
        tt=findViewById(R.id.txtthongso);
        b1=findViewById(R.id.btnback);
//        productAdapter=new ProductAdapter(getApplicationContext(),dataproduct);
//        rvproduct.setAdapter(productAdapter);
//        rvproduct.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));
//        LoadProduct1();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(DetailsProductActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });
        Intent intent;
        intent=getIntent();
        String maID=intent.getStringExtra("proID");
        Toast.makeText(this,""+maID,Toast.LENGTH_SHORT).show();
        LoadProductDetail(maID);
    bottomNavigationView=findViewById(R.id.bottomnavigationdetails);
    bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id=item.getItemId();
            switch (id)
            {
                case R.id.menuback:
                    Intent intent =new Intent(DetailsProductActivity.this,MainActivity.class);
                    startActivity(intent);
                    break;
                case  R.id.menupaynow:
                    Intent intent1 = new Intent(DetailsProductActivity.this,
                            PaymentActivity.class);
                    intent1.putExtra("proIDDD",
                            cd.proID.toString());
                    startActivity(intent1);
                    break;
                case R.id.menuaddtocart:
                    StringRequest stringRequest=new StringRequest(Request.Method.POST,Server.urlpostcart, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(DetailsProductActivity.this,response,Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(DetailsProductActivity.this,"loi" +error+"",Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String>params=new HashMap<String,String>();
                            String macart=maID+1;
                            String proid=maID;
                            String cusID=luutru.getString("us","0");
                            String sl="1";
                            int money=Integer.parseInt(cd.proPrice.toString())*Integer.parseInt(sl);
                            params.put("cartID",macart);
                            params.put("proID",proid);
                            params.put("proPhoto",cd.proDetailPhoto1.toString());
                            params.put("proName",cd.proName.toString());
                            params.put("cusID",cusID);
                            params.put("proPrice",cd.proPrice.toString());
                            params.put("proQuantity",sl);
                            params.put("proMoney",money+"");
                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);
                    break;
            }
            return true;
        }
    });


    }
    ProductDetail cd;
    public void LoadProductDetail(String ma)
    {

        String url= Server.urlgetproductdetail+"?id="+ma;
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        Response.Listener<JSONArray>thanhcong=new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject chudeObject=response.getJSONObject(i);
                         cd=new ProductDetail(chudeObject.getString("proID"),chudeObject.getString("proName"),chudeObject.getString("proPrice"),chudeObject.getString("proDetail"),chudeObject.getString("proDetailPhoto1"),chudeObject.getString("proDetailPhoto2"),chudeObject.getString("proDetailPhoto3"),chudeObject.getString("proDetailPhoto4"));
                         ten.setText(cd.proName);
                         gia.setText("Giá sản phẩm :"+cd.proPrice+""+"VND");
                         tt.setText(cd.proDetail);
                    }
                    catch (JSONException e)
                    {
                        Toast.makeText(getApplicationContext(),"loi"+e.toString(),Toast.LENGTH_SHORT).show();
                    }

                }

                ArrayList<String> manquangcao=new ArrayList<>();
                manquangcao.add(Server.urlhinhanh+cd.proDetailPhoto1);
                manquangcao.add(Server.urlhinhanh+cd.proDetailPhoto2);
                manquangcao.add(Server.urlhinhanh+cd.proDetailPhoto3);
                manquangcao.add(Server.urlhinhanh+cd.proDetailPhoto4);
                for(int i=0;i< manquangcao.size();i++)
                {
                    ImageView hinh=new ImageView(getApplicationContext());
                    Picasso.get().load(manquangcao.get(i)).into(hinh);
                    hinh.setScaleType(ImageView.ScaleType.FIT_XY);
                    viewFlipper.addView(hinh);
               }
            }
        };
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(5000);
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