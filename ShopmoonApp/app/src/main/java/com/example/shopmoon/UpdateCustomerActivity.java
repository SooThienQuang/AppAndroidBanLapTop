package com.example.shopmoon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shopmoon.Server.Server;

import java.util.HashMap;
import java.util.Map;

public class UpdateCustomerActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    EditText pass1,pass2;
    SharedPreferences luutru;
    Button change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customer);
        pass1=findViewById(R.id.txtchangepass1);
        pass2=findViewById(R.id.txtchangepass2);
        change=findViewById(R.id.btnchangepas);
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        luutru= getSharedPreferences("savefile", Context.MODE_PRIVATE);
        String cusID=luutru.getString("us","0");
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pass1.getText().toString().equals(pass2.getText().toString()))
                {
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.urlupdateuser, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            String s=response.trim();
                            Toast.makeText(getApplicationContext(), response,Toast.LENGTH_SHORT).show();
                            if(s.equals("Đổi mật khẩu thành công"))
                            {
                                Intent intent=new Intent(UpdateCustomerActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),"loi" +error+"",Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String>params=new HashMap<String,String>();
                            params.put("cusPhone",cusID);
                            params.put("cusPassword",pass1.getText().toString());
                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Password có lỗi xảy ra ?",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}