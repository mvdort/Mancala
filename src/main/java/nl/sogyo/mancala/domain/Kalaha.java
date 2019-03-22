package nl.sogyo.mancala.domain;

public class Kalaha extends Bowl{
	
	public Kalaha() {
		//nBeads default=0
	}
	
	public Kalaha(Player owner, Bowl neighbour) {
		//nBeads default=0
		this.setOwner(owner);
		this.setNeighbour(neighbour);
		System.out.println("Kalaha made");
	}
	
	public Kalaha(Player owner) {
		//second Kalaha, set neighbour to first bowl of opponent
		System.out.println("Kalaha made (no neighbour yet)");
	}

	public boolean legalMove() {
		return false;
	}
	
	public int pass(int toPass) {
		if (this.ownerTurn() == true) {
			if (toPass >= 1) {
				this.setBeads(this.getBeads() + 1);
				//System.out.println("Kalaha toPass: " + toPass + " /" + this.nBeads);
				getNeighbour().pass(toPass-1);
			}
			else if (toPass == 0) {
				//System.out.println(this.nBeads);
				this.setBeads(this.getBeads() + 1);
				//System.out.println(this.nBeads);
				System.out.println("Last bead in Kalaha, player plays again.");
				System.out.println("Status player: " + this.getOwner().isYourTurn());
				//Player plays again
				//System.out.println("Kalaha toPass=1: " + toPass + " /" + this.nBeads);
			}
		}
		else if (this.ownerTurn() == false) {
			getNeighbour().pass(toPass);
			//System.out.println("Kalaha passAll: " + toPass + " /" + this.nBeads);
		}
		//System.out.println(this.nBeads);
		return this.getBeads();
	}
	
}
