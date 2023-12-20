public abstract class Player {

	private String name;

	protected Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public abstract int[] getMove(int[] pileSizes);

	public void notifyIllegalMove(String moveInfo) {}

	public void notifyOpponentMove(String name, int[] move) {}

	public void notifyWin() {}

	public void notifyLose() {}
}
