package mateomartinelli.user2cadem.it.intragroup.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import mateomartinelli.user2cadem.it.intragroup.Controller.RWObject;
import mateomartinelli.user2cadem.it.intragroup.Controller.UtilitySharedPreference;
import mateomartinelli.user2cadem.it.intragroup.Model.Comments;
import mateomartinelli.user2cadem.it.intragroup.Model.Post;
import mateomartinelli.user2cadem.it.intragroup.R;

public class AddPostActivity extends AppCompatActivity {
    private EditText titoloPost, primoCommento;
    private TextView dataPost , autorePost;
    private FirebaseDatabase db;
    private DatabaseReference myRef;
    private String nomeGruppo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        nomeGruppo = getIntent().getStringExtra("groupName");
        db = FirebaseDatabase.getInstance();
        myRef = db.getReferenceFromUrl("https://nov01-6571d.firebaseio.com/Gruppi/"+nomeGruppo+"/Posts");
        settingXmlElements();
        autorePost.setText(UtilitySharedPreference.getLoggedUsername(this));
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String postDate = dt.format(currentTime);
        dataPost.setText(postDate);

    }

   public void aggiungiPost(View v){
        Post p = new Post();
        String titolo = titoloPost.getText().toString();
        String first = primoCommento.getText().toString();
        String autore = autorePost.getText().toString();
        Comments firstComment = new Comments(autore,first);
        Intent success = new Intent();
        if(!titolo.equals("")){
            p.setTitolo(titolo);
            p.setData(dataPost.getText().toString());
            p.setAutore(autore);
            p.setComments(firstComment);
            String lastId = UtilitySharedPreference.getLastPostId(this,nomeGruppo);
            int newId = Integer.parseInt(lastId);
            newId++;
            myRef.child(Integer.toString(newId)).child("autore").setValue(p.getAutore());
            myRef.child(Integer.toString(newId)).child("titolo").setValue(p.getTitolo());
            myRef.child(Integer.toString(newId)).child("data").setValue(p.getData());
            myRef.child(Integer.toString(newId)).child("commenti").child(p.getAutore()).setValue(firstComment.getCommento());
            RWObject.writeObject(this,"newPost",p);
            setResult(AddPostActivity.RESULT_OK,success);
            finish();
        }
    }

    private void settingXmlElements() {
        titoloPost = findViewById(R.id.titoloPost);
        primoCommento = findViewById(R.id.firstComment);
        dataPost = findViewById(R.id.dataPost);
        autorePost = findViewById(R.id.author);
    }
}
