package it.dax.futjavaapi.main;

import it.dax.futjavaapi.services.EAHashingAlgorithm;
import it.dax.futjavaapi.services.Login;

public class Main{

    public static void main(String args[]){
        Login login = new Login();


        try{
            // login.testLogin("domenico.angri@gmail.com", "Domy35701786!", "", "");
        }
        catch(Exception e){
            e.printStackTrace();
        }

        testEAHashingAlgorithm("prova");
    }

    public static void testEAHashingAlgorithm(String securityAnswer){
        EAHashingAlgorithm eaHashingAlgorithm = new EAHashingAlgorithm();
        String test = eaHashingAlgorithm.hash(securityAnswer);
        System.out.println(test);
    }

}