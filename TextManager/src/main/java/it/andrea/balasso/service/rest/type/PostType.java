package it.andrea.balasso.service.rest.type;

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
	
}
