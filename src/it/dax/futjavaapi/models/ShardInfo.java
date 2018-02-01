package it.dax.futjavaapi.models;

import java.util.List;

public class ShardInfo{

    private String shardId;
    private String clientFacingIpPort;
    private String clientProtocol;
    private String[] platforms;
    private String[] customData;
    private String[] skus;


//    private List<String> platforms;
//    private List<String> customData;
//    private List<String> skus;

    public String getShardId() {
        return shardId;
    }

    public void setShardId(String shardId) {
        this.shardId = shardId;
    }

    public String getClientFacingIpPort() {
        return clientFacingIpPort;
    }

    public void setClientFacingIpPort(String clientFacingIpPort) {
        this.clientFacingIpPort = clientFacingIpPort;
    }

    public String getClientProtocol() {
        return clientProtocol;
    }

    public void setClientProtocol(String clientProtocol) {
        this.clientProtocol = clientProtocol;
    }

//    public List<String> getPlatforms() {
//        return platforms;
//    }
//
//    public void setPlatforms(List<String> platforms) {
//        this.platforms = platforms;
//    }
//
//    public List<String> getCustomData() {
//        return customData;
//    }
//
//    public void setCustomData(List<String> customData) {
//        this.customData = customData;
//    }
//
//    public List<String> getSkus() {
//        return skus;
//    }
//
//    public void setSkus(List<String> skus) {
//        this.skus = skus;
//    }


    public String[] getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String[] platforms) {
        this.platforms = platforms;
    }

    public String[] getCustomData() {
        return customData;
    }

    public void setCustomData(String[] customData) {
        this.customData = customData;
    }

    public String[] getSkus() {
        return skus;
    }

    public void setSkus(String[] skus) {
        this.skus = skus;
    }

}