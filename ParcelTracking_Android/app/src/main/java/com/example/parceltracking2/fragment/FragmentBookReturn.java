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

public class FragmentBookReturn extends Fragment {

    private FragmentBookReturnListener listener;
    private EditText editText;
    private TextView textView;
    private Button btnReturn;

    public interface FragmentBookReturnListener {
        void onInputSentBookReturn(CharSequence input);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book_parcel_return, container,false);

        editText = v.findViewById(R.id.edit_text_cust);
        btnReturn = v.findViewById(R.id.btn_book_return);
        textView = v.findViewById(R.id.text_view_return);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit2JsonHandler retrofit2JsonHandler = Retrofit2JsonHandler.retrofit.create(Retrofit2JsonHandler.class);
                int id = Integer.parseInt(editText.getText().toString());
                ParcelDTO parcel = new ParcelDTO(id, new StatusDTO(4));
                Call<ParcelDTO> call = retrofit2JsonHandler.bookParcelReturn(parcel);

                // creates asynchronius thread and calls retrofit list
                call.enqueue(new Callback<ParcelDTO>() {
                    @Override
                    public void onResponse(Call<ParcelDTO> call, Response<ParcelDTO> response) {
                        if(!response.isSuccessful()) {
                            textView.setText("Parcel id not exist. Code: " + response.code());
                            return;
                        }
                        ParcelDTO parc = response.body();
                        String content = "";
                        textView.setText(content);
                        textView.setText(parc.getStatusDTO().getStatusName());
                        CharSequence input = textView.getText();
                        listener.onInputSentBookReturn(input);
                    }

                    @Override
                    public void onFailure(Call<ParcelDTO> call, Throwable t) {
                        textView.setText("Return status not booked "+t.getMessage());
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
        if (context instanceof  FragmentBookReturnListener) {
            listener = (FragmentBookReturnListener) context;
        }
        else {
            throw new RuntimeException(context.toString()
            + "must implement FragmentBookReturnListener ");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
