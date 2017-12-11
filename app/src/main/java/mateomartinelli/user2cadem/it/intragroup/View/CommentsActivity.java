package mateomartinelli.user2cadem.it.intragroup.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mateomartinelli.user2cadem.it.intragroup.R;

import static mateomartinelli.user2cadem.it.intragroup.Controller.RWObject.SAVE_POST;

public class CommentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        setTitle(getIntent().getStringExtra(SAVE_POST));
    }
}
