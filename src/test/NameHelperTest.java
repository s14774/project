package test;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.helpers.NameHelper;

public class NameHelperTest {
	@Test
	public void checkOnlyChars() {
		String uncorrectName = "Mateusz12";
		boolean isCorrect = NameHelper.check(uncorrectName);
		assertFalse(isCorrect);
	}
	
	@Test
	public void checkFirstBigLetter() {
		String uncorrectName = "mateusz";
		boolean isCorrect = NameHelper.check(uncorrectName);
		assertFalse(isCorrect);
	}
	
	@Test
	public void checkdoubleName() {
		String correctName = "Mateusz Szymon";
		boolean isCorrect = NameHelper.check(correctName);
		assertTrue(isCorrect);
	}
}
