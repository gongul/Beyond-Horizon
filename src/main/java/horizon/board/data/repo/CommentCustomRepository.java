package horizon.board.data.repo;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Repository;

import com.mongodb.DBRef;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import horizon.board.controller.ArticleController;
import horizon.board.data.domain.ArticleResource;
import horizon.common.util.ObjectUtil;

@Repository("commentCustomRepo")
public class CommentCustomRepository {
	@Resource(name="mongo")
	private MongoClient mongo;
	
	Logger log = Logger.getLogger(this.getClass());
	
	public ArticleResource getArticle(String id) {
		ArticleResource article = new ArticleResource();
		ObjectId objectId = new ObjectId(id);
		MongoDatabase db = mongo.getDatabase("horizon");
		MongoCollection<Document> col = db.getCollection("comment");
		
		Document doc = col.find(eq("_id",objectId)).projection(fields(include("article"),excludeId())).first();
		DBRef ref = (DBRef) doc.get("article");
		col = db.getCollection("article");
		doc = col.find(eq("_id",ref.getId())).first();
		ObjectUtil.convertMapToObject(doc,article);
		ControllerLinkBuilder linkBuilder = linkTo(ArticleController.class).slash("articles").slash(ref.getId());
		Links links = new Links(linkBuilder.withSelfRel(),linkBuilder.withRel("article"),linkBuilder.slash("comments").withRel("comments"));
		article.add(links);
		
		return article;
	}
}
