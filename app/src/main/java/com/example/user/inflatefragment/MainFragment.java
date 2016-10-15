package com.example.user.inflatefragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by User on 22.09.2016.
 */
public class MainFragment extends Fragment {

    String data;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getArguments() != null){
            data = getArguments().getString("data");
        }
        View v = inflater.inflate(R.layout.main_fragment, null);
        Button btnNextFragment = (Button) v.findViewById(R.id.btnNextFragment);
        Button btnSendNotification = (Button) v.findViewById(R.id.btnSendNotification);
        TextView txt = (TextView)v.findViewById(R.id.receivetext);
        txt.setText(data);

        btnNextFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fr = new FirstFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .addToBackStack("main")
                        .replace(R.id.activity_main, fr)
                        .commit();
            }
        });

        btnSendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager = (NotificationManager)getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);

                Intent intent = new Intent(getActivity(), MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), (int) System.currentTimeMillis(), intent, 0);
                Notification notification = new Notification.Builder(getActivity())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pendingIntent)
                        .setContentTitle("Change Activity?")
                        .setContentText("Go to second fragment")
                        .build();
                notificationManager.notify(0, notification);
            }
        });

        return v;
    }
}
