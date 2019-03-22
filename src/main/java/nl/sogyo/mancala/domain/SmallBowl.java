package nl.sogyo.mancala.domain;

public class SmallBowl extends Bowl{
	
	public SmallBowl() {
		this.setBeads(4);
		//System.out.println("Small Bowl with: " + this.nBeads);
	}
	
	public SmallBowl(Player owner) {
		this.setBeads(4);
		this.setOwner(owner);
		//System.out.println("Small Bowl with owner: " + this.nBeads);
	}
	
	//public SmallBowl(Player owner, int following) {
	//	this.setBeads(4);
	//	this.setOwner(owner);
	//	this.setFollowing(following);
	//	
	//	if (this.getFollowing() > 0) {
	//		this.setNeighbour(new SmallBowl(this.getOwner(), this.getFollowing() -1));
	//	}
	//	else if (this.getFollowing() == 0) {
	//		this.setNeighbour(new Kalaha(this.getOwner(), new SmallBowl(this.getOwner().opponent, 5, "second")));
	//	}
	//}
	
	//First SmallBowl: (also starts first player, and second SmallBowl)
	public SmallBowl(int following, String firstOrSecond) {
		this.setBeads(4);
		this.setOwner(new Player());
		this.setFollowing(following);
		this.setFirstorSecond(firstOrSecond);
		this.setNeighbour(new SmallBowl(this.getOwner(), this.getFollowing()-1, this.getFirstorSecond(), this));
		System.out.println("First bowl made, neighbour: " + this.getNeighbour() + ", content: " + this.getBeads());
	}
	
	//Second SmallBow, etc: (also starts Kalahas)
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
		System.out.println("SmallBowl, neighbour: " + this.getNeighbour() + ", content: " + this.getBeads());
	}
	
	public SmallBowl(Player owner, Bowl neighbour) {
		this.setBeads(4);
		this.setOwner(owner);
		this.setNeighbour(neighbour);
		//System.out.println("Small Bowl with owner and neighbour: " + this.nBeads);
	}
	
	public boolean legalMove() {
		boolean legalmove;
		if (this.getOwner().isYourTurn() == true && this.getBeads() > 0) {
			legalmove = true;
		}
		else {
			legalmove = false;
		}	
		return legalmove;
	}
	
	public int makeMove() {
		if (this.legalMove() == true) {	
			this.getNeighbour().pass(this.getBeads()-1);
			this.setBeads(this.getBeads() - this.getBeads());
			return this.getBeads();
		}
		else {
			return this.getBeads();
		}
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
