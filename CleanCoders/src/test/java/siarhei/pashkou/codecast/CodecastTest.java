package siarhei.pashkou.codecast;

import static org.junit.Assert.*;

import org.junit.Test;

import siarhei.pashkou.model.Codecast;

public class CodecastTest {

	@Test
	public void twoDifferentCodecast_areNotEqual() {
		Codecast codecast1 = new Codecast();
		Codecast codecast2 = new Codecast();
		assertFalse(codecast1.equals(codecast2));
	}

}
