package horizon.board.data.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import horizon.board.data.domain.Comment;

@RepositoryRestResource(collectionResourceRel="comments",path="comments")
public interface CommentRepository extends MongoRepository<Comment, String>,PagingAndSortingRepository<Comment, String>{

}
