package it.dax.futjavaapi.services;

import com.google.gson.Gson;
import it.dax.futjavaapi.constants.CommonConstants;
import it.dax.futjavaapi.constants.ServicesConstants;
import it.dax.futjavaapi.exceptions.EABadRequestException;
import it.dax.futjavaapi.exceptions.EALoginFailedException;
import it.dax.futjavaapi.models.*;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public final class LoginService {

    private Logger logger = Logger.getLogger(LoginService.class);

    private String cookies;

    private HttpClient httpClient;
    private Gson gson;

    private PidInfo pidInfo;
    private ShardInfo shardInfo;
    private UserAccount userAccount;
    private Sid sid;
    private Question question;
    private Answer answer;

    private EAHashingAlgorithm eaHashingAlgorithm;

    private String
        uriWithFidParameter,
        uriWithExecutionAndInitref,
        uriPostLogin,
        uriFromWithEndParam,
        uriFromSetCodeType,
        uriFromSendOneTimeCode,
        accessToken,
        authCode;


    // TODO DA GESTIRE!!! SOLO PER TEST!!
    String oneTimeInsertedCode;

    public LoginService(){

        // Inizializzo la stringa dei cookie vuota.
        cookies = "";

        // Creo il client e disabilito la redirect automatica.
        httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
        gson = new Gson();

        pidInfo = new PidInfo();
        shardInfo = new ShardInfo();
        userAccount = new UserAccount();
        sid = new Sid();
        question = new Question();
        answer = new Answer();

        eaHashingAlgorithm = new EAHashingAlgorithm();

        oneTimeInsertedCode = "";
    }

    public void temporaryTest() throws Exception{
        logger.debug("LOCATION: " + getUriWithFidParam(true, "https://accounts.ea.com/connect/auth?" + "prompt=login" +
                        "&accessToken=null" +
                        "&client_id=" + "asijdajisjdias" +
                        "&response_type=token" +
                        "&display=web2/loginasdaidasid" +
                        "&locale=" + "ijajijsdjaisjdia" +
                        "&redirect_uri=" + "aisidaijdiajsidjiajsidjaisjida" +
                        "&scope=basic.identity+offline+signin"));

    }

    public final void 

    public final void login(String username, String password, String oneTimeCode, String securityAnswer, Platforms platform){
        logger.debug("Ciao sono nel test login.");
        logger.info("Ciao sono nel test login.");
        logger.warn("Ciao sono nel test login.");
        logger.error("Ciao sono nel test login.");
        logger.fatal("Ciao sono nel test login.");

        try{
            uriWithFidParameter = getUriWithFidParam(false, null);
            logger.debug(uriWithFidParameter);

            uriWithExecutionAndInitref = getUriWithExecutionAndInitref(uriWithFidParameter);
            logger.debug(uriWithExecutionAndInitref);

            uriPostLogin = postLogin(uriWithExecutionAndInitref, username, password);
            logger.debug(uriPostLogin);

            uriFromWithEndParam = getWithEndParam(uriPostLogin);
            logger.debug(uriFromWithEndParam);
        }
        catch(IOException ioe){
            logger.fatal("Cannot execute request!");
            logger.fatal("CAUSE: " + ioe.getCause());
            logger.fatal("ERROR MESSAGE: " + ioe.getCause());
        }








        uriFromSetCodeType = postSetCodeType(uriFromWithEndParam, oneTimeCode.isEmpty() ? true : false);
        if(uriFromSetCodeType.equals("EXIT"))
            return;
        System.out.println(uriFromSetCodeType);

        uriFromSendOneTimeCode = postSendOneTimeCode(uriFromSetCodeType, oneTimeCode.isEmpty() ? oneTimeInsertedCode : oneTimeCode);
        System.out.println(uriFromSendOneTimeCode);

        accessToken = getAccessToken(uriFromSendOneTimeCode);
        System.out.println("Access token = " + accessToken);

        getPidData(accessToken);

        getShardsData();

        getAccountInfo();

        authCode = getAuthorizationCode(accessToken);
        System.out.println("Auth-code = " + authCode);

        postGetSidCode(authCode, platform);

        getValidateQuestion();

        postValidateAnswer(securityAnswer);

        String club = "";
        for(UserClubList userClubList : userAccount.getUserAccountInfo().getPersonas().get(0).getUserClubList())
            if(userClubList.getYear().equals("2018"))
                club += userClubList.getClubName();

        if(answer.getReason().equals("Answer is correct.")){
            System.out.println();
            System.out.println("Benvenuto " + userAccount.getUserAccountInfo().getPersonas().get(0).getPersonaName() + "!");
            System.out.println("La tua squadra si chiama: " + club + ".");
            System.out.println("Sei pronto a dominare il mercato di FUT...?!");
        }
    }











































    private String getUriWithFidParam(boolean tempTest, String uri) throws IOException{
        // Istanzio una nuova request dall'url.
        HttpGet httpGet = new HttpGet(ServicesConstants.FID_URI);

        // Setto i parametri nell'header della richiesta.
        setCommonHeaderParams(httpGet);

        // Execute della richiesta.
        HttpResponse httpResponse = httpClient.execute(httpGet);

        // Setto cookie se questi ci sono.
        setCookies(httpResponse);

        // Execute della request che ritorna la response.
        if(httpResponse.getHeaders("Location").length == 0){
            logger.error("Bad request to server.");
            throw new EABadRequestException("Bad request to server.");
        }
        else
            return httpResponse.getHeaders("Location")[0].getValue();
    }

    private String getUriWithExecutionAndInitref(String uriWithFidParam) throws IOException{
        HttpGet httpGet = new HttpGet(uriWithFidParam);
        setCommonHeaderParams(httpGet);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        setCookies(httpResponse);

        if(httpResponse.getHeaders("Location").length == 0){
            logger.error("Bad request to server.");
            throw new EABadRequestException("Bad request to server.");
        }
        else
            return httpResponse.getHeaders("Location")[0].getValue();
    }

    private String postLogin(String uriWithExecutionAndInitref, String username, String password) throws IOException{
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

        if(httpResponse.getHeaders("Location").length == 0){
            logger.error("Bad request to server.");
            throw new EABadRequestException("Bad request to server.");
        }
        else
            return httpResponse.getHeaders("Location")[0].getValue();
    }

    private String getWithEndParam(String uriPostLocation) throws IOException{
        HttpGet httpGet = new HttpGet(ServicesConstants.BASE_SIGNIN_URI + uriPostLocation + "&_eventId=end");
        setCommonHeaderParams(httpGet);

        httpGet.setHeader("Cookie", cookies);

        HttpResponse httpResponse = httpClient.execute(httpGet);
        setCookies(httpResponse);

        // TODO verificare la stringa login failed. Impostare la lingua inglese, altrimenti non la prende.

        if(EntityUtils.toString(httpResponse.getEntity(), Consts.UTF_8).contains("Login failed")){
            logger.error("Bad credential. Login failed.");
            throw new EALoginFailedException("Bad credential. Login failed.");
        }
        else
            return httpResponse.getHeaders("Location")[0].getValue();
    }

    private String postSetCodeType(String uriFromEndParam, boolean verifyMod){
        HttpPost httpPost = new HttpPost(ServicesConstants.BASE_SIGNIN_URI + uriFromEndParam);

        setCommonHeaderParams(httpPost);
        httpPost.setHeader("Content-Type", ServicesConstants.URL_ENCODED_FORM);

        // TODO da gestire

        String codeType = "APP";
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        if(verifyMod){
            do{
                System.out.println();
                System.out.println("Autenticazione a due fattori.");
                System.out.println("1) Invia il codice per email.");
                System.out.println("2) Invia il codice per SMS.");
                System.out.println("3) Esci.");
                choice = sc.nextInt();
            }
            while(choice < 1 || choice > 3);

            switch(choice){
                case 1:
                    codeType = "EMAIL";
                    break;
                case 2:
                    codeType = "SMS";
                    break;
                case 3:
                    System.out.println();
                    System.out.println("LoginService annullato.");
                    return "EXIT";
            }
        }

        List<NameValuePair> uriParams = new ArrayList<>();
        uriParams.add(new BasicNameValuePair("codeType", codeType));
        uriParams.add(new BasicNameValuePair("_eventId", "submit"));
        httpPost.setEntity(new UrlEncodedFormEntity(uriParams, Consts.UTF_8));

        HttpResponse httpResponse = httpClient.execute(httpPost);
        setCookies(httpResponse);

        if(verifyMod){
            System.out.println();
            System.out.println("Inserisci il codice che hai ricevuto: ");
            oneTimeInsertedCode = sc.next();
        }

        return httpResponse.getHeaders("Location")[0].getValue();
    }

    private String postSendOneTimeCode(String uriFromCodeType, String oneTimeCode){
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

    private String getAccessToken(String uriFromOneTimeCode){
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

    private void getPidData(String accessToken){
        HttpGet httpGet = new HttpGet(ServicesConstants.PID_DATA_URI);
        setCommonHeaderParams(httpGet);
        httpGet.setHeader("Authorization", "Bearer " + accessToken);
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/json");
        HttpResponse httpResponse = httpClient.execute(httpGet);
        setCookies(httpResponse);
        pidInfo = gson.fromJson(EntityUtils.toString(httpResponse.getEntity(), Consts.UTF_8), PidInfo.class);
    }

    private void getShardsData(){
        HttpGet httpGet = new HttpGet(ServicesConstants.SHARDS_DATA_URI + Long.toString(getUnixDataTimeNow()));
        setCommonHeaderParams(httpGet);
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/json");
        HttpResponse httpResponse = httpClient.execute(httpGet);
        setCookies(httpResponse);
        shardInfo = gson.fromJson(EntityUtils.toString(httpResponse.getEntity(), Consts.UTF_8), ShardInfo.class);
    }

    private void getAccountInfo(){
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

    private String getAuthorizationCode(String accessToken){
        HttpGet httpGet = new HttpGet(ServicesConstants.AUTH_CODE_URI + accessToken);
        setCommonHeaderParams(httpGet);
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/json");
        HttpResponse httpResponse = httpClient.execute(httpGet);
        setCookies(httpResponse);
        Map<String, String> authCodeMap = gson.fromJson(EntityUtils.toString(httpResponse.getEntity(), Consts.UTF_8), Map.class);
        return authCodeMap.get("code");
    }

    private void postGetSidCode(String authCode, Platforms platform){
        HttpPost httpPost = new HttpPost(ServicesConstants.SID_CODE_URI + Long.toString(getUnixDataTimeNow()));

        setCommonHeaderParams(httpPost);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        StringEntity stringEntity = new StringEntity(ServicesConstants.getJsonSidCode(authCode, userAccount.getUserAccountInfo().getPersonas().get(0).getPersonaId(), ServicesConstants.getGameSkuFromPlatform(platform)));
        httpPost.setEntity(stringEntity);

        HttpResponse httpResponse = httpClient.execute(httpPost);
        setCookies(httpResponse);

        sid = gson.fromJson(EntityUtils.toString(httpResponse.getEntity(), Consts.UTF_8), Sid.class);
    }

    private void getValidateQuestion(){
        HttpGet httpGet = new HttpGet(ServicesConstants.VALIDATE_QUESTION_URI + Long.toString(getUnixDataTimeNow()));
        setCommonHeaderParams(httpGet);
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/json");
        httpGet.setHeader("Easw-Session-Data-Nucleus-Id", pidInfo.getPid().getPidId());
        httpGet.setHeader("X-UT-SID", sid.getSid());
        HttpResponse httpResponse = httpClient.execute(httpGet);
        setCookies(httpResponse);
        question = gson.fromJson(EntityUtils.toString(httpResponse.getEntity(), Consts.UTF_8), Question.class);
    }

    private void postValidateAnswer(String securityAnswer){
        HttpPost httpPost = new HttpPost(ServicesConstants.VALIDATE_ANSWER_URI + eaHashingAlgorithm.hash(securityAnswer));
        setCommonHeaderParams(httpPost);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("Easw-Session-Data-Nucleus-Id", pidInfo.getPid().getPidId());
        httpPost.setHeader("X-UT-SID", sid.getSid());
        HttpResponse httpResponse = httpClient.execute(httpPost);
        setCookies(httpResponse);
        answer = gson.fromJson(EntityUtils.toString(httpResponse.getEntity(), Consts.UTF_8), Answer.class);
    }

    private void setCommonHeaderParams(HttpRequestBase httpRequestBase){
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