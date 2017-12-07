package mateomartinelli.user2cadem.it.intragroup.Controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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

    public static ArrayList<String> getUsersGroups(String toParse){
        ArrayList<String> groups = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(toParse);
            Iterator<String> gruppi = jsonObject.keys();
            while (gruppi.hasNext()){
                String gruppo= gruppi.next();
                groups.add(gruppo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return groups;
    }
}
