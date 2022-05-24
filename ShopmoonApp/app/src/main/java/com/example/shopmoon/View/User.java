package com.example.shopmoon.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shopmoon.Adapter.NotificationAdapter;
import com.example.shopmoon.MainActivity;
import com.example.shopmoon.Model.Notification_class;
import com.example.shopmoon.Model.Product;
import com.example.shopmoon.MuaHangActivity;
import com.example.shopmoon.R;
import com.example.shopmoon.Server.Server;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User extends Fragment {
    BottomNavigationView bottomNavigationViewmuahang;
    RequestQueue requestQueue;
    SharedPreferences luutru;
    ArrayList<Notification_class> data=new ArrayList<>();
    ListView listView;
    NotificationAdapter notificationAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        return view;
    }

    //xử lý bên trong
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView=view.findViewById(R.id.lstuser);
        data.add(new Notification_class(R.drawable.tim,"Đã thích gần đây","Laptop dell"));
        data.add(new Notification_class(R.drawable.xemganday,"Sản phẩm đã xem gần đây","Lap top mới ra mắt giảm từ 15%"));
        data.add(new Notification_class(R.drawable.danhgiacuatoi,"Các đánh giá của bạn","Lap top mới ra mắt giảm từ 15%"));
        data.add(new Notification_class(R.drawable.lienheshop,"Liên hệ ngay","Mail :honghac@gmail.com"));
        data.add(new Notification_class(R.drawable.trogiup,"Bạn đang gặp vấn đề ! ","Trợ giúp"));
        notificationAdapter=new NotificationAdapter(getContext(),data);
        listView.setAdapter(notificationAdapter);


        luutru = getActivity().getSharedPreferences("savefile", Context.MODE_PRIVATE);
        requestQueue = Volley.newRequestQueue(getContext());
        bottomNavigationViewmuahang = view.findViewById(R.id.bottomnavigationmuahang);
        bottomNavigationViewmuahang.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.choxacnhan:
                        Intent intent = new Intent(getActivity().getApplication(), MuaHangActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }
}
