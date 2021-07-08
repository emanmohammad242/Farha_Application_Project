package com.example.farha_application.Acticites.Invitation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.farha_application.Acticites.Users.EditeInformationActivity;
import com.example.farha_application.Acticites.Users.HomeActivity;
import com.example.farha_application.Adapters.Adapter_FrindList;
import com.example.farha_application.Models.ContactModel;
import com.example.farha_application.R;

import java.util.ArrayList;

public class FriendsListActivity extends AppCompatActivity {

    Toolbar toolbar ;
    Button wallet_btn,list_btn,home_btn,user_profile_btn,friends_btn;
    MenuItem wallet_count_menu_item;
    MenuItem list_count_menu_item;
    MenuItem home_count_menu_item;
    MenuItem user_profile_count_menu_item;
    MenuItem friends_count_menu_item;


    RecyclerView recyclerView ;
    ArrayList<ContactModel> arrayList = new ArrayList<ContactModel>();
    Adapter_FrindList adapter ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);


        recyclerView=findViewById(R.id.recyclerView);
        toolbar=findViewById(R.id.toolbar);
        
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);

        checkPermission();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new Adapter_FrindList(this,arrayList);
        recyclerView.setAdapter(adapter);

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
                Intent intent =new Intent(FriendsListActivity.this, wallletActivity.class);
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
                Intent intent =new Intent(FriendsListActivity.this, InvitationActivity.class);
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
                Intent intent =new Intent(FriendsListActivity.this, HomeActivity.class);
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
                Intent intent =new Intent(FriendsListActivity.this, EditeInformationActivity.class);
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
                Intent intent =new Intent(FriendsListActivity.this, FriendsListActivity.class);
                intent.putExtra("invitation_id",getIntent().getStringExtra("invitation_id"));
                intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
                startActivity(intent);
            }
        });

        return super.onCreateOptionsMenu(menu);

    }
    private void checkPermission() {
        if(ContextCompat.checkSelfPermission(FriendsListActivity.this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED)
        {

            ActivityCompat.requestPermissions(FriendsListActivity.this, new String[]{Manifest.permission.READ_CONTACTS},100);
        }else {
            getContactList();
        }
    }
    private void getContactList() {

        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ACS";
        Cursor cursor=getContentResolver().query(uri,null,null,null,null);

        if(cursor.getCount() > 0){
            while (cursor.moveToNext())
            {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                Uri uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?";
                Cursor phonecursor = getContentResolver().query(uriPhone,null , selection,new String [] {id},null);

                if(phonecursor.moveToNext()){
                    String number =phonecursor.getString(phonecursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    ContactModel model = new ContactModel(name , number,getIntent().getStringExtra("user_id"),getIntent().getStringExtra("invitation_id"),getIntent().getStringExtra("name"));
                    arrayList.add(model);

                    phonecursor.close();
                }
            }

            cursor.close();
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100 && grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            getContactList();
        }else
        {
            Toast.makeText(this,"Error" ,Toast.LENGTH_SHORT).show();
            checkPermission();
        }
    }
    public void finish_OnClick(View view) {
        Intent intent =new Intent(FriendsListActivity.this, CreatInvitationActivity.class);
        intent.putExtra("invitation_id",getIntent().getStringExtra("invitation_id"));
        intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
        startActivity(intent);
    }
}