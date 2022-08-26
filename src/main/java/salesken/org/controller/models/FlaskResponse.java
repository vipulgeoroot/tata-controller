package salesken.org.controller.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FlaskResponse {

    private String user_id;
    private String agent;
    private String audiosocket_ip;
    private String audiosocket_port;

}
