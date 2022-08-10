import java.util.Scanner;

public class Player {

	private int playerNumber;
	private int guessNum;
	
	public Player(int playerNumber) {
		this.playerNumber = playerNumber;
	}
	
	public int guessNumber() {
		
		Scanner in = new Scanner(System.in);
		do {
			System.out.println("Player"+playerNumber+ " kindly guess the number between 1 to 10: ");
			guessNum = in.nextInt();
		}while(guessNum < 1 || guessNum > 10);
		return guessNum;
	}
}