package com.example.user.rcexample.restful;

import android.util.Log;

import com.example.user.rcexample.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 11/08/2017.
 */

public class ProductApi
{
    static ProductCallbackListener mListener;
    public static void getProductList()
    {
        try
        {
            RestClient.getRest().getProductList().enqueue(new Callback<List<Product>>()
            {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response)
                {
                    try
                    {
                        if (response.isSuccessful())
                        {
                            if (mListener != null)
                            {
                                List<Product> list = response.body();
                                mListener.onGetProductList(list);
                            }
                        }
                        else
                        {
                            //lỗi không có kết quả
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t)
                {
                    try
                    {
                        Log.e("getProductList", "failed");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void getProductById(int id)
    {
        try
        {
            RestClient.getRest().getProductById(id).enqueue(new Callback<Product>()
            {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response)
                {
                    try
                    {
                        if (response.isSuccessful())
                        {
                            Product product = response.body();
                        }
                        else
                        {
                            //lỗi không có kết quả
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t)
                {
                    try
                    {
                        Log.e("getFileCheckSum", "failed");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // insert
    public static void InsertOneProduct()
    {
        try
        {
            Product product = new Product();
            product.productname = "AAA";
            product.productprice = 15000;
            product.productimageurl = "abc.jpg";

            Gson gson = new GsonBuilder().create();
            JsonObject postObject = gson.toJsonTree(product).getAsJsonObject();

            RestClient.getRest().insertOneProduct(postObject).enqueue(new Callback<String>()
            {
                @Override
                public void onResponse(Call<String> call, Response<String> response)
                {
                    try
                    {
                        if (response.isSuccessful())
                        {
                            int a = 0;
                        }
                        else
                        {
                            //lỗi không có kết quả
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t)
                {
                    try
                    {
                        Log.e("getFileCheckSum", "failed");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void InsertManyProduct()
    {
        try
        {
            List<Product> productList = new ArrayList<>();
            Product product = new Product();
            product.productname = "CCC";
            product.productprice = 15000;
            product.productimageurl = "abc.jpg";
            productList.add(product);

            product = new Product();
            product.productname = "BBB";
            product.productprice = 20000;
            product.productimageurl = "xyz.jpg";
            productList.add(product);


            Gson gson = new GsonBuilder().create();
            JsonArray postArray = gson.toJsonTree(productList).getAsJsonArray();

            RestClient.getRest().insertManyProduct(postArray).enqueue(new Callback<String>()
            {
                @Override
                public void onResponse(Call<String> call, Response<String> response)
                {
                    try
                    {
                        if (response.isSuccessful())
                        {
                            int a = 0;
                        }
                        else
                        {
                            //lỗi không có kết quả
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t)
                {
                    try
                    {
                        Log.e("getFileCheckSum", "failed");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // update
    public static void UpdateProduct()
    {
        try
        {
            Product product = new Product();
            product.productid = 11;
            product.productname = "Ti";
            product.productprice = 100000;
            product.productimageurl = "abc.jpg";





            Gson gson = new GsonBuilder().create();
            JsonObject postObject = gson.toJsonTree(product).getAsJsonObject();

            RestClient.getRest().updateProduct(postObject).enqueue(new Callback<String>()
            {
                @Override
                public void onResponse(Call<String> call, Response<String> response)
                {
                    try
                    {
                        if (response.isSuccessful())
                        {
                            int a = 0;
                        }
                        else
                        {
                            //lỗi không có kết quả
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t)
                {
                    try
                    {
                        Log.e("getFileCheckSum", "failed");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void deleteById(int id)
    {
        try
        {
            RestClient.getRest().deleteProduct(id).enqueue(new Callback<String>()
            {
                @Override
                public void onResponse(Call<String> call, Response<String> response)
                {
                    try
                    {
                        if (response.isSuccessful())
                        {

                        }
                        else
                        {
                            //lỗi không có kết quả
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t)
                {
                    try
                    {
                        Log.e("getFileCheckSum", "failed");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void setOnProductCallbackListener(ProductCallbackListener listener)
    {
        mListener = listener;
    }

    public interface ProductCallbackListener
    {
        void onGetProductList(List<Product> list);
    }
}


