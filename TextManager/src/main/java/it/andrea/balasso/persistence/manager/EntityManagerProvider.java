package it.andrea.balasso.persistence.manager;

import javax.persistence.EntityManager;

/**
 * 
 * @author Andrea Balasso
 * provider for the entity manager 
 */
public class EntityManagerProvider {

	private static final ThreadLocal<EntityManager> ENTITY_MANAGER = new ThreadLocal<>();
	private static EntityManagerProvider instance;

	public static synchronized EntityManagerProvider getInstance() {
		if (instance == null) {
			instance = new EntityManagerProvider();
		}
		return instance;
	}

	private EntityManagerProvider() {
	}

	public void initEntityManagerProvider() {
		set(EntityManagerFactoryProvider.getInstance().getEntityManagerFactory().createEntityManager());
	}

	public EntityManager get() {
		return ENTITY_MANAGER.get();
	}

	public void closeEntityManager() {
		final EntityManager em = get();
		if (em != null) {
			em.close();
		}
		remove();
	}

	private static void set(EntityManager entityManager) {
		ENTITY_MANAGER.set(entityManager);
	}

	private static void remove() {
		ENTITY_MANAGER.remove();
	}

}
