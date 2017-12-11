package mateomartinelli.user2cadem.it.intragroup.View;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import mateomartinelli.user2cadem.it.intragroup.Controller.ChatAdapter;
import mateomartinelli.user2cadem.it.intragroup.Controller.JSONParser;
import mateomartinelli.user2cadem.it.intragroup.Controller.RWObject;
import mateomartinelli.user2cadem.it.intragroup.Controller.RestCall;
import mateomartinelli.user2cadem.it.intragroup.Controller.TaskWaiting;
import mateomartinelli.user2cadem.it.intragroup.Controller.UtilitySharedPreference;
import mateomartinelli.user2cadem.it.intragroup.Model.Comments;
import mateomartinelli.user2cadem.it.intragroup.Model.Post;
import mateomartinelli.user2cadem.it.intragroup.R;

import static mateomartinelli.user2cadem.it.intragroup.Controller.RWObject.SAVE_POST;

public class CommentsActivity extends AppCompatActivity implements TaskWaiting {
    private RecyclerView chatList;
    private EditText newComment;
    private ArrayList<Comments> comments;
    private ProgressDialog dialog;
    private TaskWaiting waiting;
    private LinearLayoutManager lm;
    private ChatAdapter chatAdapter;
    private FirebaseDatabase db;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        setTitle(getIntent().getStringExtra(SAVE_POST));

        String selectedGroup = getIntent().getStringExtra(SAVE_POST);
        Post p = (Post) RWObject.readObject(this, SAVE_POST);
        String id = p.getIdPost();
        String nomeGruppo = p.getRefGroupName();

        settingXmlItems();

        db = FirebaseDatabase.getInstance();
        myRef = db.getReferenceFromUrl("https://nov01-6571d.firebaseio.com/Gruppi/"+nomeGruppo+"/Posts/"+id+"/commenti");

        comments = new ArrayList<>();
        lm = new LinearLayoutManager(this);

        startingTheProgressDialog();

        getCommentsList(id, nomeGruppo);
    }

    public void send(View v){
        String lastCommentId = UtilitySharedPreference.getLastCommentId(this);
        int newLastId = Integer.parseInt(lastCommentId);
        String loggedUserName = UtilitySharedPreference.getLoggedUsername(this);
        newLastId++;
        lastCommentId = Integer.toString(newLastId);
        final String commenToAdd = newComment.getText().toString();
        final Comments commentoDaAggiunger = new Comments(loggedUserName,commenToAdd);
        comments.add(commentoDaAggiunger);
        myRef.child(lastCommentId).child("Autore").setValue(loggedUserName);
        myRef.child(lastCommentId).child("commento").setValue(commenToAdd);
        comments.add(commentoDaAggiunger);
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                chatAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void getCommentsList(String id, String nomeGruppo) {
        RestCall.get("Gruppi/" + nomeGruppo + "/Posts/" + id + "/commenti.json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String toParse = new String(responseBody);
                    comments = JSONParser.getAllComments(toParse, getApplicationContext());
                }
                waiting.waitToComplete("Caricamento completato");
                chatAdapter = new ChatAdapter(comments);
                chatList.setLayoutManager(lm);
                chatList.setAdapter(chatAdapter);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                waiting.waitToComplete("ERROR: " + statusCode);
            }
        });
    }

    private void startingTheProgressDialog() {
        waiting = this;
        dialog = new ProgressDialog(this);
        dialog.onStart();
    }

    private void settingXmlItems() {
        chatList = findViewById(R.id.conversation);
        newComment = findViewById(R.id.addComment);
    }

    @Override
    public void waitToComplete(String s) {
        dialog.dismiss();
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        dialog.cancel();
    }
}
