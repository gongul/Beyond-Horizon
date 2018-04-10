package horizon.board.service;

import java.util.Map;

import org.bson.Document;
import org.springframework.stereotype.Service;

import horizon.board.data.domain.ArticleResource;

public interface ArticleService {
	public ArticleResource postArticle(Map<String,Object> map);
}
