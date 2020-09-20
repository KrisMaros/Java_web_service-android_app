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

public class ViewCustomerParcels extends AppCompatActivity {

    Button GetMyParcels;
    Button BookReturn;
    TextView ViewCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer_parcels);

        GetMyParcels = (Button) findViewById(R.id.btnGetParcels);
        BookReturn = (Button) findViewById(R.id.btnBookReturn);
        ViewCollection = (TextView) findViewById(R.id.customerCollectionView);

        int custId = getIntent().getIntExtra("CUSTOMER_ID",0);
        String custnName = getIntent().getStringExtra("CUSTOMER_NAME");

        String content = "";
        ViewCollection.setText(content);
        content ="You are logged in " + custnName;
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

                        // can be used to verify object type
                        if(dto.get(0) instanceof ParcelDTO){

                            ViewCollection.setText("");
                            for (ParcelDTO parc : dto) {

                                if(parc.getCustomerDTO().getCustName().equals(custnName)) {

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

        BookReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(getApplicationContext(), UpdateStatusMain.class);
                startActivity(startIntent);
            }
        });
    }
}
