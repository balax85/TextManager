package it.andrea.balasso.persistence.manager;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * @author Andrea Balasso
 * interceptor to get the entitymanager. It intercept the call and than call the method requested
 *
 */
public class EntityManagerInterceptor implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
        	EntityManagerProvider.getInstance().initEntityManagerProvider();
            chain.doFilter(request, response);
        } finally {
            EntityManagerProvider.getInstance().closeEntityManager();
        }
    }

    public void init(FilterConfig config) {
    }

    public void destroy() {
    }
}
