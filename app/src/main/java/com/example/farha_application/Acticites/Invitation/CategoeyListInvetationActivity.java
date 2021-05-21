package com.example.farha_application.Acticites.Invitation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.example.farha_application.Acticites.Admin.CategoryListActivity;
import com.example.farha_application.Adapters.Adapter_CategoryList;
import com.example.farha_application.Adapters.Adapter_CategoryListInvitation;
import com.example.farha_application.Models.category;
import com.example.farha_application.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class CategoeyListInvetationActivity extends AppCompatActivity {

    List<category> categoryList;
    RecyclerView recyclerView;
    public EditText searchView;
    Adapter_CategoryListInvitation adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoey_list_invetation);

        String url = "http://192.168.1.114:84/rest/getCategory.php";
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);

        } else {
            CategoeyListInvetationActivity.DownloadTextTask runner = new DownloadTextTask();
            runner.execute(url);
        }

        categoryList = new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);

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
        ArrayList<category> filterList = new ArrayList<>();
        for (category item : categoryList)
        {
            if (item.getName_cat().toLowerCase().contains(text.toLowerCase()))
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
            category cat;
            for(int i=0 ; i<obj.length ; i++)
            {

                if(! obj[i].equals(null)) {
                    String objects[] = obj[i].split(",,");

                    if(!objects.equals(null)) {
                        int id = Integer.parseInt(objects[0]);
                        String name = objects[1];
                        String image = objects[2];
                        int num_pro = Integer.parseInt(objects[3]);
                        cat = new category(id,name, image, num_pro);
                        categoryList.add(cat);
                    }
                }
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(CategoeyListInvetationActivity.this));
            adapter = new Adapter_CategoryListInvitation(CategoeyListInvetationActivity.this, categoryList);
            recyclerView.setAdapter(adapter);

        }

    }
}