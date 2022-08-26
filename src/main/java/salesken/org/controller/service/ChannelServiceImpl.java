package salesken.org.controller.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChannelServiceImpl implements ChannelService {
    private Map<String, String> channel;
    private Map<String, String> bridge;
    ChannelServiceImpl(){
        this.channel = new HashMap<>();
        this.bridge = new HashMap<>();

    }


    @Override
    public void putBridge(String bridgeId, String sId) {
            bridge.put(bridgeId, sId);
    }

    @Override
    public void putChannel(String channelId, String sId) {
        channel.put(channelId, sId);
    }



    @Override
    public String getSid(String channelId) {
        return channel.get(channelId);
    }

    @Override
    public String getSidForBridge(String bridgeId) {
        return bridge.get(bridgeId);
    }

    @Override
    public void removeChannel(String channelId) {
        channel.remove(channelId);
    }
}
