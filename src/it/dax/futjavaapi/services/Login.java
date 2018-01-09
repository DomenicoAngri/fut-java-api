package it.dax.futjavaapi.services;

import it.dax.futjavaapi.constants.ServicesConstants;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.ArrayList;
import java.util.List;

public class Login{

    public String firstGet() throws Exception{
        // URL della chiamata get.
        String url = "https://accounts.ea.com/connect/auth?" +
                "prompt=" + PROMPT +
                "&accessToken=" + ACCESS_TOKEN +
                "&client_id=" + CLIENT_ID +
                "&response_type=" + RESPONSE_TYPE +
                "&display=" + DISPLAY +
                "&locale=" + LOCALE +
                "&redirect_uri=" + REDIRECT_URI +
                "&scope=" + SCOPE;

        // Setto il client e e disabilito la redirect automatica.
        HttpClient client = HttpClientBuilder.create().disableRedirectHandling().build();

        // Istanzio una nuova request dall'url.
        HttpGet request = new HttpGet(url);

        // Setto i parametri nell'header della richiesta.
        request.addHeader("User-Agent", USER_AGENT);

        // Execute della request che ritorna la response.
        HttpResponse response = client.execute(request);

        return response.getHeaders("Location")[0].getValue();
    }

    public String secondGet(String secondUrl) throws Exception{
        HttpClient client = HttpClientBuilder.create().disableRedirectHandling().build();
        HttpGet request = new HttpGet(secondUrl);
        request.addHeader("User-Agent", USER_AGENT);
        HttpResponse response = client.execute(request);
        return response.getHeaders("Location")[0].getValue();
    }

    public String thirdPost(String secondUrl, String username, String password) throws Exception{
        HttpClient client = HttpClientBuilder.create().disableRedirectHandling().build();
        HttpPost post = new HttpPost(BASE_SIGNIN_URL + secondUrl);
        post.setHeader("User-Agent", USER_AGENT);

        // Params list
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("email", username));
        urlParameters.add(new BasicNameValuePair("password", password));
        urlParameters.add(new BasicNameValuePair("_eventId", "submit"));
        urlParameters.add(new BasicNameValuePair("country", "IT"));
        urlParameters.add(new BasicNameValuePair("phoneNumber", ""));
        urlParameters.add(new BasicNameValuePair("passwordForPhone", ""));
        urlParameters.add(new BasicNameValuePair("_rememberMe", "on"));
        // urlParameters.add(new BasicNameValuePair("rememberMe", "on"));
        urlParameters.add(new BasicNameValuePair("gCaptchaResponse", ""));
        urlParameters.add(new BasicNameValuePair("isPhoneNumberLogin", "false"));
        urlParameters.add(new BasicNameValuePair("isIncompletePhone", ""));

        // Setto la lista di parametri nella chiamata post
        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);

        return response.getHeaders("Location")[0].getValue();

    }

    public void fourthGet(String thirdUrl) throws Exception{
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(thirdUrl + "&_eventId=end");
        request.addHeader("User-Agent", USER_AGENT);
        HttpResponse response = client.execute(request);

        String verifyLogin = EntityUtils.toString(response.getEntity());

        Document doc = Jsoup.parse(verifyLogin);

    }

    public String login(String username, String password){
        final String signinUrl =
                ServicesConstants.BASE_SIGNIN_URL +
                "prompt=" + ServicesConstants.PROMPT_LOGIN +
                "&accessToken=null" +
                "&client_id=" + ServicesConstants.CLIENT_ID +
                "&response_type=" + ServicesConstants.RESPONSE_TYPE_TOKEN +
                "&display=" + ServicesConstants.DISPLAY +
                "&locale=" + ServicesConstants.LOCALE_IT +
                "&redirect_uri=" + ServicesConstants.REDIRECT_URI +
                "&scope=" + ServicesConstants.SCOPE;















        return null;
    }

}