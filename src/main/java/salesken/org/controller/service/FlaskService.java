package salesken.org.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import salesken.org.controller.configuration.FlaskConfiguration;
import salesken.org.controller.models.CallRequest;
import salesken.org.controller.models.FlaskResponse;
import salesken.org.controller.models.GetDetailsReq;
import salesken.org.controller.models.NewCallReq;

@Service
public class FlaskService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FlaskConfiguration flaskConfiguration;

    @Async
    public FlaskResponse intializeAudioSocket(NewCallReq newCallRequest) {
        HttpEntity<NewCallReq> httpEntity = new HttpEntity<>(newCallRequest);
        String newCallReqUrl = "http://" + flaskConfiguration.flaskHost + ":" + flaskConfiguration.flaskPort + "/assign";
        return restTemplate.postForObject(newCallReqUrl, httpEntity, FlaskResponse.class);
    }

    public FlaskResponse getDetails(CallRequest callRequest, String speaker) {
        GetDetailsReq getDetailsReq = GetDetailsReq.builder().agent(callRequest.getAgentId()).speaker(speaker).build();
        HttpEntity<GetDetailsReq> request = new HttpEntity<>(getDetailsReq);
        String url = "http://" + flaskConfiguration.flaskHost + ":" + flaskConfiguration.flaskPort + "/get_details";
        FlaskResponse flaskResponse = restTemplate.postForObject(url, request, FlaskResponse.class);
        return flaskResponse;
    }
}
