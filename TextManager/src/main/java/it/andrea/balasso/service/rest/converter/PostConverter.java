package it.andrea.balasso.service.rest.converter;

import it.andrea.balasso.persistence.entity.Post;
import it.andrea.balasso.service.rest.type.PostType;

public class PostConverter {
	
	public static PostType postToPostType(Post post) {
		if (post == null) return null;
		PostType postType = new PostType();
		postType.setId(post.getId());
		postType.setText(post.getText());
		postType.setTitle(post.getTitle());
		postType.setScore(post.getScore());
		return postType;
	}
	
	public static Post postTypeToPost(PostType postType) {
		if (postType == null) return null;
		Post post = new Post();
		post.setText(postType.getText());
		post.setTitle(postType.getTitle());
		post.setScore(postType.getScore());
		return post;
	}

}
