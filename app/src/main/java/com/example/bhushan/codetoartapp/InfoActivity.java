package com.example.bhushan.codetoartapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        textView=findViewById(R.id.textviewinfo);
        textView.setText("developed by BHUSHAN VOLVOIKAR");
    }
}
