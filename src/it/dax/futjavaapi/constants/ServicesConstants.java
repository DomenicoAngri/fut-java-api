package it.dax.futjavaapi.constants;

public class ServicesConstants{

    public final static String

            // Possiamo fare classe di stringhe fisse dinamiche qui che

            FIFA_WEB_CLIENT                             = "FIFA-18-WEBCLIENT",
            SKU                                         = "FUT18WEB",

            CURRENT_YEAR                                = "2018",
            CURRENT_SHORT_YEAR                          = "18",

            COUNTRY                                     = "IT",
            LOCALE                                      = "it_IT",

            USER_AGENT                                  = "Mozilla/5.0",
            ACCEPT_VALUE                                = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
            ACCEPT_LANGUAGE                             = "en-GB,en-US;q=0.9,en;q=0.8",
            URL_ENCODED_FORM                            = "application/x-www-form-urlencoded",
            ACCEPT_ENCODING                             = "gzip, deflate, br",

            BASE_SIGNIN_URI                             = "https://signin.ea.com",
            BASE_AUTH_URI                               = "https://accounts.ea.com/connect/auth?",
            BASE_REDIRECT_URI                           = "https://www.easports.com/it/fifa/ultimate-team/web-app/auth.html",

            FID_URI                                     = BASE_AUTH_URI +
                                                            "prompt=login" +
                                                            "&accessToken=null" +
                                                            "&client_id=" + FIFA_WEB_CLIENT +
                                                            "&response_type=token" +
                                                            "&display=web2/login" +
                                                            "&locale=" + LOCALE +
                                                            "&redirect_uri=" + BASE_REDIRECT_URI +
                                                            "&scope=basic.identity+offline+signin",

            PID_DATA_URI                                = "https://gateway.ea.com/proxy/identity/pids/me",

            SHARDS_DATA_URI                             = "https://utas.mob.v4.fut.ea.com/ut/shards/v2?_=",

            AUTH_CODE_URI                               = BASE_AUTH_URI + "client_id=FOS-SERVER&redirect_uri=nucleus:rest&response_type=code&access_token=",

            SID_CODE_URI                                = "https://utas.external.s2.fut.ea.com/ut/auth?sku_b=FFT18&";

    public final static String getAccountInfoUri(String userGameYearLong, String dateTimeNowUnix){
            return "https://utas.external.s2.fut.ea.com/ut/game/fifa18/user/accountinfo?filterConsoleLogin=true&sku=" + SKU + "&returningUserGameYear=" + userGameYearLong + "&_=" + dateTimeNowUnix;
    }

    public final static String getPreviouslyGameYear(){
            return String.valueOf(Integer.parseInt(ServicesConstants.CURRENT_YEAR)-1);
    }














}