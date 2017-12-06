package mateomartinelli.user2cadem.it.intragroup.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import mateomartinelli.user2cadem.it.intragroup.Controller.JSONParser;
import mateomartinelli.user2cadem.it.intragroup.Controller.RestCall;
import mateomartinelli.user2cadem.it.intragroup.Controller.UtilitySharedPreference;
import mateomartinelli.user2cadem.it.intragroup.Model.User;
import mateomartinelli.user2cadem.it.intragroup.Model.Utenti;
import mateomartinelli.user2cadem.it.intragroup.R;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    TextView noUsr;
    private boolean pwdMatching,usrMatching;
    private String usr,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hidingTheTitleBar();
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        noUsr = findViewById(R.id.warning);


        usrMatching = false;
        pwdMatching = false;

    }

    private void hidingTheTitleBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }

    public void singUp(View v){
        Intent intent = new Intent(this,SingUpActivity.class);
        startActivity(intent);
    }

    public void login(View v){
        Intent intent;
        usr  = username.getText().toString();
        pwd = password.getText().toString();
        String relativeUrl = "Users/" + usr + "/pwd.json";
        if(!(usr.equals("") && pwd.equals(""))){
            pwdMatching(relativeUrl);
            if(pwdMatching && usrMatching){
                User user = new User(usr);
                UtilitySharedPreference.addLoggedUser(this,user);
                intent = new Intent(this,OverviewActivity.class);
                startActivity(intent);
                Toast.makeText(this,"Loggin Succesful",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void pwdMatching(String relativeUrl) {
        RestCall.get(relativeUrl, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode==200){
                    String serverPwd = new String(responseBody);
                    if(!serverPwd.equals("null")) {
                        usrMatching = true;
                        serverPwd = serverPwd.substring(1, serverPwd.length() - 1);
                        pwdMatching = pwd.equals(serverPwd);
                    }else {
                        noUsr.setText("Username o password errati");
                    }
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
