package salesken.org.controller.service;

import ch.loway.oss.ari4java.generated.models.*;

public interface CallService {
    void onStasisStart(StasisStart message);
    void onDial(Dial message);
//    void onChannelCreated(ChannelCreated message);
    void onChannelDestroyed(ChannelDestroyed message);
    void onChannelHangupRequest(ChannelHangupRequest message);
    void onChannelLeftBridge(ChannelLeftBridge message);
    void onChannelUserevent(ChannelUserevent message);
    void onChannelStateChange(ChannelStateChange message);
    void onChannelEnteredBridge(ChannelEnteredBridge messaage);
    void onChannelVarset(ChannelVarset message);

    void tataTranscriptInitiation(String agentID, String agentChanelId, String customerChannelId, String callsId);
}
