package com.goryn.randomapplication;

import com.goryn.randomapplication.models.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Odinn on 29.06.2017.
 */

public interface UserService {
    @GET("api/")
    Call<UserInfo> getUserInfo();
}
