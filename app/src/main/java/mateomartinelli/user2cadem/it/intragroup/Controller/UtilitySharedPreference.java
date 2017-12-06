package mateomartinelli.user2cadem.it.intragroup.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import mateomartinelli.user2cadem.it.intragroup.Model.User;

/**
 * Created by utente2.academy on 12/6/2017.
 */

public class UtilitySharedPreference {

    public static final String LOGGED_USER = "LoggedUser";

    public static boolean checkIfUserIsLogged(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.contains(LOGGED_USER);
    }

    public static void addLoggedUser(Context context, User u){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(LOGGED_USER,u.getUserName());
        editor.commit();
    }

    public static String getLoggedUsername(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(LOGGED_USER,"");
    }

}
