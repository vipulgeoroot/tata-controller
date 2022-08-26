package salesken.org.controller.configuration;

import ch.loway.oss.ari4java.ARI;
import ch.loway.oss.ari4java.AriVersion;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AppConfiguration {

    @Value("${asterisk_host}")
    public String asteriskHost;

    @Value("${asterisk_user}")
    public String asteriskUser;

    @Value("${asterisk_password}")
    public String asteriskPassword;

    @Value("${asterisk_app}")
    public String appName;




    @Bean
    @SneakyThrows
    ARI ariConnection(){
        log.info("");
        return  ARI.build(asteriskHost, appName, asteriskUser, asteriskPassword, AriVersion.ARI_8_0_0);
    }

    @Bean
    Gson gson(){
        return new Gson();
    }

}
