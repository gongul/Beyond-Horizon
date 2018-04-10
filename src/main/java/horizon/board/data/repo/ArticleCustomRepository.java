package horizon.board.data.repo;

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

import com.fasterxml.jackson.databind.ObjectMapper;
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
import horizon.board.controller.CommentController;
import horizon.board.data.domain.ArticleResource;
import horizon.board.data.domain.CommentResource;
import horizon.common.util.ObjectUtil;

@Repository("articleCustomRepo")
public class ArticleCustomRepository{

	@Resource(name="mongo")
	private MongoClient mongoClient;
	
	@Resource(name="objectMapper")
	private ObjectMapper mapper;
	
	Logger log = Logger.getLogger(this.getClass());
	
	public ArticleResource postArticle(Document data) {
		MongoDatabase db = mongoClient.getDatabase("horizon");
		MongoCollection<Document> collection = db.getCollection("article");
		long idx = collection.count();
		ArticleResource article = new ArticleResource();
		ControllerLinkBuilder linkBuilder = linkTo(ArticleController.class).slash(idx);
		Links links = new Links(linkBuilder.withSelfRel(),linkBuilder.withRel("article"),linkBuilder.slash("comments").withRel("comments"));
		Document doc = null;
		
		data.append("_id", idx);
		collection.insertOne(data);
		doc = collection.find(Filters.eq("_id", idx)).first();
		ObjectUtil.convertMapToObject(doc, article);
		article.add(links);
		
		return article;
	}
	
	public List<CommentResource> data(int idx){
		MongoDatabase db = mongoClient.getDatabase("horizon");
		MongoCollection<Document> collection = db.getCollection("comment");
		DBRef ref = new DBRef("article", idx); 
		MongoCursor<Document> cursor = collection.find(Filters.eq("article",ref)).iterator();
		List<CommentResource> list = new ArrayList<CommentResource>();
		
		try {
			log.debug("test");
			while(cursor.hasNext()) {
				log.debug("test1");
				Document doc = cursor.next();
				CommentResource comment = new CommentResource();
				ControllerLinkBuilder linkBuilder = linkTo(CommentController.class).slash(doc.get("_id"));
				
				Links links = new Links(linkBuilder.withSelfRel(),linkBuilder.withRel("comment"),linkBuilder.slash("article").withRel("article"));
//				ObjectUtil.convertMapToObject(doc, comment);
				comment.setContent(doc.getString("content"));
				comment.setUserId(doc.getString("userId"));
				comment.setRegDate(doc.getDate("regDate"));
				comment.add(links);
				log.debug(doc);
				list.add(comment);
			}
		}finally {
			cursor.close();
		}
		log.debug("..");
		return list;
	}
}
