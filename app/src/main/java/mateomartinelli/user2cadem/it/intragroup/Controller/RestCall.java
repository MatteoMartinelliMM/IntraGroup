package mateomartinelli.user2cadem.it.intragroup.Controller;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by utente2.academy on 12/6/2017.
 */

public class RestCall {
    private static final String BASE_URL = "https://nov01-6571d.firebaseio.com/";
    private static AsyncHttpClient client= new AsyncHttpClient();
    public static void get(String url, AsyncHttpResponseHandler asyncHttpResponseHandler){
        client.get(getAbsoluteUrl(url),null,asyncHttpResponseHandler );
    }

    public static void post(String url, AsyncHttpResponseHandler asyncHttpResponseHandler){
        client.post(getAbsoluteUrl(url),null,asyncHttpResponseHandler );
    }

    private static String getAbsoluteUrl(String relativeUrl){
        return BASE_URL + relativeUrl;
    }
}



