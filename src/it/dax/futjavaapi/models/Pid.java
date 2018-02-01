package it.dax.futjavaapi.models;

import java.util.Map;

public class Pid{

    private String
            externalRefType,
            externalRefValue,
            pidId,
            email,
            emailStatus,
            strength,
            dob,
            country,
            language,
            locale,
            status,
            reasonCode,
            tosVersion,
            parentalEmail,
            thirdPartyOptin,
            globalOptin,
            dateCreated,
            dateModified,
            lastAuthDate,
            registrationSource,
            authenticationSource,
            showEmail,
            discoverableEmail,
            anonymousPid,
            underagePid,
            defaultBillingAddressUri,
            defaultShippingAddressUri,
            passwordSignature;

    public void fillClass(Map pidMap){
        Map<String, String> pidMapEntry = (Map<String, String>) pidMap.get("pid");

        for(Map.Entry<String, String> field : pidMapEntry.entrySet()){
            if(field.getKey().equals("externalRefType"))
                externalRefType = String.valueOf(field.getValue());
            else if(field.getKey().equals("externalRefValue"))
                externalRefValue = String.valueOf(field.getValue());
            else if(field.getKey().equals("pidId"))
                pidId = String.valueOf(field.getValue());
            else if(field.getKey().equals("email"))
                email = String.valueOf(field.getValue());
            else if(field.getKey().equals("emailStatus"))
                emailStatus = String.valueOf(field.getValue());
            else if(field.getKey().equals("strength"))
                strength = String.valueOf(field.getValue());
            else if(field.getKey().equals("dob"))
                dob = String.valueOf(field.getValue());
            else if(field.getKey().equals("country"))
                country = String.valueOf(field.getValue());
            else if(field.getKey().equals("language"))
                language = String.valueOf(field.getValue());
            else if(field.getKey().equals("locale"))
                locale = String.valueOf(field.getValue());
            else if(field.getKey().equals("status"))
                status = String.valueOf(field.getValue());
            else if(field.getKey().equals("reasonCode"))
                reasonCode = String.valueOf(field.getValue());
            else if(field.getKey().equals("tosVersion"))
                tosVersion = String.valueOf(field.getValue());
            else if(field.getKey().equals("parentalEmail"))
                parentalEmail = String.valueOf(field.getValue());
            else if(field.getKey().equals("thirdPartyOptin"))
                thirdPartyOptin = String.valueOf(field.getValue());
            else if(field.getKey().equals("globalOptin"))
                globalOptin = String.valueOf(field.getValue());
            else if(field.getKey().equals("dateCreated"))
                dateCreated = String.valueOf(field.getValue());
            else if(field.getKey().equals("dateModified"))
                dateModified = String.valueOf(field.getValue());
            else if(field.getKey().equals("lastAuthDate"))
                lastAuthDate = String.valueOf(field.getValue());
            else if(field.getKey().equals("registrationSource"))
                registrationSource = String.valueOf(field.getValue());
            else if(field.getKey().equals("authenticationSource"))
                authenticationSource = String.valueOf(field.getValue());
            else if(field.getKey().equals("showEmail"))
                showEmail = String.valueOf(field.getValue());
            else if(field.getKey().equals("discoverableEmail"))
                discoverableEmail = String.valueOf(field.getValue());
            else if(field.getKey().equals("anonymousPid"))
                anonymousPid = String.valueOf(field.getValue());
            else if(field.getKey().equals("underagePid"))
                underagePid = String.valueOf(field.getValue());
            else if(field.getKey().equals("defaultBillingAddressUri"))
                defaultBillingAddressUri = String.valueOf(field.getValue());
            else if(field.getKey().equals("defaultShippingAddressUri"))
                defaultShippingAddressUri = String.valueOf(field.getValue());
            else if(field.getKey().equals("passwordSignature"))
                passwordSignature = String.valueOf(field.getValue());
        }
    }

    public String toString(){
        return null;
    }

    /* **************************************************
     * Setter and getter methods.
     * ************************************************** */

    public String getExternalRefType() {
        return externalRefType;
    }

    public void setExternalRefType(String externalRefType) {
        this.externalRefType = externalRefType;
    }

    public String getExternalRefValue() {
        return externalRefValue;
    }

    public void setExternalRefValue(String externalRefValue) {
        this.externalRefValue = externalRefValue;
    }

    public String getPidId() {
        return pidId;
    }

    public void setPidId(String pidId) {
        this.pidId = pidId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(String emailStatus) {
        this.emailStatus = emailStatus;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getTosVersion() {
        return tosVersion;
    }

    public void setTosVersion(String tosVersion) {
        this.tosVersion = tosVersion;
    }

    public String getParentalEmail() {
        return parentalEmail;
    }

    public void setParentalEmail(String parentalEmail) {
        this.parentalEmail = parentalEmail;
    }

    public String getThirdPartyOptin() {
        return thirdPartyOptin;
    }

    public void setThirdPartyOptin(String thirdPartyOptin) {
        this.thirdPartyOptin = thirdPartyOptin;
    }

    public String getGlobalOptin() {
        return globalOptin;
    }

    public void setGlobalOptin(String globalOptin) {
        this.globalOptin = globalOptin;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getLastAuthDate() {
        return lastAuthDate;
    }

    public void setLastAuthDate(String lastAuthDate) {
        this.lastAuthDate = lastAuthDate;
    }

    public String getRegistrationSource() {
        return registrationSource;
    }

    public void setRegistrationSource(String registrationSource) {
        this.registrationSource = registrationSource;
    }

    public String getAuthenticationSource() {
        return authenticationSource;
    }

    public void setAuthenticationSource(String authenticationSource) {
        this.authenticationSource = authenticationSource;
    }

    public String getShowEmail() {
        return showEmail;
    }

    public void setShowEmail(String showEmail) {
        this.showEmail = showEmail;
    }

    public String getDiscoverableEmail() {
        return discoverableEmail;
    }

    public void setDiscoverableEmail(String discoverableEmail) {
        this.discoverableEmail = discoverableEmail;
    }

    public String getAnonymousPid() {
        return anonymousPid;
    }

    public void setAnonymousPid(String anonymousPid) {
        this.anonymousPid = anonymousPid;
    }

    public String getUnderagePid() {
        return underagePid;
    }

    public void setUnderagePid(String underagePid) {
        this.underagePid = underagePid;
    }

    public String getDefaultBillingAddressUri() {
        return defaultBillingAddressUri;
    }

    public void setDefaultBillingAddressUri(String defaultBillingAddressUri) {
        this.defaultBillingAddressUri = defaultBillingAddressUri;
    }

    public String getDefaultShippingAddressUri() {
        return defaultShippingAddressUri;
    }

    public void setDefaultShippingAddressUri(String defaultShippingAddressUri) {
        this.defaultShippingAddressUri = defaultShippingAddressUri;
    }

    public String getPasswordSignature() {
        return passwordSignature;
    }

    public void setPasswordSignature(String passwordSignature) {
        this.passwordSignature = passwordSignature;
    }

}