package it.andrea.balasso.service.rest;

import it.andrea.balasso.persistence.entity.Post;
import it.andrea.balasso.persistence.manager.EntityManagerProvider;
import it.andrea.balasso.service.rest.converter.PostConverter;
import it.andrea.balasso.service.rest.type.PostType;
import it.andrea.balasso.util.PostUtil;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 
 * @author Andrea Balasso
 *Rest to manage the data associated with the post data type
 */
@Path("/post")
public class PostServiceRest {
	
	private EntityManager getEm() {
		return EntityManagerProvider.getInstance().get();
	}
	
	/**
	 * get all the post present in th DB
	 * @return the list of the posts
	 * 
	 * In the  first implementation I used this request to get the list of all posts. With the add of solr,
	 * I changed the call to get all the posts with the solr services
	 */
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PostType> getAllPost() {
		List<PostType> postTypeList = new ArrayList<PostType>();
		for (Post post : getEm().createNamedQuery("post.getAll", Post.class).getResultList()) {
			postTypeList.add(PostConverter.postToPostType(post));
		}
		return postTypeList;
	}
	
	/**
	 * get the post associated with a id
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PostType getPostById(@PathParam("id") Long id) {
		if (id == null) return null;
		return PostConverter.postToPostType(getEm().createNamedQuery("post.getPostById", Post.class).setParameter("id", id).getSingleResult());
	}
	
	/**
	 * insert a new post
	 * @param newPost
	 * @return
	 */
	@POST
	@Path("/addNewPost")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addNewPost(PostType newPost) {
		EntityManager em = getEm();
		
		try {
			em.getTransaction().begin();
		
			Post post = new Post();
			post.setTitle(newPost.getTitle());
			post.setText(newPost.getText());
			post.setScore(PostUtil.calculateScore(newPost.getTitle(), newPost.getText()));
		
			em.persist(post);
		
			em.getTransaction().commit();
		} catch (Exception e)  {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return Response.serverError().build();
		}
		
		return Response.ok().build();
	}
	
	/**
	 * 
	 * @param id the id that I have to update
	 * @param newPost the new data
	 * @return result of the operation
	 */
	@PUT
	@Path("/{id}/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePost(@PathParam("id") Long id, PostType newPost) {
		
		if (id == null) return Response.noContent().build();
		
		EntityManager em = getEm();
		
		try {
			em.getTransaction().begin();
			
			Post post = em.find(Post.class, id);
			
			if (post == null) return Response.noContent().build();
		
			post.setTitle(newPost.getTitle());
			post.setText(newPost.getText());
			post.setScore(PostUtil.calculateScore(newPost.getTitle(), newPost.getText()));
		
			em.merge(post);
		
			em.getTransaction().commit();
		} catch (Exception e)  {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return Response.serverError().build();
		}
		
		return Response.ok().build();
	}

}
