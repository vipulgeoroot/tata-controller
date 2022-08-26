package salesken.org.controller.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddChannelToBridgeRequest {
    private String bridgeId;
    private String channelId;
}
