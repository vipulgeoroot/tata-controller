package salesken.org.controller.util;

import ch.loway.oss.ari4java.generated.models.Bridge;
import ch.loway.oss.ari4java.generated.models.Channel;
import com.google.common.collect.ImmutableMap;
import salesken.org.controller.models.AddChannelToBridgeRequest;
import salesken.org.controller.models.CallRequest;
import salesken.org.controller.models.CreateChannelRequest;



public interface CallUtil {
    static CreateChannelRequest toCreteChannelRequest(CallRequest callRequest, String extension){
        return CreateChannelRequest.builder()
                .channelVar(ImmutableMap
                        .of("CONNECTEDLINE(num)", callRequest.getCallerId(),
                                "CALLERID(num)", callRequest.getCallerId(),
                                "callSid",callRequest.getCallSid()
                        ))
                .app(callRequest.getAppName())
                .extension("PJSIP/"+extension+"@junk_zen")
                .build();
    }

    static AddChannelToBridgeRequest toAddChannelToBridgeRequest(Channel channel, Bridge bridge){
        return AddChannelToBridgeRequest.builder()
                .bridgeId(bridge.getId())
                .channelId(channel.getId())
                .build();
    }
}
