package com.example.user.rcexample;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.rcexample.database.DatabaseHelper;
import com.example.user.rcexample.restful.RestClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductFragment extends Fragment
{
    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private ProductRececlerViewAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DatabaseHelper mDb;
    List<Product> mList = new ArrayList<>();
    public ProductFragment()
    {
        // Required empty public constructor
    }

    public static ProductFragment newInstance()
    {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        //getProductList();
        //getProductById(3);
        //InsertOneProduct();
        //InsertManyProduct();
        //UpdateProduct();
        deleteById(11);
        List<Product> list = new ArrayList<>();
        mDb = DatabaseHelper.getInstance(getContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 10, true, true));
        adapter = new ProductRececlerViewAdapter();
        recyclerView.setAdapter(adapter);

        //dữ liệu giả
        //thêm mặt hàng gạo thơm lài vô adapter
        /*Product product = new Product();
        product.code = "GAOLAI";
        product.name = "Gạo thơm lài";
        product.price = 15000;
        product.viewType = Constant.RICE;
        adapter.add(product);
        mDb.insertProduct(product);
        List<Product> productList = mDb.selectProduct();

        //thêm mặt hàng đt samsunghẩu vô recyclerview
        product = new Product();
        product.name = "Samsung note 8";
        product.price = 15000000;
        product.viewType = Constant.PHONE;
        adapter.add(product);

        //thêm mặt hàng gạo tấm thường vô adapter
        product = new Product();
        product.name = "Gạo tấm thường";
        product.price = 10000;
        product.viewType = Constant.RICE;
        adapter.add(product);

        //thêm mặt hàng đt iphone vô recyclerview
        product = new Product();
        product.name = "iPhone 7 plus";
        product.price = 18000000;
        product.viewType = Constant.PHONE;
        adapter.add(product);

        //thêm mặt hàng gạo tấm xuất khẩu vô adapter
        product = new Product();
        product.name = "Gạo tấm xuất khẩu";
        product.price = 30000;
        product.viewType = Constant.RICE;
        adapter.add(product);

        //thêm mặt hàng đt nokia vô recyclerview
        product = new Product();
        product.name = "Nokia 3";
        product.price = 10000000;
        product.viewType = Constant.PHONE;
        adapter.add(product);

        //thêm mặt hàng gạo lứt vô adapter
        product = new Product();
        product.name = "Gạo lứt";
        product.price = 20000;
        product.viewType = Constant.RICE;
        adapter.add(product);

        //thêm mặt hàng đt nokia vô recyclerview
        product = new Product();
        product.name = "Nokia 3";
        product.price = 10000000;
        product.viewType = Constant.PHONE;
        adapter.add(product);

        //thêm mặt hàng gạo huu cơ vô adapter
        product = new Product();
        product.name = "Gạo hữu cơ";
        product.price = 50000;
        product.viewType = Constant.RICE;
        adapter.add(product);

        adapter.notifyDataSetChanged();*/
    }
    // select
    private void getProductList()
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
                            mList = response.body();
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

    private void getProductById(int id)
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
    private void InsertOneProduct()
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

    private void InsertManyProduct()
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
    private void UpdateProduct()
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

    private void deleteById(int id)
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

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener)
        {
            mListener = (OnFragmentInteractionListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
