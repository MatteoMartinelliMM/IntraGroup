package mateomartinelli.user2cadem.it.intragroup.Controller;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mateomartinelli.user2cadem.it.intragroup.R;

/**
 * Created by utente2.academy on 12/6/2017.
 */

public class GroupRecycleView extends RecyclerView.Adapter<GroupRecycleView.ViewHolder>{
    ArrayList<String> group;

    public GroupRecycleView(ArrayList<String> group) {
        this.group = group;
    }

    public GroupRecycleView() {
        group = new ArrayList<String>();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView groupContainer;
        public ImageButton groupImg;
        public ViewHolder(View v) {
            super(v);
            groupContainer = itemView.findViewById(R.id.groupContainer);
            groupImg = v.findViewById(R.id.groupButton);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return group.size();
    }


}
