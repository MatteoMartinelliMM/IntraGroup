package mateomartinelli.user2cadem.it.intragroup.Model;


import java.util.ArrayList;

/**
 * Created by utente2.academy on 12/6/2017.
 */

public class Utenti {
    private ArrayList<User> users;

    public Utenti() {
        users = new ArrayList<User>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }


    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void addUser(User u){
        this.users.add(u);
    }

    public void setUser(User u, int index){
        this.users.set(index, u);
    }

    public User bannaUser(int index){
        return users.remove(index);
    }

}
