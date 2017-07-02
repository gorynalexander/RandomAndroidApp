package com.goryn.randomapplication.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.goryn.randomapplication.R;
import com.goryn.randomapplication.models.UserInfo;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent intent = getIntent();
        String userJson = intent.getStringExtra("user");
        UserInfo user = new Gson().fromJson(userJson, UserInfo.class);
        Toast.makeText(this, user.getResults().get(0).getName().getFirst(), Toast.LENGTH_SHORT).show();
    }
}
