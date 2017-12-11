package mateomartinelli.user2cadem.it.intragroup.Controller;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mateomartinelli.user2cadem.it.intragroup.Model.Comments;
import mateomartinelli.user2cadem.it.intragroup.R;

import static android.media.CamcorderProfile.get;

/**
 * Created by utente2.academy on 12/11/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private ArrayList<Comments> comments;

    public ChatAdapter(ArrayList<Comments> comments) {
        this.comments = comments;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView comment;
        public TextView contentComment,autore;
        public ViewHolder(View v) {
            super(v);
            comment = itemView.findViewById(R.id.singleComment);
            contentComment = v.findViewById(R.id.comment);
            autore = v.findViewById(R.id.author);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        if(parent!=null)
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_element,null);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comments commento = comments.get(position);
        holder.autore.setText(commento.getAutore());
        holder.contentComment.setText(commento.getCommento());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }


}
