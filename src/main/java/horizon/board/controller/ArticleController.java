package horizon.board.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import horizon.board.data.Article;
import horizon.board.data.ArticleCustomRepository;
import horizon.board.data.CommentResource;

@RepositoryRestController 
@RequestMapping(value = "/articles/")
public class ArticleController {
	@Resource(name="articleCustomRepo")
	private ArticleCustomRepository repo;

	Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping(value="{idx}/comments",method=RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<List<?>> getComment(@PathVariable("idx") int idx) {
//		List<Map<String,Object>> list = repo.data(idx);
		List<CommentResource> list = repo.data(idx);
//		list.iterator();
//		Link link = linkTo(methodOn(ArticleController.class).getComment(idx)).withSelfRel();
//		Resources<CommentResource> resource = new Resources<CommentResource>(list);
//		log.debug("¼º°ø");
//		log.debug(resource);
//		List<String> test1 = new ArrayList<>()
//		Resources<?> a = new Resources<>(content, links)
		log.debug(list.get(0));
		log.debug(list);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
}
//Link link = linkTo(methodOn(MemberController.class).show(2L));
//Link link = linkTo(methodOn(MemberController.class).testhi(pageable, assembler, map)).withRel("hello");
//Page<Member> user = repo.findAll(pageable);
//Iterator<Member> i = user.iterator();
//Resources<Member> source = new Resources<>(user,link);