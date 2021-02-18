package com.example.company_app_lab_3.ui.contact;

import android.Manifest;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.company_app_lab_3.R;

public class ContactFragment extends Fragment {

    private ContactViewModel contactViewModel;
    private ImageButton phoneNumButton;
    private ImageButton emailButton;
    private ImageButton urlButton;
    private TextView phNumTextView;
    private TextView emailTextView;
    private TextView urlText;
    private static final int REQUEST_CALL = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_contact, container, false);
        phoneNumButton = root.findViewById(R.id.phoneImageButton);
        phNumTextView = root.findViewById(R.id.phNumTextView);
        emailButton = root.findViewById(R.id.emailImageButton);
        emailTextView = root.findViewById(R.id.emailTextView);
        urlButton = root.findViewById(R.id.linkImageButton);
        urlText = root.findViewById(R.id.linekdInTextView);

        phoneNumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNumber();
            }
        });
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
        urlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl();
            }
        });

        return root;
    }

    public void callNumber(){
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) !=
        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }
        else{
            Intent intent = new Intent(Intent.ACTION_DIAL);
            String phoneNum = (String)phNumTextView.getText();
            intent.setData(Uri.parse("tel:" + phoneNum));
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intent);
            }
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CALL){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                callNumber();
        }
    }

    public void sendEmail(){
        String [] addresses = new String[1];
        addresses[0] = (String)emailTextView.getText();
        Intent intent = new Intent(Intent.ACTION_SEND);
        //intent.setData(Uri.parse("mailto:"));
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        if(intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
        else {
            Toast.makeText(getContext(), "Email unavailable", Toast.LENGTH_SHORT).show();
        }
    }

    public void openUrl(){
        String url = (String)urlText.getText();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}