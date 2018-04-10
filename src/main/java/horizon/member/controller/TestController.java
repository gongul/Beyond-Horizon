package horizon.member.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import horizon.board.data.repo.ArticleCustomRepository;
import horizon.member.data.Member;

//@RestController
//@RequestMapping("/test")
//public class TestController {
//	Logger log = Logger.getLogger(this.getClass());
//	@Resource(name="articleCustomRepo")
//	private ArticleCustomRepository repo;
//	
//	@RequestMapping(value="",method=RequestMethod.POST)
//	public HttpEntity<Map<String, Object>> test(@RequestBody Map<String,Object> param){
////		repo.test(param);
////		log.debug(param);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//	@Autowired EntityLinks entityLinks;
	
//	@RequestMapping(value="test",method=RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
//	public HttpEntity<Resources<Member>> testhi(Pageable pageable,
//		    PagedResourcesAssembler assembler, @RequestParam Map<String,String> map) {
//		
////		Link link = linkTo(methodOn(MemberController.class).show(2L));
////		Link link = linkTo(methodOn(MemberController.class).testhi(pageable, assembler, map)).withRel("hello");
//		Link linka = entityLinks.linkToSingleResource(Member.class, "awd2123");
//		log.debug(linka);
////		log.debug(link);
//		log.debug(entityLinks.linkToCollectionResource(Member.class));
//		log.debug(entityLinks.linkForSingleResource(Member.class, "awd2123"));
//		log.debug(entityLinks.linkFor(Member.class,"awd2123"));
////		return new ResponseEntity<>(assembler.toResource(user),HttpStatus.OK);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//}
