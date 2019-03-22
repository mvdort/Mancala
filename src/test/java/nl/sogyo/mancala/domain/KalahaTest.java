package nl.sogyo.mancala.domain;

import org.junit.Assert;
import org.junit.Test;

public class KalahaTest {

	@Test
	public void checkContentBowl() {
		final int expected = 0;
		final int actual = new Kalaha().content();
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void checkPassKalaha() {
		final int expected = 5;
		Player player = new Player();
		final int actual = new SmallBowl(player, new SmallBowl(player, new Kalaha(player.getOpponent(),  new SmallBowl(player.getOpponent(), new SmallBowl(player.getOpponent(), new SmallBowl(player.getOpponent(), new SmallBowl())))))).pass(4);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void checkEndInOwnKalaha() {
		final int expected = 0;
		Player player = new Player();
		final int actual = new SmallBowl(player, new SmallBowl(player, new SmallBowl(player,  new SmallBowl(player, new Kalaha(player, new SmallBowl()))))).makeMove();
		Assert.assertEquals(expected, actual);
	}
}
