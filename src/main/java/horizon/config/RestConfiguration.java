package horizon.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

import horizon.member.config.MemberValidator;
import horizon.member.data.Member;
import horizon.member.data.MemberRepository;

@Component
public class RestConfiguration extends RepositoryRestConfigurerAdapter  {
//	private static final List<String> EVENTS;
//	 
//    static {
//        List<String> events = new ArrayList<String>();
//        events.add("beforeCreate");
//        events.add("afterCreate");
//        events.add("beforeSave");
//        events.add("afterSave");
//        events.add("beforeLinkSave");
//        events.add("afterLinkSave");
//        events.add("beforeDelete");
//        events.add("afterDelete");
//        EVENTS = Collections.unmodifiableList(events);
//    }
//
//    @Autowired
//    ListableBeanFactory beanFactory;
    
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Member.class);
//		config.withEntityLookup().forRepository(MemberRepository.class,Member::getName, MemberRepository::findByNameStartsWith);
//		config.setDefaultMediaType();
	}
	
	@Override
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener v) {
		v.addValidator("beforeCreate", new MemberValidator());
		
//		super.configureValidatingRepositoryEventListener(v);
//        Map<String, Validator> validators = beanFactory.getBeansOfType(Validator.class);
//        for (Map.Entry<String, Validator> entry : validators.entrySet()) {
//            EVENTS.stream().filter(p -> entry.getKey().startsWith(p)).findFirst()
//                    .ifPresent(p -> v.addValidator(p, entry.getValue()));
//        }
	}
	
}
