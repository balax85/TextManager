package it.andrea.balasso.persistence.entity;

import it.andrea.balasso.persistence.entity.model.EntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name="post.getAll",
			query="select p from Post p"),
	@NamedQuery(name="post.getPostById",
			query="select p from Post p where p.id = :id")		
})
@Table(name="Post")
public class Post extends EntityModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title", length=100)
	private String title;
	
	@Column(name="text")
	@Lob
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

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", text=" + text + "]";
	}
	
}
