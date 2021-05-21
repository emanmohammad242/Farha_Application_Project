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
import androidx.recyclerview.widget.RecyclerView;

import com.example.farha_application.Acticites.Admin.ProductListActivity;
import com.example.farha_application.Models.category;
import com.example.farha_application.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter_CategoryListInvitation extends RecyclerView.Adapter<Adapter_CategoryListInvitation.ViewHolder> {

    private Context context;
    private List<category> categories;

    public Adapter_CategoryListInvitation(Context context, List<category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public Adapter_CategoryListInvitation.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v= (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_invitation_view,parent,false);
        return new Adapter_CategoryListInvitation.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_CategoryListInvitation.ViewHolder holder, int position) {
        category cat = categories.get(position);
        CardView cardView=holder.cardView;
        ImageView image = cardView.findViewById(R.id.imageView);
        Picasso.get().load(cat.getImage_name()).into(image);


        TextView txt = cardView.findViewById(R.id.text);
        txt.setText(cat.getName_cat());

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(context, ProductListActivity.class);
                intent.putExtra("cat",cat.getName_cat());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount()  {
        return categories.size();
    }

    public void filteredList(ArrayList<category> filterList) {
        categories = filterList;
        notifyDataSetChanged();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;
        LinearLayout mainLayout;

        public ViewHolder(@NonNull CardView cardView) {
            super(cardView);
            this.cardView= cardView;
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
