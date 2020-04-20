package com.example.viewpagetransformdialogfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.viewpagetransformdialogfragment.dialog.ViewpagerTransFromDialogFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv_open_dialog = findViewById(R.id.tv_open_dialog);
        tv_open_dialog.setOnClickListener(v -> {
            ViewpagerTransFromDialogFragment viewpagerTransFromDialogFragment = ViewpagerTransFromDialogFragment.newInstance();
            viewpagerTransFromDialogFragment.show(getSupportFragmentManager());
        });
    }
}
