
public class Piles 
{
	private int[] sizes;
	
	public Piles() throws IllegalArgumentException
	{
		throw new IllegalArgumentException();
	}
	public Piles(int... initSizes) throws IllegalArgumentException
	{
		if(initSizes == null)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			int[] sizesCopy = new int[initSizes.length];
			for(int i=0; i<initSizes.length; i++)
			{
				if(initSizes[i] <= 0)
				{
					throw new IllegalArgumentException();
				}
				else 
				{
					sizesCopy[i] = initSizes[i];

				}
			}
			sizes = new int[sizesCopy.length];
			for(int i=0; i<sizesCopy.length; i++)
			{
				sizes[i] = initSizes[i];
			}
		}
	}

	public int[] getSizes()
	{
		int[] copy = new int[sizes.length];
		for(int i=0; i<sizes.length; i++)
		{
			copy[i] = sizes[i];
		}
		return copy;
	}
	
	public void removeObjects(int[] move) throws IllegalMoveException
	{
		if(move == null)
		{
			throw new IllegalMoveException("null move");
		}
		else if(move.length != 2)
		{
			throw new IllegalMoveException("Invalid length: " + move.length);
		}
		else if(move[0] < 0 || move[0] >= sizes.length)
		{
			throw new IllegalMoveException("Index out of bounds: " + move[0]);
		}
		else if(sizes[move[0]] == 0 )
		{
			throw new IllegalMoveException("Pile " + move[0] +" is empty." );
		}
		else if(move[1] < 0)
		{
			throw new IllegalMoveException("Nonpositive object number: " + move[1]);
		}
		else if(move[1] > sizes[move[0]]) 
		{
			throw new IllegalMoveException("Object number greater than pile size: " + move[1] + " > " + sizes[move[0]]);
		}
		else
		{
			int index = move[0];
			int removeCount = move[1];
			
			sizes[index] -= removeCount;
		}
	}
	
	public boolean isEmpty()
	{
		int emptyCounter = 0;
		for(int i=0; i<sizes.length; i++)
		{
			if(sizes[i] == 0)
			{
				emptyCounter++;
			}
		}
		if(emptyCounter == sizes.length)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
