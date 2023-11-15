package assign09;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashTableTest {
private HashTable<Integer, Integer> table;
	@BeforeEach
	void setUp() throws Exception {
		table = new HashTable<>();
		table.put(5, 5);
		
	}

	@Test
	void testRemove() {
		assertEquals(5, table.remove(5));
		

	}
	@Test
	void testContainsKey() {
		assertTrue(table.containsKey(5));
		table.put(2, 2);
		table.put(3, 1);
		table.put(15, 4);
		table.put(4, 5);
		assertTrue(table.containsKey(2));
		assertTrue(table.containsKey(3));
		assertTrue(table.containsKey(15));
		assertTrue(table.containsKey(4));
		table.put(0, 0);
		table.put(9, 9);
		assertTrue(table.containsKey(0));
		assertTrue(table.containsKey(9));
		assertTrue(table.containsKey(2));
		assertTrue(table.containsKey(3));
		assertTrue(table.containsKey(15));
		assertTrue(table.containsKey(4));
		table.remove(9);
		assertFalse(table.containsKey(9));

	}

}
