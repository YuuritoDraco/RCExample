package com.example.user.rcexample.restful;

import com.example.user.rcexample.Product;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by user on 11/04/2017.
 */

public interface RestFul
{
    @GET("product/GetProductList")
    Call<List<Product>> getProductList();

    @GET("product/GetProductById/{id}/")
    Call<Product> getProductById(@Path("id") int productId);

    @POST("product/InsertOneProduct")
    Call<String> insertOneProduct(@Body JsonObject product);

    @POST("product/InsertManyProducts")
    Call<String> insertManyProduct(@Body JsonArray products);

    @PUT("product/UpdateProduct")
    Call<String> updateProduct(@Body JsonObject product);

    @DELETE("product/DeleteProduct/{id}/")
    Call<String> deleteProduct(@Path("id") int productId);
}
