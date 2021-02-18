package com.example.company_app_lab_3.ui.location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.company_app_lab_3.R;

public class LocationFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_location, container, false);

        TextView addressText;
        String address = "Elmetorpsvägen 15, 291 39 Kristianstad";
        addressText = root.findViewById(R.id.addressTextView2);
        addressText.setText(address);
        return root;
    }
}