package com.example.user.rcexample.Utilities;

/**
 * Created by user on 10/30/2017.
 */

public class NumberUtility
{
    public static String formatNumber(int value)
    {
        return  String.format("%,d đồng", value);
    }
}
