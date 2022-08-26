package salesken.org.controller.service;

import ch.loway.oss.ari4java.generated.models.Bridge;
import ch.loway.oss.ari4java.generated.models.Channel;
import salesken.org.controller.models.*;

public interface AsteriskCommandService {
    Bridge createMixingBridgeCommand();
    AsteriskResponse createChannel(CreateChannelRequest createChannelRequest);
    void dialChannel(String channelId);
    void addChannelToBridge(AddChannelToBridgeRequest addChannelToBridgeRequest);
    void channelHangup(Bridge bridge);

    AsteriskResponse createBridge();

    AsteriskResponse createSnoopChannel(CreateSnoopChannelRequest createSnoopChannelRequest);

    AsteriskResponse createExternalChannel(CreateExternalChannelRequest createExternalChannelRequest);

    AsteriskResponse hangupChannel(HangupChannelRequest hangupChannelRequest);

}
