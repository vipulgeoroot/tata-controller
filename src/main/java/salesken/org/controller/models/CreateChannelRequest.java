package salesken.org.controller.models;

import lombok.*;

import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateChannelRequest {
//    private String callSid;
//    private String channelId;
    private Map<String, String> channelVar;
    private String extension;
//    private String trunk;
//    private String technology;
    private String app;

}
