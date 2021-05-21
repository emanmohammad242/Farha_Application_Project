package com.example.farha_application.Acticites.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.farha_application.Adapters.Adapter_ViewAccount;
import com.example.farha_application.Models.user;
import com.example.farha_application.Models.MySingleton;
import com.example.farha_application.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewAccountActivity extends AppCompatActivity {

    private List<user> users=new ArrayList<>();
    RecyclerView recyclerView;
    String url = "http://192.168.1.114:84/rest/getUserInformation.php";
    Button back_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_acount);
        back_btn=findViewById(R.id.back_btn);
        recyclerView=findViewById(R.id.recyclerView);

        loadData();
    }

    private void loadData() {



        // output = (TextView) findViewById(R.id.jsonData);
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray ja = response.getJSONArray("result");

                            for (int i = 0; i < ja.length(); i++) {

                                JSONObject jsonObject = ja.getJSONObject(i);
                                int id = jsonObject.getInt("idUser");
                                String name = jsonObject.getString("userName");
                                String phone = jsonObject.getString("phone");
                                String address = jsonObject.getString("address");
                                String type = jsonObject.getString("type");
                                if(type.equals("user")) {
                                    users.add(new user(id, name, phone, address, type));
                                }

                            }
                            recyclerView.setLayoutManager(new LinearLayoutManager(ViewAccountActivity.this));
                            Adapter_ViewAccount adapter = new Adapter_ViewAccount(ViewAccountActivity.this, users);
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

    public void back_btn_OnClick(View view){
        Intent intent = new Intent(this,HomeAdminActivity.class);
        startActivity(intent);
    }
}