package horizon.member.data;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Repository;

import horizon.member.data.Member;

import java.lang.String;

@Repository("memberRepo")
@RepositoryRestResource(collectionResourceRel="users",path="users")
public interface MemberRepository extends MongoRepository<Member, String>, PagingAndSortingRepository<Member, String>{
	
	@RestResource(path="auth",rel="auth")
	Member findByIdAndPassword(@Param("id") String id,@Param("password") String password);
	
	@Override
	@RestResource(exported=false)
	void delete(Iterable<? extends Member> entities) ;
	
	@Override
	@RestResource(exported=false)
	void delete(Member entity);
	
	@Override
	@RestResource(exported=false)
	void delete(String id);
	
	@Override
	@RestResource(exported=false)
	void deleteAll();
	
	
	
	
}
