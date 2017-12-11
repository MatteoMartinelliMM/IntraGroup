package mateomartinelli.user2cadem.it.intragroup.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;

import java.security.acl.Group;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import mateomartinelli.user2cadem.it.intragroup.Controller.JSONParser;
import mateomartinelli.user2cadem.it.intragroup.Controller.PostsAdapter;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        setTitle(getIntent().getStringExtra(SELECTED_GROUP));

        setXmlThings();

        Intent intent = getIntent();
        String nomeGruppo = intent.getStringExtra(SELECTED_GROUP);
        selectGroupImg(nomeGruppo);

        posts = new ArrayList<Post>();
        final TaskWaiting waiting = this;
        progressDialog = new ProgressDialog(this);
        progressDialog.onStart();
        RestCall.get("/Gruppi/" + nomeGruppo+"/Posts.json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode==200){
                    String toParse = new String(responseBody);
                     posts = JSONParser.getGroupsPosts(toParse);
                }
                waiting.waitToComplete("Caricamento Completato");
                LinearLayoutManager lm = new LinearLayoutManager(getApplicationContext());
                listaPost.setLayoutManager(lm);
                PostsAdapter postsAdapter = new PostsAdapter(posts);
                listaPost.setAdapter(postsAdapter);


            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void setXmlThings() {
        groupName = findViewById(R.id.nomeGruppo);
        groupImg = findViewById(R.id.immagineGruppo);
        listaPost = findViewById(R.id.listaPost);
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
