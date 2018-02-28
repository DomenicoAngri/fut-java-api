package it.dax.futjavaapi.exceptions;

import it.dax.futjavaapi.enums.GeneralErrorCodes;

public class EABadRequestException extends RuntimeException{

    public EABadRequestException(String message){
        super("General error --> " + message);
    }

    public EABadRequestException(Throwable cause){
        super("General error --> " + cause);
    }

    public EABadRequestException(String message, Throwable cause){
        super(message, cause);
    }

    public EABadRequestException(String message, Throwable cause, GeneralErrorCodes generalErrorCodes){
        super(message, cause);
    }

}