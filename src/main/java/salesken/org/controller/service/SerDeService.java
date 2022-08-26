package salesken.org.controller.service;

import salesken.org.controller.models.CallRequest;

import java.util.Map;

public interface SerDeService {
    Map<String,String> strToMap(String from);
    CallRequest strToCallRequest(String from);

}
