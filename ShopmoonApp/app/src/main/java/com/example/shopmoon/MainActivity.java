package com.example.shopmoon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopmoon.View.Cart;
import com.example.shopmoon.View.Home;
import com.example.shopmoon.View.Location;
import com.example.shopmoon.View.Notification;
import com.example.shopmoon.View.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{
   Fragment fragment;
    Toolbar toolbar;
    //user
    SharedPreferences luutru;
    ImageView user;
    TextView nameuser;
    //---------
    DrawerLayout drawerLayout;
BottomNavigationView bottomNavigationView;
Menu m1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameuser=findViewById(R.id.nameuser);
        user=findViewById(R.id.imguser);
        luutru=getSharedPreferences("savefile", Context.MODE_PRIVATE);
        nameuser.setText(luutru.getString("us","chua co gi"));
        
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawerlayoutmain);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        loadFragment(new Home());

        NavigationView mNavigationView = (NavigationView) findViewById(R.id.navigatioview2);

        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }


        //hien thi menu an
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id=item.getItemId();
                switch (id)
                {
                    case R.id.giohang:
                        fragment=new Cart();
                        break;
                }
                loadFragment(fragment);
                return true;
            }
        });
        //hien thi thanh menu nam duoi
        bottomNavigationView=findViewById(R.id.bottomnavigation1);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                switch (id)
                {
                    case R.id.home12:
                        fragment=new Home();
                        break;
                    case R.id.notification:
                        fragment=new Notification();
                        break;
                    case R.id.user:
                        fragment=new User();
                        break;
                    case R.id.cartb:
                        fragment=new Cart();
                        break;
                }
                loadFragment(fragment);
                return true;
            }
        });
    }
    public void loadFragment(Fragment f)
    {
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout_main,f);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.giohang,menu);
        return  super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Toast.makeText(this,"navhome",Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.navlogout) {
            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }
        if (id == R.id.navchangepass) {
            Intent intent=new Intent(MainActivity.this,UpdateCustomerActivity.class);
            startActivity(intent);
        }
        return true;
    }
}