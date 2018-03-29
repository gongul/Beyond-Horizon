package horizon.depercated;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.DB;

public class AbstractMongoDAO {
	
	@Autowired
    private MongoTemplate mongoTemplate;
	
	public void insert(String collectionName,Object params){
		mongoTemplate.insert(params, collectionName);
	}
	
	public Object getDB(){
		return mongoTemplate.getDb();
	}
	
	public Object update(String collectionName,Query query,Update update){
		return mongoTemplate.updateFirst(query, update, collectionName);
	}
	
	public Object delete(String collectionName,Object params){
		return mongoTemplate.remove(params, collectionName);
	}
	
	public Object selectOne(String collectionName,Query query,Class<?> entityClass){
		return mongoTemplate.findOne(query, entityClass, collectionName);
	}
	
	public List<?> selectList(String collectionName,Query query,Class<?> entityClass){
		return mongoTemplate.find(query, entityClass, collectionName);
	}
	
	public List<?> selectAllList(String collectionName,Class<?> entityClass){
		return mongoTemplate.findAll(entityClass, collectionName);
	}
    
}
