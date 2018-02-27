package it.dax.futjavaapi.enums;

public enum ErrorCodes{

    ERROR_000(000, "Test_000"),
    ERROR_001(001, "Test_001");

    private final int errorNumber;
    private final String errorMessage;

    ErrorCodes(final int errorNumber, final String errorMessage){
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