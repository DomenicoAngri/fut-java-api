package it.dax.futjavaapi.main;

import it.dax.futjavaapi.enums.ErrorCodes;
import it.dax.futjavaapi.exceptions.EAGeneralErrorException;
import it.dax.futjavaapi.services.EAHashingAlgorithm;
import it.dax.futjavaapi.services.LoginService;
import org.apache.log4j.Logger;

public class Main{

    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String args[]){
        LoginService loginService = new LoginService();

        it.dax.futjavaapi.models.UserClubList userClubList;

        throw new EAGeneralErrorException(EAGeneralErrorException.provaErrorMessage_001);

//        try{
//            //loginService.testLogin("", "", "", "", "");
//            loginService.temporaryTest();
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }

        // testEAHashingAlgorithm("ricuttaro");
    }

    public static void testEAHashingAlgorithm(String securityAnswer){
        EAHashingAlgorithm eaHashingAlgorithm = new EAHashingAlgorithm();
        String test = eaHashingAlgorithm.hash(securityAnswer);
        System.out.println(test);
    }

}