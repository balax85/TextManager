package it.andrea.balasso.service.rest;

import it.andrea.balasso.persistence.entity.Post;
import it.andrea.balasso.persistence.manager.EntityManagerProvider;
import it.andrea.balasso.service.rest.type.PostType;

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
	 * @return
	 */
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Post> getAllPost() {		
		return getEm().createNamedQuery("post.getAll", Post.class).getResultList();
	}
	
	/**
	 * get the post associated with a id
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Post getPostById(@PathParam("id") Long id) {		
		return getEm().createNamedQuery("post.getPostById", Post.class).setParameter("id", id).getSingleResult();
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
	
	@PUT
	@Path("/{id}/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePost(@PathParam("id") Long id, PostType newPost) {
		EntityManager em = getEm();
		
		try {
			em.getTransaction().begin();
			
			Post post = em.find(Post.class, id);
			
			if (post == null) return Response.noContent().build();
		
			post.setTitle(newPost.getTitle());
			post.setText(newPost.getText());
		
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
