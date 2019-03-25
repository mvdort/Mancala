package nl.sogyo.mancala.domain;

public class Mancala {
	
	
	//Create game
	SmallBowl bowl1 = new SmallBowl(5, "first");
		
	public static void main(String[] args){
		//Create mancala game:
		Mancala mancala = new Mancala();
		//Print board at start:
		mancala.bowl1.printBoard();		
		//Start game: initiate first Turn (rest of turns via SmallBowl)
		mancala.bowl1.firstTurn();
		
		//end game, print winner (via SmallBowl)
	}
	
}
