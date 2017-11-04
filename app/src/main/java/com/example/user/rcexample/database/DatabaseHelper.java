package com.example.user.rcexample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.location.LocationManager;

import com.example.user.rcexample.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Manh on 07/11/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static DatabaseHelper sInstance;
    private SQLiteDatabase sqlDB = this.getWritableDatabase();

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "test.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";
    //ChatHistory
    private static final String SQL_RICE_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + DatabaseContract.Rice.TABLE_NAME + " (" +
            DatabaseContract.Rice.COL_CODE + TEXT_TYPE + " PRIMARY KEY," +
            DatabaseContract.Rice.COL_NAME + TEXT_TYPE + COMMA_SEP +
            DatabaseContract.Rice.COL_PRICE + INTEGER_TYPE + " )";
    private static final String SQL_RICE_DELETE = "DROP TABLE IF EXISTS " + DatabaseContract.Rice.TABLE_NAME;


    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context)
    {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null)
        {
            sInstance = new DatabaseHelper(context);
        }
        return sInstance;
    }

    public void closeDatabase()
    {
        try
        {
            if (sqlDB != null)
                sqlDB.close();

            if (sInstance != null)
                sInstance.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void onCreate(SQLiteDatabase db)
    {
        try
        {
            if (db == null) return;

            db.execSQL(SQL_RICE_CREATE_ENTRIES);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        try
        {
            onUpgrade(db, oldVersion, newVersion);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    // Getting All Chat History
    public List<Product> selectProduct()
    {
        List<Product> productList = new ArrayList<Product>();
        try
        {
            String query = "SELECT * FROM " +  DatabaseContract.Rice.TABLE_NAME;

            if (sqlDB == null)
                sqlDB = this.getWritableDatabase();
            Cursor cursor = sqlDB.rawQuery(query, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst())
            {
                do
                {
                    Product product = new Product();
                    product.code = cursor.getString(0);
                    product.name = cursor.getString(1);
                    product.price = cursor.getInt(2);
                    productList.add(product);

                } while (cursor.moveToNext());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return productList;
    }

    // insert single contact
    public void insertProduct(Product product)
    {
        try
        {
            if (sqlDB == null) sqlDB = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(DatabaseContract.Rice.COL_CODE, product.code);
            values.put(DatabaseContract.Rice.COL_NAME, product.name);
            values.put(DatabaseContract.Rice.COL_PRICE, product.price);

            sqlDB.insert(DatabaseContract.Rice.TABLE_NAME, null, values);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void updateProduct(Product product)
    {
        try
        {
            if (sqlDB == null)
                sqlDB = this.getWritableDatabase();

            String query = "UPDATE " + DatabaseContract.Rice.TABLE_NAME +
                    " SET " + DatabaseContract.Rice.COL_CODE + " = '" + product.code + "', " +
                    DatabaseContract.Rice.COL_NAME + " = '" + product.name + "', " +
                    DatabaseContract.Rice.COL_PRICE + " = " + product.price  +
                    " WHERE " + DatabaseContract.Rice.COL_CODE + " = '" + product.code + "'";
            sqlDB.execSQL(query);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void deleteProduct(String code)
    {
        try
        {
            if (sqlDB == null)
                sqlDB = this.getWritableDatabase();
            String query = "DELETE FROM " + DatabaseContract.Rice.TABLE_NAME +
                    " WHERE " + DatabaseContract.Rice.COL_CODE + " = '" + code + "'";

            sqlDB.execSQL(query);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
