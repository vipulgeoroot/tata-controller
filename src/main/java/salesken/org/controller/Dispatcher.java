package salesken.org.controller;



import ch.loway.oss.ari4java.ARI;
import ch.loway.oss.ari4java.generated.AriWSHelper;
import ch.loway.oss.ari4java.generated.models.*;
import ch.loway.oss.ari4java.tools.ARIException;
import ch.loway.oss.ari4java.tools.AriConnectionEvent;
import ch.loway.oss.ari4java.tools.RestException;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import salesken.org.controller.service.CallService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@Slf4j
@Component
public class Dispatcher {

    private CallService callService;
    private ARI ari;

    @Autowired
    Dispatcher(CallService callService, ARI ari){
        this.callService = callService;
        this.ari = ari;
    }




    public void dispatchCall() throws InterruptedException, ARIException {
        final ExecutorService  threadPool = Executors.newFixedThreadPool(10);



        ari.eventsCallback(new AriWSHelper() {

            @Override
            public void onFailure(RestException e) {
                e.printStackTrace();
            }

            @Override
            protected void onChannelUserevent(ChannelUserevent message) {
//                log.info("user event "+message.getEventname());
                log.info("user event "+message.getUserevent());
                callService.onChannelUserevent(message);
            }

            @Override
            protected void onChannelVarset(ChannelVarset message) {
//                log.info("VARSET ::{} ::{}",message.getVariable(), message.getValue());
                callService.onChannelVarset(message);
            }

            @Override
            public void onConnectionEvent(AriConnectionEvent event) {
                log.info("Event:  " +  event.name() );
                // if you wish to know the status (connected/disconnected) of the WS connection
            }
            @Override
            protected void onStasisStart(StasisStart message) {
                callService.onStasisStart(message);

            }

            @Override
            protected void onPlaybackFinished(PlaybackFinished message) {
/*        log.info("playback finished id:{}", message.getPlayback().getId());
        String channelId = message.getPlayback().getId().split("_")[1];
        log.info("channelId:{}", channelId);*/


            }

            @Override
            protected void onChannelStateChange(ChannelStateChange message) {
                callService.onChannelStateChange(message);
            }

            @Override
            @SneakyThrows
            protected void onDial(Dial message) {
//                log.info("DIALLED::{}", message.getDialstatus());
//                log.info("PEER::{}", message.getPeer().getId());
//                log.info("CALLER::{}", message.getCaller());
//                log.info("FORWARDED::{}", message.getForwarded());
//                log.info("DSTRING::{}", message.getDialstring());
                callService.onDial(message);
            }

            @Override
            @SneakyThrows
            protected void onChannelCreated(ChannelCreated message) {
//                log.info(" On Channel Create", message.toString());
            }

            @Override
            @SneakyThrows
            protected void onChannelDestroyed(ChannelDestroyed message) {
              //  log.info(" On Channed Destroyed" + message.toString());
                callService.onChannelDestroyed(message);
            }

            @Override
            @SneakyThrows
            protected void onChannelHangupRequest(ChannelHangupRequest message) {
//                log.info(" HANGUP :{}" , message.getCause());
//                log.info(" CHANNEL ID HANGUP :{}" ,message.getChannel().getId());
//                log.info(" CALLER ID HANGUP :{}" , message.getChannel().getCaller().toString());
            }


            @Override
            @SneakyThrows
            protected void onChannelLeftBridge(ChannelLeftBridge message) {
                callService.onChannelLeftBridge(message);

            }

            @Override
            @SneakyThrows
            protected void onChannelEnteredBridge(ChannelEnteredBridge message) {
//                proxyCallService.onChannelEnteredBridge(message);
            }


            @Override
            @SneakyThrows
            protected void onChannelTalkingStarted(ChannelTalkingStarted message) {
//                log.info("On channel Talking started" + message.getAsterisk_id() + " : " + message.getType());
            }

            @Override
            protected void onPeerStatusChange(PeerStatusChange message) {
//                log.info("PEER STATUS", message.getPeer().getPeer_status());
            }

            @Override
            protected void onChannelConnectedLine(ChannelConnectedLine message) {
//                log.info("CHANNEL CONNECTED LINE:{}", message.getChannel().getState());
//                log.info("CHANNEL CONNECTED LINE ID:{}", message.getChannel().getId());
            }

            @Override
            protected void onChannelDtmfReceived(ChannelDtmfReceived message) {
//                log.info(" On Channel DTMF" + message.toString());
            }
        });
        threadPool.awaitTermination(365, TimeUnit.DAYS);
        ari.cleanup();
        System.exit(0);
    }

}
