package com.example.shopmoon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class CreateAccountActivity extends AppCompatActivity {

    EditText mail, pw,name,phone,address;
    Button btnSignIn;
    RequestQueue requestQueue;
    String urls=Server.urlpostuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        mail = findViewById(R.id.txtmail);
        pw = findViewById(R.id.txtpassword);
        name=findViewById(R.id.txtname);
        phone=findViewById(R.id.txtphone);
        address=findViewById(R.id.txtaddress);
        btnSignIn = findViewById(R.id.btnregisterr);
        requestQueue= Volley.newRequestQueue(this);
        //tạo ra sự kiện cho buuton btnSingin bên trang này
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest=new StringRequest(Request.Method.POST,urls, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(CreateAccountActivity.this,response,Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CreateAccountActivity.this,"loi" +error+"",Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>params=new HashMap<String,String>();
                        String ma=phone.getText().toString();
                        String user=name.getText().toString();
                        String email=mail.getText().toString();
                        String pho=phone.getText().toString();
                        String pass=pw.getText().toString();
                        String addre=address.getText().toString();
                        String photo="user.png";
                        params.put("cusID",ma);
                        params.put("cusName",user);
                        params.put("cusPhone",pho);
                        params.put("cusEmail",email);
                        params.put("cusPassword",pass);
                        params.put("cusAddress",addre);
                        params.put("cusPhoto",photo);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
                Intent intent = new Intent(CreateAccountActivity.this,
                        LoginActivity.class);
                intent.putExtra("username",
                        phone.getText().toString());
                intent.putExtra("password",
                        pw.getText().toString());
                //tạo ra 1 thuộc tính resultcode để xác nhận phía trang bên kia
                setResult(101, intent);
                finish();
            }
        });
    }
}

