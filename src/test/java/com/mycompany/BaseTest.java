package com.mycompany;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 * Created by Igor Dmitriev / Mikalai Alimenkou on 4/29/16
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({
		TransactionalTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class
})
@Transactional
public abstract class BaseTest {

	private static final String[] DB_UNIT_SET_UP = { "",
			"****************************************************************",
			"*************** DATABASE HAS BEEN ALREADY SET UP ***************",
			"****************************************************************"
	};

	@Before
	public void dbAllSet() {
		for (String line : DB_UNIT_SET_UP) {
			System.out.println(line);
		}
		System.out.println();
	}

	@PersistenceContext
	protected EntityManager em;

	protected Session getSession() {
		return em.unwrap(Session.class);
	}
}
