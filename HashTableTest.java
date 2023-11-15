package assign09;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashTableTest {
private HashTable<String, Integer> table;
	@BeforeEach
	void setUp() throws Exception {
		table = new HashTable();
		table.put("hello", 3);
		
	}

	@Test
	void testRemove() {
		table.remove("hello");
		assertEquals(3, "hello");
	}

}
