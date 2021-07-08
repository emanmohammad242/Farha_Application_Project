package com.example.farha_application.Acticites.Invitation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farha_application.Acticites.Users.EditeInformationActivity;
import com.example.farha_application.Acticites.Users.HomeActivity;
import com.example.farha_application.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class wallletActivity extends AppCompatActivity {

    EditText amount ;
    TextView balance;
   // LoginActivity loginActivity;
    // double Balance =loginActivity.userData.wallet.getBalance();
    double amountData ;
    Boolean addsucsses=false;
    String id ,Balanc;
    public String resultData;
    double newBalance;
    Button AddAmount;

    Toolbar toolbar ;
    Button wallet_btn,list_btn,home_btn,user_profile_btn,friends_btn;
    MenuItem wallet_count_menu_item;
    MenuItem list_count_menu_item;
    MenuItem home_count_menu_item;
    MenuItem user_profile_count_menu_item;
    MenuItem friends_count_menu_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walllet);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);

        AddAmount=findViewById(R.id.AddAmount);
        balance=findViewById(R.id.balance);
        amount =findViewById(R.id.amount);
        id=getIntent().getStringExtra("id");
       // Balanc=getIntent().getStringExtra("Balanc");
        Balanc="50";
        balance.setText(Balanc+" ₪"); // her show the balance
    }

    public void addAmount(View view) {
        String amoun=amount.getText().toString();
        //int id =loginActivity.userData.getId();
        amountData= Double.parseDouble(amoun);
        newBalance =amountData+Double.parseDouble(Balanc);
        String url = "http://172.19.29.67:84/rest/updateWallet.php?iduser="+"2"+"&&balance="+newBalance;
        try{
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.INTERNET)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET},
                        123);
            }else{
                DownloadTextTask runner = new DownloadTextTask();
                runner.execute(url);
            }
            //++++++++++
            String[] user = resultData.split(",");
            if(Integer.parseInt(user[0])==1){
                String  resUpdate="The amount has been added";
                Toast.makeText(getBaseContext(), resUpdate, Toast.LENGTH_LONG).show();
                addsucsses=true;
                balance.setText(newBalance+"$");
            }
            if(Integer.parseInt(user[0])==0){
                addsucsses=false;
                String  resUpdate="The data was not sent ";
                Toast.makeText(getBaseContext(), resUpdate, Toast.LENGTH_LONG).show();
            }
            if(Integer.parseInt(user[0])==2){
                addsucsses=false;
                String resUpdate="No data addition occurred";
                //loginActivity.userData.wallet.Withdrawal(amountData);
                Toast.makeText(getBaseContext(), resUpdate, Toast.LENGTH_LONG).show();
            }
            if(resultData == null){
                Toast.makeText(getBaseContext(), "ther is no data ", Toast.LENGTH_LONG).show();
            }

        }catch(Exception e){}





    }

//    private void alertDialog() {
//        AlertDialog.Builder builder=new AlertDialog.Builder(this);
//        builder.setCancelable(false);
//        builder.setMessage("Do you want to Exit?");
//
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //if user pressed "yes", then he is allowed to exit from application
//                finish();
//            }
//        });
//        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //if user select "No", just cancel this dialog and continue with app
//                dialog.cancel();
//            }
//        });
//        AlertDialog alert=builder.create();
//        alert.show();
//    }

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
    private class DownloadTextTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return DownloadText(urls[0]);
        }
        @Override
        protected void onPostExecute(String result) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);


        wallet_count_menu_item=menu.findItem(R.id.wallet_count_menu_item);
        View actionView_wallet=wallet_count_menu_item.getActionView();

        if(actionView_wallet!=null) {
            wallet_btn = actionView_wallet.findViewById(R.id.wallet_btn);
        }
        wallet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(wallletActivity.this, wallletActivity.class);
                intent.putExtra("id",getIntent().getStringExtra("id"));
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("phoneNumber",getIntent().getStringExtra("phoneNumber"));
                intent.putExtra("Balance",getIntent().getStringExtra("Balance"));
                startActivity(intent);
            }
        });


        list_count_menu_item=menu.findItem(R.id.list_count_menu_item);
        View actionView_list=list_count_menu_item.getActionView();

        if(actionView_list!=null){

            list_btn=actionView_list.findViewById(R.id.list_btn);
        }

        list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(wallletActivity.this, InvitationActivity.class);
                intent.putExtra("id",getIntent().getStringExtra("id"));
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("phoneNumber",getIntent().getStringExtra("phoneNumber"));
                intent.putExtra("Balance",getIntent().getStringExtra("Balance"));
                startActivity(intent);
            }
        });


        home_count_menu_item=menu.findItem(R.id.home_count_menu_item);
        View actionView_home=home_count_menu_item.getActionView();

        if(actionView_home!=null){

            home_btn=actionView_home.findViewById(R.id.home_btn);
        }


        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(wallletActivity.this, HomeActivity.class);
                intent.putExtra("id",getIntent().getStringExtra("id"));
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("phoneNumber",getIntent().getStringExtra("phoneNumber"));
                intent.putExtra("Balance",getIntent().getStringExtra("Balance"));
                startActivity(intent);
            }
        });

        user_profile_count_menu_item=menu.findItem(R.id.user_profile_count_menu_item);
        View actionView_user_profile=user_profile_count_menu_item.getActionView();

        if(actionView_user_profile!=null){

            user_profile_btn=actionView_user_profile.findViewById(R.id.user_profile_btn);
        }

        user_profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(wallletActivity.this, EditeInformationActivity.class);
                intent.putExtra("id",getIntent().getStringExtra("id"));
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("phoneNumber",getIntent().getStringExtra("phoneNumber"));
                intent.putExtra("Balance",getIntent().getStringExtra("Balance"));
                startActivity(intent);
            }
        });


        friends_count_menu_item=menu.findItem(R.id.friends_count_menu_item);
        View actionView_friends=friends_count_menu_item.getActionView();

        if(actionView_friends!=null){

            friends_btn=actionView_friends.findViewById(R.id.friends_btn);
        }
        friends_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(wallletActivity.this, FriendsListActivity.class);
                intent.putExtra("id",getIntent().getStringExtra("id"));
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("phoneNumber",getIntent().getStringExtra("phoneNumber"));
                intent.putExtra("Balance",getIntent().getStringExtra("Balance"));
                startActivity(intent);
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}