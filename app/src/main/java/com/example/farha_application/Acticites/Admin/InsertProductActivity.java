package com.example.farha_application.Acticites.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.farha_application.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class InsertProductActivity extends AppCompatActivity {

    private EditText productName_txt ,productPrice_txt,productColor_txt,productSize_txt,productToken_txt,imageUrl_txt,productCategory_txt;
    String name_pro="" , price_pro="" , color_pro="" , size_pro="" , image_id ="" , category_pro="" , ids="" ,token_pro="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_product);
        setupViews();


    }

    public void setupViews()
    {
        productName_txt=findViewById(R.id.productName_txt);
        productPrice_txt=findViewById(R.id.productPrice_txt);
        productColor_txt=findViewById(R.id.productColor_txt);
        productSize_txt=findViewById(R.id.productSize_txt);
        productToken_txt=findViewById(R.id.productToken_txt);
        imageUrl_txt=findViewById(R.id.imageUrl_txt);
        productCategory_txt=findViewById(R.id.productCategory_txt);
    }

    public void insert_btn_OnClick(View view){
        String restUrl = "http://172.19.29.67:84/rest/addProduct.php";
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);

        } else{
            InsertProductActivity.SendPostRequest runner = new SendPostRequest();
            runner.execute(restUrl);
        }
        Intent intent = new Intent(this,ProductListActivity.class);
        startActivity(intent);
    }
private String processRequest(String restUrl) throws UnsupportedEncodingException {


    name_pro=productName_txt.getText().toString().trim();
    price_pro=productPrice_txt.getText().toString().trim();
    color_pro=productColor_txt.getText().toString().trim();
    size_pro=productSize_txt.getText().toString().trim();
    image_id=imageUrl_txt.getText().toString().trim();
    token_pro=productToken_txt.getText().toString().trim();
    category_pro=productCategory_txt.getText().toString().trim();



    String data = URLEncoder.encode("product_name", "UTF-8")
            + "=" + URLEncoder.encode(name_pro, "UTF-8");

    data += "&" + URLEncoder.encode("product_pric", "UTF-8") + "="
            + URLEncoder.encode(price_pro, "UTF-8");


    data += "&" + URLEncoder.encode("product_color", "UTF-8")
            + "=" + URLEncoder.encode(color_pro, "UTF-8");


    data += "&" + URLEncoder.encode("product_size", "UTF-8")
            + "=" + URLEncoder.encode(size_pro, "UTF-8");

    data += "&" + URLEncoder.encode("product_imageId", "UTF-8")
            + "=" + URLEncoder.encode(image_id, "UTF-8");

    data += "&" + URLEncoder.encode("product_token", "UTF-8")
            + "=" + URLEncoder.encode(token_pro, "UTF-8");

    data += "&" + URLEncoder.encode("category", "UTF-8")
            + "=" + URLEncoder.encode(category_pro, "UTF-8");

    String text = "";
    BufferedReader reader=null;

    // Send data
    try
    {

        // Defined URL  where to send data
        URL url = new URL(restUrl);

        // Send POST data request

        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write( data );
        wr.flush();

        // Get the server response

        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = "";

        // Read Server Response
        while((line = reader.readLine()) != null)
        {
            // Append server response in string
            sb.append(line + "\n");
        }


        text = sb.toString();
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    finally{
        if ((reader != null)) {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Show response on activity
    return text;



}

public class SendPostRequest extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        try {
            return processRequest(strings[0]);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
}