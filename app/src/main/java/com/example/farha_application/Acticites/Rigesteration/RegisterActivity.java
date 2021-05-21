package com.example.farha_application.Acticites.Rigesteration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farha_application.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class RegisterActivity extends AppCompatActivity {

    private EditText  fullName_txt;
    private EditText  phoneNumber_txt;
    private EditText emailAddress_txt;
    private EditText password_txt;
    private EditText repeatPassword_txt;
    private EditText address_txt;
    private Button   register_btn;
    TextView text;

    private String fullName="",phoneNumber="",emailAddress="",password="",repeatPassword="",address="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setUpViews();


    }

    public void setUpViews()
    {
        fullName_txt=findViewById(R.id.fullName_txt);
        phoneNumber_txt=findViewById(R.id.phoneNumber_txt);
        emailAddress_txt=findViewById(R.id.emailAddress_txt);
        password_txt=findViewById(R.id.password_txt);
        repeatPassword_txt=findViewById(R.id.repeatPassword_txt);
        address_txt=findViewById(R.id.address_txt);
        text=findViewById(R.id.text);
        register_btn=findViewById(R.id.register_btn);
    }

    public void register_btn_OnClick(View view)
    {
        String URL = "http://192.168.1.114:84/rest/addusers.php";
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);

        } else{
            SendPostRequest runner = new SendPostRequest();
            runner.execute(URL);
        }
    }

    private String processRequest(String restUrl) throws UnsupportedEncodingException {


        fullName=fullName_txt.getText().toString();
        phoneNumber=phoneNumber_txt.getText().toString();
        emailAddress=emailAddress_txt.getText().toString();
        password=password_txt.getText().toString();
        repeatPassword=repeatPassword_txt.getText().toString();
        address=address_txt.getText().toString();
        String data=null;
   //  if(fullName!=null&&phoneNumber!=null&&emailAddress!=null&&password!=null&&repeatPassword!=null&&address!=null&&password==repeatPassword) {
         data = URLEncoder.encode("name", "UTF-8")
                 + "=" + URLEncoder.encode(fullName, "UTF-8");

         data += "&" + URLEncoder.encode("phone", "UTF-8") + "="
                 + URLEncoder.encode(phoneNumber, "UTF-8");


         data += "&" + URLEncoder.encode("email", "UTF-8")
                 + "=" + URLEncoder.encode(emailAddress, "UTF-8");


         data += "&" + URLEncoder.encode("password", "UTF-8")
                 + "=" + URLEncoder.encode(password, "UTF-8");


         data += "&" + URLEncoder.encode("repassword", "UTF-8")
                 + "=" + URLEncoder.encode(repeatPassword, "UTF-8");

         data += "&" + URLEncoder.encode("address", "UTF-8")
                 + "=" + URLEncoder.encode(address, "UTF-8");


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

    private class SendPostRequest extends AsyncTask<String, Void, String> {

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
        text.setText(s+"h");
        if(s.equals("New record created successfully"))
        {

        }
        }
    }
}