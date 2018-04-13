package horizon.board.service;

import java.util.Map;

import org.bson.Document;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;

import horizon.board.data.domain.ArticleResource;
import horizon.board.data.domain.CommentResource;

public interface ArticleService {
	public ArticleResource postArticle(Map<String,Object> map);
	
	public Resources<CommentResource> getComments(int idx);
}
