package com.example.user.rcexample.restful;

import com.example.user.rcexample.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 11/04/2017.
 */

public class RestClient
{
    private static RestFul restService;

    public RestClient()
    {
    }

    public static synchronized RestFul getRest()
    {
        if (restService == null)
        {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();

            restService = retrofit.create(RestFul.class);
        }
        return restService;
    }
}
