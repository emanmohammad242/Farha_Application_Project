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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farha_application.Acticites.Admin.CategoryListActivity;
import com.example.farha_application.Acticites.Admin.EditProductActivity;
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
import java.util.List;

public class Adapter_ProductList  extends RecyclerView.Adapter<Adapter_ProductList.ViewHolder>{

    private Context context;
    private List<product> productss;
    Button up_date_btn,delete_btn,add_btn;
    String ids="";

    public Adapter_ProductList(Context context, List<product> productss) {
        this.context = context;
        this.productss = productss;
    }

    public Adapter_ProductList() {}

    @NonNull
    @Override
    public Adapter_ProductList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v= (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        product pro = productss.get(position);
        CardView cardView=holder.cardView;

        ImageView image = cardView.findViewById(R.id.imageView);
        Picasso.get().load(pro.getProductImageId2()).into(image);

        TextView txt = cardView.findViewById(R.id.text);
        txt.setText(pro.getProductName());

        up_date_btn=cardView.findViewById(R.id.update_btn);
        delete_btn=cardView.findViewById(R.id.delete_btn);
        add_btn=cardView.findViewById(R.id.update_btn);


        up_date_btn.setOnClickListener(new View.OnClickListener()
                                       {

                                           @Override
                                           public void onClick(View v) {
                                               Intent intent =new Intent(context, EditProductActivity.class);
                                               String ids = pro.getId()+"" , tokens= pro.getProductToken()+"";
                                               intent.putExtra("id",ids);
                                               intent.putExtra("name",pro.getProductName());
                                               intent.putExtra("price",pro.getProductPric());
                                               intent.putExtra("color",pro.getProductColor());
                                               intent.putExtra("size",pro.getProductSize());
                                               intent.putExtra("token",tokens);
                                               intent.putExtra("imageId",pro.getProductImageId());
                                               intent.putExtra("category",pro.getCategory());
                                               context.startActivity(intent);
                                           }
                                       }
        );

        delete_btn.setOnClickListener(new View.OnClickListener()
                                      {
                                          @Override
                                          public void onClick(View v) {
                                              String restUrl = "http://172.19.29.67:84/rest/deleteProduct.php";
                                              ids=pro.getId()+"";
                                              if (ContextCompat.checkSelfPermission(context, Manifest.permission.INTERNET)
                                                      != PackageManager.PERMISSION_GRANTED) {

                                                  ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.INTERNET}, 123);

                                              } else{
                                                  Adapter_ProductList.SendPostRequest runner = new SendPostRequest();
                                                  runner.execute(restUrl);
                                              }
                                              Intent intent =new Intent(context, CategoryListActivity.class);
                                              context.startActivity(intent);
                                          }
                                      }
        );
    }

    @Override
    public int getItemCount() {return productss.size();}

    public  class ViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;
        LinearLayout mainLayout;
        public ViewHolder(@NonNull CardView cardView) {
            super(cardView);
            this.cardView= cardView;
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

    private String processRequest(String restUrl) throws UnsupportedEncodingException {





        String data = URLEncoder.encode("id", "UTF-8")
                + "=" + URLEncoder.encode(ids+"", "UTF-8");

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
