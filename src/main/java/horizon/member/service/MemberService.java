package horizon.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import horizon.member.data.Member;
import horizon.member.data.MemberResource;

public interface MemberService {
//	public List<MemberResource> findByName(String name);
	
	public Page<Member> findByNamePassword(Member vo, Pageable pageable);
}
