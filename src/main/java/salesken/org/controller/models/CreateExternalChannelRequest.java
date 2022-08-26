package salesken.org.controller.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateExternalChannelRequest {

    String appName;
    String externalHostWithPort;
    String format;
    String encapsulation;
    String transport;
    String data;
}
