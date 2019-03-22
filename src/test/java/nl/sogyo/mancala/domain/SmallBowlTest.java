package nl.sogyo.mancala.domain;

import org.junit.Assert;
import org.junit.Test;

public class SmallBowlTest {

	@Test
	public void checkContentBowl() {
		final int expected = 4;
		final int actual = new SmallBowl().content();
		Assert.assertEquals(actual, expected);
	}
	
	
	@Test
	public void checkLegalMove() {
		final boolean expected = true;
		final boolean actual = new SmallBowl(new Player()).ownerTurn();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void checkmakeMove() {
		final int expected = 0;
		Player player = new Player();
		final int actual = new SmallBowl(player, new SmallBowl(player, new SmallBowl(player,  new SmallBowl(player, new SmallBowl(player,  new SmallBowl(player, new SmallBowl())))))).makeMove();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void checkPass() {
		final int expected = 5;
		Player player = new Player();
		final int actual = new SmallBowl(player, new SmallBowl(player, new SmallBowl(player,  new SmallBowl(player, 
				new SmallBowl(player,  new SmallBowl(player, new SmallBowl())))))).pass(4);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void checkLegalMovePass() {
		final int expected = 4;
		Player player = new Player();
		final int actual = new SmallBowl(player.getOpponent(), new SmallBowl(player.getOpponent(), new SmallBowl(player.getOpponent(),  
				new SmallBowl(player.getOpponent(), new SmallBowl())))).makeMove();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void checkMakeBoard() {
		final int expected = 0;
		final int actual = new SmallBowl(5, "first").makeMove();
		Assert.assertEquals(expected, actual);
	}
	
}