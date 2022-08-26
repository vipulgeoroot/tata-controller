package salesken.org.controller.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class FlaskConfiguration {

    @Value("${flask_host}")
    public String flaskHost;

    @Value("${flask_port}")
    public String flaskPort;
}
