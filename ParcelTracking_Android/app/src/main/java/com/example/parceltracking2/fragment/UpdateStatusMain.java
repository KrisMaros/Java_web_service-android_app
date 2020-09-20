package com.example.parceltracking2.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.parceltracking2.R;
import com.example.parceltracking2.fragment.FragmentBookReturn;
import com.example.parceltracking2.fragment.FragmentUpdateParcel;

public class UpdateStatusMain extends AppCompatActivity implements FragmentBookReturn.FragmentBookReturnListener,
                                                                   FragmentUpdateParcel.FragmentUpdateParcelListener {

    private FragmentBookReturn fragmentBookReturn;
    private FragmentUpdateParcel fragmentUpdateParcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_status_main);

        fragmentBookReturn = new FragmentBookReturn();
        fragmentUpdateParcel = new FragmentUpdateParcel();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containar_customer, fragmentBookReturn)
                .replace(R.id.containar_driver, fragmentUpdateParcel)
                .commit();
    }

    @Override
    public void onInputSentBookReturn(CharSequence input) {
        fragmentUpdateParcel.updateEditText(input);
    }

    @Override
    public void onInputSentUpdateParcel(CharSequence input) {
         fragmentBookReturn.updateEditText(input);
    }
}
