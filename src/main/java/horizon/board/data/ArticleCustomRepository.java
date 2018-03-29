package horizon.board.data;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.codecs.BsonTypeClassMap;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBRef;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import horizon.board.controller.ArticleController;

@Repository("articleCustomRepo")
public class ArticleCustomRepository{

	@Resource(name="mongoTemplate")
	private MongoOperations mongoOps; 
	
	@Resource(name="mongo")
	private MongoClient mongoClient;
	
	@Autowired
	EntityLinks entityLinks;
	
	Logger log = Logger.getLogger(this.getClass());
	
	public Object test() {
		MongoDatabase db = mongoClient.getDatabase("horizon");
//		
////		log.debug(db.runCommand(new Document("$eval","db.loadServerScripts()")));
//		
//		Document test = db.runCommand(new Document("$eval","echoFunction1()"));
//		log.debug("test"+test);
//		
//		BasicDBObject dbObject = new BasicDBObject();
//		dbObject.append("$eval", "echoFunction1()");
//		
//		CommandResult result = mongoOps.executeCommand(dbObject);
//		log.debug("result"+result.get("retval"));
		MongoCollection<Document> collection = db.getCollection("article");
		
		Document test = collection.find().first();
		log.debug(test.toJson());
		
//		Query q = new Query(Criteria.where("_id").is(0));
//				.limit(1)
//				.with(new Sort(Sort.Direction.DESC,"_id"));
		
		
//		Article a = mongoOps.findOne(q, Article.class);
//		log.debug(a);
//		log.debug(a.getIdx());
		
		return "tt";
	}
	
//	public Resources<T>
	
	public void PostComment(Map<String,Object> param) {
		MongoDatabase db = mongoClient.getDatabase("horizon");
		MongoCollection<Document> collection = null;
		Document doc = null;
		DBRef ref = null;
		
		ref = new DBRef("article", param.get("article"));
		doc = Document.parse(new JSONObject(param).toString());
		doc.append("_id", new ObjectId());	
		doc.replace("article", ref);
		collection = db.getCollection("comment");
		collection.insertOne(doc);
		
		collection = db.getCollection("article");
		ref = new DBRef("comment",doc.get("_id"));
//		collection.updateOne(eq("_id",param.get("article")), new Docuemnt("$set", new Document("comments",doc.get("_id"))));
//		collection.updateOne(Filters.eq("i",10), new Docuemnt("$set", new Document("comments",doc.get("_id")))););
	}
	
	public List<CommentResource> data(int idx){
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry());
		final DocumentCodec codec = new DocumentCodec(codecRegistry, new BsonTypeClassMap());
		MongoDatabase db = mongoClient.getDatabase("horizon");
		MongoCollection<Document> collection = db.getCollection("comment");
		DBRef ref = new DBRef("article", idx); 
//		Query que = new Query();
//		log.debug(ref);
//		que.addCriteria(Criteria.where("article").is(ref)).limit(1);
//		que.addCriteria(Criteria.where("userId").is("test011")).limit(1);
//		Document doc = mongoOps.findOne(que, Document.class);
		MongoCursor<Document> cursor = collection.find(Filters.eq("article",ref)).iterator();
		List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
		List<CommentResource> list2 = new ArrayList<CommentResource>();
		try {
			while(cursor.hasNext()) {
				Document doc = cursor.next();
				CommentResource comment = new CommentResource();
				ControllerLinkBuilder linkBuilder = linkTo(Comment.class).slash(doc.get("_id"));
				
				Links links = new Links(linkBuilder.withSelfRel(),linkBuilder.withRel("comment"),linkBuilder.slash("article").withRel("article"));
				comment.setContent(doc.getString("content"));
				comment.setUserId(doc.getString("userId"));
//				comment.add(entityLinks.linkToSingleResource(Comment.class, doc.get("_id")).withSelfRel());
				comment.add(links);
				
 						
//				comment.setRegDate(doc.getDate("regDate"));
//				Map<String,Object> map = new HashMap<>();
//				Set<Entry<String, Object>> set = cursor.next().entrySet();
//				
//				for(Entry<String, Object> entry : set) {
//					map.put(entry.getKey(), entry.getValue());
//				}
//				
//				log.debug(cursor.next().toJson(codec));
//				log.debug(message);
//				list2.add(cursor.next());
				log.debug(doc);
				list2.add(comment);
			}
		}finally {
			cursor.close();
		}
//		Document doc = collection.find().first();
//		log.debug(doc.toJson(codec));
		return list2;
	}
}
