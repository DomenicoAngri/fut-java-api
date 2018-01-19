package it.dax.futjavaapi.services;

import it.dax.futjavaapi.constants.CommonConstants;
import it.dax.futjavaapi.constants.ServicesConstants;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.ArrayList;
import java.util.List;

public class Login{

    private String cookies;

    private HttpClient httpClient;

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
        // Inizializzo la stringa dei cookie vuota.
        cookies = "";

        // Creo il client e disabilito la redirect automatica.
        httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
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

    private String getUriWithFidParam() throws Exception{
        // URL della chiamata get.
        String uri = ServicesConstants.BASE_AUTH_URI +
            ServicesConstants.PARAM_KEY_PROMPT_LOGIN + "=" + ServicesConstants.PARAM_VALUE_PROMPT_LOGIN +
            "&" + ServicesConstants.PARAM_KEY_ACCESS_TOKEN + "=" + CommonConstants.NULL_STRING +
            "&" + ServicesConstants.PARAM_KEY_CLIENT_ID + "=" + ServicesConstants.PARAM_VALUE_CLIENT_ID +
            "&" + ServicesConstants.PARAM_KEY_RESPONSE_TYPE + "=" + ServicesConstants.PARAM_VALUE_RESPONSE_TYPE_TOKEN +
            "&" + ServicesConstants.PARAM_KEY_DISPLAY + "=" + ServicesConstants.PARAM_VALUE_DISPLAY +
            "&" + ServicesConstants.PARAM_KEY_LOCALE + "=" + ServicesConstants.PARAM_VALUE_LOCALE_IT +
            "&" + ServicesConstants.PARAM_KEY_REDIRECT_URI + "=" + ServicesConstants.BASE_REDIRECT_URI +
            "&" + ServicesConstants.PARAM_KEY_SCOPE + "=" + ServicesConstants.PARAM_VALUE_SCOPE;

        // Istanzio una nuova request dall'url.
        HttpGet httpGet = new HttpGet(uri);

        // Setto i parametri nell'header della richiesta.
        // request.addHeader(ServicesConstants.PARAM_KEY_USER_AGENT, ServicesConstants.PARAM_VALUE_USER_AGENT);
        setCommonHeaderParams(httpGet);

        // Execute della request che ritorna la response.
        return httpClient.execute(httpGet).getHeaders(ServicesConstants.PARAM_KEY_LOCATION)[0].getValue();
    }

    private String getUriWithExecutionAndInitref(String uriWithFidParam) throws Exception{
        HttpGet httpGet = new HttpGet(uriWithFidParam);
        setCommonHeaderParams(httpGet);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        // TODO questi cookie non devono uscire fuori, cambiare la logica
        setCookies(httpResponse.getHeaders(ServicesConstants.PARAM_KEY_SET_COOKIE)[0].getValue());
        return httpResponse.getHeaders(ServicesConstants.PARAM_KEY_LOCATION)[0].getValue();
    }

    // TODO vedere se nelle chiamate si devono settare i cookie.
    public String postLogin(String uriWithExecutionAndInitref, String username, String password) throws Exception{
        HttpPost httpPost = new HttpPost(uriWithExecutionAndInitref);

        setCommonHeaderParams(httpPost);
        httpPost.setHeader(ServicesConstants.PARAM_KEY_CONTENT_TYPE, ServicesConstants.PARAM_VALUE_URL_ENCODED_FORM_CONTENT_TYPE);
        httpPost.setHeader(ServicesConstants.PARAM_KEY_COOKIE, getCookies());

        // Params list
        List<NameValuePair> uriParams = new ArrayList<>();
        uriParams.add(new BasicNameValuePair("email", username));
        uriParams.add(new BasicNameValuePair("password", password));
        uriParams.add(new BasicNameValuePair("country", "IT"));
        uriParams.add(new BasicNameValuePair("_rememberMe", "on"));
        uriParams.add(new BasicNameValuePair("rememberMe", "on"));
        uriParams.add(new BasicNameValuePair("_eventId", "submit"));
        uriParams.add(new BasicNameValuePair("isPhoneNumberLogin", "false"));
        uriParams.add(new BasicNameValuePair("isIncompletePhone", ""));
        uriParams.add(new BasicNameValuePair("phoneNumber", ""));
        uriParams.add(new BasicNameValuePair("passwordForPhone", ""));
        uriParams.add(new BasicNameValuePair("gCaptchaResponse", ""));

        // Setto la lista di parametri nella chiamata post
        httpPost.setEntity(new UrlEncodedFormEntity(uriParams, Consts.UTF_8));

        return httpClient.execute(httpPost).getHeaders(ServicesConstants.PARAM_KEY_LOCATION)[0].getValue();
    }

    public void fourthGet(String thirdUrl) throws Exception{
        HttpGet request = new HttpGet(thirdUrl + "&_eventId=end");
        request.setHeader("Cookie", getCookies());
        request.addHeader("User-Agent", ServicesConstants.USER_AGENT);
        HttpResponse response = client.execute(request);

        String verifyLogin = EntityUtils.toString(response.getEntity());

        Document doc = Jsoup.parse(verifyLogin);
    }

    private void setCommonHeaderParams(HttpRequestBase httpRequestBase){
        httpRequestBase.setHeader(ServicesConstants.PARAM_KEY_USER_AGENT, ServicesConstants.PARAM_VALUE_USER_AGENT);
        httpRequestBase.setHeader(ServicesConstants.PARAM_KEY_ACCEPT_ENCODING, ServicesConstants.PARAM_VALUE_ACCEPT_ENCODING);
        httpRequestBase.setHeader(ServicesConstants.PARAM_KEY_CONNECTION, ServicesConstants.PARAM_VALUE_CONNECTION);
        httpRequestBase.setHeader(ServicesConstants.PARAM_KEY_PRAGMA, ServicesConstants.PARAM_VALUE_PRAGMA);
        httpRequestBase.setHeader(ServicesConstants.PARAM_KEY_CACHE_CONTROL, ServicesConstants.PARAM_VALUE_CACHE_CONTROL);
        httpRequestBase.setHeader(ServicesConstants.PARAM_KEY_ACCEPT, ServicesConstants.PARAM_VALUE_ACCEPT);
        httpRequestBase.setHeader(ServicesConstants.PARAM_KEY_ACCEPT_LANGUAGE, ServicesConstants.PARAM_VALUE_ACCEPT_LANGUAGE_EN);
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

}