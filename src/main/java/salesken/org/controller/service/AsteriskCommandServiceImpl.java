package salesken.org.controller.service;

import ch.loway.oss.ari4java.ARI;
import ch.loway.oss.ari4java.generated.models.Bridge;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import salesken.org.controller.configuration.AppConfiguration;
import salesken.org.controller.models.*;

import java.util.Base64;

@Service
@Slf4j
public class AsteriskCommandServiceImpl implements AsteriskCommandService {
    private final String MIXING_BRIDGE = "mixing";
    private ARI ari;

    @Autowired
    AsteriskCommandServiceImpl(ARI ari) {
        this.ari = ari;
    }

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppConfiguration appConfiguration;

    @Override
    public Bridge createMixingBridgeCommand() {
        Bridge bridge = null;
        try {
            bridge = ari.bridges().create().setType(MIXING_BRIDGE).execute();
            log.info("BRIDGE CREATED WITH ID::{}", bridge.getId().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bridge;
    }

    @Override
    public AsteriskResponse createChannel(CreateChannelRequest createChannelRequest) {
        log.info("channel create request::{}", createChannelRequest.toString());
        AsteriskResponse responseEntity = null;
        try {
            String authStr = appConfiguration.asteriskUser+":"+appConfiguration.asteriskPassword;
            String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);
            HttpEntity request = new HttpEntity(headers);
            String url = appConfiguration.asteriskHost + "ari/channels/create?" + "app=" + createChannelRequest.getApp() +
                    "&endpoint=" + createChannelRequest.getExtension();
            responseEntity = restTemplate.postForObject(url, request, AsteriskResponse.class);
            log.info("CHANNEL CREATED WITH ID::{}", responseEntity.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @Override
    public void dialChannel(String channelId) {
        log.info("DIALING  CHANNEL::{}", channelId);
        try {
            String authStr = appConfiguration.asteriskUser+":"+appConfiguration.asteriskPassword;
            String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);
            HttpEntity request = new HttpEntity(headers);
            String url = appConfiguration.asteriskHost + "ari/channels/"+channelId+"/dial";
            restTemplate.postForObject(url, request, AsteriskResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addChannelToBridge(AddChannelToBridgeRequest addChannelToBridgeRequest) {
        try {
            String bridgeId = addChannelToBridgeRequest.getBridgeId();
            String channelId = addChannelToBridgeRequest.getChannelId();
            log.info("ADDING CHANNEL ::{}  TO BRIDGE::{}", channelId, bridgeId);
            String authStr = appConfiguration.asteriskUser+":"+appConfiguration.asteriskPassword;
            String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);
            HttpEntity request = new HttpEntity(headers);
            String url = appConfiguration.asteriskHost + "ari/bridges/"+addChannelToBridgeRequest.getBridgeId()+"/addChannel?bridgeId="+addChannelToBridgeRequest.getBridgeId()+"&channel="+addChannelToBridgeRequest.getChannelId();
            restTemplate.postForObject(url, request, AsteriskResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelHangup(Bridge bridge) {
        try {
            String channelId = bridge.getChannels().get(0);
            log.info("HANGING UP CHANNEL ::{} ", channelId);
            ari.channels().hangup(channelId).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public AsteriskResponse createBridge() {
        log.info("Bridge create request");
        AsteriskResponse responseEntity = null;
        try {
            String authStr = appConfiguration.asteriskUser+":"+appConfiguration.asteriskPassword;
            String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);
            HttpEntity request = new HttpEntity(headers);
            String url = appConfiguration.asteriskHost + "ari/bridges";
            responseEntity = restTemplate.postForObject(url, request, AsteriskResponse.class);
            log.info("BRIDGE CREATED WITH ID::{}", responseEntity.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @Override
    public AsteriskResponse createSnoopChannel(CreateSnoopChannelRequest createSnoopChannelRequest) {
        log.info("snoop channel create request::{}", createSnoopChannelRequest.toString());
        AsteriskResponse responseEntity = null;
        try {
            String authStr = appConfiguration.asteriskUser+":"+appConfiguration.asteriskPassword;
            String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);
            HttpEntity request = new HttpEntity(headers);
            String url = appConfiguration.asteriskHost + "ari/channels/"+createSnoopChannelRequest.getChannelId()+"/snoop?" + "spy=" + createSnoopChannelRequest.getSpy() +
                    "&app=" + createSnoopChannelRequest.getAppName();
            responseEntity = restTemplate.postForObject(url, request, AsteriskResponse.class);
            log.info("SNOOP CHANNEL CREATED WITH ID::{}", responseEntity.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @Override
    public AsteriskResponse createExternalChannel(CreateExternalChannelRequest createExternalChannelRequest) {
        log.info("external channel create request::{}", createExternalChannelRequest.toString());
        AsteriskResponse responseEntity = null;
        try {
            String authStr = appConfiguration.asteriskUser+":"+appConfiguration.asteriskPassword;
            String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);
            HttpEntity request = new HttpEntity(headers);
            String url = appConfiguration.asteriskHost + "ari/channels/externalMedia?" + "app=" + createExternalChannelRequest.getAppName() +
                    "&external_host=" + createExternalChannelRequest.getExternalHostWithPort() +
                    "&format=" + createExternalChannelRequest.getFormat() + "&encapsulation=" + createExternalChannelRequest.getEncapsulation()
                    + "&transport=" + createExternalChannelRequest.getTransport() + "&data=" + createExternalChannelRequest.getData();
            responseEntity = restTemplate.postForObject(url, request, AsteriskResponse.class);
            log.info("EXTERNAL CHANNEL CREATED WITH ID::{}", responseEntity.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @Override
    public AsteriskResponse hangupChannel(HangupChannelRequest hangupChannelRequest) {
        log.info("channel hangup request::{}", hangupChannelRequest.toString());
        ResponseEntity<AsteriskResponse> responseEntity = null;
        try {
            String authStr = appConfiguration.asteriskUser+":"+appConfiguration.asteriskPassword;
            String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);
            HttpEntity request = new HttpEntity(headers);
            String url = appConfiguration.asteriskHost + "ari/channels/"+hangupChannelRequest.getChannelId();
            responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, request,AsteriskResponse.class);
            log.info("CHANNEL HANGUP WITH ID::{}", hangupChannelRequest.getChannelId());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error while hanging up channel - "+hangupChannelRequest.getChannelId());
        }
        return responseEntity.getBody();
    }

    @Override
    public HttpStatus getChannel(String channelId) {
        log.info("GET CHANNEL ::{}", channelId);
        ResponseEntity<AsteriskResponse> responseEntity = null;
        try {
            String authStr = appConfiguration.asteriskUser+":"+appConfiguration.asteriskPassword;
            String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);
            HttpEntity request = new HttpEntity(headers);
            String url = appConfiguration.asteriskHost + "ari/channels/"+channelId;
            responseEntity = restTemplate.exchange(url, HttpMethod.GET, request,AsteriskResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error while fetching channel - "+channelId);
        }
        return responseEntity.getStatusCode();
    }
}
