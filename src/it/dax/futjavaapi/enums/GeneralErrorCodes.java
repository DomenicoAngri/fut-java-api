package it.dax.futjavaapi.enums;

public enum GeneralErrorCodes{

    ERROR_000(000, "TEST000");

    private final int errorNumber;
    private final String errorMessage;

    GeneralErrorCodes(final int errorNumber, final String errorMessage){
        this.errorNumber = errorNumber;
        this.errorMessage = errorMessage;
    }

    public int getErrorNumber() {
        return errorNumber;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}