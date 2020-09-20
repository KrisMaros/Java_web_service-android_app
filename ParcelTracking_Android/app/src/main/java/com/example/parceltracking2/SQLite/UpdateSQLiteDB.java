package com.example.parceltracking2.SQLite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.parceltracking2.R;
import com.example.parceltracking2.dto.CustomerDTO;

import java.util.List;

public class UpdateSQLiteDB extends AppCompatActivity {

    private TextView textViewSQLite;
    private List<CustomerDTO> customerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sqlite_db);

        textViewSQLite = findViewById(R.id.textViewSQLite);

        CustomerDbHelper dbHelper = new CustomerDbHelper(this);
        customerList = dbHelper.getAllCustomers();

        for (CustomerDTO cust : customerList) {

            String content ="";

            content += "ID: " + cust.getCustId() + "\n";
            content += "Name : " + cust.getCustName() + "\n";
            content += "Pwd : " + cust.getCustPwd() + "\n";
            content += "Address : " + cust.getCustAddress() + "\n";
            textViewSQLite.append(content);
        }
    }
}
