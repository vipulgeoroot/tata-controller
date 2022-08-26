package salesken.org.controller.util;

import ch.loway.oss.ari4java.generated.models.Channel;
import salesken.org.controller.constants.CallState;
import salesken.org.controller.models.CallRequest;
import salesken.org.controller.models.dao.CallDao;

import java.util.Date;

public interface ESUtil {

    static CallDao toESCallDao( CallState callState, String bridgeId, String callSid, String channelId ){
        return CallDao.builder()
                .legId(channelId)
                .createdAt(new Date())
                .updatedAt(new Date())
                .sId(callSid)
                .bridgeId(bridgeId)
                .callState(callState)
                .build();
    }

    static CallDao toESCallDao(Channel channel , CallState callState, String callSid,
                               String extension, String bridgeId){
        return CallDao.builder()
                .sId(callSid)
                .extension(extension)
                .legId(channel.getId())
                .bridgeId(bridgeId)
                .createdAt(new Date())
                .updatedAt(new Date())
                .callState(callState)
                .build();
    }


}
