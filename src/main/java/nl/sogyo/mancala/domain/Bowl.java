package nl.sogyo.mancala.domain;

public abstract class Bowl  {
	
	private int beads;
	private Player owner;
	private Bowl neighbour;
	private int following;
	private String end;
	
	public int getBeads() {
		return beads;
	}

	public void setBeads(int beads) {
		this.beads = beads;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Bowl getNeighbour() {
		return neighbour;
	}

	public void setNeighbour(Bowl neighbour) {
		this.neighbour = neighbour;
	}

	public int getFollowing() {
		return following;
	}

	public void setFollowing(int following) {
		this.following = following;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public int content() {
		return this.getBeads();
	}
	
	public boolean ownerTurn() {
		return getOwner().isYourTurn();
	}
	
	public int pass(int toPass) {
		if (toPass == 0) {
			this.setBeads(this.getBeads() +1);
			//System.out.println("Status player: " + this.getOwner().isYourTurn());
			this.getOwner().switchPlayer();
			//System.out.println("Status player: " + this.getOwner().isYourTurn());
		}
		else if (toPass >= 1) {
			this.setBeads(this.getBeads() + 1);
			getNeighbour().pass(toPass-1);
		}
		return this.getBeads();
	}
	
	
	
	
	

}
