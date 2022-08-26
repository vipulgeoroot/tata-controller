/**
 *
 */
package salesken.org.controller.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DBProperties {
    private static final Logger logger = LogManager.getLogger(DBProperties.class);

    public static Properties configProperties;
    static {
        try {
            String deploymentType;
            InputStream inputStreamType = DBProperties.class.getClassLoader().getResourceAsStream("db.properties");
            Properties configPropertiesType = new Properties();
            configPropertiesType.load(inputStreamType);
            deploymentType = (String) configPropertiesType.get(DBProperties.PropertyNames.deployment_type.name());

            String systemEnvironment = System.getenv("ENVIRONMENT");
            if (systemEnvironment != null) {
                deploymentType = systemEnvironment;
                logger.info("==========Environment loading from System Variable: " + deploymentType + "==========");
            }

            InputStream inputStream = DBProperties.class.getClassLoader()
                    .getResourceAsStream("db-" + deploymentType + ".properties");
            configProperties = new Properties();
            configProperties.load(inputStream);
            logger.info("==========Environment loaded: " + deploymentType + "==========");
        } catch (Exception e) {
            logger.error("Could not load the file");
            logger.info("context" + e);
        }
    }

    public static String getProperty(String key) {
        return (String) configProperties.get(key);
    }

    public enum PropertyNames {
        LOCAL_AUDIO_BASE, ANALYSIS_URL, API_URL, CUE_URL, DEFAULT_SIP_DOMAIN, DEFAULT_SIP_PASSWORD,
        DEFAULT_SIP_CREDENTIAL_LIST, ANALYZE_URL, ANALYZE_SIP_URL, DEFAULT_SIP_DOMAIN_SID_VIRTUAL,
        DEFAULT_SIP_DOMAIN_SID_CONF, DEFAULT_SIP_DOMAIN_VIRTUAL_URL, DEFAULT_SIP_DOMAIN_CONF_URL, VIRTUAL_SIP_BASE_URL,
        DEFAULT_SIP_DOMAIN_THIN_CLIENT_URL, SLASHRTC_APP_KEY, SLASHRTC_APP_SECRET, SALESKEN_PROXY_URL,
        CRM_TASK_HOOK_URL, deployment_type, ZOOM_AUTH_URL_DEV, ZOOM_AUTH_URL_PROD, PHONE_NUMBER_VALIDATOR_API,
        PROXYCALL_EVENT_URL, PROXYCALL_HANGUP_URL, SUPERADMIN_URL, DISPOSITION_BASE_URL, SEMANTIC_URL, ELASTIC_URL,
        COMM_BASE_URL, GCS_FLIE_BUCKET_NAME, BACKEND_URL, MEDIA_SERVER_URL, MILVUS_BASE_URL, MILVUS_JAVA_URL, BASE_URL,
        AWS_BUCKET_SNIPPET, GLOBAL_PROXYCALL_URL, CDR_IMPORT_BASE_URL, LOG_ELASTIC_AUTH, LOG_ELASTIC_USERNAME,
        LOG_ELASTIC_PASSWORD, LOG_ELASTIC_URL, LOG_ELASTIC_PORT, LOG_ELASTIC_PROTOCOL, DISPOSITION_SYNC_ELK_URL, GCS_BUCKET
    };
}
