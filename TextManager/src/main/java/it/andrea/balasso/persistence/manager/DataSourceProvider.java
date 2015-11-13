package it.andrea.balasso.persistence.manager;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.jboss.logging.Logger;

public class DataSourceProvider {

	private final Logger logger = Logger.getLogger(this.getClass());

	private static DataSource dataSource = null;
	private static DataSourceProvider instance;

	public synchronized static DataSourceProvider getInstance() {
		if (instance == null) {
			instance = new DataSourceProvider();
		}
		return instance;
	}

	private DataSourceProvider() {
		try {
			final InitialContext ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/textdb"); //$NON-NLS-1$
		} catch (NamingException e) {
			logger.error("Could not lookup the default datasource", e); //$NON-NLS-1$
			throw new RuntimeException(e);
		}
	}

	public DataSource get() {
		return dataSource;
	}

}
