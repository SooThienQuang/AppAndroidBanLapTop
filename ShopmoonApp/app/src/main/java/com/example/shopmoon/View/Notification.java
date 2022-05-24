package com.example.shopmoon.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shopmoon.Adapter.NotificationAdapter;
import com.example.shopmoon.Model.Notification_class;
import com.example.shopmoon.R;

import java.util.ArrayList;

public class Notification extends Fragment {
    ArrayList<Notification_class>data=new ArrayList<>();
    ListView listView;
    NotificationAdapter notificationAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_notification,container,false);
        return view;
    }
    //xử lý bên trong
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView=view.findViewById(R.id.lstNoti);
        data.add(new Notification_class(R.drawable.khuyen_mai,"Khuyến mãi","Lap top mới ra mắt giảm từ 15%"));
        data.add(new Notification_class(R.drawable.hot,"Một số sản phẩm hot mới ra mắt gần đây","Lap top mới ra mắt giảm từ 15%"));
        data.add(new Notification_class(R.drawable.qua,"Quà tặng giá trị lên đến 500k dành cho bạn","Lap top mới ra mắt giảm từ 15%"));
        data.add(new Notification_class(R.drawable.sale,"Giảm giá ngay cho khách hàng mới","Lap top mới ra mắt giảm từ 15%"));
        data.add(new Notification_class(R.drawable.saleoff,"Sale off cuối tháng nhanh tay nào bạn ơi !","Lap top mới ra mắt giảm từ 15%"));
        notificationAdapter=new NotificationAdapter(getContext(),data);
        listView.setAdapter(notificationAdapter);
    }
}
