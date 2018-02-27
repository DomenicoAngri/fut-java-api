package it.dax.futjavaapi.exceptions;

public class EAGetInformationsFailedException extends RuntimeException{

    public EAGetInformationsFailedException(String message){
        super(message);
    }

    public EAGetInformationsFailedException(String message, Throwable cause){
        super(message, cause);
    }

}