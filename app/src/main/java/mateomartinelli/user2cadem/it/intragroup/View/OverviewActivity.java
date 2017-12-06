package mateomartinelli.user2cadem.it.intragroup.View;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import mateomartinelli.user2cadem.it.intragroup.Controller.RestCall;
import mateomartinelli.user2cadem.it.intragroup.Controller.TaskWaiting;
import mateomartinelli.user2cadem.it.intragroup.Controller.UtilitySharedPreference;
import mateomartinelli.user2cadem.it.intragroup.R;

public class OverviewActivity extends AppCompatActivity implements TaskWaiting{
    private ProgressDialog dialog;
    private ArrayList<String> loggedUserGruops;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hidingTheTitleBar();
        setContentView(R.layout.activity_overview);

        TaskWaiting taskWaiting = this;
        dialog = new ProgressDialog(this);
        dialog.onStart();
        String loggedUserName = UtilitySharedPreference.getLoggedUsername(this);
        RestCall.get(loggedUserName + "/Gruppi.json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode==200){
                    String toParse = new String(responseBody);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    @Override
    public void waitToComplete(String s) {

    }


    private void hidingTheTitleBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }

}
