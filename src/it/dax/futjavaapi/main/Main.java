package it.dax.futjavaapi.main;

import it.dax.futjavaapi.services.EAHashingAlgorithm;
import it.dax.futjavaapi.services.Login;

public class Main{

    public static void main(String args[]){
        // Login con libreria apache
        Login login = new Login();


        try{

            login.testLogin("domenico.angri@gmail.com", "Domy35701786!", "", "");
//            String secondUrl = login.secondGet(firstUrl);
//            String thirdUrl = login.thirdPost(secondUrl, "domenico.angri@gmail.com", "Domy35701786!");
//            login.fourthGet(thirdUrl);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        // testEAHashingAlgorithm("prova");
    }

    public static void testEAHashingAlgorithm(String securityAnswer){
        EAHashingAlgorithm eaHashingAlgorithm = new EAHashingAlgorithm();
        String test = eaHashingAlgorithm.hash(securityAnswer);
        System.out.println(test);
    }

}