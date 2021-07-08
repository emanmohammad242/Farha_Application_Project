package com.example.farha_application.Acticites.Users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.farha_application.Acticites.Invitation.CategoeyListInvetationActivity;
import com.example.farha_application.Acticites.Invitation.CreatInvitationActivity;
import com.example.farha_application.Acticites.Invitation.FriendsListActivity;
import com.example.farha_application.Acticites.Invitation.InvitationActivity;
import com.example.farha_application.Acticites.Invitation.wallletActivity;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.example.farha_application.Adapters.SliderAdapter2;
import com.example.farha_application.R;


import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    SliderView sliderView;
    List<String> images = new ArrayList<>();

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
        setContentView(R.layout.activity_home); // TODO :

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);


        images.add("https://besthqwallpapers.com/img/original/17040/white-roses-bridal-bouquet-bride-and-groom-wedding-roses.jpg");
        images.add("https://ak.imgag.com/imgag/product/siteassets/general/3513191/image.jpg");
        images.add("https://wallpapercave.com/wp/wp2714794.jpg");
        images.add("https://i.ytimg.com/vi/xK8TvlyG6Ro/maxresdefault.jpg");

        sliderView=findViewById(R.id.sliderimage);
        SliderAdapter2 sliderAdapter = new SliderAdapter2(images);


        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
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
                Intent intent =new Intent(HomeActivity.this, wallletActivity.class);
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
                Intent intent =new Intent(HomeActivity.this, InvitationActivity.class);
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
                Intent intent =new Intent(HomeActivity.this, HomeActivity.class);
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
                Intent intent =new Intent(HomeActivity.this, EditeInformationActivity.class);
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
                Intent intent =new Intent(HomeActivity.this, FriendsListActivity.class);
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