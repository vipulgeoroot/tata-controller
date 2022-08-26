package salesken.org.controller.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import salesken.org.controller.models.CallRequest;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SerDeServiceImpl implements SerDeService{

    private Gson gson;

    @Autowired
    SerDeServiceImpl(Gson gson){
        this.gson = gson;
    }

    @Override
    public Map<String, String> strToMap(String from) {
        return gson.fromJson(from, HashMap.class);
    }

    @Override
    public CallRequest strToCallRequest(String from) {
        return gson.fromJson(from, CallRequest.class);
    }


}
