package com.example.farha_application.Acticites.Rigesteration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.farha_application.Acticites.Admin.CategoryListActivity;
import com.example.farha_application.Acticites.Admin.HomeAdminActivity;
import com.example.farha_application.Acticites.MainActivity;
import com.example.farha_application.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class LogInActivity extends AppCompatActivity {

    private EditText phoneNumber_txt;
    private EditText password_txt;
    private Button login_btn , button2;
    String phoneNumber="",password="",s="";
    public Boolean isSuccess ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        phoneNumber_txt=findViewById(R.id.phoneNumber_txt);
        password_txt=findViewById(R.id.password_edt);
        button2=findViewById(R.id.button2);
        button2.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    public void login_btn_OnClick(View view)
    {
     phoneNumber =phoneNumber_txt.getText().toString();
     password =password_txt.getText().toString();
        String url = "http://172.19.29.67:84/rest/login.php?phone="+phoneNumber+"&&password="+password;
        try {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.INTERNET}, 123);
            } else {
                LogInActivity.DownloadTextTask runner = new DownloadTextTask();
                runner.execute(url);
            }
            //++++++++++++++++++++++++
            String[] user = s.split(",");
            if(user.length <=0){
                String error ="Transmission failed ";
                Toast.makeText(getBaseContext(), error, Toast.LENGTH_LONG).show();
            }
            else if(user.length == 1){                  // faild login
                if(s.equals("1")){        //Integer.parseInt(user[0])== 1
                    isSuccess =false;
                    String error1 ="Password is incorrect\n" +
                            "Please try again ";
                    Toast.makeText(getBaseContext(), error1, Toast.LENGTH_LONG).show();
                }
                if(s.equals("2")){
                    isSuccess =false;
                    String error2 ="The phone number is invalid\n" +
                            "Please register first";
                    Toast.makeText(getBaseContext(), error2, Toast.LENGTH_LONG).show();
                }
                if(s.equals("3")){
                    isSuccess =false;
                    String error3 ="Data transmission error";
                    Toast.makeText(getBaseContext(), error3, Toast.LENGTH_LONG).show();
                }
                if(Integer.parseInt(s)== 0){
                    isSuccess =false;
                    String error5 ="Data is zero";
                    Toast.makeText(getBaseContext(), error5, Toast.LENGTH_LONG).show();
                }
            }
            else if(user.length > 5 ){          //login is successful
               // userData =new User(Integer.parseInt(user[0]),user[1],user[2],user[3], user[4],user[5],Integer.parseInt(user[6]),user[7],new Wallet(Integer.parseInt(user[8])));
                String success ="Success login \n Welcome "+user[1];
                isSuccess =true;
                Toast.makeText(getBaseContext(), success, Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(this, WelcomeActivity.class);
                Intent intent2 = new Intent(this, HomeAdminActivity.class);
                intent1.putExtra("user_id",user[0]);
                intent1.putExtra("name",user[1]);
                intent1.putExtra("phoneNumber",user[2]);
                intent1.putExtra("Balance",user[8]);

                if(user[7].equals("user"))
                {
                    startActivity(intent1);
                }else if(user[7].equals("admin"))
                {  startActivity(intent2);}


            }
            Toast.makeText(getBaseContext(), isSuccess+"", Toast.LENGTH_LONG).show();
        }catch(Exception e){
        }
    }
    private InputStream OpenHttpConnection(String urlString) throws IOException
    {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");
        try{
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        }
        catch (Exception ex)
        {
            Log.d("Networking", ex.getLocalizedMessage());
            throw new IOException("Error connecting");
        }
        return in;
    }
    private String DownloadText(String URL)
    {
        int BUFFER_SIZE = 2000;
        InputStream in = null;
        try {
            in = OpenHttpConnection(URL);
        } catch (IOException e) {
            Log.d("Networking", e.getLocalizedMessage());
            return "";
        }

        InputStreamReader isr = new InputStreamReader(in);
        int charRead;
        String str = "";
        char[] inputBuffer = new char[BUFFER_SIZE];
        try {
            while ((charRead = isr.read(inputBuffer))>0) {
                //---convert the chars to a String---
                String readString =
                        String.copyValueOf(inputBuffer, 0, charRead);
                str += readString;
                inputBuffer = new char[BUFFER_SIZE];
            }
            in.close();
        } catch (IOException e) {
            Log.d("Networking", e.getLocalizedMessage());
            return "";
        }
        return str;
    }

    public void Forgetpassword(View view) {
        // send the massige on the phone
        //TODO: create the forgetpasswprd ?
    }

    public void Register(View view) {
        // her put Eman Work
        //TODO: go to the Register InterFace doing by eman
    }

    private class DownloadTextTask extends AsyncTask<String, Void, String> {
        LogInActivity loginActivity;
        @Override
        protected String doInBackground(String... urls) {
            return DownloadText(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getBaseContext(), s+"!", Toast.LENGTH_LONG).show();
            s=result;
        }
    }
}