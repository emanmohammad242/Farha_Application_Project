package com.example.farha_application.Acticites.Rigesteration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.farha_application.Acticites.Users.HomeActivity;
import com.example.farha_application.R;

public class WelcomeActivity extends AppCompatActivity {

    public static int timeout=3000;
    TextView welcome_name_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcome_name_txt=findViewById(R.id.welcome_name_txt);
        String name [] = getIntent().getStringExtra("name").split(" ");
        welcome_name_txt.setText("Welcome "+name[0] );
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
                intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("phoneNumber",getIntent().getStringExtra("phoneNumber"));
                intent.putExtra("Balance",getIntent().getStringExtra("Balance"));
                startActivity(intent);
                finish();
            }
        }, timeout);

    }
}