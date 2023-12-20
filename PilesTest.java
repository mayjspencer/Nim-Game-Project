import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class PilesTest {

	@Test
	void testConstructor() {
		Piles piles = new Piles(1);
		assertArrayEquals(new int[] {1}, piles.getSizes());

		piles = new Piles(3, 4, 5);
		assertArrayEquals(new int[] {3, 4, 5}, piles.getSizes());

		piles = new Piles(1, 3, 5, 7);
		assertArrayEquals(new int[] {1, 3, 5, 7}, piles.getSizes());

		// Check invalid initial pile sizes.
		try {
			new Piles(null);
			fail();
		} catch (IllegalArgumentException e) {}

		try {
			new Piles();
			fail();
		} catch (IllegalArgumentException e) {}

		try {
			new Piles(0);
			fail();
		} catch (IllegalArgumentException e) {}

		try {
			new Piles(3, 4, -5);
			fail();
		} catch (IllegalArgumentException e) {}

		try {
			new Piles(1, 0, 5, 7);
			fail();
		} catch (IllegalArgumentException e) {}
	}

	@Test
	void testEncapsulation() {
		int size = 5;
		int[] initSizes = {size};
		Piles piles = new Piles(initSizes);

		initSizes[0] = -1234567;
		assertEquals(size, piles.getSizes()[0]);

		int[] returnedSizes = piles.getSizes();
		returnedSizes[0] = -1234567;
		assertEquals(size, piles.getSizes()[0]);
	}

	@Test
	void testLegalMoves() throws IllegalMoveException {
		Piles piles = new Piles(3, 4, 5);
		assertArrayEquals(new int[] {3, 4, 5}, piles.getSizes());

		piles.removeObjects(new int[] {0, 1});
		assertArrayEquals(new int[] {2, 4, 5}, piles.getSizes());
		piles.removeObjects(new int[] {2, 5});
		assertArrayEquals(new int[] {2, 4, 0}, piles.getSizes());
		piles.removeObjects(new int[] {1, 3});
		assertArrayEquals(new int[] {2, 1, 0}, piles.getSizes());
		piles.removeObjects(new int[] {0, 2});
		assertArrayEquals(new int[] {0, 1, 0}, piles.getSizes());
		piles.removeObjects(new int[] {1, 1});
		assertArrayEquals(new int[] {0, 0, 0}, piles.getSizes());

		piles = new Piles(7, 3, 5, 1);
		assertArrayEquals(new int[] {7, 3, 5, 1}, piles.getSizes());

		piles.removeObjects(new int[] {2, 3});
		assertArrayEquals(new int[] {7, 3, 2, 1}, piles.getSizes());
		piles.removeObjects(new int[] {0, 7});
		assertArrayEquals(new int[] {0, 3, 2, 1}, piles.getSizes());
		piles.removeObjects(new int[] {2, 2});
		assertArrayEquals(new int[] {0, 3, 0, 1}, piles.getSizes());
		piles.removeObjects(new int[] {3, 1});
		assertArrayEquals(new int[] {0, 3, 0, 0}, piles.getSizes());
		piles.removeObjects(new int[] {1, 3});
		assertArrayEquals(new int[] {0, 0, 0, 0}, piles.getSizes());
	}

	@Test
	void testIllegalMoves() throws IllegalMoveException {
		Piles piles1 = new Piles(3, 4, 5);

		try {
			piles1.removeObjects(null);
			fail();
		} catch (IllegalMoveException e) {
			assertEquals("null move", e.getMessage());
		}

		try {
			piles1.removeObjects(new int[] {});
			fail();
		} catch (IllegalMoveException e) {
			assertEquals("Invalid length: 0", e.getMessage());
		}

		try {
			piles1.removeObjects(new int[] {2, 3, 1});
			fail();
		} catch (IllegalMoveException e) {
			assertEquals("Invalid length: 3", e.getMessage());
		}

		try {
			piles1.removeObjects(new int[] {-1, 2});
			fail();
		} catch (IllegalMoveException e) {
			assertEquals("Index out of bounds: -1", e.getMessage());
		}

		try {
			piles1.removeObjects(new int[] {3, -1});
			fail();
		} catch (IllegalMoveException e) {
			assertEquals("Index out of bounds: 3", e.getMessage());
		}

		piles1.removeObjects(new int[] {2, 5});
		try {
			piles1.removeObjects(new int[] {2, 1});
			fail();
		} catch (IllegalMoveException e) {
			assertEquals("Pile 2 is empty.", e.getMessage());
		}

		try {
			piles1.removeObjects(new int[] {2, -1});
			fail();
		} catch (IllegalMoveException e) {
			assertEquals("Pile 2 is empty.", e.getMessage());
		}

		try {
			piles1.removeObjects(new int[] {1, -1});
			fail();
		} catch (IllegalMoveException e) {
			assertEquals("Nonpositive object number: -1", e.getMessage());
		}

		try {
			piles1.removeObjects(new int[] {0, -42});
			fail();
		} catch (IllegalMoveException e) {
			assertEquals("Nonpositive object number: -42", e.getMessage());
		}

		try {
			piles1.removeObjects(new int[] {0, 4});
			fail();
		} catch (IllegalMoveException e) {
			assertEquals("Object number greater than pile size: 4 > 3",
					e.getMessage());
		}

		try {
			piles1.removeObjects(new int[] {1, 5});
			fail();
		} catch (IllegalMoveException e) {
			assertEquals("Object number greater than pile size: 5 > 4",
					e.getMessage());
		}
	}

	@Test
	void testIsEmpty() throws IllegalMoveException {
		Piles piles = new Piles(1, 2);
		assertFalse(piles.isEmpty());

		piles.removeObjects(new int[] {1, 1});
		assertFalse(piles.isEmpty());
		piles.removeObjects(new int[] {1, 1});
		assertFalse(piles.isEmpty());
		piles.removeObjects(new int[] {0, 1});
		assertTrue(piles.isEmpty());

		piles = new Piles(1, 3, 5, 7);
		assertFalse(piles.isEmpty());

		piles.removeObjects(new int[] {0, 1});
		assertFalse(piles.isEmpty());
		piles.removeObjects(new int[] {1, 3});
		assertFalse(piles.isEmpty());
		piles.removeObjects(new int[] {3, 7});
		assertFalse(piles.isEmpty());
		piles.removeObjects(new int[] {2, 5});
		assertTrue(piles.isEmpty());
	}
}
