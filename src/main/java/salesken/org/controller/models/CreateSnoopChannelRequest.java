package salesken.org.controller.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateSnoopChannelRequest {

    String channelId;
    String appName;
    String spy;
}
