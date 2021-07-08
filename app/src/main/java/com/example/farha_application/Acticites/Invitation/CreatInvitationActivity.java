package com.example.farha_application.Acticites.Invitation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.farha_application.Acticites.Admin.EditProductActivity;
import com.example.farha_application.Acticites.Admin.ProductListActivity;
import com.example.farha_application.Acticites.Users.EditeInformationActivity;
import com.example.farha_application.Adapters.Adapter_InvitationList;
import com.example.farha_application.Models.Invitation;
import com.example.farha_application.Models.MySingleton;
import com.example.farha_application.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreatInvitationActivity extends AppCompatActivity {
    Toolbar toolbar ;
    Button wallet_btn,list_btn,home_btn,user_profile_btn,friends_btn;
    MenuItem wallet_count_menu_item;
    MenuItem list_count_menu_item;
    MenuItem home_count_menu_item;
    MenuItem user_profile_count_menu_item;
    MenuItem friends_count_menu_item;


    private EditText final_date_txt,title_txt,dec_txt,url_txt;

    int day,month,year;
    final Calendar calendar=Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_invitation);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);

        final_date_txt=findViewById(R.id.final_date_txt);
        title_txt=findViewById(R.id.title_txt);
        dec_txt=findViewById(R.id.dec_txt);
        url_txt=findViewById(R.id.url_txt);

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
                Intent intent =new Intent(CreatInvitationActivity.this, wallletActivity.class);
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
                Intent intent =new Intent(CreatInvitationActivity.this, InvitationActivity.class);
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
                Toast.makeText(CreatInvitationActivity.this, "Image Button is Clicked", Toast.LENGTH_SHORT).show();
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
                Intent intent =new Intent(CreatInvitationActivity.this, EditeInformationActivity.class);
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
                Intent intent =new Intent(CreatInvitationActivity.this, FriendsListActivity.class);
                startActivity(intent);
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void date_OnClick(View view) {

        day=calendar.get(Calendar.DAY_OF_MONTH);
        month=calendar.get(Calendar.MONTH);
        year=calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(CreatInvitationActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
               final_date_txt.setText(dayOfMonth+"-"+month+"-"+year);


            }
        },year,month,day);
        datePickerDialog.show();
    }

    public void add_gift_OnClick(View view) {
        Intent intent =new Intent(CreatInvitationActivity.this, CategoeyListInvetationActivity.class);
        intent.putExtra("invitation_id",getIntent().getStringExtra("invitation_id"));
        intent.putExtra("user_id",getIntent().getStringExtra("user_id"));

        startActivity(intent);
    }

    public void add_friends_OnClick(View view) {
        Intent intent =new Intent(CreatInvitationActivity.this, FriendsListActivity.class);
        intent.putExtra("invitation_id",getIntent().getStringExtra("invitation_id"));
        intent.putExtra("user_id",getIntent().getStringExtra("user_id"));

        startActivity(intent);
    }

    public void create_OnClick(View view) {

        String restUrl = "http://172.19.29.67:84/rest/upDateInvitation.php";
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);

        } else{
            CreatInvitationActivity.SendPostRequest runner = new SendPostRequest();
            runner.execute(restUrl);
        }


    }

    private String processRequest(String restUrl) throws UnsupportedEncodingException {

        String id_user=getIntent().getStringExtra("user_id");
        String title = title_txt.getText().toString();
        String dec = dec_txt.getText().toString();
        String final_date = final_date_txt.getText().toString();
        String invitation_id = getIntent().getStringExtra("invitation_id");


        String data = URLEncoder.encode("id_user", "UTF-8")
                + "=" + URLEncoder.encode(id_user+"", "UTF-8");

        data += "&" + URLEncoder.encode("invitation_id", "UTF-8") + "="
                + URLEncoder.encode(invitation_id, "UTF-8");


        data += "&" + URLEncoder.encode("title", "UTF-8") + "="
                + URLEncoder.encode(title, "UTF-8");

        data += "&" + URLEncoder.encode("description", "UTF-8") + "="
                + URLEncoder.encode(dec, "UTF-8");

        data += "&" + URLEncoder.encode("final_date", "UTF-8") + "="
                + URLEncoder.encode(final_date, "UTF-8");


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
            Intent intent = new Intent(CreatInvitationActivity.this, InvitationActivity.class);
            intent.putExtra("invitation_id",getIntent().getStringExtra("invitation_id"));
            intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
            intent.putExtra("phoneNumber",getIntent().getStringExtra("phoneNumber"));
            startActivity(intent);
        }
    }


}