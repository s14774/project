package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.helpers.NameHelper;

public class NameHelperTest {
	@Test
	public void checkOnlyChars() {
		String uncorrectName = "Mateusz12";
		boolean isCorrect = NameHelper.checkName(uncorrectName);
		assertFalse(isCorrect);
	}
	
	@Test
	public void checkFirstBigLetter() {
		String uncorrectName = "mateusz";
		boolean isCorrect = NameHelper.checkName(uncorrectName);
		assertFalse(isCorrect);
	}
	
	@Test
	public void checkdoubleName() {
		String correctName = "Mateusz Szymon";
		boolean isCorrect = NameHelper.checkName(correctName);
		assertTrue(isCorrect);
	}
	
	@Test
	public void checkSecondNameSmallLetter() {
		String correctName = "Mateusz szymon";
		boolean isCorrect = NameHelper.checkName(correctName);
		assertFalse(isCorrect);
	}
	
	@Test
	public void checkBigLetterInside() {
		String correctName = "MateuszSzymon";
		boolean isCorrect = NameHelper.checkName(correctName);
		assertFalse(isCorrect);
	}
	
	@Test
	public void checkSpaceAtStart() {
		String correctName = " Mateusz";
		boolean isCorrect = NameHelper.checkName(correctName);
		assertFalse(isCorrect);
	}
	
	@Test
	public void checkSpaceAtEnd() {
		String correctName = "Mateusz ";
		boolean isCorrect = NameHelper.checkName(correctName);
		assertFalse(isCorrect);
	}
	
	@Test
	public void checkSpaceAtEndSecondName() {
		String correctName = "Mateusz Szymon ";
		boolean isCorrect = NameHelper.checkName(correctName);
		assertFalse(isCorrect);
	}
	
	@Test
	public void checkDoubleSpaceBetweenNames() {
		String correctName = "Mateusz  Szymon";
		boolean isCorrect = NameHelper.checkName(correctName);
		assertFalse(isCorrect);
	}
	
	@Test
	public void checkTripleName() {
		String correctName = "Mateusz Szymon Marek";
		boolean isCorrect = NameHelper.checkName(correctName);
		assertFalse(isCorrect);
	}
	
	@Test
	public void checkSurname() {
		String correctName = "Mateusz Szymon";
		boolean isCorrect = NameHelper.checkName(correctName);
		assertFalse(isCorrect);
	}
	
}
