package com.example.farha_application.Acticites.Invitation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import com.example.farha_application.Acticites.Admin.ViewAccountActivity;
import com.example.farha_application.Acticites.Users.EditeInformationActivity;
import com.example.farha_application.Acticites.Users.HomeActivity;
import com.example.farha_application.Adapters.Adapter_CartList;
import com.example.farha_application.Adapters.Adapter_ViewAccount;
import com.example.farha_application.Models.MySingleton;
import com.example.farha_application.Models.cart;
import com.example.farha_application.Models.user;
import com.example.farha_application.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListOfGiftActivity extends AppCompatActivity {

    Toolbar toolbar ;
    Button wallet_btn,list_btn,home_btn,user_profile_btn,friends_btn;
    MenuItem wallet_count_menu_item;
    MenuItem list_count_menu_item;
    MenuItem home_count_menu_item;
    MenuItem user_profile_count_menu_item;
    MenuItem friends_count_menu_item;

    private List<cart> cartList;
    RecyclerView recyclerView;
    private TextView counter ;
    Button back_btn , finish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_gift);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);
        back_btn=findViewById(R.id.back_btn);
        finish=findViewById(R.id.finish);
        cartList=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        counter = findViewById(R.id.counter);
        loadData();

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ListOfGiftActivity.this, CreatInvitationActivity.class);
                intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
                intent.putExtra("invitation_id",getIntent().getStringExtra("invitation_id"));

                startActivity(intent);
            }
        });

    }

    private void loadData() {

        String invitation_id = getIntent().getStringExtra("invitation_id");

        String url = "http://172.19.29.67:84/rest/getGift.php?cat="+invitation_id;
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray ja = response.getJSONArray("result");

                            for (int i = 0; i < ja.length(); i++) {

                                JSONObject jsonObject = ja.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String product_id = jsonObject.getString("product_id");
                                String user_ids = jsonObject.getString("user_ids");
                                String prodct_imageId = jsonObject.getString("prodct_imageId");
                                String product_name = jsonObject.getString("product_name");
                                String product_price = jsonObject.getString("product_price");
                                int token = jsonObject.getInt("token");
                                int remaining = jsonObject.getInt("remaining");

                                    cartList.add(new cart(id, product_id, user_ids, prodct_imageId, product_name,product_price,token,remaining,invitation_id));

                            }
                            //counter.setText(cartList.size()+" ");
                            recyclerView.setLayoutManager(new LinearLayoutManager(ListOfGiftActivity.this));
                            Adapter_CartList adapter = new Adapter_CartList(ListOfGiftActivity.this, cartList);
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
                Intent intent =new Intent(ListOfGiftActivity.this, wallletActivity.class);
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
                Intent intent =new Intent(ListOfGiftActivity.this, InvitationActivity.class);
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
                Intent intent =new Intent(ListOfGiftActivity.this, HomeActivity.class);
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
                Intent intent =new Intent(ListOfGiftActivity.this, EditeInformationActivity.class);
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
                Intent intent =new Intent(ListOfGiftActivity.this, FriendsListActivity.class);
                intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("phoneNumber",getIntent().getStringExtra("phoneNumber"));
                intent.putExtra("Balance",getIntent().getStringExtra("Balance"));
                startActivity(intent);
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


    public void submitOnclick(View view)
    {}

    public void backOnClick(View view)
    {
        Intent intent =new Intent(ListOfGiftActivity.this, CategoeyListInvetationActivity.class);
        intent.putExtra("user_id",cartList.get(0).getUser_ids());
        intent.putExtra("invitation_id",getIntent().getStringExtra("invitation_id"));
        intent.putExtra("name",getIntent().getStringExtra("name"));
        intent.putExtra("phoneNumber",getIntent().getStringExtra("phoneNumber"));
        intent.putExtra("Balance",getIntent().getStringExtra("Balance"));
        startActivity(intent);
    }
}