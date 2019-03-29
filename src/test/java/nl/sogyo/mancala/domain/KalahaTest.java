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
		final int expected = 0;
		SmallBowl bowl1 = new SmallBowl(5, "first");
		bowl1.setBeads(13);
		final int actual = bowl1.makeMove();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void checkEndInOwnKalaha() {
		final int expected = 0;
		SmallBowl bowl1 = new SmallBowl(5, "first");
		final int actual = ((SmallBowl)bowl1.getNeighbour().getNeighbour()).makeMove();
		Assert.assertEquals(expected, actual);
	}
	
	@Test 
	public void checkPassToBowl1() {
		final int expected = 0;
		SmallBowl bowl1 = new SmallBowl(5, "first");
		//System.out.println("p1: "+bowl1.getOwner().isYourTurn());
		//System.out.println("p2: "+bowl1.getOwner().getOpponent().isYourTurn());
		bowl1.getOwner().switchPlayer();
		//System.out.println("p1: "+bowl1.getOwner().isYourTurn());
		//System.out.println("p2: "+bowl1.getOwner().getOpponent().isYourTurn());
		//final int actual = ((SmallBowl)bowl1.getNeighbour().getNeighbour().getNeighbour().getNeighbour().getNeighbour().getNeighbour().getNeighbour()
		//		.getNeighbour().getNeighbour().getNeighbour()).makeMove();
		final int actual = ((SmallBowl)bowl1.getNeighbour().getNeighbour().getNeighbour().getNeighbour()
				.getNeighbour().getNeighbour().getNeighbour().getNeighbour().getNeighbour().getNeighbour()).makeMove();
		Assert.assertEquals(expected, actual);
	}
}
