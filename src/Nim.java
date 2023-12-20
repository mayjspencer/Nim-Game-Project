public class Nim {

	private Player currentPlayer;
	private Player waitingPlayer;
	private Piles piles;
	private Player winner = null;
	private Player loser = null;

	public Nim(Player p1, Player two, int[] initSizes) {
		currentPlayer = p1;
		waitingPlayer = two;
		piles = new Piles(initSizes);
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public Player getWaitingPlayer() {
		return waitingPlayer;
	}

	public int[] getPileSizes() {
		return piles.getSizes();
	}

	public Player getWinner() {
		return winner;
	}

	public Player getLoser() {
		return loser;
	}

	public void takeTurn() 
	{
		int[] move;
		int i=0;
		int[] pileCopy = new int[getPileSizes().length];
		for(int k=0; k<getPileSizes().length; k++)
		{
			pileCopy[k] = getPileSizes()[k];
		}
		move = getCurrentPlayer().getMove(getPileSizes());
		while(i==0)
		{
			try 
			{
				piles.removeObjects(move);
				getWaitingPlayer().notifyOpponentMove(getCurrentPlayer().getName(), move);
				boolean same = true;
				for(int j=0; j<getPileSizes().length; j++)
				{
					if(pileCopy[i] == getPileSizes()[i])
					{
						same = false;
					}
				}
				if(same == false)
				{
					i++;
				}
			}
			catch(IllegalMoveException e)
			{
				getCurrentPlayer().notifyIllegalMove(e.getMessage());
				move = getCurrentPlayer().getMove(getPileSizes());
			}
		}
	}

	public void checkGameOver() {
		if (piles.isEmpty()) {
			winner = waitingPlayer;
			loser = currentPlayer;
		}
	}

	public void swapPlayers() {
		Player temp = currentPlayer;
		currentPlayer = waitingPlayer;
		waitingPlayer = temp;
	}

	public void play() {
		while (winner == null || loser == null) {
			takeTurn();
			checkGameOver();
			swapPlayers();
		}
		winner.notifyWin();
		loser.notifyLose();
	}
}
