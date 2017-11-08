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
import com.example.user.rcexample.restful.ProductApi;
import com.example.user.rcexample.restful.RestClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

        //getProductById(3);
        //InsertOneProduct();
        //InsertManyProduct();
        //UpdateProduct();
        //deleteById(11);
        List<Product> list = new ArrayList<>();
        mDb = DatabaseHelper.getInstance(getContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 10, true, true));
        adapter = new ProductRececlerViewAdapter(getContext());
        recyclerView.setAdapter(adapter);

        ProductApi.setOnProductCallbackListener(new ProductApi.ProductCallbackListener()
        {
            @Override
            public void onGetProductList(List<Product> list)
            {
                for(int idx = 0; idx < list.size(); idx++)
                {
                    adapter.add(list.get(idx));
                }
                adapter.notifyDataSetChanged();
            }
        });

        ProductApi.getProductList();

        String md5 = getMD5("abc");
    }

    private String getMD5(String input)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32)
            {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }
    // select


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
