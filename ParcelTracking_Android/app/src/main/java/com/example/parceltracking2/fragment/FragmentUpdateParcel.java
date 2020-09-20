package com.example.parceltracking2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.parceltracking2.R;
import com.example.parceltracking2.dto.ParcelDTO;
import com.example.parceltracking2.dto.StatusDTO;
import com.example.parceltracking2.retrofit.Retrofit2JsonHandler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentUpdateParcel extends Fragment {

    private FragmentUpdateParcelListener listener;
    private EditText editParcelId;
    private EditText editStatusId;
    private TextView textView;
    private Button btnUpdate;

    public interface FragmentUpdateParcelListener {
        void onInputSentUpdateParcel(CharSequence input);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_update_parcel, container,false);

        editParcelId = v.findViewById(R.id.edit_text_parcel_id);
        editStatusId = v.findViewById(R.id.edit_text_parcel_status);
        btnUpdate = v.findViewById(R.id.btn_update_parcel);
        textView = v.findViewById(R.id.text_view_update);

        btnUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Retrofit2JsonHandler retrofit2JsonHandler = Retrofit2JsonHandler.retrofit.create(Retrofit2JsonHandler.class);

                int id = Integer.parseInt(editParcelId.getText().toString());
                int status = Integer.parseInt(editStatusId.getText().toString());
                ParcelDTO parcDTO = new ParcelDTO();
                StatusDTO statDTO = new StatusDTO();

                if (status > 0 && status < 4 || status > 4 && status < 7 ) {
                    parcDTO.setParcelId(id);
                    statDTO.setStatusId(status);
                    parcDTO.setStatusDTO(statDTO);
                }
                Call<ParcelDTO> call = retrofit2JsonHandler.updateParcelStatus(parcDTO);
                // creates asynchronius thread and calls retrofit list
                call.enqueue(new Callback<ParcelDTO>() {
                    @Override
                    public void onResponse(Call<ParcelDTO> call, Response<ParcelDTO> response) {
                        if(!response.isSuccessful()) {
                            textView.setText("Parcel id or status id not exist. Code: " + response.code());
                            return;
                        }
                        ParcelDTO parc = response.body();
                        String content = "";
                        textView.setText(content);
                        textView.setText(parc.getStatusDTO().getStatusName());
                        CharSequence input = textView.getText();
                        listener.onInputSentUpdateParcel(input);
                    }
                    @Override
                    public void onFailure(Call<ParcelDTO> call, Throwable t) {
                        textView.setText("Credentials are not correct! "+t.getMessage());
                    }
                });
            }
        });

        return v;
    }

    public void updateEditText(CharSequence newText) {
        textView.setText(newText);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof  FragmentUpdateParcelListener) {
            listener = (FragmentUpdateParcelListener) context;
        }
        else {
            throw new RuntimeException(context.toString()
            + "must implement FragmentUpdateParcelListener ");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
