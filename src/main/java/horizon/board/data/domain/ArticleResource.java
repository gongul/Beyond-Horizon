package horizon.board.data.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArticleResource extends ResourceSupport{
	@Id
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private int idx;
	private String content;
	private String title;
	private String userId;
	private Date regDate;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private int views;
	
	public ArticleResource() {
		this.regDate = new Date();
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}

}
