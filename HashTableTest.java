package assign09;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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
		assertEquals(0, table.size());
		table.put(1, 5);
		table.put(2, 3);
		table.put(3, 2);
		assertEquals(5, table.remove(1));
		assertEquals(2, table.remove(3));
		assertEquals(null, table.remove(3));


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
		assertTrue(table.containsKey(5));
		table.put(9, 0);
		assertTrue(table.containsKey(9));
		table.put(23, 0);
		table.put(25, 0);
		table.put(100, 0);
		table.put(10, 0);
		table.put(50, 0);
		assertTrue(table.containsKey(50));
		assertTrue(table.containsKey(0));
		assertTrue(table.containsKey(9));
		assertTrue(table.containsKey(2));
		assertTrue(table.containsKey(3));
		assertTrue(table.containsKey(15));
		assertTrue(table.containsKey(4));
		assertTrue(table.containsKey(5));
		assertTrue(table.containsKey(10));
		assertTrue(table.containsKey(100));
		assertTrue(table.containsKey(25));
		assertTrue(table.containsKey(23));

	}
	@Test
	void testClear() {
		assertEquals(1, table.size());
		table.clear();
		assertEquals(0, table.size());
	}
	@Test
	void testContainsValue() {
		assertTrue(table.containsValue(5));
		for(int i = 1; i <= 10; i++)
			table.put(i + 20, i);
		assertTrue(table.containsValue(10));
		assertTrue(table.containsValue(1));
		table.remove(21);
		assertFalse(table.containsValue(1));
	}
	@Test
	void testEntries() {
		List<MapEntry<Integer, Integer>> list = new ArrayList<>();
		MapEntry<Integer, Integer> entry1 = new MapEntry<>(1, 2);
		table.remove(5);
		list.add(entry1);
		table.put(1, 2);
		assertTrue(list.equals(table.entries()));
		MapEntry<Integer, Integer> entry2 = new MapEntry<>(2, 3);
		MapEntry<Integer, Integer> entry3 = new MapEntry<>(3, 4);
		list.add(entry2);
		list.add(entry3);
		table.put(2, 3);
		table.put(3, 4);
		assertTrue(list.equals(table.entries()));



	}
	@Test
	void testIsEmpty() {
		assertFalse(table.isEmpty());
		table.clear();
		assertTrue(table.isEmpty());
		table.put(1, 1);
		table.put(2, 2);
		table.remove(1);
		assertFalse(table.isEmpty());
		table.remove(2);
		assertTrue(table.isEmpty());
		table.put(1, 1);
		table.put(1, 2);
		table.remove(1);
		assertTrue(table.isEmpty());
	}
	@Test
	void get() {
		assertEquals(5, table.get(5));
		table.clear();
		assertEquals(null, table.get(5));
		table.put(1, 2);
		assertEquals(2, table.get(1));
		for(int i = 1; i <= 10; i++) {
			table.put(i + 10, i);
		}
		assertEquals(1, table.get(11));
		assertEquals(10, table.get(20));

	}
	@Test 
	void testPut() {
		for(int i = 0; i < 6; i++) {
			table.put(i, i);
		}
		assertEquals(6, table.size());
		assertEquals(5, table.get(5));
		table.put(5, 100);
		assertEquals(100, table.get(5));
		
	}
	@Test
	void size() {
		assertEquals(1, table.size());
		table.put(2, 2);
		table.put(1, 2);
		table.put(3, 2);
		assertEquals(4, table.size());
		table.put(3, 1);
		assertEquals(4, table.size());
		
	}
	

}
