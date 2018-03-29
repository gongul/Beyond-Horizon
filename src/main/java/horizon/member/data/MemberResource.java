package horizon.member.data;

import java.util.List;

import javax.persistence.Column;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","name","sex"})
public class MemberResource extends ResourceSupport{
	@JsonProperty(value="id",index=1)
	private String userId;
	@JsonIgnore
	private String password;
	private String name;
	private String sex;
	
	public MemberResource() {
		
	}
	
	public MemberResource(Member member) {
		this.userId = member.getId();
		this.password = member.getPassword();
		this.name = member.getName();
		this.sex = member.getSex();
	}
	//	public String getUserId() {
//		return id;
//	}
//
//	public void setUserId(String id) {
//		this.id = id;
//	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

}
