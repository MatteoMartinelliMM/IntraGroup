package mateomartinelli.user2cadem.it.intragroup.Controller;


import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import mateomartinelli.user2cadem.it.intragroup.R;
import mateomartinelli.user2cadem.it.intragroup.View.GroupActivity;

import static mateomartinelli.user2cadem.it.intragroup.Controller.UtilitySharedPreference.SELECTED_GROUP;

/**
 * Created by utente2.academy on 12/6/2017.
 */

public class GroupRecycleViewAdapter extends RecyclerView.Adapter<GroupRecycleViewAdapter.ViewHolder>{
    ArrayList<String> group;
    private String gruppo;
    public GroupRecycleViewAdapter(ArrayList<String> group) {
        this.group = group;
    }

    public GroupRecycleViewAdapter() {
        group = new ArrayList<String>();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView groupContainer;
        public ImageButton groupImg;
        public TextView nome;

        public ViewHolder(View v) {
            super(v);
            groupContainer = itemView.findViewById(R.id.groupContainer);
            groupImg = v.findViewById(R.id.groupButton);
            nome = v.findViewById(R.id.nomeGruppo);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder vHolder;
        View v=null;
        if(parent!=null){
             v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_group,null);
        }
        vHolder = new ViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        gruppo = group.get(position);
        holder.nome.setText(gruppo);
        loadGroupImage(holder);
        holder.groupImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (view.getContext(), GroupActivity.class);
                intent.putExtra(SELECTED_GROUP,gruppo);
                view.getContext().startActivity(intent);
            }
        });
    }

    private void loadGroupImage(ViewHolder holder) {
        switch (gruppo.toLowerCase()){
            case "android":
                holder.groupImg.setBackgroundResource(R.drawable.androidcoding);
                break;
            case "andiamodadonangelinn":
                holder.groupImg.setBackgroundResource(R.drawable.pizza);
                break;
            case "esselungalove":
                holder.groupImg.setBackgroundResource(R.drawable.esselunga);
                break;
            case "nogiargiana":
                holder.groupImg.setBackgroundResource(R.drawable.madonnina);
                break;
            default:
                holder.groupImg.setBackgroundResource(R.drawable.defcoding);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return group.size();
    }


}
