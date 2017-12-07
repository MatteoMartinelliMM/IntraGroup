package mateomartinelli.user2cadem.it.intragroup.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import mateomartinelli.user2cadem.it.intragroup.Controller.UtilitySharedPreference;
import mateomartinelli.user2cadem.it.intragroup.R;

import static mateomartinelli.user2cadem.it.intragroup.Controller.UtilitySharedPreference.SELECTED_GROUP;

public class GroupActivity extends AppCompatActivity {
    TextView groupName;
    ImageView groupImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        groupName = findViewById(R.id.nomeGruppo);
        groupImg = findViewById(R.id.immagineGruppo);
        Intent intent = getIntent();
        String nomeGruppo = intent.getStringExtra(SELECTED_GROUP);
        groupName.setText(nomeGruppo);
        selectGroupImg(nomeGruppo);
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
                groupImg.setImageResource(R.drawable.defcoding);
                break;
        }
    }
}
