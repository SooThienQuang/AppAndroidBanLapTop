package com.example.shopmoon;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
import com.example.shopmoon.Model.Customer;
import com.example.shopmoon.Model.ProducerType;
import com.example.shopmoon.Server.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    int requestcode=111;
    //tạo ra 2 đối tượng tương ứng
    EditText us,pwd;
    CheckBox chkluu;
    Button btnlogin;
    TextView bnregister;
    SharedPreferences luutru;
    RequestQueue requestQueue;
    ActivityResultLauncher<Intent> activityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //tìm và gán thuộc tính vào các id tương ứng
        requestQueue= Volley.newRequestQueue(this);
        us=findViewById(R.id.txtphonelogin);
        pwd=findViewById(R.id.txtpasswordlogin);
        btnlogin=findViewById(R.id.btnLogin);
        //tìm xem phía trang design có button tên login
        chkluu=findViewById(R.id.checkLuu);
        bnregister=findViewById(R.id.txtregister);
        //tạo ra sự kiện lắng nghe cho nó
        //khi click vào sẽ ra sự kiện chuyển sang trang login
        bnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivityForResult(intent, requestcode);
            }
        });

        luutru=getSharedPreferences("savefile", Context.MODE_PRIVATE);
        boolean coluukhong= luutru.getBoolean("luuthongtin",false);
        if(coluukhong)
        {
            us.setText(luutru.getString("us","chua co gi"));
            pwd.setText(luutru.getString("pwd","chua co gi"));
            chkluu.setChecked(true);
        }
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest=new StringRequest(Request.Method.POST,Server.urlgetuser, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String s=response.trim();
                        if(s.equals("Đăng nhập thành công"))
                        {
                            SharedPreferences.Editor editor=luutru.edit();
                    //check box lưu mật khẩu được check bắt đầu lưu trữ dữ liệu
                    if(chkluu.isChecked())
                    {
                        editor.putString("us",us.getText().toString());
                        editor.putString("pwd",pwd.getText().toString());
                    }
                    //lưu lại trạng thái check lưu mật khẩu
                    editor.putBoolean("luuthongtin",chkluu.isChecked());
                    editor.commit();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                            Toast.makeText(LoginActivity.this,response,Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(LoginActivity.this,response,Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,"loi" +error+"",Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>params=new HashMap<String,String>();
                        String email=us.getText().toString();
                        String pho=pwd.getText().toString();
                        params.put("cusPhone",email);
                        params.put("cusPassword",pho);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);

//                if(us.getText().toString().equals("1")&&pwd.getText().toString().equals("1"))
//                {
//                    SharedPreferences.Editor editor=luutru.edit();
//                    //check box lưu mật khẩu được check bắt đầu lưu trữ dữ liệu
//                    if(chkluu.isChecked())
//                    {
//                        editor.putString("us",us.getText().toString());
//                        editor.putString("pwd",pwd.getText().toString());
//                    }
//                    //lưu lại trạng thái check lưu mật khẩu
//                    editor.putBoolean("luuthongtin",chkluu.isChecked());
//                    editor.commit();
//                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
//                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
//                    startActivity(intent);
//                }
//                else
//                {
//                    Toast.makeText(getApplicationContext(),"Login fail",Toast.LENGTH_SHORT).show();
//                }

            }
        });
        //kiểm tra nut check



    }
    @Override
    //nhận dữ liệu
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent
            data) {
        super.onActivityResult(requestCode, resultCode, data);
        //kiểm tra xem result code đã truyền từ trang create account
        if(resultCode == 101){
            //nếu hợp lệ thì lấy ra 2 giá trị đã truyền theo id và gán chúng lên edit text đã khởi tạo trên kia
            us.setText(data.getStringExtra("username"));
            pwd.setText(data.getStringExtra("password"));
        }
    }

    public boolean getUser(String user,String pass)
    {
        String url= Server.urlgetuser+"?user="+user+"and pass="+pass;
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        Response.Listener<JSONArray>thanhcong=new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject chudeObject=response.getJSONObject(i);
                        Customer cd=new Customer(chudeObject.getString("cusID"),chudeObject.getString("cusName"),chudeObject.getString("cusPhone"),chudeObject.getString("cusEmail"),chudeObject.getString("cusPassword"),chudeObject.getString("cusAddress"),chudeObject.getString("cusPhoto"));
                       if(cd.cusID!=null)
                       {
                           return;
                       }

                    }
                    catch (JSONException e)
                    {
                        Toast.makeText(getApplicationContext(),"loi"+e.toString(),Toast.LENGTH_SHORT).show();
                    }

                }
                //cap nhat lai danh sach

//                for(int i=0;i< data.size();i++)
//                {
//                    ImageView hinh=new ImageView(getContext());
//                    Picasso.get().load(Server.urlhinhanh+data.get(i).proProducerPhoto).into(hinh);
//                    hinh.setScaleType(ImageView.ScaleType.FIT_XY);
//                    viewFlipper.addView(hinh);
//                }

            }
        };
//        viewFlipper.setAutoStart(true);
//        viewFlipper.setFlipInterval(5000);
        Response.ErrorListener thatbai=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"that bai"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url,thanhcong,thatbai);
        requestQueue.add(jsonArrayRequest);
        return false;
    }
}