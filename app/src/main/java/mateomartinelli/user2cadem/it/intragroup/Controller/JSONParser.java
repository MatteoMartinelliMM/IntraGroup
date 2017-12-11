package mateomartinelli.user2cadem.it.intragroup.Controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import mateomartinelli.user2cadem.it.intragroup.Model.Post;

/**
 * Created by utente2.academy on 12/6/2017.
 */

public class JSONParser {
    public static void userTree(String toParse) {
        try {
            JSONObject userTree = new JSONObject(toParse);
            Iterator<String> keys = userTree.keys();
            JSONArray userArray = new JSONArray();
            while (keys.hasNext()) {
                String keyInExam = keys.next();
                userArray.put(userTree.get(keyInExam));
            }
            for (int i = 0; i < userArray.length(); i++) {

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getUsersGroups(String toParse) {
        ArrayList<String> groups = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(toParse);
            Iterator<String> gruppi = jsonObject.keys();
            while (gruppi.hasNext()) {
                String gruppo = gruppi.next();
                groups.add(gruppo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return groups;
    }

    private static JSONArray fromJSONObjectToJSONArray(JSONObject o) {
        JSONArray jsonArray = new JSONArray();
        try {
            Iterator<String> keys = o.keys();
            while (keys.hasNext()) {
                String inExam = keys.next();
                jsonArray.put(o.get(inExam));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static ArrayList<Post> getGroupsPosts(String toParse) {
        ArrayList<Post> posts = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        try {
            JSONObject JPosts = new JSONObject(toParse);
            JSONObject JSinglePost = new JSONObject();
            Iterator<String> postsId = JPosts.keys();
            String postInExam;
            while (postsId.hasNext()){
                postInExam = postsId.next();
                JSinglePost = JPosts.getJSONObject(postInExam);
                jsonArray  = fromJSONObjectToJSONArray(JSinglePost);
                Post p = new Post();

                p.setAutore(jsonArray.getString(0));
                p.setData(jsonArray.getString(2));
                p.setTitolo(jsonArray.getString(3));
                posts.add(p);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return posts;
    }
}
