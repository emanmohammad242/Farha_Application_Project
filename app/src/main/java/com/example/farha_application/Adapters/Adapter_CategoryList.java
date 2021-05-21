package com.example.farha_application.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farha_application.Acticites.Admin.EditCategoryActivity;
import com.example.farha_application.Acticites.Admin.ProductListActivity;
import com.example.farha_application.Models.category;
import com.example.farha_application.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_CategoryList extends RecyclerView.Adapter<Adapter_CategoryList.ViewHolder>{
    private Context context;
    private List<category> categories;

    private Button up_date_btn,delete_btn,add_btn;

    public Adapter_CategoryList(Context context, List<category> categories) {
        this.context = context;
        this.categories = categories;
    }


    @NonNull
    @Override
    public Adapter_CategoryList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v= (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_CategoryList.ViewHolder holder, int position) {
        category cat = categories.get(position);
        CardView cardView=holder.cardView;
        ImageView image = cardView.findViewById(R.id.imageView);
        Picasso.get().load(cat.getImage_name()).into(image);


        TextView txt = cardView.findViewById(R.id.text);
        txt.setText(cat.getName_cat());
        up_date_btn=cardView.findViewById(R.id.update_btn);
        delete_btn=cardView.findViewById(R.id.delete_btn);


        up_date_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String categoryId = cat.getId()+"";
                String pro_num = cat.getNum_ofProducts()+"";
                Intent intent =new Intent(context, EditCategoryActivity.class);
                intent.putExtra("id",categoryId);
                intent.putExtra("name",cat.getName_cat());
                intent.putExtra("image",cat.getImage_name());
                intent.putExtra("pro",pro_num);
                context.startActivity(intent);

            }
        });


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
    public int getItemCount() {
        return categories.size();
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
