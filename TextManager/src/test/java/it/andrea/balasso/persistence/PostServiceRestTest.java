package it.andrea.balasso.persistence;

import it.andrea.balasso.service.rest.PostServiceRest;
import it.andrea.balasso.service.rest.type.PostType;
import it.andrea.balasso.test.EntityManagerBaseTest;

import org.junit.Test;

public class PostServiceRestTest extends EntityManagerBaseTest {

	private PostServiceRest postServiceRest = new PostServiceRest();
	
	@Test
	public void testAddNewPost() {
		PostType postType = new PostType();
		postType.setTitle("First test");
		postType.setText("I will do the first test");
		postServiceRest.addNewPost(postType);
	}
	
	@Test
	public void testGetAllPost() {
		postServiceRest.getAllPost();
	}
	
	@Test
	public void testGetPostById() {
		postServiceRest.getPostById(1L);
	}
	
	@Test
	public void testUpdatePost() {
		PostType post = new PostType();
		post.setTitle("New title");
		post.setText("Set this new text");
		postServiceRest.updatePost(1L, post);
	}

}
