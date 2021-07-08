package com.example.farha_application.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farha_application.Acticites.Invitation.DetailsProductActivity;
import com.example.farha_application.Acticites.Invitation.ProductInvitationActivity;
import com.example.farha_application.Models.category;
import com.example.farha_application.Models.product;
import com.example.farha_application.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter_ProductListInvitation extends RecyclerView.Adapter<Adapter_ProductListInvitation.ViewHolder>{
    private Context context;
    private List<product> productss;


    public Adapter_ProductListInvitation(Context context, List<product> productss) {
        this.context = context;
        this.productss = productss;
    }

    @NonNull
    @Override
    public Adapter_ProductListInvitation.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v= (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_invitation_view,parent,false);
        return new Adapter_ProductListInvitation.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_ProductListInvitation.ViewHolder holder, int position) {
        product pro = productss.get(position);
        CardView cardView=holder.cardView;

        ImageView image = cardView.findViewById(R.id.imageView);
        Picasso.get().load(pro.getProductImageId()).into(image);

        TextView text = cardView.findViewById(R.id.text);
        text.setText(pro.getProductName());

        TextView textView = cardView.findViewById(R.id.textView);
        textView.setText("Price: "+pro.getProductPric());
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(context, DetailsProductActivity.class);
                String id =pro.getId()+"";
                int i = pro.getProductToken();
                intent.putExtra("product_id",id);
                intent.putExtra("product_name",pro.getProductName());
                intent.putExtra("product_imageId1",pro.getProductImageId());
                intent.putExtra("product_imageId2",pro.getProductImageId2());
                intent.putExtra("product_imageId3",pro.getProductImageId3());
                intent.putExtra("product_price",pro.getProductPric());
                intent.putExtra("product_color",pro.getProductColor());
                intent.putExtra("product_size",pro.getProductSize());
                intent.putExtra("product_token",pro.getProductToken());
                intent.putExtra("invitation_id",pro.getInvitation_id());
                intent.putExtra("user_id",pro.getUser_id());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return productss.size();
    }

    public void filteredList(ArrayList<product> filterList) {

        productss = filterList;
        notifyDataSetChanged();
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
}
