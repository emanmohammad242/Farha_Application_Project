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

public class EditCategoryActivity extends AppCompatActivity {

    EditText categoryName_txt ,imageUrl_txt,numberOfProduct_txt;
    String categoryName="",imageUrl="",numberOfProduct="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);
        setupViews();
        categoryName_txt.setText(getIntent().getStringExtra("name"));
        imageUrl_txt.setText(getIntent().getStringExtra("image"));
        numberOfProduct_txt.setText(getIntent().getStringExtra("pro"));
    }

    public void setupViews()
    {
        categoryName_txt=findViewById(R.id.categoryName_txt);
        imageUrl_txt=findViewById(R.id.imageUrl_txt);
        numberOfProduct_txt=findViewById(R.id.numberOfProduct_txt);
    }

    public void edit_btn_OnClick(View view)
    {
        String restUrl = "http://192.168.1.114:84/rest/upDateCategory.php";
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);

        } else{
            EditCategoryActivity.SendPostRequest runner = new SendPostRequest();
            runner.execute(restUrl);
        }

        Intent intent = new Intent(this,CategoryListActivity.class);
        startActivity(intent);
    }
    private String processRequest(String restUrl) throws UnsupportedEncodingException {

        String idCategory=getIntent().getStringExtra("id");
        categoryName= categoryName_txt.getText().toString();
        imageUrl=imageUrl_txt.getText().toString();
        numberOfProduct=numberOfProduct_txt.getText().toString();




        String data = URLEncoder.encode("category_id", "UTF-8")
                + "=" + URLEncoder.encode(idCategory+"", "UTF-8");

        data += "&" + URLEncoder.encode("category_name", "UTF-8") + "="
                + URLEncoder.encode(categoryName, "UTF-8");

        data += "&" + URLEncoder.encode("category_imageId", "UTF-8") + "="
                + URLEncoder.encode(imageUrl, "UTF-8");


        data += "&" + URLEncoder.encode("numPro", "UTF-8")
                + "=" + URLEncoder.encode(numberOfProduct, "UTF-8");

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


        }
    }
}