package nl.sogyo.mancala.domain;

public class Kalaha extends Bowl{
	
	public Kalaha() {
		//nBeads default=0
	}
	
	public Kalaha(Player owner, Bowl neighbour, SmallBowl firstSmallBowl) {
		//nBeads default=0
		this.setOwner(owner);
		this.setNeighbour(neighbour);
		this.setFirstSmallBowl(firstSmallBowl);
		//this.setOpposite(this);
		//System.out.println("Kalaha 1, neighbour: " + this.getNeighbour() + ", content: " + this.getBeads());
	}
	
	public Kalaha(Player owner, SmallBowl firstSmallBowl) {
		//second Kalaha, set neighbour to first bowl of opponent
		this.setOwner(owner);
		this.setNeighbour(firstSmallBowl);
		this.setFirstSmallBowl(firstSmallBowl);
		//this.setOpposite(this);
		//System.out.println("Kalaha 2, neighbour: " + this.getNeighbour() + ", content: " + this.getBeads());
	}

	public boolean legalMove() {
		return false;
	}
	
	public Bowl opposite() {
		return this;
	}
	
	public int pass(int toPass) {
		//System.out.println("Kalaha owner turn? "+this.ownerTurn());
		//System.out.println("Kalaha neighbour " +this.getNeighbour());
		if (this.ownerTurn() == true) {
			//System.out.println(this.ownerTurn());
			if (toPass >= 1) {
				this.setBeads(this.getBeads() + 1);
				//System.out.println("Kalaha toPass: " + toPass + " /" + this.getBeads());
				getNeighbour().pass(toPass-1);
			}
			else if (toPass == 0) {
				//System.out.println(this.nBeads);
				this.setBeads(this.getBeads() + 1);
				//System.out.println(this.nBeads);
				//Player plays again
				System.out.println("Last bead in own Kalaha, player plays again.");
				//System.out.println("Status player: " + this.getOwner().isYourTurn());
				//System.out.println("Kalaha toPass=1: " + toPass + " /" + this.nBeads);
				//New Turn (same player) - check if there are moves left;
				
			}
		}
		else if (this.ownerTurn() == false) {
			//System.out.println(this.ownerTurn());
			//System.out.println("Kalaha passAll: " + toPass + " /" + this.getBeads());
			this.getNeighbour().pass(toPass);
		}
		//else {
			//System.out.println(this.ownerTurn());
		//}
		//System.out.println(this.nBeads);
		return this.getBeads();
	}
	
}
