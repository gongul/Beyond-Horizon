package horizon.board.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.ExposesResourceFor;
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

import horizon.board.data.domain.Article;
import horizon.board.data.domain.ArticleResource;
import horizon.board.data.domain.Comment;
import horizon.board.data.repo.ArticleCustomRepository;
import horizon.board.data.repo.CommentCustomRepository;

@RepositoryRestController 
public class CommentController {
//	@Resource(name="commentCustomRepo")
//	private CommentCustomRepository repo;
//	
//	@RequestMapping(value="/comments/{id}/article",method=RequestMethod.GET,produces="application/hal+json")
//	public HttpEntity<org.springframework.hateoas.Resource<ArticleResource>> getArticle(@PathVariable("id") String id) {
//		ArticleResource article = repo.getArticle(id);
//		
//		org.springframework.hateoas.Resource<ArticleResource> resource = new org.springframework.hateoas.Resource<ArticleResource>(article);
//		
//		return new ResponseEntity<>(resource,HttpStatus.OK);
//
//	}
}
