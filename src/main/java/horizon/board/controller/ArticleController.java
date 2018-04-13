package horizon.board.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import horizon.board.data.domain.Article;
import horizon.board.data.domain.ArticleResource;
import horizon.board.data.domain.CommentResource;
import horizon.board.data.repo.ArticleCustomRepository;
import horizon.board.data.repo.ArticleRepository;
import horizon.board.service.ArticleService;

@RepositoryRestController
public class ArticleController {
	@Resource(name="articleCustomRepo")
	private ArticleCustomRepository cRepo;
	
	@Resource(name="articleService")
	private ArticleService service;
	
	@Resource(name="articleRepo")
	private ArticleRepository repo;
	
	Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping(value="/articles/{idx}/comments",method=RequestMethod.GET,produces="application/hal+json")
	public HttpEntity<Resources<CommentResource>> getComments(@PathVariable("idx") int idx) {
		Resources<CommentResource> resource = service.getComments(idx);
		
		return new ResponseEntity<>(resource,HttpStatus.OK);
	}
	
	@RequestMapping(value="/articles",method=RequestMethod.POST,produces="application/hal+json")
	public HttpEntity<ArticleResource> postArticle(@RequestBody Map<String,Object> map) {
		ArticleResource article = service.postArticle(map);
		
		log.debug(article);
		return new ResponseEntity<>(article, HttpStatus.OK);
	}
	
	
	
}
