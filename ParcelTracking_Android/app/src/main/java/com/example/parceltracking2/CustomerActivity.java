package com.example.parceltracking2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.parceltracking2.SQLite.CustomerDbHelper;
import com.example.parceltracking2.dto.CustomerDTO;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

public class CustomerActivity extends AppCompatActivity {

    TextView loginText;
    EditText custName;
    EditText custPwd;
    Button custLogin;
    private List<CustomerDTO> customerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        loginText = (TextView) findViewById(R.id.textMessage);
        custName = (EditText) findViewById(R.id.editCustName);
        custPwd = (EditText) findViewById(R.id.editCustPwd);
        custLogin = (Button) findViewById(R.id.btnLogin);

        CustomerDbHelper dbHelper = new CustomerDbHelper(this);
        customerList = dbHelper.getAllCustomers();

        custLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String content = "";
                loginText.setText(content);

                 for (CustomerDTO cust : customerList) {

                     String customerName = custName.getText().toString();
                     String customerPwd = custPwd.getText().toString();

                     byte[] hash = new byte[0];
                     try {
                         hash = MessageDigest.getInstance("SHA-256")
                                 .digest(customerPwd.getBytes(StandardCharsets.UTF_8));

                     } catch (NoSuchAlgorithmException e) {
                         e.printStackTrace();
                     }
                     customerPwd = Base64.getEncoder().encodeToString(hash);

                     if(customerPwd.equals(cust.getCustPwd()) && customerName.equals(cust.getCustName()))  {

                         Intent startIntent = new Intent(getApplicationContext(), ViewCustomerParcels.class);
                         startIntent.putExtra("CUSTOMER_ID", cust.getCustId());
                         startIntent.putExtra("CUSTOMER_NAME", cust.getCustName());
                         startActivity(startIntent);
                     }
                     else {
                         loginText.setText("Customer credentials are not correct!");
                     }
                     customerName = "";
                     customerPwd = "";

//                     content += cust.getCustId() + "\n";
//                     content += cust.getCustName() +" : "+ customerName + "\n";
//                     content += cust.getCustPwd() +" : "+ customerPwd + "\n";
//                     loginText.append(content);
                 }
            }
        });
    }
}
