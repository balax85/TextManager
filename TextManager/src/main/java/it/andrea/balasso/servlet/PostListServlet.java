package it.andrea.balasso.servlet;

import it.andrea.balasso.persiste.entity.Post;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@WebServlet("/listPosts")
public class PostListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
//        try {
//            Context initContext = new InitialContext();
//            Context envContext = (Context) initContext.lookup("java:comp/env");
//            DataSource ds = (DataSource) envContext.lookup("jdbc/textdb");
//            Connection conn = ds.getConnection();
            
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("textdbPersistence");
        EntityManager em = emf.createEntityManager();
        
//            SessionFactory sessionFactory = (SessionFactory) request.getServletContext().getAttribute("SessionFactory");
//            
//            Session session = sessionFactory.getCurrentSession();
//            Transaction tx = session.beginTransaction();

        Post post = em.find(Post.class, 1L);

        //            tx.commit();
            
               writer.println(String.format("User #%d: %-15s %s", 1,
                        post.getTitle(), post.getText()));
            
//        } catch (NamingException ex) {
//            System.err.println(ex);
//        } catch (SQLException ex) {
//            System.err.println(ex);
//        }
    }	

}
