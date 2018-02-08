package it.dax.futjavaapi.models;

public class Question{

    private String question;
    private String attempts;
    private String recoverAttempts;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAttempts() {
        return attempts;
    }

    public void setAttempts(String attempts) {
        this.attempts = attempts;
    }

    public String getRecoverAttempts() {
        return recoverAttempts;
    }

    public void setRecoverAttempts(String recoverAttempts) {
        this.recoverAttempts = recoverAttempts;
    }

}