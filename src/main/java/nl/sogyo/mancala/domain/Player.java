package nl.sogyo.mancala.domain;

public class Player {
	
	private boolean yourTurn;
	private Player opponent;
	
	//Create player
	public Player() {
		this.setYourTurn(true);
		//System.out.println("p1: " + this.isYourTurn());
		this.setOpponent(new Player(this)) ;
	}
	//Create other player
	public Player(Player opponent) {
		this.setOpponent(opponent);
		this.setYourTurn(!opponent.isYourTurn());
		//System.out.println("p2: " + this.isYourTurn());
	}
	
	public Player getOpponent() {
		return opponent;
	}
	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}
	public boolean isYourTurn() {
		return yourTurn;
	}
	public void setYourTurn(boolean yourTurn) {
		this.yourTurn = yourTurn;
	}
	//Switch player	
	public boolean switchPlayer() {
		this.setYourTurn(!this.isYourTurn());
		getOpponent().setYourTurn(!this.isYourTurn());
		
		return this.isYourTurn();
	}
	
	//new Player(true);

}
