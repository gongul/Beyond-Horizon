package horizon.member.config;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import horizon.member.data.Member;

public class MemberValidator implements Validator{
	Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public boolean supports(Class<?> arg0) {
		return Member.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Member member = (Member) obj;
//		String message = checkAllData(member);
		
//		if(message != null) {
//			errors.rejectValue(message, message+".empty");
//		}
//		
		log.debug("name"+member.getName());
		log.debug("password"+member.getPassword());
		
		if(checkInputString(member.getName())) {
			errors.rejectValue("name", "name.empty");
		}
		if(checkInputString(member.getPassword())) {
			errors.rejectValue("password", "password.empty");
		}
//		
//		log.debug(member.getName());
		
//		errors.reject("TEST");
	}
	
	private String checkAllData(Member member) {
		String message = null;
		
		if(member.getId() == null || member.getId().trim().length() == 0) {
			message = "id";
		}else if(member.getPassword() == null || member.getPassword().trim().length() == 0) {
			message = "name";
		}else if(member.getName() == null || member.getName().trim().length() == 0) {
			message = "name";
		}else if(member.getSex() == null || member.getSex().trim().length() == 0) {
			message = "sex";
		}
	
        return message;
    }
	
	private boolean checkInputString(String input) {
		
		boolean test = (input == null || input.trim().length() == 0);
        return test;
    }
	
}
