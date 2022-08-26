package salesken.org.controller.service;

public interface ChannelService {

    void putBridge(String bridgeId, String sId);
    void putChannel(String channelId, String sId);
    String getSid(String channelId);
    String getSidForBridge(String bridgeId);
    void removeChannel(String channelId);
}
