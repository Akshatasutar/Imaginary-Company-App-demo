package com.example.company_app_lab_3.ui.contact;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.company_app_lab_3.MainActivity;
import com.example.company_app_lab_3.R;

public class ContactFragment extends Fragment {

    private ContactViewModel contactViewModel;
    private ImageButton phoneNumButton;
    private ImageButton emailButton;
    private TextView phNumTextView;
    private TextView emailTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_contact, container, false);
        phoneNumButton = root.findViewById(R.id.phoneImageButton);
        emailButton = root.findViewById(R.id.emailImageButton);

        phoneNumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPhoneNumButtonClick(v);
            }
        });
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmailButtonClick(v);
            }
        });
        return root;
    }

    public void onPhoneNumButtonClick(View v){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        phNumTextView = v.findViewById(R.id.phNumTextView);
        String phoneNum = (String)phNumTextView.getText();
        intent.setData(Uri.parse("tel:" + phoneNum));
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
        startActivity(intent);
    }

    public void onEmailButtonClick(View v){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        if(intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
        else {
            Toast.makeText(getContext(), "Email unavailable", Toast.LENGTH_SHORT).show();
        }
    }
}