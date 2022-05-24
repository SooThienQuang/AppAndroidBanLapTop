package com.example.shopmoon.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.shopmoon.LoginActivity;
import com.example.shopmoon.MainActivity;
import com.example.shopmoon.Model.Cart_class;
import com.example.shopmoon.Model.Product;
import com.example.shopmoon.PaymentActivity;
import com.example.shopmoon.R;
import com.example.shopmoon.Server.Server;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cart extends Fragment {
    ArrayList<Cart_class> datacart=new ArrayList<>();
    //khai bao adapter
    CartAdapter cartAdapter;
    RecyclerView rvcart;
    SharedPreferences luutru;
    TextView tongtien;
    Button thanhtoan;
    RequestQueue requestQueue;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_cart,container,false);
        return view;
    }
    //xử lý bên trong
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvcart=view.findViewById(R.id.rvcart);
        thanhtoan=view.findViewById(R.id.btnthanhtoan);
        tongtien=view.findViewById(R.id.carttongtien);
        cartAdapter=new CartAdapter(getContext(),datacart);
        rvcart.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        rvcart.setAdapter(cartAdapter);
        luutru= getActivity().getSharedPreferences("savefile", Context.MODE_PRIVATE);
        int cusID= Integer.parseInt( luutru.getString("us","0"));
        LoadCart(cusID);


        thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(getContext(), PaymentActivity.class);
               startActivity(intent);

            }
        });
    }
    public void LoadCart(int cusID)
    {
        String url= Server.urlgetcart+"?cusID="+cusID;
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
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
                        Toast.makeText(getContext(),"loi"+e.toString(),Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getContext(),"that bai"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url,thanhcong,thatbai);
        requestQueue.add(jsonArrayRequest);
    }
}
