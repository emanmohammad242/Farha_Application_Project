package com.example.farha_application.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farha_application.Acticites.Invitation.CategoeyListInvetationActivity;
import com.example.farha_application.Acticites.Invitation.DescriptionActivity;
import com.example.farha_application.Models.Invitation;
import com.example.farha_application.R;

import java.util.List;

public class Adapter_InvitationList extends RecyclerView.Adapter<Adapter_InvitationList.ViewHolder>{

    private List<Invitation> invitations;
    private Context context;
    private Button show,edit;
    private String id;
    private int pos =0;

    public Adapter_InvitationList( Context context,List<Invitation> invitations) {
        this.invitations=invitations;
        this.context=context;

    }

    @NonNull
    @Override
    public Adapter_InvitationList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.invitiation_list_view, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull Adapter_InvitationList.ViewHolder holder, final int position) {
        Invitation invitation = invitations.get(position);
        CardView cardView=holder.cardView;
        
        TextView txt = cardView.findViewById(R.id.text);
        txt.setText(invitation.getTitle());
        show=cardView.findViewById(R.id.show_invitee);

        if(invitation.getType().equals("inviter"))
        {
            edit.setVisibility(View.VISIBLE);
            show.setVisibility(View.INVISIBLE);
        }

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DescriptionActivity.class);
                intent.putExtra("dec",invitation.getDescription());
                intent.putExtra("user_id_data",invitation.getIduser());
                intent.putExtra("invitation_id_data",invitation.getId());
                context.startActivity(intent);
            }
        });


    }
    @Override
    public int getItemCount() {
        return  invitations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;
        ConstraintLayout mainLayout;
        public ViewHolder(@NonNull CardView cardView) {
            super(cardView);
            this.cardView= cardView;
            mainLayout = itemView.findViewById(R.id.mainlayout);
        }
    }
}
