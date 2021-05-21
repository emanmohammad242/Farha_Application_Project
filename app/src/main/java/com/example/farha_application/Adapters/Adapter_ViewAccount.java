package com.example.farha_application.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farha_application.Models.category;
import com.example.farha_application.Models.user;
import com.example.farha_application.R;

import java.util.List;

public class Adapter_ViewAccount extends RecyclerView.Adapter<Adapter_ViewAccount.ViewHolder>{

    private Context context;
    private List<user> users;
    private Button view_btn ;

    public Adapter_ViewAccount(Context context, List<user> users) {
        this.context = context;
        this.users = users;
    }

    public Adapter_ViewAccount() {}


    @NonNull
    @Override
    public Adapter_ViewAccount.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v= (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.view_account_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_ViewAccount.ViewHolder holder, int position) {
        user Users = users.get(position);
        CardView cardView=holder.cardView;

        TextView text = cardView.findViewById(R.id.text);
        view_btn=cardView.findViewById(R.id.view_btn);
        text.setText(Users.getName());


    }

    @Override
    public int getItemCount() {
        return users.size();
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
