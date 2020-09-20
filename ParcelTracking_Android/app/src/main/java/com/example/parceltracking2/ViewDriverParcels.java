package com.example.parceltracking2;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.parceltracking2.dto.ParcelDTO;
import com.example.parceltracking2.fragment.UpdateStatusMain;
import com.example.parceltracking2.retrofit.Retrofit2JsonHandler;

import java.util.List;

public class ViewDriverParcels extends AppCompatActivity {

    Button GetMyParcels;
    TextView ViewCollection;
    Button UpdateParcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_driver_parcels);

        GetMyParcels = (Button) findViewById(R.id.btn_get_all_parcels);
        UpdateParcel = (Button) findViewById(R.id.btn_update_parcel);
        ViewCollection = (TextView) findViewById(R.id.customerCollectionView);

        int drivId = getIntent().getIntExtra("DRIVER_ID", 0);
        String drivName = getIntent().getStringExtra("DRIVER_NAME");

        String content = "";
        ViewCollection.setText(content);
        content ="You are logged in " + drivName;
        ViewCollection.append(content);

        GetMyParcels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit2JsonHandler retrofit2JsonHandler = Retrofit2JsonHandler.retrofit.create(Retrofit2JsonHandler.class);
                Call<List<ParcelDTO>> call = retrofit2JsonHandler.getParcel();

                call.enqueue(new Callback<List<ParcelDTO>>() {
                    @Override
                    public void onResponse(Call<List<ParcelDTO>> call, Response<List<ParcelDTO>> response) {
                        if(!response.isSuccessful()) {
                            ViewCollection.setText("Code: " + response.code());
                            return;
                        }
                        List<ParcelDTO> dto = response.body();

                        if(dto.get(0) instanceof ParcelDTO){

                            ViewCollection.setText("");
                            for (ParcelDTO parc : dto) {

                                if(parc.getDriverDTO().getDrivName().equals(drivName)) {
                                    String content ="";
                                    content += "Parcel ID: " + parc.getParcelId() + "\n\n";
                                    content += "Awaiting collection date: " + parc.getAwaiting_collection_date() + "\n\n";
                                    if(parc.getCollection_date() != null) {
                                        content += "Collection date: " + parc.getCollection_date() + "\n\n";
                                    }
                                    if(parc.getDelivery_date() != null) {
                                        content += "Delivery date: " + parc.getDelivery_date() + "\n\n";
                                    }
                                    if(parc.getAwaiting_return_collection_date() != null) {
                                        content += "Awaiting return collection date: " + parc.getAwaiting_return_collection_date() + "\n\n";
                                    }
                                    if(parc.getCollection_return_date() != null) {
                                        content += "Collection return date: " + parc.getCollection_return_date() + "\n\n";
                                    }
                                    if(parc.getReturn_delivery_date() != null) {
                                        content += "Return delivery date: " + parc.getReturn_delivery_date() + "\n\n";
                                    }
                                    content += "Status: " + parc.getStatusDTO().getStatusName() + "\n\n";

                                    ViewCollection.append(content);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ParcelDTO>> call, Throwable t) {
                        ViewCollection.setText("Error: "+t.getMessage());
                    }
                });

            }
        });

        UpdateParcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(getApplicationContext(), UpdateStatusMain.class);
                startActivity(startIntent);
            }
        });
    }
}
