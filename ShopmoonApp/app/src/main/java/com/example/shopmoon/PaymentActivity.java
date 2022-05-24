package com.example.shopmoon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shopmoon.Adapter.CartAdapter;
import com.example.shopmoon.Adapter.ProductAdapter;
import com.example.shopmoon.Model.Cart_class;
import com.example.shopmoon.Model.Order;
import com.example.shopmoon.Model.Product;
import com.example.shopmoon.Model.ProductDetail;
import com.example.shopmoon.Model.User_class;
import com.example.shopmoon.Server.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {

    TextView tenkh,sdt,diachi,tongtien,email;
    ImageView back;
    SharedPreferences luutru;
    Button pay;
    RequestQueue requestQueue;
    ProductAdapter productAdapter;
    RecyclerView rvproduct;
    ArrayList<Product> dataproduct=new ArrayList<>();
    ArrayList<Cart_class> datacart=new ArrayList<>();
    CartAdapter cartAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        back=findViewById(R.id.backhome);
        email=findViewById(R.id.txtpaymentemail);
        pay=findViewById(R.id.btnpaymentend);
        requestQueue= Volley.newRequestQueue(this);
        tenkh=findViewById(R.id.txtpaymentname);
        sdt=findViewById(R.id.txtpaymentphone);
        diachi=findViewById(R.id.txtpaymentaddress);
        tongtien=findViewById(R.id.txttongtienpay);
        luutru = getSharedPreferences("savefile", Context.MODE_PRIVATE);
        String ma= luutru.getString("us","chua co gi");
        LoadUser(ma);
        Intent intent;
        intent=getIntent();
        String maID=intent.getStringExtra("proIDDD");
        rvproduct=findViewById(R.id.rvpayment);
        int cusID= Integer.parseInt( luutru.getString("us","0"));
        if(maID!=null)
        {

            rvproduct.setHasFixedSize(true);
            productAdapter=new ProductAdapter(getApplicationContext(),dataproduct);
            rvproduct.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
            rvproduct.setAdapter(productAdapter);
            LoadProduct(maID);
        }
        else
        {
            cartAdapter=new CartAdapter(getApplicationContext(),datacart);
            rvproduct.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
            rvproduct.setAdapter(cartAdapter);
            luutru= getApplicationContext().getSharedPreferences("savefile", Context.MODE_PRIVATE);

            LoadCart(cusID);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(PaymentActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maID != null) {
                    String url = Server.urlgetproductdetail + "?id=" + maID;
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject chudeObject = response.getJSONObject(i);
                                    Product cdd = new Product(chudeObject.getString("proID"), chudeObject.getString("proName"), chudeObject.getString("proPrice"), chudeObject.getString("proDetailPhoto1"), chudeObject.getString("proDetail"), "1");
                                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.urlpostorder, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Toast.makeText(PaymentActivity.this, response, Toast.LENGTH_SHORT).show();
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(PaymentActivity.this, "loi" + error + "", Toast.LENGTH_SHORT).show();
                                        }
                                    }) {
                                        @Nullable
                                        @Override
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            Map<String, String> params = new HashMap<String, String>();
                                            params.put("orderID", cdd.proID.toString() + 1);
                                            params.put("proID", cdd.proID.toString());
                                            params.put("proPhoto", cdd.proPhoto.toString());
                                            params.put("proName", cdd.proName.toString());
                                            params.put("cusID", ma.toString());
                                            params.put("cusName", tenkh.getText().toString());
                                            params.put("cusPhone", sdt.getText().toString());
                                            params.put("cusEmail", "a@gmail.com");
                                            params.put("cusAddress", diachi.getText().toString());
                                            params.put("proPrice", cdd.proPrice.toString());
                                            params.put("proQuantity", cdd.proQuantity);
                                            params.put("proMoney", cdd.proPrice.toString());
                                            params.put("orderStatus", "Đang đặt hàng");
                                            return params;
                                        }
                                    };
                                    requestQueue.add(stringRequest);
                                } catch (JSONException e) {
                                    Toast.makeText(getApplicationContext(), "loi" + e.toString(), Toast.LENGTH_SHORT).show();
                                }

                            }

                        }
                    };
                    Response.ErrorListener thatbai = new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "that bai" + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    };
                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, thanhcong, thatbai);
                    requestQueue.add(jsonArrayRequest);
                }
                else
                {
                    String url= Server.urlgetcart+"?cusID="+cusID;
                    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                    Response.Listener<JSONArray>thanhcong=new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            for(int i=0;i<response.length();i++)
                            {
                                try {
                                    JSONObject productObject=response.getJSONObject(i);
                                    Cart_class cdd=new Cart_class(productObject.getString("cartID"),productObject.getString("proID"),productObject.getString("proPhoto"),productObject.getString("proName"),productObject.getString("cusID"),productObject.getString("proPrice"),productObject.getString("proQuantity"),productObject.getString("proMoney"));

                                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.urlpostorder, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Toast.makeText(PaymentActivity.this, response, Toast.LENGTH_SHORT).show();
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(PaymentActivity.this, "loi" + error + "", Toast.LENGTH_SHORT).show();
                                        }
                                    }) {
                                        @Nullable
                                        @Override
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            Map<String, String> params = new HashMap<String, String>();
                                            params.put("orderID", cdd.proID.toString() + 1);
                                            params.put("proID", cdd.proID.toString());
                                            params.put("proPhoto", cdd.proPhoto.toString());
                                            params.put("proName", cdd.proName.toString());
                                            params.put("cusID", ma.toString());
                                            params.put("cusName", tenkh.getText().toString());
                                            params.put("cusPhone", sdt.getText().toString());
                                            params.put("cusEmail", email.getText().toString());
                                            params.put("cusAddress", diachi.getText().toString());
                                            params.put("proPrice", cdd.proPrice.toString());
                                            params.put("proQuantity", cdd.proQuantity);
                                            params.put("proMoney", cdd.proPrice.toString());
                                            params.put("orderStatus", "Đang đặt hàng");
                                            return params;
                                        }
                                    };
                                    requestQueue.add(stringRequest);
                                }
                                catch (JSONException e)
                                {
                                    Toast.makeText(getApplicationContext(),"loi"+e.toString(),Toast.LENGTH_SHORT).show();
                                }
                            }

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
        });
    }

    public void LoadUser(String ma)
    {
        String url= Server.urlgetalluser+"?cusPhone="+ma;
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        Response.Listener<JSONArray>thanhcong=new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject chudeObject=response.getJSONObject(i);
                        User_class cd=new User_class(chudeObject.getString("cusID"),chudeObject.getString("cusName"),chudeObject.getString("cusPhone"),chudeObject.getString("cusEmail"),chudeObject.getString("cusPassword"),chudeObject.getString("cusAddress"),chudeObject.getString("cusPhoto"));
                        tenkh.setText(cd.cusName);
                        diachi.setText(cd.cusAddress);
                        sdt.setText(cd.cusPhone);
                        email.setText(cd.cusEmail);
                    }
                    catch (JSONException e)
                    {
                        Toast.makeText(getApplicationContext(),"loi"+e.toString(),Toast.LENGTH_SHORT).show();
                    }

                }


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

    public void LoadProduct(String ma)
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
                        Product cdd=new Product(chudeObject.getString("proID"),chudeObject.getString("proName"),chudeObject.getString("proPrice"),chudeObject.getString("proDetailPhoto1"),chudeObject.getString("proDetail"),"1");
//                        ten.setText(cd.proName);
//                        gia.setText("Giá sản phẩm :"+cd.proPrice+""+"VND");
//                        tt.setText(cd.proDetail);
                        tongtien.setText(cdd.proPrice+"VND");
                        dataproduct.add(cdd);
                    }
                    catch (JSONException e)
                    {
                        Toast.makeText(getApplicationContext(),"loi"+e.toString(),Toast.LENGTH_SHORT).show();
                    }

                }
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
    public void LoadCart(int cusID)
    {
        String url= Server.urlgetcart+"?cusID="+cusID;
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        Response.Listener<JSONArray>thanhcong=new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject productObject=response.getJSONObject(i);
                        Cart_class cd=new Cart_class(productObject.getString("cartID"),productObject.getString("proID"),productObject.getString("proPhoto"),productObject.getString("proName"),productObject.getString("cusID"),productObject.getString("proPrice"),productObject.getString("proQuantity"),productObject.getString("proMoney"));
                        datacart.add(cd);
                    }
                    catch (JSONException e)
                    {
                        Toast.makeText(getApplicationContext(),"loi"+e.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
                int tongg=0;
                for(int i=0;i<datacart.size();i++)
                {
                    tongg=tongg+Integer.parseInt( datacart.get(i).proMoney);
                }
                tongtien.setText(tongg+"VND");
                //cap nhat lai danh sach
                cartAdapter.notifyDataSetChanged();
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


    public  void insertOrder(Order od)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,Server.urlpostorder, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(PaymentActivity.this,response,Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PaymentActivity.this,"loi" +error+"",Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<String,String>();
                params.put("orderID",od.orderID.toString());
                params.put("proID",od.proID.toString());
                params.put("proPhoto",od.proPhoto.toString());
                params.put("proName",od.proName.toString());
                params.put("cusID",od.cusID.toString());
                params.put("cusName",od.cusName.toString());
                params.put("cusPhone",od.cusPhone.toString());
                params.put("cusEmail",od.cusEmail.toString());
                params.put("cusAddress",od.cusAddress.toString());
                params.put("proPrice",od.proPrice.toString());
                params.put("proQuantity",od.proQuantity);
                params.put("proMoney",od.proMoney.toString());
                params.put("orderStatus",od.orderStatus);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}