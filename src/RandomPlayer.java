import java.util.Random;

public class RandomPlayer extends Player
{
	private Random generator;
	
	public RandomPlayer(String name)
	{
		super(name);
		generator = new Random();
	}
	
	public int[] getMove(int[] pileSizes)
	{
		int length = pileSizes.length;
		int[] move = new int[] {0,0};
		if(length > 0)
		{
			
			int rIdx = 0;
			int i = 0;
			while(i == 0)
			{
				rIdx = generator.nextInt(length);
				if(pileSizes[rIdx] !=  0)
				{
					i++;
				}
			}
			
			int rNum = 0;
			int j=0; 
			while(j==0)
			{
				rNum = generator.nextInt(pileSizes[rIdx]+1);
				if(rNum > 0)
				{
					j++;
				}
			}
			
			move[0] = rIdx;
			move[1] = rNum;
		}
		return move;
	}
	
}
