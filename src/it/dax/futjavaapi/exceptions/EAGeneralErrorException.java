package it.dax.futjavaapi.exceptions;

import it.dax.futjavaapi.enums.ErrorCodes;

public class EAGeneralErrorException extends RuntimeException{

    private ErrorCodes errorCodes;

    public final static String provaErrorMessage_001 = "PROVA ERRORE MESSAGGIO GENERALE";

    public EAGeneralErrorException(String message){
        super("MESSAGGIO ERRORE GENERALE --> " + message);
    }

    public EAGeneralErrorException(ErrorCodes errorCodes){
        super("MESSAGGIO ERRORE GENERALE --> " + errorCodes.getErrorMessage());
    }

    public EAGeneralErrorException(String errorCode, String message){

    }

    public ErrorCodes getErrorCodes() {
        return errorCodes;
    }
}