package horizon.board.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="articles",path="articles")
public interface ArticleRepository extends MongoRepository<Article, Integer>,PagingAndSortingRepository<Article, Integer>{

}
