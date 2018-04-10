package horizon.board.service;

import java.util.Map;

import javax.annotation.Resource;

import org.bson.Document;
import org.springframework.stereotype.Service;

import horizon.board.data.domain.ArticleResource;
import horizon.board.data.repo.ArticleCustomRepository;


@Service("articleService")
public class ArticleServiceImpl implements ArticleService{

	@Resource(name="articleCustomRepo")
	private ArticleCustomRepository repo;
	
	@Override
	public ArticleResource postArticle(Map<String, Object> data) {
		Document doc = new Document(data).append("views", 0);

		return repo.postArticle(doc);
	}
	
}
