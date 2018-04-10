package horizon.board.data.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import horizon.board.data.domain.Article;
import java.lang.String;

@Repository("articleRepo")
@RepositoryRestResource(collectionResourceRel="articles",path="articles")
public interface ArticleRepository extends MongoRepository<Article, Integer>,PagingAndSortingRepository<Article, Integer>{
	
	List<Article> findByUserId(@Param("userId") String userId);
}
