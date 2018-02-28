package it.dax.futjavaapi.exceptions;

import it.dax.futjavaapi.enums.GeneralErrorCodes;

public class EAGeneralErrorException extends RuntimeException{

    private GeneralErrorCodes generalErrorCodes;

    public final static String provaErrorMessage_001 = "PROVA ERRORE MESSAGGIO GENERALE";

    public EAGeneralErrorException(String message){
        super("MESSAGGIO ERRORE GENERALE --> " + message);
    }

    public EAGeneralErrorException(GeneralErrorCodes generalErrorCodes){
        super("MESSAGGIO ERRORE GENERALE --> " + generalErrorCodes.getErrorMessage());
    }

    public EAGeneralErrorException(String errorCode, String message){

    }

    public GeneralErrorCodes getGeneralErrorCodes() {
        return generalErrorCodes;
    }
}