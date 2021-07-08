package com.example.farha_application.Acticites.Invitation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.farha_application.Acticites.Admin.CategoryListActivity;
import com.example.farha_application.Acticites.Admin.InsertProductActivity;
import com.example.farha_application.Acticites.Users.EditeInformationActivity;
import com.example.farha_application.Acticites.Users.HomeActivity;
import com.example.farha_application.Adapters.Adapter_ProductList;
import com.example.farha_application.Adapters.Adapter_ProductListInvitation;
import com.example.farha_application.Models.product;
import com.example.farha_application.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ProductInvitationActivity extends AppCompatActivity {

    List<product> productList;
    RecyclerView recyclerView;
    String cat="";
    Adapter_ProductListInvitation adapter;
    public EditText searchView;

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
        setContentView(R.layout.activity_product_list_invitation);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);
        productList = new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);

        cat=getIntent().getStringExtra("cat");

        String url = "http://172.19.29.67:84/rest/getProduct.php?cat="+cat;
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);

        } else {
            ProductInvitationActivity.DownloadTextTask runner = new DownloadTextTask();
            runner.execute(url);
        }



        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String text) {

        ArrayList<product> filterList = new ArrayList<>();
        for (product item : productList)
        {
            if (item.getProductName().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        adapter.filteredList(filterList);
    }

    private InputStream OpenHttpConnection(String urlString) throws IOException {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");
        try {
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        } catch (Exception ex) {
            Log.d("Networking", ex.getLocalizedMessage());
            throw new IOException("Error connecting");
        }
        return in;
    }

    private String DownloadText(String URL) {
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
            while ((charRead = isr.read(inputBuffer)) > 0) {
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
        protected String doInBackground(String... strings) {
            return DownloadText(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {


            String obj[] = s.split("////");
            product pro;
            for(int i=0 ; i<obj.length ; i++)
            {

                if(! obj[i].equals(null)) {
                    String objects[] = obj[i].split(",");

                    if(!objects.equals(null)) {


                        int id = Integer.parseInt(objects[0]);
                        String proName=objects[1];
                        String proPrice=objects[2];
                        String proColor=objects[3];
                        int proToken = Integer.parseInt(objects[4]);
                        String proSize = objects[5];
                        String proImageId = objects[6];
                        String proImageId2 = objects[7];
                        String proImageId3 = objects[8];
                        String category = objects[9];
                        pro=new product(id ,proName,proPrice,proColor,proToken,proSize,proImageId,proImageId2,proImageId3,category,getIntent().getStringExtra("user_id"),getIntent().getStringExtra("invitation_id"));
                        productList.add(pro);;
                    }
                }
            }
            GridLayoutManager gridLayoutManager = new GridLayoutManager(ProductInvitationActivity.this,2,GridLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(gridLayoutManager );
            adapter = new Adapter_ProductListInvitation(ProductInvitationActivity.this, productList);
            recyclerView.setAdapter(adapter);

        }

    }

    public void insert_btn_OnClick(View view){
        Intent intent =new Intent(this, InsertProductActivity.class);
        intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
        intent.putExtra("name",getIntent().getStringExtra("name"));
        intent.putExtra("phoneNumber",getIntent().getStringExtra("phoneNumber"));
        intent.putExtra("Balance",getIntent().getStringExtra("Balance"));
        startActivity(intent);
    }
    public void back_btn_OnClick(View view){
        Intent intent = new Intent(this, CategoryListActivity.class);
        intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
        intent.putExtra("name",getIntent().getStringExtra("name"));
        intent.putExtra("phoneNumber",getIntent().getStringExtra("phoneNumber"));
        intent.putExtra("Balance",getIntent().getStringExtra("Balance"));
        startActivity(intent);
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
                Intent intent =new Intent(ProductInvitationActivity.this, wallletActivity.class);
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
                Intent intent =new Intent(ProductInvitationActivity.this, InvitationActivity.class);
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
                Intent intent =new Intent(ProductInvitationActivity.this, HomeActivity.class);
                intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
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
                Intent intent =new Intent(ProductInvitationActivity.this, EditeInformationActivity.class);
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
                Intent intent =new Intent(ProductInvitationActivity.this, FriendsListActivity.class);
                intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("phoneNumber",getIntent().getStringExtra("phoneNumber"));
                intent.putExtra("Balance",getIntent().getStringExtra("Balance"));
                startActivity(intent);
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}