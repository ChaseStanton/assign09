package assign09;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a hash table implementing custom map class.
 * 
 * @author Reece Kalmar, Chase Stanton
 * @version November 15, 2023
 *
 * @param table    - Stores the hashtables key value pairs
 * @param items    - The amount of items stored in the hash table
 * @param capacity - The Total size of the hash table
 */

public class HashTable<K, V> implements Map<K, V> {
	private ArrayList<MapEntry<K, V>> table;
	private int items;
	private int capacity;


	/**
	 * Constructs an empty hash table with a default capacity of 11.
	 */
	public HashTable() {
		this.items = 0;
		this.capacity = 11;
		table = new ArrayList<MapEntry<K, V>>();
		for (int i = 0; i < capacity; i++) {
			table.add(null);
		}
	}

	/**
	 * Clears all entries from the hash table.
	 */
	@Override
	public void clear() {
		this.table = new ArrayList<MapEntry<K, V>>(capacity);

	}

	/**
	 * Checks if the hash table contains a specific key.
	 *
	 * @param key The key to check for.
	 * @return true if the key is found, false otherwise.
	 */
	@Override
	public boolean containsKey(K key) {
		int index = hash(key);
		int startingIndex = index;
		int i = 1;

		while (table.get(index) != null) {
			if (table.get(index).getKey().equals(key)) {
				return true;
			}
			index = (startingIndex + i * i) % capacity;
			i++;
			if (index == startingIndex) {
				break;
			}
		}
		return false;
	}

	/**
	 * Checks if the hash table contains a specific value.
	 *
	 * @param value The value to check for.
	 * @return true if the value is found, false otherwise.
	 */
	@Override
	public boolean containsValue(V value) {
		for (MapEntry<K, V> entry : this.table) {
			if (entry != null) {
				if (entry.getValue().equals(value))
					return true;
			}
		}
		return false;
	}

	/**
	 * Returns a list of all entries in the hash table.
	 *
	 * @return A list of MapEntry objects representing the entries in the hash
	 *         table.
	 */
	@Override
	public List<MapEntry<K, V>> entries() {
		List<MapEntry<K, V>> list = new ArrayList<>();
		for (MapEntry<K, V> entry : this.table) {
			if (entry != null)
				list.add(entry);
		}
		return list;
	}

	/**
	 * Retrieves the value associated with a specific key.
	 *
	 * @param key The key whose associated value is to be retrieved.
	 * @return The value associated with the given key, or null if the key is not
	 *         found.
	 */
	@Override
	public V get(K key) {
		int index = hash(key);
		int startingIndex = index;
		int i = 1;

		while (table.get(index) != null) {
			if (table.get(index).getKey().equals(key))
				return table.get(index).getValue();
			index = (startingIndex + i * i) % capacity;
			i++;
			if (startingIndex == index)
				break;
		}
		return null;
	}

	/**
	 * Checks if the hash table is empty.
	 *
	 * @return true if the hash table is empty, false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return this.table.isEmpty();
	}

	/**
	 * Inserts a key-value pair into the hash table.
	 *
	 * @param key   The key to be inserted.
	 * @param value The value to be associated with the key.
	 * @return The previous value associated with the key, or null if the key is
	 *         new.
	 */
	@Override
	public V put(K key, V value) {
		int index = hash(key);
		int startingIndex = index;
		int i = 1;

		while (table.get(index) != null) {
			if (table.get(index).getKey().equals(key)) {
				V prevValue = table.get(index).getValue();
				table.get(index).setValue(value);
				return prevValue;
			}
			index = (startingIndex + i * i) % capacity;
			i++;
			if (index == startingIndex) {
				break;
			}
		}
		table.set(index, new MapEntry<>(key, value));
		this.items++;
		resize();
		return null;
	}

	/**
	 * Removes the entry associated with a specific key from the hash table.
	 *
	 * @param key The key whose associated entry is to be removed.
	 * @return The value associated with the removed key, or null if the key is not
	 *         found.
	 */
	@Override
	public V remove(K key) {
		int index = hash(key);
		int startingIndex = index;
		int i = 1;
		while (table.get(index) != null) {
			if (table.get(index).getKey().equals(key)) {
				V value = table.get(index).getValue();
				table.set(index, null);
				this.items--;
				return value;
			}
			index = (startingIndex + i * i) % capacity;
			i++;
			if (index == startingIndex)
				break;
		}
		return null;
	}

	/**
	 * Returns the number of entries in the hash table.
	 *
	 * @return The number of entries in the hash table.
	 */
	@Override
	public int size() {
		return this.items;
	}

	/**
	 * Computes the hash code for a given key.
	 *
	 * @param key The key for which to compute the hash code.
	 * @return The computed hash code.
	 */
	private int hash(K key) {
		int hashCode = key.hashCode();
		int hashIndex = Math.abs(hashCode) % capacity;
		return hashIndex;
	}

	/**
	 * Resizes the hash table when the load factor is exceeded.
	 */
	private void resize() {
		double loadFactor = (double) items / capacity;
		if (loadFactor >= 0.5) {
			int newCapacity = nextPrime(capacity * 2);
			ArrayList<MapEntry<K, V>> newTable = new ArrayList<>(newCapacity);
			for (int i = 0; i < newCapacity; i++) {
				newTable.add(null);
			}

			for (MapEntry<K, V> entry : table) {
				if (entry != null) {
					int oldIndex = rehash(entry.getKey(), newCapacity);
					int newIndex = oldIndex;
					int i = 1;
					while (newTable.get(newIndex) != null) {
						newIndex = (oldIndex + i * i) % newCapacity;
						i++;
					}
					newTable.set(newIndex, entry);
				}
			}

			table = newTable;
			capacity = newCapacity;
		}
	}

	/**
	 * Computes a new hash index for rehashing during resizing.
	 *
	 * @param key         The key for which to compute the new hash index.
	 * @param newCapacity The new capacity of the hash table after resizing.
	 * @return The computed hash index for rehashing.
	 */
	private int rehash(K key, int newCapacity) {
		int hashCode = key.hashCode();
		int hashIndex = Math.abs(hashCode) % newCapacity;
		return hashIndex;
	}

	/**
	 * Checks if a given number is prime.
	 *
	 * @param num The number to check for primality.
	 * @return true if the number is prime, false otherwise.
	 */
	private boolean isPrime(int num) {
		if (num <= 1)
			return false;
		for (int i = 2; i * i < num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Finds the next prime number greater than or equal to a given number.
	 *
	 * @param num The starting point for finding the next prime.
	 * @return The next prime number greater than or equal to the given number.
	 */
	private int nextPrime(int num) {
		while (!isPrime(num)) {
			num++;
		}
		return num;
	}

}
