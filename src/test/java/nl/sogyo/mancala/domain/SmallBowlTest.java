package nl.sogyo.mancala.domain;

import org.junit.Assert;
import org.junit.Test;

public class SmallBowlTest {
	
	//Check content of new SmallBowl (should be 4)
	@Test
	public void checkContentFirstBowl() {
		System.out.println("Check conent:");
		final int expected = 4;
		final int actual = new SmallBowl(1, "first").content();
		Assert.assertEquals(expected, actual);
	}
		
	//Check whether it's a legal move (based on ownership)
	@Test
	public void checkLegalMove() {
		System.out.println("Check legal move:");
		final boolean expected = true;
		final boolean actual = new SmallBowl(1, "first").ownerTurn();
		Assert.assertEquals(expected, actual);
	}
	
	//Check whether move can be made (legal, not 0) and then make move
	@Test
	public void checkmakeMove() {
		System.out.println("Check make move:");
		final int expected = 0;
		final int actual = new SmallBowl(5, "first").makeMove();
		Assert.assertEquals(expected, actual);
	}
	
	//Check whether bead is added to own Kalaha
	@Test
	public void checkMakeMove8() {
		System.out.println("Check bead added to own Kalaha:");
		final int expected = 0;
		SmallBowl bowl1 = new SmallBowl(5, "first");
		bowl1.setBeads(8);
		final int actual = bowl1.makeMove();
		Assert.assertEquals(expected, actual);
	}
	
	//Check the pass-method
	@Test
	public void checkPass() {
		System.out.println("Check pass 4:");
		final int expected = 5;
		final int actual = new SmallBowl(7, "first").pass(4);
		Assert.assertEquals(expected, actual);
	}
	
	//Check whether move is legal (owner, not 0), initiate move, and pass beads
	@Test
	public void checkLegalMovePass() {
		System.out.println("Check not-legal move:");
		final int expected = 4;
		SmallBowl bowl1 = new SmallBowl(5, "first");
		bowl1.getOwner().switchPlayer();
		final int actual = bowl1.makeMove();
		Assert.assertEquals(expected, actual);
	}
	
	//Check whether move can be initiated on 3rd SmallBowl
	@Test
	public void checkMoveThird() {
		System.out.println("Check move on third SmallBowl:");
		final int expected = 0;
		SmallBowl bowl1 = new SmallBowl(5, "first");
		final int actual = ((SmallBowl)bowl1.getNeighbour().getNeighbour()).makeMove();
		Assert.assertEquals(expected, actual);
	}
	
	//Check whether full loop is made with 13 beads
	@Test
	public void checkFromOpposite() {
		System.out.println("Check full loop:");
		final int expected = 0;
		SmallBowl bowl1 = new SmallBowl(5, "first");
		bowl1.setBeads(13);
		final int actual = bowl1.makeMove();
		Assert.assertEquals(expected, actual);
	}
	
	//Check whether the x-th neighbour function works
	@Test
	public void checkStartNeighbourX() {
		System.out.println("Start neighbour X:");
		final int expected = 0;
		SmallBowl bowl1 = new SmallBowl(5, "first");
		final int actual = ((SmallBowl)bowl1.getNeighbour(2)).makeMove();
		Assert.assertEquals(expected, actual);
	}
	
	//Check whether the stealing rule works (steal from opposite and all beads to Kalaha)
	@Test
	public void checkStealing() {
		System.out.println("Check stealing:");
		final int expected = 0;
		SmallBowl bowl1 = new SmallBowl(5, "first");
		bowl1.getNeighbour(4).setBeads(0);
		final int actual = bowl1.makeMove();
		Assert.assertEquals(expected, actual);
	}
	
	//Check whether game also ends when last beads ends in own Kalaha
	@Test
	public void endGame() {
		System.out.println("Check end game:");
		final int expected = 0;
		SmallBowl bowl1 = new SmallBowl(5, "first");
		bowl1.setBeads(0);
		bowl1.getNeighbour().setBeads(0);
		bowl1.getNeighbour(2).setBeads(0);
		bowl1.getNeighbour(3).setBeads(0);
		bowl1.getNeighbour(4).setBeads(0);
		bowl1.getNeighbour(5).setBeads(1);
		bowl1.turn(); //give  input: 6
		final int actual = bowl1.getNeighbour(5).content();
		Assert.assertEquals(expected, actual);
	}
}