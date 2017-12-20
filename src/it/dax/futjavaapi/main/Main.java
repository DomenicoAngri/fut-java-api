package it.dax.futjavaapi.main;

import it.dax.futjavaapi.services.Login;

public class Main{

    public static void main(String args[]){

        Login login = new Login();

        // Login con libreria apache
        try{

            String firstUrl = login.firstGet();

            String secondUrl = login.secondGet(firstUrl);

            String thirdUrl = login.thirdPost(secondUrl, "domenico.angri@gmail.com", "Domy35701786!");

            login.fourthGet(thirdUrl);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        System.out.println();

    }

}