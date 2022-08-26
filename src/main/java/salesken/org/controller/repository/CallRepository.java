package salesken.org.controller.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import salesken.org.controller.models.dao.CallDao;


public interface CallRepository extends ElasticsearchRepository<CallDao,String> {

}
