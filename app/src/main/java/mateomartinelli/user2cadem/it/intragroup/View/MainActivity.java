package mateomartinelli.user2cadem.it.intragroup.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import mateomartinelli.user2cadem.it.intragroup.Controller.UtilitySharedPreference;
import mateomartinelli.user2cadem.it.intragroup.Model.User;
import mateomartinelli.user2cadem.it.intragroup.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hidingTheTitleBar();
        setContentView(R.layout.activity_main);

        Intent intent;
        if(UtilitySharedPreference.checkIfUserIsLogged(this)){
            intent = new Intent(this,OverviewActivity.class);
            startActivity(intent);
            finish();
        }else{
            intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }
    private void hidingTheTitleBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }

}
