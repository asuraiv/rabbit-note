package com.ntscorp.rnote.mgr;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author Jupyo Hong
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class QueueManagerTest {
	
	@Autowired
	QueueManager manager;
	
	@Test
	public void testCreateManager() throws Exception {
		assertNotNull(manager);
	}

	@Test
	public void testCreateChannel() throws Exception {
		assertNotNull(manager.createChannel());
	}
}
