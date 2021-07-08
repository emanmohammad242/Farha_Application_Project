package com.example.farha_application.Adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farha_application.Acticites.Admin.CategoryListActivity;
import com.example.farha_application.Acticites.Admin.InsertCategoryActivity;
import com.example.farha_application.Acticites.Invitation.FriendsListActivity;
import com.example.farha_application.Models.ContactModel;
import com.example.farha_application.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

public class Adapter_FrindList  extends RecyclerView.Adapter<Adapter_FrindList.ViewHolder>{

    private Context context;
    private List<ContactModel> contactModels;
    private Button insertf_btn ;

    private TextView tv_name ;
    private TextView tv_number ;
    private String name="", number="", invitation_id="",user_id="",name_person="";
    public Adapter_FrindList(Context context, List<ContactModel> contactModels) {
        this.context = context;
        this.contactModels = contactModels;
    }

    @NonNull
    @Override
    public Adapter_FrindList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v= (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_list_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactModel pro = contactModels.get(position);
        CardView cardView=holder.cardView;

        TextView tv_name = cardView.findViewById(R.id.tv_name);
        TextView tv_number = cardView.findViewById(R.id.tv_number);
        insertf_btn=cardView.findViewById(R.id.insertf_btn);
        tv_name.setText(pro.getName());
        tv_number.setText(pro.getNumber());

          name=pro.getName();
          number=pro.getNumber();
          invitation_id = pro.getInvitation_id();
          user_id=pro.getUser_id();

        insertf_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String restUrl = "http://172.19.29.67:84/rest/add_friend.php";

                if (ContextCompat.checkSelfPermission(context, Manifest.permission.INTERNET)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.INTERNET}, 123);

                }else{
                    Adapter_FrindList.SendPostRequest runner = new SendPostRequest();
                    runner.execute(restUrl);
                }

                Intent intent = new Intent(context, FriendsListActivity.class);
                intent.putExtra("invitation_id",invitation_id);
                intent.putExtra("user_id",user_id);

                context.startActivity(intent);
            }
        });



    }
    @Override
    public int getItemCount() {return contactModels.size();}
    public  class ViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;
        ConstraintLayout mainLayout;
        public ViewHolder(@NonNull CardView cardView) {
            super(cardView);
            this.cardView= cardView;
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
    private String processRequest(String restUrl) throws UnsupportedEncodingException {

        String data = URLEncoder.encode("id_user", "UTF-8")
                + "=" + URLEncoder.encode(user_id, "UTF-8");

        data += "&" + URLEncoder.encode("invitation_id", "UTF-8") + "="
                + URLEncoder.encode(invitation_id, "UTF-8");

        data += "&" + URLEncoder.encode("friend_name", "UTF-8") + "="
                + URLEncoder.encode(name, "UTF-8");


        data += "&" + URLEncoder.encode("phone_number", "UTF-8") + "="
                + URLEncoder.encode(number, "UTF-8");


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

        }
    }
}
