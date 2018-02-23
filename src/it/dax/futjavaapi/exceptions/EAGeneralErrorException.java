package it.dax.futjavaapi.exceptions;

public class EAGeneralErrorException extends RuntimeException{

    private final String ERROR_001 = "";

    public EAGeneralErrorException(String message){
        super(message);
    }

    public EAGeneralErrorException(String errorCode, String message){

    }

}