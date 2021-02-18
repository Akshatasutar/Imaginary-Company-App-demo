package com.example.company_app_lab_3.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.company_app_lab_3.R;

public class AboutFragment extends Fragment {

    private AboutViewModel aboutViewModel;
    private String values = "Listen. Be present. Be patient. Don't judge.";
    private String about = "This company consists of only me at the moment, beacuse " +
            "I could not find anyone else who was willing to do this weird thing with me. " +
            "The basic idea is we/I/one of us go hang out with you, the client, for one day and give you company." +
            "But we say absolutely nothing. We just follow you arond while you do anything you want. " +
            "You can choose to speak to us if you want to. All we do is listen. " +
            "Sometimes it is easier to share things with a stranger that " +
            "you might never see again than it is to be vulnerable with someone you know.";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        aboutViewModel =
                new ViewModelProvider(this).get(AboutViewModel.class);
        View root = inflater.inflate(R.layout.fragment_about, container, false);

        final TextView aboutTextView = root.findViewById(R.id.aboutTextView);
        aboutViewModel.setmText(about);
        aboutViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                aboutTextView.setText(s);
            }
        });

        TextView valuesTextView = root.findViewById(R.id.valuesTextView);
        valuesTextView.setText(values);

        return root;
    }
}