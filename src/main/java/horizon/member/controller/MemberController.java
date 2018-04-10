package horizon.member.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.print.attribute.standard.Media;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import horizon.config.MongoConfig;
import horizon.depercated.MongoDAO;
import horizon.member.data.Member;
import horizon.member.data.MemberRepository;
import horizon.member.data.MemberResource;
import horizon.member.service.MemberService;
import horizon.member.service.MemberServiceImpl;

//@ExposesResourceFor(Member.class)
@RepositoryRestController
@RequestMapping(value = "/users/searche/")
public class MemberController {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="memberService")
	private MemberService memberService;
	
	@Resource(name="memberRepo")
	private MemberRepository repo;
	
//	@RequestMapping(value="test",method=RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
//	public HttpEntity<Resources<Member>> testhi() {
//		
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
	
//	@RequestMapping(value="test",method=RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
//	public HttpEntity<Resources<Member>> testhi(Pageable pageable,
//		    PagedResourcesAssembler assembler, @RequestParam Map<String,String> map) {
//		
////		Link link = linkTo(methodOn(MemberController.class).show(2L));
//		Link link = linkTo(methodOn(MemberController.class).testhi(pageable, assembler, map)).withRel("hello");
//		Page<Member> user = repo.findAll(pageable);
//		Iterator<Member> i = user.iterator();
//		Resources<Member> source = new Resources<>(user,link);
//		
//		
//		while(i.hasNext()) {
//			log.debug(i.next());
//		}
//		
////		return new ResponseEntity<>(assembler.toResource(user),HttpStatus.OK);
//		return new ResponseEntity<>(source,HttpStatus.OK);
//	}
	
	
    
//	@RequestMapping(value="findOneByNameStartsWith",method=RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
//	public HttpEntity<MemberResource> testhi(@RequestParam String name) {
//		MemberResource resource = null;
//		
//		Links links = new Links(linkTo(methodOn(MemberController.class).testhi(name)).withSelfRel(),
//								linkTo(methodOn(MemberController.class).testhi(name)).withRel("member"));
//		
//		try {
//			Member user = repo.findOneByNameStartsWith(name);
//			resource = new MemberResource(user);
//		}catch (NullPointerException e) {
//			log.error(e);
//		}catch (Exception e) {
//			log.error(e);
//		}finally {
//			if(resource == null) {
//				resource = new MemberResource();
//			}
//			resource.add(links);
//		}
//		
//		return new ResponseEntity<MemberResource>(resource,HttpStatus.OK);
//	}
	
	
}
