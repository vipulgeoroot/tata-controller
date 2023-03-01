package salesken.org.controller.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import salesken.org.controller.service.CallService;

@RestController
@RequestMapping(value = "/tataController")
public class TataController {
    private final Logger logger = LoggerFactory.getLogger(TataController.class);

    @Autowired
    public CallService callService;

    @GetMapping( value = "/transcriptInitialization")
    public String tataControlStreamController(@RequestParam (name = "agentID") String agentID,
                                              @RequestParam (name = "agentChanelId") String agentChanelId,
                                              @RequestParam (name = "customerChannelId") String customerChannelId,
                                              @RequestParam (name = "callsId") String callsId) {
        callService.tataTranscriptInitiation(agentID, agentChanelId, customerChannelId, callsId);
        return null;

    }
}
