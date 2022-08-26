package salesken.org.controller.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SocketConfig {

    @Value("${audiosocket_host}")
    public String audiosocketHost;

    @Value("${qing_base_url}")
    public String cueingBaseUrl;

    @Value("${qing_end_point}")
    public String cueingEndPoint;

}
