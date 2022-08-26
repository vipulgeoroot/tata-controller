package salesken.org.controller.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallRequest {
    private String type;
    private String fromNumber;
    private String toNumber;
    private String callerId;
    private String extension;
    private String callSid;
    private String appName;
    private String agentId;
    private String uniqueId;
}
