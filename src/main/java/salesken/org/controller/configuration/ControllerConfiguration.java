package salesken.org.controller.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ControllerConfiguration {

    @Value("${customer_prefix}")
    public String customerPrefix;

    @Value("${agent_prefix}")
    public String agentPrefix;

    @Value("${cdr_prefix}")
    public String cdrPrefix;
}
