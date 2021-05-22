package com.example.farha_application.Acticites.Invitation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.farha_application.R;
import com.squareup.picasso.Picasso;

public class DetailsProductActivity extends AppCompatActivity {

    TextView productName1 ;
    ImageView imageView1,imageView2,imageView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_product);
        productName1=findViewById(R.id.productName1_txt);
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);

        productName1.setText(getIntent().getStringExtra("product_name"));
        Picasso.get().load(getIntent().getStringExtra("product_imageId1")).into(imageView1);
        Picasso.get().load(getIntent().getStringExtra("product_imageId2")).into(imageView2);
        Picasso.get().load(getIntent().getStringExtra("product_imageId3")).into(imageView3);

    }

}