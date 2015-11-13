package it.andrea.balasso.test;

import it.andrea.balasso.persistence.manager.EntityManagerProvider;

import org.junit.Before;

public class EntityManagerBaseTest {
	
	@Before
	public void initEntityManager() {
        try {
        	EntityManagerProvider.getInstance().initEntityManagerProvider();
        } finally {
        }
	}

}
