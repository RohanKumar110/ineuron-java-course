
public class Umpire {

	int guesserNum;
	int playerOneNum;
	int playerTwoNum;
	int playerThreeNum;
	
	public void collectNumFromGuesser(){
		
		Guesser guesser = new Guesser();
		this.guesserNum = guesser.guessNumber();
	}
	
	public void collectNumFromPlayer(){
		
		Player player1 = new Player(1);
		Player player2 = new Player(2);
		Player player3 = new Player(3);
		
		this.playerOneNum = player1.guessNumber();
		this.playerTwoNum = player2.guessNumber();
		this.playerThreeNum = player3.guessNumber();
	}
	
	public void compare() {
		
		if(playerOneNum == guesserNum) {
			
			if(playerTwoNum == guesserNum && playerThreeNum == guesserNum) {
				System.out.println("Game tied all three players guessed correctly!!!");
			}else if(playerTwoNum == guesserNum) {
				System.out.println("Player1 and Player2 won the game!!!");
				System.out.println("Second Match Between Player1 and Player2");
				compareTwoPlayers(1, 2);
			}else if(playerThreeNum == guesserNum) {
				System.out.println("Player1 and Player3 won the game!!!");
				System.out.println("Second Match Between Player1 and Player3");
				compareTwoPlayers(1, 3);
			}else {
				System.out.println("Player1 won the game!!!");
			}
		} 
		else if(playerTwoNum == guesserNum) {
			
			if(playerThreeNum == guesserNum) {
				System.out.println("Player2 and Player3 won the game!!!");
				System.out.println("Second Match Between Player2 and Player3");
				compareTwoPlayers(2, 3);
			}else {
				System.out.println("Player2 won the game!!!");
			}
		} 
		else if(playerThreeNum == guesserNum) {
			System.out.println("Player3 won the game!!!");
		} 
		else {
			System.out.println("Game lost!!!");
		}
	}
	
	public void compareTwoPlayers(int player1,int player2) {
		
		collectNumFromGuesser();
		Player p1 = new Player(player1);
		Player p2 = new Player(player2);
		this.playerOneNum = p1.guessNumber();
		this.playerTwoNum = p2.guessNumber();
		
		if(playerOneNum == guesserNum) {
			
			if(playerTwoNum == guesserNum) {
				System.out.println("Game tied both players guessed correctly!!!");
			}
			else {
				System.out.println("Player"+player1+" won the game!!!");
			}
		}
		else if(playerTwoNum == guesserNum) {
			System.out.println("Player"+player2+" won the game!!!");
		}else {
			System.out.println("Game lost!!!");
		}
	}
}