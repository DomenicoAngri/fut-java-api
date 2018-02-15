package it.dax.futjavaapi.main;

import it.dax.futjavaapi.services.EAHashingAlgorithm;
import it.dax.futjavaapi.services.LoginService;

public class Main{

    public static void main(String args[]){
        LoginService loginService = new LoginService();

        it.dax.futjavaapi.models.UserClubList userClubList;

        try{
            //loginService.testLogin("", "", "", "", "");
            loginService.temporaryTest();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        // testEAHashingAlgorithm("ricuttaro");
    }

    public static void testEAHashingAlgorithm(String securityAnswer){
        EAHashingAlgorithm eaHashingAlgorithm = new EAHashingAlgorithm();
        String test = eaHashingAlgorithm.hash(securityAnswer);
        System.out.println(test);
    }

}