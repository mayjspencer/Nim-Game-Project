import java.util.Scanner;

public class PlayNim 
{
	public static void main(String[] args)
	{
		Scanner scnr = new Scanner(System.in);
		
		HumanPlayer one = new HumanPlayer("Spencer", scnr);
		RandomPlayer two = new RandomPlayer("Bot");
		int[] initSizes = new int[] {3,4,5};
		
		
		Nim game = new Nim(one, two, initSizes);
		game.play();
	}
}
