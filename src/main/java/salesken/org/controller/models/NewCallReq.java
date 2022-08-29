package salesken.org.controller.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class NewCallReq {

    public String audiosocket_ip;
    public String audiosocket_port;
    public String qing_base_url;
    public Integer user_id;
    public String agent;
    public String callSid;
    public String speaker;

}
