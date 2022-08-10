import java.util.Scanner;

public class Guesser {

	private int guessNum;
	
	public int guessNumber() {
		
		Scanner in = new Scanner(System.in);
		do {
			System.out.println("Guesser Kindly guess the number between 1 to 10: ");
			guessNum = in.nextInt();
		}while(guessNum < 1 || guessNum > 10);
		return guessNum;
	}
}