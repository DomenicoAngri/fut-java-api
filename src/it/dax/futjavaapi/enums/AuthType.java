package it.dax.futjavaapi.enums;

public enum AuthType{

    APP("APP"),
    SMS("SMS"),
    EMAIL("EMAIL"),
    UNKNOWN("");

    private String value;

    AuthType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}