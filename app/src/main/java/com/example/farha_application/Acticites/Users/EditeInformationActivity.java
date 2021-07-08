package com.example.farha_application.Acticites.Users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.farha_application.Acticites.Invitation.CategoeyListInvetationActivity;
import com.example.farha_application.Acticites.Invitation.FriendsListActivity;
import com.example.farha_application.Acticites.Invitation.ListOfGiftActivity;
import com.example.farha_application.R;

public class EditeInformationActivity extends AppCompatActivity {

    Toolbar toolbar ;
    Button wallet_btn,list_btn,home_btn,user_profile_btn,friends_btn;
    MenuItem wallet_count_menu_item;
    MenuItem list_count_menu_item;
    MenuItem home_count_menu_item;
    MenuItem user_profile_count_menu_item;
    MenuItem friends_count_menu_item;

    EditText name_ed, currentPassword_ed , newPassword_ed , rePassword_ed , email_ed , address_ed ;

    Button save_btn ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edite_information);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);
        setUpViews();

        String pass1 = currentPassword_ed.getText().toString();
        String pass2 = newPassword_ed.getText().toString();
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pass1!=pass2)
                {
                    newPassword_ed.setTextColor(Color.RED);
                }
            }
        });


    }

    public void setUpViews()
    {
        name_ed=findViewById(R.id.name_ed);
        currentPassword_ed=findViewById(R.id.currentPassword_ed);
        newPassword_ed=findViewById(R.id.newPassword_ed);
        rePassword_ed=findViewById(R.id.rePassword_ed);
        email_ed=findViewById(R.id.email_ed);
        address_ed=findViewById(R.id.address_ed);
        save_btn=findViewById(R.id.save_btn);

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
                Toast.makeText(EditeInformationActivity.this, "Image Button is Clicked", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(EditeInformationActivity.this, "Image Button is Clicked", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(EditeInformationActivity.this, "Image Button is Clicked", Toast.LENGTH_SHORT).show();
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
                Intent intent =new Intent(EditeInformationActivity.this, EditeInformationActivity.class);
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
                Intent intent =new Intent(EditeInformationActivity.this, FriendsListActivity.class);
                startActivity(intent);
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}