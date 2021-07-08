package com.example.farha_application.Adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farha_application.Acticites.Admin.CategoryListActivity;
import com.example.farha_application.Acticites.Invitation.DetailsProductActivity;
import com.example.farha_application.Acticites.Invitation.ListOfGiftActivity;
import com.example.farha_application.Models.cart;
import com.example.farha_application.Models.product;
import com.example.farha_application.R;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Adapter_CartList  extends RecyclerView.Adapter<Adapter_CartList.ViewHolder> {

    private Context context;
    private List<cart> cartList;
    private Button remove_btn ;
    private String id = "", product_id="";
    int remaining =0 ;

    public Adapter_CartList(Context context, List<cart> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    public Adapter_CartList() {}

    @NonNull
    @Override
    public Adapter_CartList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v= (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_view,parent,false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull Adapter_CartList.ViewHolder holder, int position) {
        cart carts = cartList.get(position);
        CardView cardView=holder.cardView;

        ImageView image = cardView.findViewById(R.id.imageView);
        Picasso.get().load(carts.getProdct_imageId()).into(image);

        TextView textView = cardView.findViewById(R.id.textView);
        textView.setText(carts.getProduct_name());

        TextView remaining_txt = cardView.findViewById(R.id.remaining_txt);
        remaining_txt.setText(carts.getRemaining()+"/"+carts.getToken());

        product_id=carts.getProduct_id();

        String arr [] = carts.getProduct_price().split(" ");
        double product_price=Double.parseDouble(arr[0]);
        product_price=product_price/carts.getToken();

        Button shared_btn = cardView.findViewById(R.id.shared_btn);
        shared_btn.setText("Shared "+ product_price+"₪");
       remaining=carts.getRemaining();
        if(carts.getToken() == carts.getRemaining()){
            shared_btn.setContextClickable(false);
        }else {
            shared_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    String restUrl = "http://172.19.29.67:84/rest/sharedGift.php";

                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.INTERNET)
                            != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.INTERNET}, 123);

                    } else{
                        Adapter_CartList.SendPostRequest runner = new SendPostRequest();
                        runner.execute(restUrl);
                    }
                    Intent intent =new Intent(context, ListOfGiftActivity.class);
                    intent.putExtra("user_id",carts.getUser_ids());
                    intent.putExtra("invitation_id",carts.getInvitation_id());
                    context.startActivity(intent);
                }
            });
        }

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(context, DetailsProductActivity.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

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



        remaining=remaining+1;

        String data = URLEncoder.encode("product_id", "UTF-8")
                + "=" + URLEncoder.encode(product_id+"", "UTF-8");

        data += "&" + URLEncoder.encode("remaining", "UTF-8") + "="
                + URLEncoder.encode(String.valueOf(remaining), "UTF-8");

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

    private class SendPostRequest extends AsyncTask<String, Void, String> {

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
