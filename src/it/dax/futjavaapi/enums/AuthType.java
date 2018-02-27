package it.dax.futjavaapi.enums;

public enum AuthType{

    APP("APP"),
    SMS("SMS"),
    EMAIL("EMAIL"),
    UNKNOWN("");

    private String platform;

    AuthType(String platform){
        this.platform = platform;
    }

    public String getPlatform(){
        return platform;
    }

}