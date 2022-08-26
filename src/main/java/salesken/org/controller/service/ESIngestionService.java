package salesken.org.controller.service;

import salesken.org.controller.constants.CallState;
import salesken.org.controller.models.dao.CallDao;

public interface ESIngestionService {
    void ingestEs(CallDao callDao);
}
