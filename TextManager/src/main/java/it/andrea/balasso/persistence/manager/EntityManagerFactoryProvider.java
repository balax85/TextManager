package it.andrea.balasso.persistence.manager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
			this.factory = createEntityManagerFactory();
		}
		return this.factory;
	}

	private EntityManagerFactory createEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("textdbPersistence");
	}

	public synchronized void close() {
		if (this.factory != null) {
			this.factory.close();
			this.factory = null;
		}
	}

}
