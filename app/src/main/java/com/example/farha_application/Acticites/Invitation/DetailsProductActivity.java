package com.example.farha_application.Acticites.Invitation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.farha_application.Acticites.Users.EditeInformationActivity;
import com.example.farha_application.Acticites.Users.HomeActivity;
import com.example.farha_application.Adapters.SliderAdapter;
import com.example.farha_application.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class DetailsProductActivity extends AppCompatActivity {

    TextView product_name_txt , name_txt , price_txt ,counter ;
    SliderView sliderView;
    List<String> images = new ArrayList<>();
    Toolbar toolbar ;
    Button wallet_btn,list_btn,home_btn,user_profile_btn,friends_btn,add_to_list_btn,back_btn,gift_list;
    MenuItem wallet_count_menu_item;
    MenuItem list_count_menu_item;
    MenuItem home_count_menu_item;
    MenuItem user_profile_count_menu_item;
    MenuItem friends_count_menu_item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_product);

        name_txt=findViewById(R.id.name_txt);
        price_txt=findViewById(R.id.price_txt);
        product_name_txt=findViewById(R.id.product_name_txt);
        counter=findViewById(R.id.counter);
        add_to_list_btn=findViewById(R.id.add_to_list_btn);
        back_btn=findViewById(R.id.back_btn);
        gift_list=findViewById(R.id.gift_list);

        name_txt.setText(getIntent().getStringExtra("product_name"));
        price_txt.setText("Price: "+getIntent().getStringExtra("product_price"));
        product_name_txt.setText(getIntent().getStringExtra("product_name"));
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);

        images.add(getIntent().getStringExtra("product_imageId1"));
        images.add(getIntent().getStringExtra("product_imageId2"));
        images.add(getIntent().getStringExtra("product_imageId3"));


        gift_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(DetailsProductActivity.this, ListOfGiftActivity.class);
                intent.putExtra("invitation_id",getIntent().getStringExtra("invitation_id"));
                intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
                startActivity(intent);

            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(DetailsProductActivity.this, CategoeyListInvetationActivity.class);

                intent.putExtra("invitation_id",getIntent().getStringExtra("invitation_id"));
                intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
                startActivity(intent);
            }
        });

        // Adding the Adapter to the ViewPager
        sliderView=findViewById(R.id.sliderimage);
        SliderAdapter sliderAdapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();


    }

    public void add_to_cartOnClick(View view){

        String restUrl = "http://172.19.29.67:84/rest/add_to_cart.php";
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);

        } else{
            DetailsProductActivity.SendPostRequest runner = new SendPostRequest();
            runner.execute(restUrl);
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
                Intent intent =new Intent(DetailsProductActivity.this, wallletActivity.class);

                intent.putExtra("invitation_id",getIntent().getStringExtra("invitation_id"));
                intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
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
                Intent intent =new Intent(DetailsProductActivity.this, InvitationActivity.class);

                intent.putExtra("invitation_id",getIntent().getStringExtra("invitation_id"));
                intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
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
                Intent intent =new Intent(DetailsProductActivity.this, HomeActivity.class);

                intent.putExtra("invitation_id",getIntent().getStringExtra("invitation_id"));
                intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
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
                Intent intent =new Intent(DetailsProductActivity.this, EditeInformationActivity.class);

                intent.putExtra("invitation_id",getIntent().getStringExtra("invitation_id"));
                intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
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
                Intent intent =new Intent(DetailsProductActivity.this, FriendsListActivity.class);

                intent.putExtra("invitation_id",getIntent().getStringExtra("invitation_id"));
                intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
                startActivity(intent);
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
    private String processRequest(String restUrl) throws UnsupportedEncodingException {


        String product_id = getIntent().getStringExtra("product_id");
        String user_id =getIntent().getStringExtra("user_id");
        String image=getIntent().getStringExtra("product_imageId1");
        String product_name=getIntent().getStringExtra("product_name");
        String product_price=getIntent().getStringExtra("product_price");
        int token=getIntent().getIntExtra("product_token",0);
        int remaining=0;
        String invitation_id=getIntent().getStringExtra("invitation_id");

        String data = URLEncoder.encode("id_product", "UTF-8")
                + "=" + URLEncoder.encode(product_id, "UTF-8");

        data += "&" + URLEncoder.encode("id_user", "UTF-8") + "="
                + URLEncoder.encode(user_id, "UTF-8");


        data += "&" + URLEncoder.encode("product_name", "UTF-8") + "="
                + URLEncoder.encode(product_name, "UTF-8");


        data += "&" + URLEncoder.encode("prodct_imageId", "UTF-8") + "="
                + URLEncoder.encode(image, "UTF-8");

        data += "&" + URLEncoder.encode("product_price", "UTF-8") + "="
                + URLEncoder.encode(product_price, "UTF-8");


        data += "&" + URLEncoder.encode("token", "UTF-8") + "="
                + URLEncoder.encode(String.valueOf(token), "UTF-8");

        data += "&" + URLEncoder.encode("remaining", "UTF-8") + "="
                + URLEncoder.encode(String.valueOf(remaining), "UTF-8");

        data += "&" + URLEncoder.encode("invitation_id", "UTF-8") + "="
                + URLEncoder.encode(invitation_id, "UTF-8");


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