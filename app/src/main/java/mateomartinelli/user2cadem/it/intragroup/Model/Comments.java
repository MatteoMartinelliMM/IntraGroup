package mateomartinelli.user2cadem.it.intragroup.Model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by utente2.academy on 12/11/2017.
 */

public class Comments implements Serializable {
    String autore;
    String commento;

    public Comments(String autore, String commento) {
        this.autore = autore;
        this.commento = commento;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }
}
