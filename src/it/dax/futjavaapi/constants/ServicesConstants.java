package it.dax.futjavaapi.constants;

public class ServicesConstants{

    public final static String


            FIFA_WEB_CLIENT                             = "FIFA-18-WEBCLIENT",

            USER_AGENT                                  = "Mozilla/5.0",
            ACCEPT_VALUE                                = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
            ACCEPT_LANGUAGE                             = "en-GB,en-US;q=0.9,en;q=0.8",
            URL_ENCODED_FORM                            = "application/x-www-form-urlencoded",
            ACCEPT_ENCODING                             = "gzip, deflate, br",

            COUNTRY                                     = "IT",
            LOCALE                                      = "it_IT",

            BASE_SIGNIN_URI                             = "https://signin.ea.com",
            BASE_AUTH_URI                               = "https://accounts.ea.com/connect/auth?",
            BASE_REDIRECT_URI                           = "https://www.easports.com/it/fifa/ultimate-team/web-app/auth.html",

            FID_URI = BASE_AUTH_URI +
                    "prompt=login" +
                    "&accessToken=null" +
                    "&client_id=" + FIFA_WEB_CLIENT +
                    "&response_type=token" +
                    "&display=web2/login" +
                    "&locale=" + LOCALE +
                    "&redirect_uri=" + BASE_REDIRECT_URI +
                    "&scope=basic.identity+offline+signin",

            PID_DATA_URI = "https://gateway.ea.com/proxy/identity/pids/me";












                ;

}