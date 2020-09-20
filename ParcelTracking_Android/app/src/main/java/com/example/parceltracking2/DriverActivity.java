package com.example.parceltracking2;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.parceltracking2.dto.DriverDTO;
import com.example.parceltracking2.retrofit.Retrofit2JsonHandler;

public class DriverActivity extends AppCompatActivity {

    TextView loginText;
    EditText drivName;
    EditText drivPwd;
    Button drivLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        loginText = (TextView) findViewById(R.id.textMessage);
        drivName = (EditText) findViewById(R.id.editDrivName);
        drivPwd = (EditText) findViewById(R.id.editDrivPwd);
        drivLogin = (Button) findViewById(R.id.btnLogin);

        drivLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit2JsonHandler retrofit2JsonHandler = Retrofit2JsonHandler.retrofit.create(Retrofit2JsonHandler.class);

                DriverDTO driv = new DriverDTO();
                driv.setDrivName(drivName.getText().toString());
                driv.setDrivPwd(drivPwd.getText().toString());
                Call<DriverDTO> call = retrofit2JsonHandler.loginDriver(driv);

                call.enqueue(new Callback<DriverDTO>() {
                    @Override
                    public void onResponse(Call<DriverDTO> call, Response<DriverDTO> response) {
                        if(!response.isSuccessful()) {
                            loginText.setText("Code: " + response.code());
                            return;
                        }
                        DriverDTO driv = response.body();

                        if(driv.isCredentialsOk() == true) {

                            Intent startIntent = new Intent(getApplicationContext(), ViewDriverParcels.class);
                            startIntent.putExtra("DRIVER_ID", driv.getDriverId());
                            startIntent.putExtra("DRIVER_NAME", driv.getDrivName());
                            startActivity(startIntent);
                        }
                        else {
                            String content = "";
                            loginText.setText(content);
                            content +="Provided credentials are not correct !" + "\n";
                            content +="Please try again !";
                            content += driv.getDrivName() + "\n";
                            content += driv.getDrivPwd() + "\n";
                            content += driv.isCredentialsOk() + "\n";
                            content += driv.getDriverId() + "\n";
                            loginText.append(content);
                        }
                    }
                    @Override
                    public void onFailure(Call<DriverDTO> call, Throwable t) {
                        loginText.setText("Credentials are not correct! "+t.getMessage());
                    }
                });
            }
        });
    }
}
