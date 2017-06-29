package com.goryn.randomapplication.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.goryn.randomapplication.Entity.User;
import com.goryn.randomapplication.R;
import com.goryn.randomapplication.adapter.UserAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvUserList;
    private UserAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
    }

    private void initRecyclerView() {
        rvUserList = (RecyclerView) findViewById(R.id.rvUserList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvUserList.setLayoutManager(layoutManager);

        rvAdapter = new UserAdapter(mockData());
        rvUserList.setAdapter(rvAdapter);

    }

    private List<User> mockData() {
        List<User> list = new ArrayList<>();
        list.add(new User("kek"));
        list.add(new User("kek1"));
        list.add(new User("kek2"));
        return list;
    }
}
