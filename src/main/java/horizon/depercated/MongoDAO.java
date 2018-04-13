package horizon.depercated;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MongoDAO extends AbstractMongoDAO{
    Logger log = Logger.getLogger(this.getClass());
    
	public void connectPoolTest(){
		log.debug(getDB());
	}
	
    @SuppressWarnings("unchecked")
	public void selectAllUser(){
    	List<TestVO> list = (List<TestVO>) selectAllList("members", TestVO.class);
    	Iterator<TestVO> listKey = list.iterator();
    	
    	
    	while(listKey.hasNext()){
    		TestVO vo = listKey.next();
    		
    	}
    	
    }
    
	public void insertUser(){
    } 
    
}