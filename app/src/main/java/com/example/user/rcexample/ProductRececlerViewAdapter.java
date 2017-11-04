package com.example.user.rcexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.rcexample.Utilities.NumberUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 10/21/2017.
 */

public class ProductRececlerViewAdapter extends RecyclerView.Adapter<ProductRececlerViewAdapter.ViewHolder>
{
    List<Product> mList = new ArrayList<>();

    public ProductRececlerViewAdapter()
    {
        //mList = list;
    }

    public void add(Product product)
    {
        mList.add(product);
    }

    @Override
    public int getItemViewType(int position)
    {
        Product product = mList.get(position);
        return product.viewType;
    }

    @Override
    public ProductRececlerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = null;
        switch (viewType)
        {
            case Constant.RICE:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_rice, parent, false);
                return new Rice(itemView);

            case Constant.PHONE:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_phone, parent, false);
                return new Phone(itemView);
        }
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductRececlerViewAdapter.ViewHolder holder, int position)
    {
        Product product = mList.get(position);
        holder.bindData(product);
    }

    @Override
    public int getItemCount()
    {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        public ViewHolder(View itemView)
        {
            super(itemView);


        }

        public void bindData(Product object)
        {

        }
    }

    public class Rice extends ViewHolder
    {
        TextView txtRiceName; TextView txtRicePrice;

        public Rice(View itemView)
        {
            super(itemView);
            txtRiceName = (TextView) itemView.findViewById(R.id.txtRiceName);
            txtRicePrice = (TextView) itemView.findViewById(R.id.txtRicePrice);
        }

        public void bindData(Product product)
        {
            txtRiceName.setText(product.name);
            String price = NumberUtility.formatNumber(product.price);
            txtRicePrice.setText("Giá: " + price);
        }
    }

    public class Phone extends ViewHolder
    {
        TextView txtName, txtPrice;
        public Phone(View itemView)
        {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtPhoneName);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPhonePrice);
        }

        public void bindData(Product product)
        {
            txtName.setText(product.name);
            String price = NumberUtility.formatNumber(product.price);
            txtPrice.setText("Giá: " + price);
        }
    }
}
