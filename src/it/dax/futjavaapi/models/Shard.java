package it.dax.futjavaapi.models;

import java.util.List;

public class Shard{

    private String shardId;
    private String clientFacingIpPort;
    private String clientProtocol;
    private List<String> platforms;
    private List<String> customdata1;
    private List<String> skus;

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

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public List<String> getCustomdata1() {
        return customdata1;
    }

    public void setCustomdata1(List<String> customdata1) {
        this.customdata1 = customdata1;
    }

    public List<String> getSkus() {
        return skus;
    }

    public void setSkus(List<String> skus) {
        this.skus = skus;
    }

}