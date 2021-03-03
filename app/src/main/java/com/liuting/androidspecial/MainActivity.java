package com.liuting.androidspecial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.liuting.androidspecial.activity.FlowLayoutActivity;

public class MainActivity extends AppCompatActivity {

    private Button flowBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flowBT = findViewById(R.id.bt_flowlayout);
        initListener();
    }

    private void initListener() {
        flowBT.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, FlowLayoutActivity.class);
            startActivity(intent);
        });
    }
}