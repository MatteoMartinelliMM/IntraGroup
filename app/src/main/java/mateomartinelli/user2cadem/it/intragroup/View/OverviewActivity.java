package mateomartinelli.user2cadem.it.intragroup.View;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import mateomartinelli.user2cadem.it.intragroup.Controller.GroupRecycleViewAdapter;
import mateomartinelli.user2cadem.it.intragroup.Controller.JSONParser;
import mateomartinelli.user2cadem.it.intragroup.Controller.RestCall;
import mateomartinelli.user2cadem.it.intragroup.Controller.TaskWaiting;
import mateomartinelli.user2cadem.it.intragroup.Controller.UtilitySharedPreference;
import mateomartinelli.user2cadem.it.intragroup.R;

public class OverviewActivity extends AppCompatActivity implements TaskWaiting{
    private ProgressDialog dialog;
    private ArrayList<String> loggedUserGroups;
    private RecyclerView groups;
    private GroupRecycleViewAdapter groupAdapter;
    private GridLayoutManager gLM;
    private TextView userName;
    private String loggedUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hidingTheTitleBar();
        setContentView(R.layout.activity_overview);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference db = database.getReferenceFromUrl("https://nov01-6571d.firebaseio.com/Users/Gruppi");
        setUsefulThings();

        loggedUserName = UtilitySharedPreference.getLoggedUsername(this);
        userName.setText(loggedUserName);

        final TaskWaiting taskWaiting = this;
        dialog = new ProgressDialog(this);
        dialog.onStart();

        getUsersGroupsList(loggedUserName, taskWaiting, this);
    }

    private void setUsefulThings() {
        userName = findViewById(R.id.loggedUser);
        groups = findViewById(R.id.groupList);
    }

    private void getUsersGroupsList(final String loggedUserName, final TaskWaiting taskWaiting, final Context context) {
        RestCall.get("Users/"+loggedUserName + "/Gruppi.json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode==200){
                    String toParse = new String(responseBody);
                    loggedUserGroups = JSONParser.getUsersGroups(toParse);
                }
                taskWaiting.waitToComplete("Caricamento completato");
                int howManyGroups = loggedUserGroups.size();
                howManyGroupPerRow(howManyGroups, context);
                groups.setLayoutManager(gLM);
                groupAdapter = new GroupRecycleViewAdapter(loggedUserGroups);
                groups.setAdapter(groupAdapter);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                taskWaiting.waitToComplete("Error: "+statusCode);

            }
        });
    }

    private void howManyGroupPerRow(int howManyGroups, Context context) {
        if(howManyGroups<=3)
            gLM = new GridLayoutManager(context,1);
        if(howManyGroups==4)
            gLM = new GridLayoutManager(context,2);
        if(howManyGroups>=5)
            gLM = new GridLayoutManager(context,3);
    }

    public void logout(View v){
        UtilitySharedPreference.removeLoggedUser(this);
        Intent intent = new Intent(this,LoginActivity.class);
        Toast.makeText(this,"Logout..",Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();
    }

    @Override
    public void waitToComplete(String s) {
        dialog.dismiss();
        dialog.cancel();
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }


    private void hidingTheTitleBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }

}
