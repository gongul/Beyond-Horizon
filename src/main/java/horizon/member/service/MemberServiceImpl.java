package horizon.member.service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import horizon.member.controller.MemberController;
import horizon.member.data.Member;
import horizon.member.data.MemberRepository;
import horizon.member.data.MemberResource;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	@Resource(name="memberRepo")
	private MemberRepository repo;
	
	@Resource(name="mongoTemplate")
	private MongoTemplate mongo;

	@Override
	public Page<Member> findByNamePassword(Member vo, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}


//	@Override
//	public List<MemberResource> findByName(String name) {
//		List<Member> memberList = repo.findByName(name);
//		List<MemberResource> resourceList = new ArrayList<MemberResource>();
//		
//		for(int i=0,n=memberList.size();i<n;i++) {
//			Member member = memberList.get(0);
//			Link link = linkTo(MemberController.class).slash(member.getObjectId()).withSelfRel();
//			MemberResource resource = new MemberResource(member);
//			
//			resource.add(link);
//			resourceList.add(resource);
//			
//		}
//		
//		return resourceList;
//		
//	}
	
	
	
	
	
}
