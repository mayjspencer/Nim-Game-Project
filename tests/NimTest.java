import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class NimTest {

	@Test
	void testTakeTurn() {
		int[][] moves = {{2, 3}, {0, 3}};
		MockPlayer p1 = new MockPlayer("Player 1", moves);
		MockPlayer p2 = new MockPlayer("Player 2", null);
		Nim game = new Nim(p1, p2, new int[] {3, 5, 4});

		// Player 1 removes 3 from pile 2.
		game.takeTurn();
		assertArrayEquals(new int[] {3, 5, 1}, game.getPileSizes());
		assertNull(p1.getIllegalMoveInfo());
		assertNull(p1.getOpponentName());
		assertNull(p1.getOpponentMove());
		assertNull(p2.getIllegalMoveInfo());
		assertEquals("Player 1", p2.getOpponentName());
		assertArrayEquals(new int[] {2, 3}, p2.getOpponentMove());

		// Player 1 removes 3 from pile 0. (It is still Player 1's turn because
		// takeTurn does not swap Players.)
		game.takeTurn();
		assertArrayEquals(new int[] {0, 5, 1}, game.getPileSizes());
		assertNull(p1.getIllegalMoveInfo());
		assertNull(p1.getOpponentName());
		assertNull(p1.getOpponentMove());
		assertNull(p2.getIllegalMoveInfo());
		assertEquals("Player 1", p2.getOpponentName());
		assertArrayEquals(new int[] {0, 3}, p2.getOpponentMove());

		// Create a new game with new players.
		moves = new int[][] {{1, 0}, {0, 1}, {0, 7}, {1, 2}};
		p1 = new MockPlayer("Player 1", moves);
		p2 = new MockPlayer("Player 2", null);
		game = new Nim(p1, p2, new int[] {2, 2});

		// Player 1 tries to remove 0 from pile 1, which causes an illegal move
		// exception. Player 1 then successfully removes 1 from pile 0.
		game.takeTurn();
		assertArrayEquals(new int[] {1, 2}, game.getPileSizes());
		String moveInfo = "Nonpositive object number: 0";
		assertEquals(moveInfo, p1.getIllegalMoveInfo());
		assertNull(p1.getOpponentName());
		assertNull(p1.getOpponentMove());
		assertNull(p2.getIllegalMoveInfo());
		assertEquals("Player 1", p2.getOpponentName());
		assertArrayEquals(new int[] {0, 1}, p2.getOpponentMove());

		// Player 1 tries to remove 7 from pile 0 and then successfully removes
		// 2 from pile 1.
		game.takeTurn();
		assertArrayEquals(new int[] {1, 0}, game.getPileSizes());
		moveInfo = "Object number greater than pile size: 7 > 1";
		assertEquals(moveInfo, p1.getIllegalMoveInfo());
		assertNull(p1.getOpponentName());
		assertNull(p1.getOpponentMove());
		assertNull(p2.getIllegalMoveInfo());
		assertEquals("Player 1", p2.getOpponentName());
		assertArrayEquals(new int[] {1, 2}, p2.getOpponentMove());
	}
}
