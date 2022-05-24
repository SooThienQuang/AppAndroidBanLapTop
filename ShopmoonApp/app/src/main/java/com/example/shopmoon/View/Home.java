package com.example.shopmoon.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.shopmoon.Adapter.ProducerTypeAdapter;
import com.example.shopmoon.Adapter.ProductAdapter;
import com.example.shopmoon.Model.ProducerType;
import com.example.shopmoon.Model.Product;
import com.example.shopmoon.Model.slide;
import com.example.shopmoon.R;
import com.example.shopmoon.Server.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    //khaio bao mang
    ArrayList<ProducerType> data=new ArrayList<>();
    ArrayList<Product> dataproduct=new ArrayList<>();


    //khai bao adapter
    ProducerTypeAdapter chuDeAdapter;
    ProductAdapter productAdapter;
    //anh xa
    RecyclerView rvproducer;
    RecyclerView rvproduct;
    ViewFlipper viewFlipper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        return view;
    }
    //xử lý bên trong
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //anhxa
        rvproducer=view.findViewById(R.id.rvproducer);
        rvproduct=view.findViewById(R.id.rvproduct);
        viewFlipper=view.findViewById(R.id.viewfliperslide);

        //su kieon onlick

        //add du lieu
        chuDeAdapter=new ProducerTypeAdapter(getContext(),data);
        rvproducer.setAdapter(chuDeAdapter);
        rvproducer.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));

        rvproduct.setHasFixedSize(true);
        productAdapter=new ProductAdapter(getContext(),dataproduct);
        rvproduct.setLayoutManager(new GridLayoutManager(getContext(),2));
        rvproduct.setAdapter(productAdapter);
        LoadChuDe();
        LoadProduct();
        loadSlide();

    }
    public void LoadChuDe()
    {
        String url= Server.urllayhangsanxuat;
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        Response.Listener<JSONArray>thanhcong=new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject chudeObject=response.getJSONObject(i);
                        ProducerType cd=new ProducerType(chudeObject.getString("proProducer"),chudeObject.getString("proProducerName"),chudeObject.getString("proProducerPhoto"));
                        data.add(cd);

                    }
                    catch (JSONException e)
                    {
                        Toast.makeText(getContext(),"loi"+e.toString(),Toast.LENGTH_SHORT).show();
                    }

                }
                //cap nhat lai danh sach
                chuDeAdapter.notifyDataSetChanged();

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
                Toast.makeText(getContext(),"that bai"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url,thanhcong,thatbai);
        requestQueue.add(jsonArrayRequest);
    }

public  void loadSlide()
{

//    viet lay hinh tu server ve
    ArrayList<String>manquangcao=new ArrayList<>();
    manquangcao.add(Server.urlSlides+"ipxanh-720-220-720x220.png");
    manquangcao.add(Server.urlSlides+"720-220-720x220-162.png");
    manquangcao.add(Server.urlSlides+"sea-aseri-720-220-720x220-4.png");
    for(int i=0;i< manquangcao.size();i++)
    {
        ImageView hinh=new ImageView(getContext());
        Picasso.get().load(manquangcao.get(i)).into(hinh);
        hinh.setScaleType(ImageView.ScaleType.FIT_XY);
        viewFlipper.addView(hinh);
    }
    viewFlipper.setAutoStart(true);
    viewFlipper.setFlipInterval(5000);
}



    public void LoadProduct()
    {
        String url= Server.urlProduct;
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
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
                        Toast.makeText(getContext(),"loi"+e.toString(),Toast.LENGTH_SHORT).show();
                    }

                }
                //cap nhat lai danh sach
                productAdapter.notifyDataSetChanged();
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
