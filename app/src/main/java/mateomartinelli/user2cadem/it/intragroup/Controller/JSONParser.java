package mateomartinelli.user2cadem.it.intragroup.Controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by utente2.academy on 12/6/2017.
 */

public class JSONParser {
    public static void userTree(String toParse){
        try {
            JSONObject userTree = new JSONObject(toParse);
            Iterator<String> keys = userTree.keys();
            JSONArray userArray = new JSONArray();
            while (keys.hasNext()){
                String keyInExam = keys.next();
                userArray.put(userTree.get(keyInExam));
            }
            for(int i=0 ; i < userArray.length() ;  i++){

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static String getUserPwd(String toParse,String user){
        String pwd = "";
        try {
            JSONObject jsonObject = new JSONObject(toParse);
            pwd = jsonObject.getString(user);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pwd;
    }
}
