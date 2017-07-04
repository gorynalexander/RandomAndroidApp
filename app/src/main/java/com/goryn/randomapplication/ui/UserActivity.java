package com.goryn.randomapplication.ui;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        Intent intent = getIntent();
        String userJson = intent.getStringExtra("user");
        user = new Gson().fromJson(userJson, UserInfo.class);
        initUI();

    }

    private void initUI() {
        CircleImageView ivAvatar = (CircleImageView) findViewById(R.id.ivAvatar);
        TextView tvBDay = (TextView) findViewById(R.id.tvBday);
        TextView tvGender = (TextView) findViewById(R.id.tvGender);
        TextView tvState = (TextView) findViewById(R.id.tvState);
        TextView tvCity = (TextView) findViewById(R.id.tvCity);
        TextView tvStreet = (TextView) findViewById(R.id.tvStreet);
        TextView tvPostcode = (TextView) findViewById(R.id.tvPostcode);
        TextView tvEmail = (TextView) findViewById(R.id.tvEmail);
        TextView tvPhone = (TextView) findViewById(R.id.tvPhone);
        TextView tvUsername = (TextView) findViewById(R.id.tvUsername);
        TextView tvPassword = (TextView) findViewById(R.id.tvPassword);
        TextView tvSalt = (TextView) findViewById(R.id.tvSalt);
        TextView tvMD5 = (TextView) findViewById(R.id.tvMD5);
        TextView tvSha1 = (TextView) findViewById(R.id.tvSha1);
        TextView tvSha256 = (TextView) findViewById(R.id.tvSha256);


        Result result = user.getResults().get(0);
        String dob = result.getDob().substring(0, 11);


        try {
            getSupportActionBar().setTitle(toUpperCase(result.getName().getTitle()) + " " + toUpperCase(result.getName().getFirst())+ " " + toUpperCase(result.getName().getLast()));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            Picasso.with(this)
                    .load(result.getPicture().getLarge())
                    .resize(300, 300)
                    .into(ivAvatar);
            tvBDay.setText(dob);
            tvGender.setText(toUpperCase(result.getGender()));
            tvState.setText(toUpperCase(result.getLocation().getState()));
            tvCity.setText(toUpperCase(result.getLocation().getCity()));
            tvStreet.setText(result.getLocation().getStreet());
            tvPostcode.setText(String.valueOf(result.getLocation().getPostcode()));
            tvEmail.setText(result.getEmail());
            tvPhone.setText(result.getPhone());
            tvUsername.setText(result.getLogin().getUsername());
            tvPassword.setText(result.getLogin().getPassword());
            tvMD5.setText(result.getLogin().getMd5());
            tvSha1.setText(result.getLogin().getSha1());
            tvSha256.setText(result.getLogin().getSha256());
            tvSalt.setText(result.getLogin().getSalt());
        } catch (NullPointerException ignored){}


    }

    private String toUpperCase(String data){
        if (data.contains(" ")){
            // for states which consist of 2 words
            for (int i = 0; i<data.length(); i++){
                if (data.charAt(i) == ' '){
                    return data.substring(0, 1).toUpperCase() + data.substring(1, i+1) + data.substring(i+1, i+2).toUpperCase() + data.substring(i+2);
                }
            }
        }
        return data.substring(0,1).toUpperCase() + data.substring(1);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            this.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
