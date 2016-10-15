package com.example.user.inflatefragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by User on 22.09.2016.
 */
public class FirstFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.first_fragment, null);
        Button btnBack = (Button)v.findViewById(R.id.buttonback);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });

        Button btnBackData = (Button)v.findViewById(R.id.buttonsend);
        final EditText editText = (EditText)v.findViewById(R.id.editText);
        final Fragment current = this;

        btnBackData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
                Fragment main = new MainFragment();
                Bundle bundle = new Bundle();
                bundle.putString("data", editText.getText().toString());
                main.setArguments(bundle);
                fm.beginTransaction()
                        .replace(R.id.activity_main, main)
                        .remove(current)
                        .commit()
                ;
            }
        });
        return v;
    }
}
