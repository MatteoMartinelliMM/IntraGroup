package mateomartinelli.user2cadem.it.intragroup.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by utente2.academy on 12/6/2017.
 */

public class User implements Serializable{
    private String userName;
    private ArrayList<String> groups;

    public User(String userName) {
        this.userName = userName;
     }
     public User(String userName, ArrayList<String> groups){
        this.userName = userName;
        this.groups = groups;
     }

    public ArrayList<String> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<String> groups) {
        this.groups = groups;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String nome) {
        this.userName = nome;
    }
}
