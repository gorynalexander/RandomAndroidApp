package com.goryn.randomapplication.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.goryn.randomapplication.UserService;
import com.goryn.randomapplication.models.User;
import com.goryn.randomapplication.R;
import com.goryn.randomapplication.adapters.UserAdapter;
import com.goryn.randomapplication.models.UserInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvUserList;
    private UserAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();

        getDataFromApi();
    }

    private void getDataFromApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService service = retrofit.create(UserService.class);
        Call<UserInfo> call = service.getUserInfo();
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                UserInfo userInfo = response.body();
                Toast.makeText(MainActivity.this, userInfo.getResults().get(0).getName().getTitle() , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Toast.makeText(MainActivity.this, "sosat", Toast.LENGTH_SHORT).show();
            }
        });
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
        list.add(new User("kek2"));
        list.add(new User("kek1"));
        list.add(new User("kek2"));
        list.add(new User("kek2"));
        list.add(new User("kek2"));
        list.add(new User("kek2"));
        list.add(new User("kek2"));
        list.add(new User("kek2"));
        return list;
    }
}
