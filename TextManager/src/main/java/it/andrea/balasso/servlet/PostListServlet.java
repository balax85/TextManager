package it.andrea.balasso.servlet;

import it.andrea.balasso.persistence.entity.Post;
import it.andrea.balasso.persistence.manager.EntityManagerProvider;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Andrea Balasso
 * Test servlet to do some test with Tomcat
 *
 */
@WebServlet("/listPosts")
public class PostListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();

        EntityManager em = EntityManagerProvider.getInstance().get();
        
        Post post = em.find(Post.class, 1L);

        writer.println(String.format("User #%d: %-15s %s", 1,
        		post.getTitle(), post.getText()));
            
    }	

}
