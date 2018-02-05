package it.dax.futjavaapi.models;

import java.util.Map;

public class UserClubList{

    private String
        year,
        assetId,
        teamId,
        lastAccessTime,
        platform,
        clubName,
        clubAbbr,
        established,
        divisionOnline,
        badgeId;

    private Map<String, String> skuAccessList;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(String lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubAbbr() {
        return clubAbbr;
    }

    public void setClubAbbr(String clubAbbr) {
        this.clubAbbr = clubAbbr;
    }

    public String getEstablished() {
        return established;
    }

    public void setEstablished(String established) {
        this.established = established;
    }

    public String getDivisionOnline() {
        return divisionOnline;
    }

    public void setDivisionOnline(String divisionOnline) {
        this.divisionOnline = divisionOnline;
    }

    public String getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(String badgeId) {
        this.badgeId = badgeId;
    }

    public Map<String, String> getSkuAccessList() {
        return skuAccessList;
    }

    public void setSkuAccessList(Map<String, String> skuAccessList) {
        this.skuAccessList = skuAccessList;
    }

}