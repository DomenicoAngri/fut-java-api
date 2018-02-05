package it.dax.futjavaapi.models;

import java.util.List;

public class Personas{

    private String personaId;
    private String personaName;
    private String returningUser;
    private String trial;
    private String userState;
    private List<UserClubList> userClubList;
    private String trialFree;

    public String getPersonaId() {
        return personaId;
    }

    public void setPersonaId(String personaId) {
        this.personaId = personaId;
    }

    public String getPersonaName() {
        return personaName;
    }

    public void setPersonaName(String personaName) {
        this.personaName = personaName;
    }

    public String getReturningUser() {
        return returningUser;
    }

    public void setReturningUser(String returningUser) {
        this.returningUser = returningUser;
    }

    public String getTrial() {
        return trial;
    }

    public void setTrial(String trial) {
        this.trial = trial;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public List<UserClubList> getUserClubList() {
        return userClubList;
    }

    public void setUserClubList(List<UserClubList> userClubList) {
        this.userClubList = userClubList;
    }

    public String getTrialFree() {
        return trialFree;
    }

    public void setTrialFree(String trialFree) {
        this.trialFree = trialFree;
    }

}