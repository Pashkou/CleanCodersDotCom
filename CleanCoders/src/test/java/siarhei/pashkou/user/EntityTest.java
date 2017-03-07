package siarhei.pashkou.user;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import siarhei.pashkou.codecast.ContextSetup;
import siarhei.pashkou.context.Context;
import siarhei.pashkou.model.Entity;
import siarhei.pashkou.model.User;

public class EntityTest {

	@Before
	public void setUp(){
		 ContextSetup.initializeContext();
	}
	
	@Test
	public void twoDifferentUsersAreNotEqual() {
		Entity entity1 = Context.userGateway.save("entity1");
		Entity entity2 = Context.userGateway.save("entity2");
		assertFalse(entity1.equals(entity2));
	}
	
	@Test
	public void oneUserIdEqualsToILtself(){
		Entity user = Context.userGateway.save("user");
		assertTrue(user.equals(user));
	}

	@Test
	public void usersWithTheSameIdAreEqual(){
		Entity entity1 = Context.userGateway.save("entity1");
		Entity entity2 = Context.userGateway.save("entity2");
		String id = UUID.randomUUID().toString();
		entity1.setId(id);
		entity2.setId(id);
		assertTrue(entity1.equals(entity2));
	}
	
	@Test
	public void usersWithNullIsa_areNeverEqual(){
		Entity entity1 = new User("entity1");
		Entity entity2 = new User("entity2");
		assertFalse(entity1.equals(entity2));
	}
}
