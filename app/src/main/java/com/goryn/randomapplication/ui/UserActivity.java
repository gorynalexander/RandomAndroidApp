package com.goryn.randomapplication.ui;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.goryn.randomapplication.R;
import com.goryn.randomapplication.models.Result;
import com.goryn.randomapplication.models.UserInfo;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserActivity extends AppCompatActivity {
    private UserInfo user;

    private CircleImageView ivAvatar;
    private TextView tvBDay;
    private TextView tvGender;
    private TextView tvState;
    private TextView tvCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        Intent intent = getIntent();
        String userJson = intent.getStringExtra("user");
        user = new Gson().fromJson(userJson, UserInfo.class);
        Toast.makeText(this, user.getResults().get(0).getName().getFirst(), Toast.LENGTH_SHORT).show();

        initUI();

    }

    private void initUI() {
        ivAvatar = (CircleImageView) findViewById(R.id.ivAvatar);
        tvBDay = (TextView) findViewById(R.id.tvBday);
        tvGender = (TextView) findViewById(R.id.tvGender);
        tvState = (TextView) findViewById(R.id.tvState);
        tvCity = (TextView) findViewById(R.id.tvCity);
        Result result = user.getResults().get(0);

//        String dateString = "03/26/2012 11:49:00 AM";
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
//        Date convertedDate = new Date();
//        try {
//            convertedDate = dateFormat.parse(dateString);
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        System.out.println(convertedDate);

        getSupportActionBar().setTitle(result.getName().getTitle() + " " + result.getName().getFirst()+ " " + result.getName().getLast());

        Picasso.with(this)
                .load(result.getPicture().getLarge())
                .resize(300, 300)
                .into(ivAvatar);
        tvBDay.setText(result.getDob());
        tvGender.setText(result.getGender());
        tvState.setText(result.getLocation().getState());
        tvCity.setText(result.getLocation().getCity());

    }
}
