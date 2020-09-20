package com.example.parceltracking2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.parceltracking2.SQLite.UpdateSQLiteDB;

public class MainActivity extends AppCompatActivity {

    Button custLogin;
    Button drivLogin;
    Button sqliteTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        custLogin = (Button) findViewById(R.id.btnCustLogin);
        drivLogin = (Button) findViewById(R.id.btnDrivLogin);
        sqliteTest = (Button) findViewById(R.id.btnSQLiteTest);

        custLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), CustomerActivity.class);
                startActivity(startIntent);
            }
        });

        drivLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), DriverActivity.class);
                startActivity(startIntent);
            }
        });

        sqliteTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), UpdateSQLiteDB.class);
                startActivity(startIntent);
            }
        });
    }
}
