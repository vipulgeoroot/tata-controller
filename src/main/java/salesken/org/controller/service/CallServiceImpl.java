package salesken.org.controller.service;

import ch.loway.oss.ari4java.ARI;
import ch.loway.oss.ari4java.generated.models.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import salesken.org.controller.configuration.SocketConfig;
import salesken.org.controller.models.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class CallServiceImpl implements CallService {

    private ARI ari;
    private SerDeService serDeService;
    private AsteriskCommandService asteriskCommandService;
    private ChannelService channelService;
    private ESIngestionService esIngestionService;

    private Map<String,String > snoopToMediaChannelCache;

    private Map<String,String > snoopCustomerToAgentChannel;

    @Autowired
    private SocketConfig socketConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FlaskService flaskService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CallServiceImpl(SerDeService serDeService,
                           AsteriskCommandService asteriskCommandService,
                           ARI ari,
                           ChannelService channelService,
                           ESIngestionService esIngestionService
    ) {
        this.serDeService = serDeService;
        this.asteriskCommandService = asteriskCommandService;
        this.ari = ari;
        this.channelService = channelService;
        this.esIngestionService = esIngestionService;
        this.snoopToMediaChannelCache = new HashMap<>();
        this.snoopCustomerToAgentChannel = new HashMap<>();
    }

    @Override
    @SneakyThrows
    public void onStasisStart(StasisStart message) {

//        try {
//            String toNumber = message.getChannel().getId().split("_")[0];
//            String callSid = message.getChannel().getId().split("_")[1];
//
//            log.info("CALL SID:{}", message.getChannel().getId());
//            if (channelService.getSid(callSid) == null) {
//                ari.channels().dial(message.getChannel().getId()).execute(new AriCallback<Void>() {
//                    @Override
//                    @SneakyThrows
//                    public void onSuccess(Void unused) {
//                        log.info("DIALLED LEG A :{}", callSid);
//                        ari.bridges().create().setBridgeId(callSid).execute(new AriCallback<Bridge>() {
//                            @Override
//                            @SneakyThrows
//                            public void onSuccess(Bridge bridge) {
//                                log.info("BRIDGE CREATED WITH ID :{}", callSid);
//                                channelService.putChannel(callSid, bridge.getId());
//                                ari.bridges().addChannel(bridge.getId(), message.getChannel().getId()).execute(new AriCallback<Void>() {
//                                    @Override
//                                    @SneakyThrows
//                                    public void onSuccess(Void unused) {
//                                        log.info("ADDED LEG A TO BRIDGE :{}",callSid);
//                                        ari.channels()
//                                                .create("pjsip/" + toNumber + "@tatasip", ari.getAppName())
//                                                .setVariables(ImmutableMap.of(
//                                                        "CONNECTEDLINE(num)", "+919898244436",
//                                                        "CALLERID(num)", "+919898244436"))
//                                                .setChannelId("legB" + "_" + callSid)
//                                                .execute(new AriCallback<Channel>() {
//                                                    @Override
//                                                    public void onSuccess(Channel channel) {
//                                                        log.info("LEG B CREATED:{}",callSid);
//
//                                                    }
//                                                    @Override
//                                                    public void onFailure(RestException e) {
//                                                        log.error("ERROR CREATING LEG B :{}", callSid);
//                                                    }
//                                                }
//                                        );
//                                    }
//                                    @Override
//                                    public void onFailure(RestException e) {
//                                        log.error("ERROR ADDING  LEG A TO BRIDGE :{}",callSid);
//                                    }
//                                });
//                            }
//                            @Override
//                            public void onFailure(RestException e) {
//                                log.error("FAILURE TO CREATE BRIDGE WITH ID:{}",callSid);
//                            }
//                        });
//
//
//                    }
//
//                    @Override
//                    public void onFailure(RestException e) {
//                        log.error("ERROR DIALING LEG A:{}", callSid);
//                    }
//                });
//
//            } else {
//                log.info("CALL SID WAS FOUND::{}", callSid);
//                log.info("CHANNEL ID::{}", message.getChannel().getId());
//                String bridgeId = channelService.getSid(callSid);
//                ari.bridges().addChannel(bridgeId, message.getChannel().getId()).execute(new AriCallback<Void>() {
//                    @Override
//                    @SneakyThrows
//                    public void onSuccess(Void unused) {
//                        log.info("ADDED LEG B TO BRIDGE::{}", callSid);
//                        try {
//                            ari.channels().dial(message.getChannel().getId()).execute(new AriCallback<Void>() {
//                                @Override
//                                public void onSuccess(Void unused) {
//                                    log.info("DIALLED LEG B  ::{}", bridgeId);
//                                }
//
//                                @Override
//                                public void onFailure(RestException e) {
//                                    log.error("ERROR DIALLING LEG B::{}",bridgeId );
//                                }
//                            });
//                        }catch (Exception e){
////                            log.error("ERROR DIALLING :{}", message.getChannel().getId());
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(RestException e) {
//                        log.error("ERROR ADDING LEG B TO BRIDGE :{}", callSid);
//                    }
//                });
//
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }

    }

    @Override
    @SneakyThrows
    public void onDial(Dial message) {

    }

//    @Override
//    public void onChannelCreated(ChannelCreated message) {
//
//    }

    @Override
    @SneakyThrows
    public void onChannelDestroyed(ChannelDestroyed message) {

    }

    @Override
    @SneakyThrows
    public void onChannelHangupRequest(ChannelHangupRequest message) {

    }

    @Override
    @SneakyThrows
    public void onChannelLeftBridge(ChannelLeftBridge message) {
        String externalMediaChannel = snoopToMediaChannelCache.get(message.getChannel().getId());
        String agentChannel = snoopCustomerToAgentChannel.get(message.getChannel().getId());
        if( externalMediaChannel!=null && !externalMediaChannel.isEmpty() )
            asteriskCommandService.hangupChannel(HangupChannelRequest.builder().channelId(externalMediaChannel).build());
        if( agentChannel!=null && !agentChannel.isEmpty() ) {
            asteriskCommandService.hangupChannel(HangupChannelRequest.builder().channelId(agentChannel).build());
        }
    }

    @Override
    @SneakyThrows
    public void onChannelUserevent(ChannelUserevent message) {
        CallRequest callRequest = serDeService.strToCallRequest(message.getUserevent().toString());
        Integer userId = getUserId(callRequest.getAgentId());
        if( userId == null)
        {
            log.error("Not able to map user");
            return;
        }
        log.info("User mapped - " + userId);

        NewCallReq newCallReq = NewCallReq.builder()
                .agent(callRequest.getAgentId())
                .audiosocket_ip(socketConfig.audiosocketHost)
                .qing_base_url(socketConfig.cueingBaseUrl)
                .user_id(userId)
                .callSid("SAMPARK-"+callRequest.getUniqueId())
                .speaker("customer")
                .build();

        flaskService.intializeAudioSocket(newCallReq);

        TimeUnit.MILLISECONDS.sleep(200);

        log.info("Initiated audio-socket");
        FlaskResponse flaskResponse = flaskService.getDetails(callRequest, "customer");
        log.info("Got details regarding audio-socket " + flaskResponse.toString());

        String audioSocketUrl = flaskResponse.getAudiosocket_ip() + ":" + flaskResponse.getAudiosocket_port();

        AsteriskResponse customerChannel = asteriskCommandService.createChannel(CreateChannelRequest.builder()
                .extension("pjsip/0" + callRequest.getUniqueId() + "@samparkPro")
                .app(ari.getAppName()).build());

        if( customerChannel.getId() ==  null) {
            log.error("Not able to create customer channel");
            return;
        }

        AsteriskResponse snoopChannel = asteriskCommandService.createSnoopChannel(CreateSnoopChannelRequest.builder()
                .channelId(customerChannel.getId())
                .appName(ari.getAppName())
                .spy("in")
                .build());

        if( snoopChannel.getId() ==  null){
            log.error("Not able to create snoop channel");
            return;

        }

        AsteriskResponse externalMediaChannel = asteriskCommandService.createExternalChannel(CreateExternalChannelRequest.builder()
                .appName(ari.getAppName())
                .externalHostWithPort(audioSocketUrl)
                .format("ulaw")
                .encapsulation("audiosocket")
                .transport("tcp")
                .data(UUID.randomUUID().toString())
                .build());

        if( externalMediaChannel.getId() ==  null) {
            log.error("Not able to create external channel");
            return;
        }

        AsteriskResponse bridge = asteriskCommandService.createBridge();

        if( bridge.getId() ==  null) {
            log.error("Not able to create bridge");
            return;
        }

        asteriskCommandService.addChannelToBridge(AddChannelToBridgeRequest.builder().bridgeId(bridge.getId()).channelId(snoopChannel.getId()).build());
        asteriskCommandService.addChannelToBridge(AddChannelToBridgeRequest.builder().bridgeId(bridge.getId()).channelId(externalMediaChannel.getId()).build());
        asteriskCommandService.dialChannel(customerChannel.getId());
        this.snoopToMediaChannelCache.put(snoopChannel.getId(), externalMediaChannel.getId());

        NewCallReq agentCallReq = NewCallReq.builder()
                .agent(callRequest.getAgentId())
                .audiosocket_ip(socketConfig.audiosocketHost)
                .qing_base_url(socketConfig.cueingBaseUrl)
                .user_id(userId)
                .callSid("SAMPARK-"+callRequest.getUniqueId())
                .speaker("agent")
                .build();

        flaskService.intializeAudioSocket(agentCallReq);

        TimeUnit.MILLISECONDS.sleep(200);

        log.info("Initiated audio-socket");
        FlaskResponse flaskResponseAgent = flaskService.getDetails(callRequest,"agent");
        log.info("Got details regarding audio-socket " + flaskResponseAgent.toString());

        String audioSocketUrlAgent = flaskResponse.getAudiosocket_ip() + ":" + flaskResponseAgent.getAudiosocket_port();

        AsteriskResponse agentChannel = asteriskCommandService.createChannel(CreateChannelRequest.builder()
                .extension("pjsip/1" + callRequest.getUniqueId() + "@samparkPro")
                .app(ari.getAppName()).build());

        if( agentChannel.getId() ==  null) {
            log.error("Not able to create agent channel");
            return;
        }


        AsteriskResponse snoopChannelAgent = asteriskCommandService.createSnoopChannel(CreateSnoopChannelRequest.builder()
                .channelId(agentChannel.getId())
                .appName(ari.getAppName())
                .spy("in")
                .build());

        if( snoopChannelAgent.getId() ==  null){
            log.error("Not able to create snoop channel");
            return;

        }

        AsteriskResponse externalMediaChannelAgent = asteriskCommandService.createExternalChannel(CreateExternalChannelRequest.builder()
                .appName(ari.getAppName())
                .externalHostWithPort(audioSocketUrlAgent)
                .format("ulaw")
                .encapsulation("audiosocket")
                .transport("tcp")
                .data(UUID.randomUUID().toString())
                .build());

        if( externalMediaChannelAgent.getId() ==  null) {
            log.error("Not able to create external channel");
            return;
        }

        AsteriskResponse bridgeAgent = asteriskCommandService.createBridge();

        if( bridgeAgent.getId() ==  null) {
            log.error("Not able to create bridge");
            return;
        }

        asteriskCommandService.addChannelToBridge(AddChannelToBridgeRequest.builder().bridgeId(bridgeAgent.getId()).channelId(snoopChannelAgent.getId()).build());
        asteriskCommandService.addChannelToBridge(AddChannelToBridgeRequest.builder().bridgeId(bridgeAgent.getId()).channelId(externalMediaChannelAgent.getId()).build());
        asteriskCommandService.dialChannel(agentChannel.getId());
        this.snoopCustomerToAgentChannel.put(snoopChannel.getId(),agentChannel.getId());
        this.snoopToMediaChannelCache.put(snoopChannelAgent.getId(), externalMediaChannelAgent.getId());
    }



    private Integer getUserId(String userName) {
        String query = "Select usr.id from istar_user usr where email like '" + userName + "%'";
        try {
            List<Map<String, Object>> data = jdbcTemplate.queryForList(query);
            if (data.isEmpty()) {
                throw new Exception("No user mapped to user - "+userName);
            } else {
                return (Integer) data.get(0).get("id");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    @SneakyThrows
    public void onChannelStateChange(ChannelStateChange message) {

    }

    @Override
    public void onChannelEnteredBridge(ChannelEnteredBridge message) {

//        log.info("CHANNEL ENTERED BRIDGE {}", message.getChannel().getId());
//        if(message.getBridge().getChannels().size() >=2){
//            String bridgeId = message.getBridge().getId();
//            String sId = channelService.getSid(message.getChannel().getId());
//            if(message.getBridge().getChannels().size() == 2) {
//                esIngestionService.ingestEs(ESUtil.toESCallDao(CallState.CONNECTED, bridgeId, sId,
//                        message.getBridge().getChannels().get(0)));
//                esIngestionService.ingestEs(ESUtil.toESCallDao(CallState.CONNECTED, bridgeId, sId,
//                        message.getBridge().getChannels().get(1)));
//            } else {
//                esIngestionService.ingestEs(ESUtil.toESCallDao(CallState.CONNECTED, bridgeId, sId,
//                        message.getChannel().getId()));
//            }
//        }

    }

    @Override
    @SneakyThrows
    public void onChannelVarset(ChannelVarset message) {
//         String toNumber = ari.channels().getChannelVar(message.getChannel().getId(), "toNumber").execute().getValue();
//         String callSid =  ari.channels().getChannelVar(message.getChannel().getId(), "callSid").execute().getValue();
//         if(toNumber!=null && callSid!=null){
//             log.info("TO :{} CALLSID:{} ", toNumber, callSid);
//             ari.channels().dial(message.getChannel().getId()).setCaller(message.getChannel().getId()).execute();
//         }
    }


}

