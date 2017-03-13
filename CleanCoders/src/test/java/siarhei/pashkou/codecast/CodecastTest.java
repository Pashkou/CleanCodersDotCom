package siarhei.pashkou.codecast;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import siarhei.pashkou.model.Codecast;

public class CodecastTest {

	@Test
	public void twoDifferentCodecast_areNotEqual() {
		Codecast codecast1 = new Codecast();
		Codecast codecast2 = new Codecast();
		assertFalse(codecast1.equals(codecast2));
	}
	
	@Test
	public void compareTwoCodecastsWithSameDate(){
		Codecast codecast = new Codecast();
		codecast.setPublished(LocalDate.now());
		assertEquals(LocalDate.now(), codecast.getPublishedDate());
	}

}
