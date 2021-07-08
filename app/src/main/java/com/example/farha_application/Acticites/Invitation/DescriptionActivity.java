package com.example.farha_application.Acticites.Invitation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.farha_application.R;

public class DescriptionActivity extends AppCompatActivity {

    TextView dec_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        dec_txt=findViewById(R.id.dec_text);
        dec_txt.setText(getIntent().getStringExtra("dec"));

    }

    public void show_gift_OnClick(View view) {
        Intent intent =new Intent(DescriptionActivity.this, ListOfGiftActivity.class);
        intent.putExtra("user_id",getIntent().getStringExtra("user_id_data"));
        intent.putExtra("invitation_id",getIntent().getStringExtra("invitation_id_data"));
        startActivity(intent);
    }


}