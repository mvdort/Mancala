package nl.sogyo.mancala.domain;
import java.util.Scanner;

public class SmallBowl extends Bowl{
		
	//First SmallBowl: (also creates first player, and second SmallBowl)
	public SmallBowl(int following, String firstOrSecond) {
		this.setBeads(4);
		this.setOwner(new Player());
		this.setFollowing(following);
		this.setFirstorSecond(firstOrSecond);
		this.setFirstSmallBowl(this);
		this.setNeighbour(new SmallBowl(this.getOwner(), this.getFollowing()-1, this.getFirstorSecond(), this));
	}
	
	//Second SmallBow, etc: (also creates Kalahas)
	public SmallBowl(Player owner, int following, String end, SmallBowl firstSmallBowl) {
		this.setBeads(4);
		this.setOwner(owner);
		this.setFollowing(following);
		this.setFirstorSecond(end);
		this.setFirstSmallBowl(firstSmallBowl);
		
		if (this.getFollowing() > 0 && this.getFirstorSecond() .equals("first")) {
			this.setNeighbour(new SmallBowl(this.getOwner(), this.getFollowing()-1, this.getFirstorSecond(), this.getFirstSmallBowl()));
		}
		else if (this.getFollowing() > 0 && this.getFirstorSecond() .equals("second")) {
			this.setNeighbour(new SmallBowl(this.getOwner(), this.getFollowing()-1, this.getFirstorSecond(), this.getFirstSmallBowl()));
		}
		
		else if (this.getFollowing() == 0 && this.getFirstorSecond() .equals("first")) {
			this.setNeighbour(new Kalaha(this.getOwner(), new SmallBowl(this.getOwner().getOpponent(), 5, "second", this.getFirstSmallBowl()),
					this.getFirstSmallBowl()));
		}
		else if (this.getFollowing() == 0 && this.getFirstorSecond() .equals("second")) {
			this.setNeighbour(new Kalaha(this.getOwner(), this.getFirstSmallBowl()));
		}
	}
		
	//Method to make sure the first move is legal (doesn't matter which player starts)
	public boolean makeFirstLegal() {
		boolean legalmove;
		if (this.getOwner().isYourTurn() == true) {
			legalmove = true;
		}
		else {
			legalmove = false;
		}	
		return legalmove;
	}
	
	//Method to check whether a chosen move is legal (i.e. SmallBowl is owned by current player, and not 0)
	public boolean legalMove() {
		boolean legalmove;
		if (this.getOwner().isYourTurn() == true && this.getBeads() > 0) {
			legalmove = true;
		}
		else {
			legalmove = false;
			System.out.println(">>Not a legal move<<");
		}	
		return legalmove;
	}
	
	//Method to make the move (after legal move check):
	public int makeMove() {
		if (this.legalMove() == true) {	
			int toPass = this.getBeads();
			this.setBeads(this.getBeads() - this.getBeads());
			this.getNeighbour().pass(toPass-1);
			System.out.println("New situation (after move):");
			this.printBoard();
			return this.getBeads();
		}
		else {
			System.out.println("Choose another bowl to start");
			return this.getBeads();
		}
	}
	
	//Method for first turn (different from subsequent turns: make first turn legal, and no check for content in own small bowls required)
	void firstTurn() {
		int chosen;
		System.out.print("User input (chose bowl 1-6,8-13): ");
		Scanner userInp = new Scanner(System.in);
		chosen = Integer.parseInt(userInp.next());
		//adapt current player to starting player
		if (((SmallBowl)this.getNeighbour(chosen-1)).makeFirstLegal() == false) {
			this.getOwner().switchPlayer();
		}
		//start actual move
		((SmallBowl)this.getNeighbour(chosen-1)).makeMove();
		this.checkTurnPossible();
	}
	
	//Method for second and subsequent turns: 
	void turn() {
		int chosen;
		System.out.print("User input (chose bowl 1-6,8-13): ");
		Scanner userInp = new Scanner(System.in);
		chosen = Integer.parseInt(userInp.next());
		((SmallBowl)this.getNeighbour(chosen-1)).makeMove();
		//After the move (incl possible player switch), check whether there are possible moves for the current player
		this.checkTurnPossible();
	}
	
	//Method to check whether the current player has possible moves (i.e. not all SmallBowls empty)
	//void checkTurnPossible() {
	//	if (this.ownerTurn() == true) {
	//		//check: beads left in this players small bowls? If yes, then nextTurn
	//		if (this.getNeighbour(0).getBeads() > 0 ||
	//				this.getNeighbour(1).getBeads() > 0 ||
	//				this.getNeighbour(2).getBeads() > 0 ||
	//				this.getNeighbour(3).getBeads() > 0 ||
	//				this.getNeighbour(4).getBeads() > 0 ||
	//				this.getNeighbour(5).getBeads() > 0) {
	//			this.turn();
	//		}
	//		else {
	//			//end game, check winner
	//			System.out.println("No more turns possible for current player, end game");
	//			this.checkWinner();
	//		}
	//	}
	//	else if ((this.getOwner().getOpponent()).isYourTurn() == true) {
	//		//check: beads left in this players small bowls? If yes, then nextTurn
	//		if (this.getNeighbour(7).getBeads() > 0 ||
	//				this.getNeighbour(8).getBeads() > 0 ||
	//				this.getNeighbour(9).getBeads() > 0 ||
	//				this.getNeighbour(10).getBeads() > 0 ||
	//				this.getNeighbour(11).getBeads() > 0 ||
	//				this.getNeighbour(12).getBeads() > 0) {
	//			this.turn();
	//		}
	//		else {
	//			//end game, check winner
	//			System.out.println("No more turns possible for current player, end game");
	//			this.checkWinner();
	//		}
	//	}
	//}
	
	void checkTurnPossible() {
		int validTurns = 0;
		for (int j=0; j<14; j++) {
			if (this.getNeighbour(j) instanceof SmallBowl == true &&
					this.getNeighbour(j).ownerTurn() == true && 
					this.getNeighbour(j).getBeads() > 0) {
				validTurns = validTurns + 1;
			}
		}
		if (validTurns > 0) {
			this.turn();
		}
		else if (validTurns == 0) {
			System.out.println("No more valid turns...");
			this.checkWinner();
		}
	}
	
	void checkWinner() {
		int score_1 = 0;
		int score_2 = 0;
		
		for (int i=0; i<14; i++) {
			if (this.getNeighbour(i).ownerTurn() == true) {
				score_1 = score_1 + this.getNeighbour(i).getBeads();
			}
			else if (this.getNeighbour(i).ownerTurn() == false) {
				score_2 = score_2 + this.getNeighbour(i).getBeads();
			}
		}
		
		int totalBeads = score_1 + score_2;
		if (totalBeads != 48) {
			System.out.println("Number of beads is not 48 but " + totalBeads);
		}
		
		if (score_1 == score_2) {
			System.out.println("No winner, equal score (score current player: " + score_1 + ", score other player: " + score_2 + ")");
		}
		else if (score_1 > score_2) {
			System.out.println("Current player wins! (score: "+score_1+", score other player: "+score_2+")");
		}
		else if (score_1 < score_2) {
			System.out.println("Other player wins! (score: "+score_2+", score current player: "+score_1+")");
		}
		
		
	}
	
	
	
	
	
	
	
	public int giveAll() {
		int toGive = this.getBeads();
		this.setBeads(this.getBeads() - this.getBeads());
		this.opposite().takeAll(toGive);
		return toGive;
	}
	
	
	
	void printBoard() {
		System.out.println("		Kalaha, content " + this.getFirstSmallBowl().getNeighbour(13).getBeads());
		System.out.print("SmallBowl 1, content " + this.getFirstSmallBowl().getBeads());
		System.out.println("  -  SmallBowl 13, content " + this.getFirstSmallBowl().getNeighbour(12).getBeads());
		System.out.print("SmallBowl 2, content " + this.getFirstSmallBowl().getNeighbour(1).getBeads());
		System.out.println("  -  SmallBowl 12, content " + this.getFirstSmallBowl().getNeighbour(11).getBeads());
		System.out.print("SmallBowl 3, content " + this.getFirstSmallBowl().getNeighbour(2).getBeads());
		System.out.println("  -  SmallBowl 11, content " + this.getFirstSmallBowl().getNeighbour(10).getBeads());
		System.out.print("SmallBowl 4, content " + this.getFirstSmallBowl().getNeighbour(3).getBeads());
		System.out.println("  -  SmallBowl 10, content " + this.getFirstSmallBowl().getNeighbour(9).getBeads());
		System.out.print("SmallBowl 5, content " + this.getFirstSmallBowl().getNeighbour(4).getBeads());
		System.out.println("  -  SmallBowl 9,  content " + this.getFirstSmallBowl().getNeighbour(8).getBeads());
		System.out.print("SmallBowl 6, content " + this.getFirstSmallBowl().getNeighbour(5).getBeads());
		System.out.println("  -  SmallBowl 8,  content " + this.getFirstSmallBowl().getNeighbour(7).getBeads());
		System.out.println("		Kalaha, content " + this.getFirstSmallBowl().getNeighbour(6).getBeads());
		
	}
	
	//public boolean legalMove() {
	//	boolean legalmove;
	//	if (this.owner.yourTurn == true && this.nBeads > 0) {
	//		legalmove = true;
	//	}
	//	else {
	//		legalmove = false;
	//	}	
	//	//System.out.println("This is a legal move: " + legalmove);
	//	return legalmove;
	//}
	
	//public int makeMove() {
	//	
	//	if (this.legalMove() == true) {
	//		//System.out.println("legal move");		
	//		neighbour.pass(this.nBeads-1);
	//		this.nBeads = this.nBeads - this.nBeads;
	//		return this.nBeads;
	//	}
	//	else {
	//		//System.out.println("Not a legal move");
	//		return this.nBeads;
	//	}
	//}
	
	
	
}
