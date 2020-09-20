package com.example.parceltracking2.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.parceltracking2.SQLite.CustomerContract.*;
import com.example.parceltracking2.dto.CustomerDTO;
import com.example.parceltracking2.retrofit.Retrofit2JsonHandler;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Customer.db";
    private static final int DATABASE_VERSION = 2;
    private SQLiteDatabase db;

    public CustomerDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_CUSTOMER_TABLE = "CREATE TABLE " +
                CustomersTable.TABLE_NAME + "(" +
                CustomersTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CustomersTable.COLUMN_NAME + " TEXT, " +
                CustomersTable.COLUMN_PWD + " TEXT, " +
                CustomersTable.COLUMN_ADDRESS + " TEXT " +
                ")";
               db.execSQL(SQL_CREATE_CUSTOMER_TABLE);
               fillCustomerTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           db.execSQL("DROP TABLE IF EXISTS " + CustomersTable.TABLE_NAME);
           onCreate(db);
    }

    private void fillCustomerTable() {
        Retrofit2JsonHandler retrofit2JsonHandler = Retrofit2JsonHandler.retrofit.create(Retrofit2JsonHandler.class);
        Call<List<CustomerDTO>> call = retrofit2JsonHandler.getCustomer();

        call.enqueue(new Callback<List<CustomerDTO>>() {
            @Override
            public void onResponse(Call<List<CustomerDTO>> call, Response<List<CustomerDTO>> response) {
                if(!response.isSuccessful()) {
                    response.code();
                    return;
                }
                List<CustomerDTO> dto = response.body();

                if(dto.get(0) instanceof CustomerDTO){

                    for (CustomerDTO cust : dto) {
                        addCustomer(cust);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<CustomerDTO>> call, Throwable t) {
                t.getMessage();
            }
        });
    }

    private void addCustomer(CustomerDTO cust) {
        ContentValues cv = new ContentValues();
        cv.put(CustomersTable.COLUMN_NAME, cust.getCustName());
        cv.put(CustomersTable.COLUMN_PWD, cust.getCustPwd());
        cv.put(CustomersTable.COLUMN_ADDRESS, cust.getCustAddress());
        db.insert(CustomersTable.TABLE_NAME, null, cv);
    }

    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customerList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c =db.rawQuery("SELECT * FROM " +CustomersTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                CustomerDTO cust = new CustomerDTO();
                cust.setCustId(c.getInt(c.getColumnIndex(CustomersTable._ID)));
                cust.setCustName(c.getString(c.getColumnIndex(CustomersTable.COLUMN_NAME)));
                cust.setCustPwd(c.getString(c.getColumnIndex(CustomersTable.COLUMN_PWD)));
                cust.setCustAddress(c.getString(c.getColumnIndex(CustomersTable.COLUMN_ADDRESS)));
                customerList.add(cust);

            } while (c.moveToNext());
        }
        c.close();
        return customerList;
    }

}
