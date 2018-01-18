package it.dax.futjavaapi.services;

import it.dax.futjavaapi.constants.ServicesConstants;
import org.apache.http.Consts;
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

    private String cookies;

    public boolean login(String username, String password, String temporaneyToken, String securityAnswer){
        // 1. Get fid
        // 2. Get execution & initref
        // 3. Login post
        // 4. Get with end parameter
        // 5. Security temp code
        // 6. Security answer

        return true;
    }

    public Login(){
        cookies = "";
    }

    public void testLogin(String username, String password, String tempCode, String securityAnswer) throws Exception{
        String urlWithFidParameter = getUrlWithFidParameter();
        System.out.println(urlWithFidParameter);
        String executionAndInitrefParameters = getExecutionAndInitrefParameters(urlWithFidParameter);
        System.out.println(ServicesConstants.BASE_SIGNIN_URL + executionAndInitrefParameters);
        // String getFromPostLogin = postLogin(ServicesConstants.BASE_SIGNIN_URL + executionAndInitrefParameters, username, password);
        String getFromPostLogin = postLoginApache(ServicesConstants.BASE_SIGNIN_URL + executionAndInitrefParameters, username, password);
        System.out.println(getFromPostLogin);
        fourthGet(ServicesConstants.BASE_SIGNIN_URL + getFromPostLogin);
    }

    private String getUrlWithFidParameter() throws Exception{
        // URL della chiamata get.
        String url = ServicesConstants.BASE_AUTH_URL +
                "prompt=" + ServicesConstants.PROMPT_LOGIN +
                "&accessToken=" + "null" +
                "&client_id=" + ServicesConstants.CLIENT_ID +
                "&response_type=" + ServicesConstants.RESPONSE_TYPE_TOKEN +
                "&display=" + ServicesConstants.DISPLAY +
                "&locale=" + ServicesConstants.LOCALE_IT +
                "&redirect_uri=" + ServicesConstants.REDIRECT_URI +
                "&scope=" + ServicesConstants.SCOPE;

        // Setto il client e disabilito la redirect automatica.
        HttpClient client = HttpClientBuilder.create().disableRedirectHandling().build();

        // Istanzio una nuova request dall'url.
        HttpGet request = new HttpGet(url);

        // Setto i parametri nell'header della richiesta.
        request.addHeader("User-Agent", ServicesConstants.USER_AGENT);

        // Execute della request che ritorna la response.
        HttpResponse response = client.execute(request);

        return response.getHeaders("Location")[0].getValue();
    }

    private String getExecutionAndInitrefParameters(String urlWithFidParameter) throws Exception{
        HttpClient client = HttpClientBuilder.create().disableRedirectHandling().build();
        HttpGet request = new HttpGet(urlWithFidParameter);
        request.addHeader("User-Agent", ServicesConstants.USER_AGENT);
        HttpResponse response = client.execute(request);
        setCookies(response.getHeaders("Set-Cookie")[0].getValue());
        return response.getHeaders("Location")[0].getValue();
    }

    public String postLoginApache(String executionAndInitrefParameters, String username, String password) throws Exception{
        HttpClient client = HttpClientBuilder.create().disableRedirectHandling().build();
        HttpPost request = new HttpPost(executionAndInitrefParameters);

        request.setHeader("User-Agent", ServicesConstants.USER_AGENT);
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("Cookie", getCookies());

        // Params list
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("email", username));
        urlParameters.add(new BasicNameValuePair("password", password));
        urlParameters.add(new BasicNameValuePair("country", "IT"));
        urlParameters.add(new BasicNameValuePair("_rememberMe", "on"));
        urlParameters.add(new BasicNameValuePair("rememberMe", "on"));
        urlParameters.add(new BasicNameValuePair("_eventId", "submit"));
        urlParameters.add(new BasicNameValuePair("isPhoneNumberLogin", "false"));
        urlParameters.add(new BasicNameValuePair("isIncompletePhone", ""));
        urlParameters.add(new BasicNameValuePair("phoneNumber", ""));
        urlParameters.add(new BasicNameValuePair("passwordForPhone", ""));
        urlParameters.add(new BasicNameValuePair("gCaptchaResponse", ""));

        // Setto la lista di parametri nella chiamata post
        request.setEntity(new UrlEncodedFormEntity(urlParameters, Consts.UTF_8));

        HttpResponse response = client.execute(request);

        return response.getHeaders("Location")[0].getValue();
    }

    public void fourthGet(String thirdUrl) throws Exception{
        // HttpClient client = HttpClientBuilder.create().build();
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(thirdUrl + "&_eventId=end");
        request.setHeader("Cookie", getCookies());
        request.addHeader("User-Agent", ServicesConstants.USER_AGENT);
        HttpResponse response = client.execute(request);

        String verifyLogin = EntityUtils.toString(response.getEntity());

        Document doc = Jsoup.parse(verifyLogin);
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

}