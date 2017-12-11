package mateomartinelli.user2cadem.it.intragroup.Controller;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mateomartinelli.user2cadem.it.intragroup.Model.Post;
import mateomartinelli.user2cadem.it.intragroup.R;
import mateomartinelli.user2cadem.it.intragroup.View.CommentsActivity;

import static mateomartinelli.user2cadem.it.intragroup.Controller.RWObject.SAVE_POST;

/**
 * Created by utente2.academy on 12/11/2017.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder>{
    ArrayList<Post> posts;
    public PostsAdapter() {
        posts = new ArrayList<Post>();
    }

    public PostsAdapter(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView post;
        public TextView autore, dataPost,titoloPost;
        public ViewHolder(View v) {
            super(v);
            post = itemView.findViewById(R.id.singlePost);
            titoloPost = v.findViewById(R.id.titoloPost);
            autore = v.findViewById(R.id.author);
            dataPost = v.findViewById(R.id.data);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=null;
        if(parent!= null){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_element,null);
        }
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Post p = posts.get(position);

        final String postName = p.getTitolo();
        String postDate = p.getData();
        String postAuthor = p.getAutore();

        holder.titoloPost.setText(postName);
        holder.dataPost.setText(postDate);
        holder.autore.setText(postAuthor);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int typeOfClick = v.getId();
                RWObject.writeObject(v.getContext(), SAVE_POST,p);
                Intent intent = new Intent(v.getContext(), CommentsActivity.class);
                intent.putExtra(SAVE_POST,postName);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


}
