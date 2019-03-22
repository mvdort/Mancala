package nl.sogyo.mancala.domain;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

	@Test
	public void switchTurn() {
		
		final boolean expectedTurn = false;
		final boolean actualTurn = new Player().switchPlayer();
		Assert.assertEquals(expectedTurn, actualTurn); 
	}
	
	
}