package salesken.org.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import salesken.org.controller.models.dao.CallDao;
import salesken.org.controller.repository.CallRepository;

@Service
public class ESIngestionServiceImpl implements ESIngestionService{

    private CallRepository callRepository;

    @Autowired
    ESIngestionServiceImpl(CallRepository callRepository){
        this.callRepository =callRepository;
    }

    @Override
    public void ingestEs(CallDao callDao) {
        try {
            callRepository.save(callDao);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
