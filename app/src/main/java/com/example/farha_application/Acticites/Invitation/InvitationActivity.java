package com.example.farha_application.Acticites.Invitation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.farha_application.Acticites.Admin.InsertProductActivity;
import com.example.farha_application.Acticites.Admin.ProductListActivity;
import com.example.farha_application.Acticites.Admin.ViewAccountActivity;
import com.example.farha_application.Acticites.Users.EditeInformationActivity;
import com.example.farha_application.Adapters.Adapter_CategoryList;
import com.example.farha_application.Adapters.Adapter_InvitationList;
import com.example.farha_application.Adapters.Adapter_ListInvitation;
import com.example.farha_application.Adapters.Adapter_ViewAccount;
import com.example.farha_application.Models.Invitation;
import com.example.farha_application.Models.MySingleton;
import com.example.farha_application.Models.user;
import com.example.farha_application.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class InvitationActivity extends AppCompatActivity {

    RecyclerView recyclerView , recyclerView2;
    Adapter_InvitationList adapter;
    List<Invitation> invitationslist=new ArrayList<>();
    List<Invitation> invitationslist2=new ArrayList<>();
    List<String> id_invitations=new ArrayList<>();
    int i=0;
    String ii="";

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
        setContentView(R.layout.activity_invitation);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);

        recyclerView2=findViewById(R.id.recycleView2);
        recyclerView = findViewById(R.id.recyclerView);
        loadData();
        loadData2();


    }


    public void CreatInvitation(View view) {
        String restUrl = "http://172.19.29.67:84/rest/CreateInvitation.php";
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);

        } else{
            InvitationActivity.SendPostRequest runner = new SendPostRequest();
            runner.execute(restUrl);
        }


    }

    private void loadData() {
        String id = getIntent().getStringExtra("user_id");
        String url = "http://172.19.29.67:84/rest/listOfInvitation.php?cat="+id;
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray ja = response.getJSONArray("result");

                            for (int i = 0; i < ja.length(); i++) {

                                JSONObject jsonObject = ja.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String iduser = jsonObject.getString("iduser");
                                String title = jsonObject.getString("title");
                                String description = jsonObject.getString("description");
                                String Initial_date = jsonObject.getString("Initial_date");
                                String Final_date = jsonObject.getString("Final_date");
                                String 	type = jsonObject.getString("type");

                                    invitationslist.add(new Invitation(id, iduser, title, description, Initial_date,Final_date,type));
                            }
                            recyclerView.setLayoutManager(new LinearLayoutManager(InvitationActivity.this));
                            adapter = new Adapter_InvitationList(InvitationActivity.this, invitationslist);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", "Error");
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(jor);
    }

    private void loadData2() {
        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        String url = "http://172.19.29.67:84/rest/getInvitation.php?phone="+phoneNumber;
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray ja = response.getJSONArray("result");

                            for (int i = 0; i < ja.length(); i++) {

                                JSONObject jsonObject = ja.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String iduser = jsonObject.getString("iduser");
                                String title = jsonObject.getString("title");
                                String description = jsonObject.getString("description");
                                String Initial_date = jsonObject.getString("Initial_date");
                                String Final_date = jsonObject.getString("Final_date");
                                String 	type = jsonObject.getString("type");

                                invitationslist2.add(new Invitation(id, iduser, title, description, Initial_date,Final_date,type));

                            }
                            recyclerView2.setLayoutManager(new LinearLayoutManager(InvitationActivity.this));
                            Adapter_ListInvitation adapter2 = new Adapter_ListInvitation(InvitationActivity.this, invitationslist2);
                            recyclerView2.setAdapter(adapter2);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", "Error");
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(jor);
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
                Intent intent =new Intent(InvitationActivity.this, wallletActivity.class);
                intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
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
                Intent intent =new Intent(InvitationActivity.this, InvitationActivity.class);
                intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
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
                Toast.makeText(InvitationActivity.this, "Image Button is Clicked", Toast.LENGTH_SHORT).show();
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
                Intent intent =new Intent(InvitationActivity.this, EditeInformationActivity.class);
                intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
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
                Intent intent =new Intent(InvitationActivity.this, FriendsListActivity.class);
                intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("phoneNumber",getIntent().getStringExtra("phoneNumber"));
                intent.putExtra("Balance",getIntent().getStringExtra("Balance"));
                startActivity(intent);
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


    private String processRequest(String restUrl) throws UnsupportedEncodingException {


        String id_user=getIntent().getStringExtra("user_id");

        String data = URLEncoder.encode("id_user", "UTF-8")
                + "=" + URLEncoder.encode(id_user, "UTF-8");


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

            i = Integer.parseInt(invitationslist.get(0).getId());
            i=i+1;
            ii = i+"";
            Intent intent = new Intent(InvitationActivity.this, CreatInvitationActivity.class);
            intent.putExtra("invitation_id",ii);
            intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
            startActivity(intent);
        }
    }
}