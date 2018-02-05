package it.dax.futjavaapi.services;

import com.google.gson.Gson;
import it.dax.futjavaapi.constants.CommonConstants;
import it.dax.futjavaapi.constants.ServicesConstants;
import it.dax.futjavaapi.models.PidInfo;
import it.dax.futjavaapi.models.ShardInfo;
import it.dax.futjavaapi.models.UserAccount;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Login{

    private String cookies;

    private HttpClient httpClient;
    private Gson gson;

    private PidInfo pidInfo;
    private ShardInfo shardInfo;
    private UserAccount userAccount;

    // TODO gestire situazione delle eccezioni nelle chiamate.

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
        gson = new Gson();

        pidInfo = new PidInfo();
        shardInfo = new ShardInfo();
        userAccount = new UserAccount();
    }

    public void testLogin(String username, String password, String oneTimeCode, String securityAnswer) throws Exception{
        String urlWithFidParameter = getUriWithFidParam();
        System.out.println(urlWithFidParameter);

        String uriWithExecutionAndInitref = getUriWithExecutionAndInitref(urlWithFidParameter);
        System.out.println(uriWithExecutionAndInitref);

        String uriPostLogin = postLogin(uriWithExecutionAndInitref, username, password);
        System.out.println(uriPostLogin);

        String uriFromWithEndParam = getWithEndParam(uriPostLogin);
        System.out.println(uriFromWithEndParam);

        String uriFromSetCodeType = postSetCodeType(uriFromWithEndParam);
        System.out.println(uriFromSetCodeType);

        String uriFromSendOneTimeCode = postSendOneTimeCode(uriFromSetCodeType, oneTimeCode);
        System.out.println(uriFromSendOneTimeCode);

        String accessToken = getAccessToken(uriFromSendOneTimeCode);
        System.out.println("Access token = " + accessToken);

        getPidData(accessToken);

        getShardsData();

        getAccountInfo();
    }

    public String getUriWithFidParam() throws Exception{
        // URI della richiesta (ServicesConstants.FID_PARAM_URI).
        // https://accounts.ea.com/connect/auth?prompt=login&accessToken=null&client_id=FIFA-18-WEBCLIENT&response_type=token&display=web2/login&locale=it_IT&redirect_uri=https://www.easports.com/it/fifa/ultimate-team/web-app/auth.html&scope=basic.identity+offline+signin

        // Istanzio una nuova request dall'url.
        HttpGet httpGet = new HttpGet(ServicesConstants.FID_URI);

        // Setto i parametri nell'header della richiesta.
        // request.addHeader(ServicesConstants.PARAM_KEY_USER_AGENT, ServicesConstants.PARAM_VALUE_USER_AGENT);
        setCommonHeaderParams(httpGet);

        // Execute della richiesta.
        HttpResponse httpResponse = httpClient.execute(httpGet);

        // Setto cookie se questi ci sono
        setCookies(httpResponse);

        // Execute della request che ritorna la response.
        return httpResponse.getHeaders("Location")[0].getValue();
    }

    public String getUriWithExecutionAndInitref(String uriWithFidParam) throws Exception{
        HttpGet httpGet = new HttpGet(uriWithFidParam);
        setCommonHeaderParams(httpGet);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        setCookies(httpResponse);
        return httpResponse.getHeaders("Location")[0].getValue();
    }

    public String postLogin(String uriWithExecutionAndInitref, String username, String password) throws Exception{
        HttpPost httpPost = new HttpPost(ServicesConstants.BASE_SIGNIN_URI + uriWithExecutionAndInitref);

        setCommonHeaderParams(httpPost);
        httpPost.setHeader("Content-Type", ServicesConstants.URL_ENCODED_FORM);

        // Params list
        List<NameValuePair> uriParams = new ArrayList<>();
        uriParams.add(new BasicNameValuePair("email", username));
        uriParams.add(new BasicNameValuePair("password", password));
        uriParams.add(new BasicNameValuePair("country", ServicesConstants.COUNTRY));
        uriParams.add(new BasicNameValuePair("_rememberMe", "on"));
        uriParams.add(new BasicNameValuePair("rememberMe", "on"));
        uriParams.add(new BasicNameValuePair("_eventId", "submit"));
        uriParams.add(new BasicNameValuePair("isPhoneNumberLogin", CommonConstants.FALSE_STRING));
        uriParams.add(new BasicNameValuePair("isIncompletePhone", CommonConstants.EMPTY_STRING));
        uriParams.add(new BasicNameValuePair("phoneNumber", CommonConstants.EMPTY_STRING));
        uriParams.add(new BasicNameValuePair("passwordForPhone", CommonConstants.EMPTY_STRING));
        uriParams.add(new BasicNameValuePair("gCaptchaResponse", CommonConstants.EMPTY_STRING));

        // Setto la lista di parametri nella chiamata post
        httpPost.setEntity(new UrlEncodedFormEntity(uriParams, Consts.UTF_8));

        HttpResponse httpResponse = httpClient.execute(httpPost);
        setCookies(httpResponse);

        return httpResponse.getHeaders("Location")[0].getValue();
    }

    public String getWithEndParam(String uriPostLocation) throws Exception{
        HttpGet httpGet = new HttpGet(ServicesConstants.BASE_SIGNIN_URI + uriPostLocation + "&_eventId=end");
        setCommonHeaderParams(httpGet);

        // TODO, forse serve il settaggio cookie a tutti?
        httpGet.setHeader("Cookie", cookies);

        HttpResponse httpResponse = httpClient.execute(httpGet);
        setCookies(httpResponse);

        // TODO fare la verifica del login.

        return httpResponse.getHeaders("Location")[0].getValue();
    }

    public String postSetCodeType(String uriFromEndParam) throws Exception{
        HttpPost httpPost = new HttpPost(ServicesConstants.BASE_SIGNIN_URI + uriFromEndParam);

        setCommonHeaderParams(httpPost);
        httpPost.setHeader("Content-Type", ServicesConstants.URL_ENCODED_FORM);

        List<NameValuePair> uriParams = new ArrayList<>();
        uriParams.add(new BasicNameValuePair("codeType", "APP"));
        uriParams.add(new BasicNameValuePair("_eventId", "submit"));
        httpPost.setEntity(new UrlEncodedFormEntity(uriParams, Consts.UTF_8));

        HttpResponse httpResponse = httpClient.execute(httpPost);
        setCookies(httpResponse);

        return httpResponse.getHeaders("Location")[0].getValue();
    }

    public String postSendOneTimeCode(String uriFromCodeType, String oneTimeCode) throws Exception{
        HttpPost httpPost = new HttpPost(ServicesConstants.BASE_SIGNIN_URI + uriFromCodeType);

        setCommonHeaderParams(httpPost);
        httpPost.setHeader("Content-Type", ServicesConstants.URL_ENCODED_FORM);

        List<NameValuePair> uriParams = new ArrayList<>();
        uriParams.add(new BasicNameValuePair("oneTimeCode", oneTimeCode));
        uriParams.add(new BasicNameValuePair("_trustThisDevice", "on"));
        uriParams.add(new BasicNameValuePair("trustThisDevice", "on"));
        uriParams.add(new BasicNameValuePair("_eventId", "submit"));
        httpPost.setEntity(new UrlEncodedFormEntity(uriParams, Consts.UTF_8));

        HttpResponse httpResponse = httpClient.execute(httpPost);
        setCookies(httpResponse);

        // TODO effettuare il check se tutto è andato bene?

        return httpResponse.getHeaders("Location")[0].getValue();
    }

    public String getAccessToken(String uriFromOneTimeCode) throws Exception{
        HttpGet httpGet = new HttpGet(uriFromOneTimeCode);
        setCommonHeaderParams(httpGet);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        setCookies(httpResponse);

        String[] uriParams = httpResponse.getHeaders("Location")[0].getValue().split("#")[1].split("&");
        for(String uriParam : uriParams)
            if(uriParam.contains("access_token"))
                return uriParam.split("=")[1];

        return CommonConstants.NULL_STRING;
    }

    public void getPidData(String accessToken) throws Exception{
        HttpGet httpGet = new HttpGet(ServicesConstants.PID_DATA_URI);
        setCommonHeaderParams(httpGet);
        httpGet.setHeader("Authorization", "Bearer " + accessToken);
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/json");
        HttpResponse httpResponse = httpClient.execute(httpGet);
        setCookies(httpResponse);
        pidInfo = gson.fromJson(EntityUtils.toString(httpResponse.getEntity(), Consts.UTF_8), PidInfo.class);
    }

    public void getShardsData() throws Exception{
        HttpGet httpGet = new HttpGet(ServicesConstants.SHARDS_DATA_URI + Long.toString(getUnixDataTimeNow()));
        setCommonHeaderParams(httpGet);
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/json");
        HttpResponse httpResponse = httpClient.execute(httpGet);
        setCookies(httpResponse);
        shardInfo = gson.fromJson(EntityUtils.toString(httpResponse.getEntity(), Consts.UTF_8), ShardInfo.class);
    }

    public void getAccountInfo() throws Exception{
        HttpGet httpGet = new HttpGet(ServicesConstants.getAccountInfoUri(ServicesConstants.getPreviouslyGameYear(), Long.toString(getUnixDataTimeNow())));
        setCommonHeaderParams(httpGet);
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/json");
        httpGet.setHeader("Easw-Session-Data-Nucleus-Id", pidInfo.getPid().getPidId());
        httpGet.setHeader("X-UT-SID", "");
        HttpResponse httpResponse = httpClient.execute(httpGet);
        setCookies(httpResponse);
        userAccount = gson.fromJson(EntityUtils.toString(httpResponse.getEntity(), Consts.UTF_8), UserAccount.class);
    }































    private void setCommonHeaderParams(HttpRequestBase httpRequestBase) {
        httpRequestBase.setHeader("User-Agent", ServicesConstants.USER_AGENT);
        httpRequestBase.setHeader("Accept-Encoding", ServicesConstants.ACCEPT_ENCODING);
        httpRequestBase.setHeader("Connection", "keep-alive");
        httpRequestBase.setHeader("Pragma", "no-cache");
        httpRequestBase.setHeader("Cache-Control", "no-cache");
        httpRequestBase.setHeader("Accept", ServicesConstants.ACCEPT_VALUE);
        httpRequestBase.setHeader("Accept-Language", ServicesConstants.ACCEPT_LANGUAGE);
        httpRequestBase.setHeader("Cookie", cookies);
    }

    private void setCookies(HttpResponse httpResponse){
        // TODO decidere se è giusto ritornare qualcoa da questi metodi void.
        try{
            if(!httpResponse.getHeaders("Set-Cookie")[0].getValue().isEmpty())
                cookies = httpResponse.getHeaders("Set-Cookie")[0].getValue();
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Non ci sono cookie da settare!");
        }
    }

    private long getUnixDataTimeNow(){
        return new Date().getTime() / 1000L;
    }

}