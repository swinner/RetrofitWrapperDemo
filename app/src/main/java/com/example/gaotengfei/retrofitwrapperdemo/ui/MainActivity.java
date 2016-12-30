package com.example.gaotengfei.retrofitwrapperdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gaotengfei.retrofitwrapperdemo.R;
import com.example.gaotengfei.retrofitwrapperdemo.base.BaseActivity;



public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_jump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_jump = (Button)findViewById(R.id.btn_jump);
        btn_jump.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,OneActivity.class);
        startActivity(intent);
    }
}
