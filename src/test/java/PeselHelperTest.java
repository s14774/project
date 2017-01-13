package test.java;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import domain.helpers.PeselHelper;

public class PeselHelperTest {
	@Test
	public void checkPeselTest() {
		String correctPesel = "96061010469";
		boolean isCorrect = PeselHelper.check(correctPesel);
		assertTrue(isCorrect);
	}
	
	@Test
	public void checkPeselWithInvalidCharacterTest(){
	 
		assertFalse(PeselHelper.check("sdzjkbnfsdasd"));
	}
	
	@Test
	public void checkPeselWithInvalidlengthTest(){
		assertFalse(PeselHelper.check("960610104691"));
	}
	
	@Test
	public void checkPeselWithInvalidCheckSumTest(){
		assertFalse(PeselHelper.check("96061010468"));
	}
}
