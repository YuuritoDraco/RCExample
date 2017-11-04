package com.example.user.rcexample.database;

import android.provider.BaseColumns;

/**
 * Created by Manh on 07/11/2016.
 */

public class DatabaseContract
{
    public static class Rice implements BaseColumns
    {
        public static final String TABLE_NAME = "Rice";
        public static final String COL_CODE = "code";
        public static final String COL_NAME = "name";
        public static final String COL_PRICE = "price";
    }
}
