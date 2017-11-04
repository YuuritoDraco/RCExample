package com.example.user.rcexample;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.rcexample.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements ProductFragment.OnFragmentInteractionListener
{
    ProductFragment productFragment = ProductFragment.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().
                add(R.id.fragment_container, productFragment, "ProductFragment").commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri)
    {

    }
}
