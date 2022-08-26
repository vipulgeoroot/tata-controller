package salesken.org.controller.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class HangupChannelRequest {
    String channelId;

}
