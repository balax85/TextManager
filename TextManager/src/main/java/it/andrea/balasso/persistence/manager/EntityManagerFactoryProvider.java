package it.andrea.balasso.persistence.manager;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

public class EntityManagerFactoryProvider {

	private static EntityManagerFactoryProvider instance = null;

	private EntityManagerFactory factory = null;

	private EntityManagerFactoryProvider() {
	}

	public static synchronized EntityManagerFactoryProvider getInstance() {
		if (instance == null) {
			instance = new EntityManagerFactoryProvider();
		}

		return instance;
	}

	public synchronized EntityManagerFactory getEntityManagerFactory() {
		if (this.factory == null) {
			//this.factory = createEntityManagerFactory(DataSourceProvider.getInstance().get());
			this.factory = createEntityManagerFactory();
		}
		return this.factory;
	}

	//private EntityManagerFactory createEntityManagerFactory(DataSource dataSource) {
	private EntityManagerFactory createEntityManagerFactory() {
//		final Map<Object, Object> properties = new HashMap<>();
//		properties.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, dataSource);
		
		return Persistence.createEntityManagerFactory("textdbPersistence");
		
		//return Persistence.createEntityManagerFactory("planner", properties); //$NON-NLS-1$
	}

	public synchronized void close() {
		if (this.factory != null) {
			this.factory.close();
			this.factory = null;
		}
	}

}
