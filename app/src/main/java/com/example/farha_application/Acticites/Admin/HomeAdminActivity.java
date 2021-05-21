package com.example.farha_application.Acticites.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.farha_application.R;

public class HomeAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
    }

    public void viewAccount_btn_OnClick(View view)
    {
        Intent intent =new Intent(this, ViewAccountActivity.class);
        startActivity(intent);
    }
    public void updateItem_btn_OnClick(View view)
    {
        Intent intent =new Intent(this, CategoryListActivity.class);
        startActivity(intent);
    }
}