package com.goryn.randomapplication.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.goryn.randomapplication.UserService;
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
    private List<UserInfo> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userList = new ArrayList<>();
        createListOfGottenData();
        initRecyclerView();

    }

    private void createListOfGottenData() {
        /*
            В API есть запрос только на 1го юзера, пришлось выдумать подобное
         */
        int i = 0;
        while (i < 5) {
            getDataFromApi();
            i++;
        }
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
                // userInfo.getResults().get(0).getLocation().getCity();
                if (userInfo != null) {
                    userList.add(userInfo);
                    rvAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                t.printStackTrace();
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

    private List<UserInfo> mockData() {
        return userList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemLoadMore:
                createListOfGottenData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
