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

/**
 * 
 * @author Andrea Balasso
 *Entity that represents a post
 */
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
	
	/**
	 * I put this field in the persistence state for the implementation with solr. I use solr to get the list of posts and I need to show this data.
	 * In this manner, I have more space occupied on the RDBMS, but minor operation that I need to do everytime that someone call the information about posts,
	 * because I need to read it from the entity and not to calculate it.
	 */
	@Column(name="score")
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

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", text=" + text + "]";
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
}
