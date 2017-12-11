package mateomartinelli.user2cadem.it.intragroup.Model;

/**
 * Created by utente2.academy on 12/11/2017.
 */

public class Post {
    private String autore;
    private String data;
    private String titolo;
    private Comments comments;
    private String idPost;

    public Post(String autore, String data, String titolo, Comments comments) {
        this.autore = autore;
        this.data = data;
        this.titolo = titolo;
        this.comments = comments;
    }

    public Post() {
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }
}
