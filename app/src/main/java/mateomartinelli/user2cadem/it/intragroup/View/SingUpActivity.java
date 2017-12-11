package mateomartinelli.user2cadem.it.intragroup.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mateomartinelli.user2cadem.it.intragroup.Controller.UtilitySharedPreference;
import mateomartinelli.user2cadem.it.intragroup.Model.User;
import mateomartinelli.user2cadem.it.intragroup.Model.Utenti;
import mateomartinelli.user2cadem.it.intragroup.R;

public class SingUpActivity extends AppCompatActivity {
    EditText userName, pwd, pwd2;
    TextView  noMatch;
    private Utenti utenti;
    private FirebaseDatabase db;
    private DatabaseReference refDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        setTitle("Sing Up");
        db = FirebaseDatabase.getInstance();
        refDb = db.getReferenceFromUrl("https://nov01-6571d.firebaseio.com/Users");
        utenti = new Utenti();
        userName = findViewById(R.id.userName);
        pwd = findViewById(R.id.pwd);
        pwd2 = findViewById(R.id.confpwd);
        noMatch = findViewById(R.id.noMatch);
    }

    public void done(View v){
        boolean thereIsALoggedUser = UtilitySharedPreference.checkIfUserIsLogged(this);
        String sPwd = pwd.getText().toString();
        String sPwd2 = pwd.getText().toString();
        String sUserName = userName.getText().toString();
        User u ;
        Intent intent;
        if(sPwd.equals(sPwd2)){
           u = new User(sUserName);
           utenti.addUser(u);
           refDb.child(sUserName).child("pwd").setValue(sPwd);
           refDb.child(sUserName).child("Gruppi").setValue("");

            if(thereIsALoggedUser){
                   Toast.makeText(this,"Registration succesful",Toast.LENGTH_SHORT).show();
                   intent = new Intent(this,LoginActivity.class);
                   startActivity(intent);
                   finish();
               }else{
                   Toast.makeText(this,"Registration succesful",Toast.LENGTH_SHORT).show();
                   UtilitySharedPreference.addLoggedUser(this,u);
                   intent = new Intent(this,OverviewActivity.class);
                   startActivity(intent);
                   finish();
               }

        }else noMatch.setText("Password non corrispondenti");

    }
}
