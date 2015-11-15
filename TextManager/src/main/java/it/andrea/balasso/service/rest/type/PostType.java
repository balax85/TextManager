package it.andrea.balasso.service.rest.type;

import it.andrea.balasso.util.PostUtil;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author Andrea Balasso
 * 
 * Support class that represent the information to send/receive to/from the client
 *
 */
public class PostType {
	
	private Long id;

	private String title;
	
	private String text;
	
	private Integer score;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public Integer getScore() {
		return this.score;
	}
	
	public void setScore(Integer score) {
		this.score = score;
	}
	
}
