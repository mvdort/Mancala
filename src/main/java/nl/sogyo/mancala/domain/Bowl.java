package nl.sogyo.mancala.domain;

public abstract class Bowl  {
	
	private int beads;
	private Player owner;
	private Bowl neighbour;
	private int following;
	private String end;
	private SmallBowl firstSmallBowl;
	
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
	
	public Bowl getNeighbour(int nr) {
		Bowl neighbourX;
		if (nr == 0) {
			neighbourX = this;
		}
		else if (nr == 1) {
			neighbourX = this.getNeighbour();
			//return neighbourX;
		}
		else {
			neighbourX = this.getNeighbour().getNeighbour(nr-1);
			//System.out.println("get neighbour...");
		}
		return neighbourX;
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

	public String getFirstorSecond() {
		return end;
	}

	public void setFirstorSecond(String end) {
		this.end = end;
	}

	public SmallBowl getFirstSmallBowl() {
		return firstSmallBowl;
	}

	public void setFirstSmallBowl(SmallBowl firstSmallBowl) {
		this.firstSmallBowl = firstSmallBowl;
	}

	public int content() {
		return this.getBeads();
	}
	
	public boolean ownerTurn() {
		//System.out.println(this.getOwner().isYourTurn());
		return this.getOwner().isYourTurn();
	}
	
	public int pass(int toPass) {
		//System.out.println("To pass: " + toPass);
		if (toPass == 0) {
			//System.out.println("to pass is zero " + toPass);
			this.setBeads(this.getBeads() +1);
			
			//check content=1?
			if (this.getBeads() == 1 && this.ownerTurn() == true) {
				((SmallBowl)this.opposite()).giveAll();
			}
			//else continue
			
			//System.out.println("Status player: " + this.getOwner().isYourTurn());
			this.getOwner().switchPlayer();
			System.out.println(">>Switch player!<<");
			//System.out.println("Status player: " + this.getOwner().isYourTurn());
			
		}
		else if (toPass >= 1) {
			this.setBeads(this.getBeads() + 1);
			getNeighbour().pass(toPass-1);
		}
		return this.getBeads();
	}
	
	public Bowl opposite() {
		return (this.getNeighbour().opposite()).getNeighbour();
	}
	
	public int takeAll(int toTake) {
		this.setBeads(this.getBeads() + toTake);
		int beadsToKalaha = this.getBeads();
		this.setBeads(this.getBeads() - this.getBeads());
		this.getNeighbour().toKalaha(beadsToKalaha);
		return this.getBeads();
	}
	
	public int toKalaha(int beadsToKalaha) {
		if (this instanceof Kalaha) {
			this.setBeads(this.getBeads() + beadsToKalaha);
		}
		else {
			this.getNeighbour().toKalaha(beadsToKalaha);
		}
		return this.getBeads();
	}
	
	
	

}
