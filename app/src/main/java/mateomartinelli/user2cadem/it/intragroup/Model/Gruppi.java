package mateomartinelli.user2cadem.it.intragroup.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by utente2.academy on 12/11/2017.
 */

public class Gruppi implements Serializable {
    private String nomeGruppo;
    private ArrayList<Post> posts;

    public Gruppi(String nomeGruppo, ArrayList<Post> posts) {
        this.nomeGruppo = nomeGruppo;
        this.posts = posts;
    }
    public Gruppi(){
        posts = new ArrayList<>();
    }

    public String getNomeGruppo() {
        return nomeGruppo;
    }

    public void setNomeGruppo(String nomeGruppo) {
        this.nomeGruppo = nomeGruppo;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

}
