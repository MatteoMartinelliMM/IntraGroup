package mateomartinelli.user2cadem.it.intragroup.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;

import java.security.acl.Group;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import mateomartinelli.user2cadem.it.intragroup.Controller.JSONParser;
import mateomartinelli.user2cadem.it.intragroup.Controller.PostsAdapter;
import mateomartinelli.user2cadem.it.intragroup.Controller.RWObject;
import mateomartinelli.user2cadem.it.intragroup.Controller.RestCall;
import mateomartinelli.user2cadem.it.intragroup.Controller.TaskWaiting;
import mateomartinelli.user2cadem.it.intragroup.Model.Post;
import mateomartinelli.user2cadem.it.intragroup.R;

import static mateomartinelli.user2cadem.it.intragroup.Controller.UtilitySharedPreference.SELECTED_GROUP;

public class GroupActivity extends AppCompatActivity implements TaskWaiting {
    TextView groupName;
    ImageView groupImg;
    private  ArrayList<Post> posts;
    private ProgressDialog progressDialog;
    private RecyclerView listaPost;
    private FloatingActionButton addPost;
    private PostsAdapter postsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        setTitle(getIntent().getStringExtra(SELECTED_GROUP));

        setXmlThings();

        final Intent intent = getIntent();
        final String nomeGruppo = intent.getStringExtra(SELECTED_GROUP);
        selectGroupImg(nomeGruppo);

        posts = new ArrayList<Post>();
        final TaskWaiting waiting = this;
        progressDialog = new ProgressDialog(this);
        progressDialog.onStart();
        getPostsList(nomeGruppo, waiting);

        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPost = new Intent(v.getContext(), AddPostActivity.class);
                addPost.putExtra("groupName",nomeGruppo);
                startActivityForResult(addPost,1);;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
            if(AddPostActivity.RESULT_OK == resultCode){
                Post p = (Post) RWObject.readObject(this,"newPost");
                posts.add(p);
                postsAdapter.notifyDataSetChanged();
            }
        }
    }

    private void getPostsList(final String nomeGruppo, final TaskWaiting waiting) {
        RestCall.get("/Gruppi/" + nomeGruppo+"/Posts.json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode==200){
                    String toParse = new String(responseBody);
                     posts = JSONParser.getGroupsPosts(toParse,getApplicationContext(),nomeGruppo);
                }
                waiting.waitToComplete("Caricamento Completato");
                LinearLayoutManager lm = new LinearLayoutManager(getApplicationContext());
                listaPost.setLayoutManager(lm);
                 postsAdapter = new PostsAdapter(posts);
                listaPost.setAdapter(postsAdapter);


            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                waiting.waitToComplete("ERROR: "+statusCode);
            }
        });
    }

    private void setXmlThings() {
        groupName = findViewById(R.id.nomeGruppo);
        groupImg = findViewById(R.id.immagineGruppo);
        listaPost = findViewById(R.id.listaPost);
        addPost = findViewById(R.id.addPost);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }


    private void selectGroupImg(String nomeGruppo) {
        switch (nomeGruppo.toLowerCase()){
            case "android":
                groupImg.setImageResource(R.drawable.androidcoding);
                break;
            case "andiamodadonangelinn":
                groupImg.setImageResource(R.drawable.pizza);
                break;
            case "esselungalove":
                groupImg.setImageResource(R.drawable.esselunga);
                break;
            case "nogiargiana":
                groupImg.setImageResource(R.drawable.madonnina);
                break;
            default:
                groupImg.setImageResource(R.drawable.def_group);
                break;
        }
    }

    @Override
    public void waitToComplete(String s) {

    }
}
