package assign09;

import java.util.ArrayList;
import java.util.List;

public class HashTable<K, V> implements Map<K, V> {
	private ArrayList<MapEntry<K, V>> table;
	private int items;
	private int capacity = 11;

	//MAKE SURE LOAD FACTOR IS LESS THAN .5

	public HashTable() {
		this.table = new ArrayList<MapEntry<K, V>>(capacity);
	}

	@Override
	public void clear() {
		this.items = 0;
		this.table = new ArrayList<MapEntry<K, V>>(capacity);
	}

	@Override
	public boolean containsKey(K key) {
		if (this.table.get(hash(key)) == null) {
			return false;
		}
		return this.table.get(hash(key)).getKey().equals(key);
	}

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

	@Override
	public List<MapEntry<K, V>> entries() {
		List<MapEntry<K, V>> list = new ArrayList<>();
		for (MapEntry<K, V> entry : this.table) {
			if (entry != null)
				list.add(entry);
		}
		return list;
	}

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

	@Override
	public boolean isEmpty() {
		return this.table.isEmpty();
	}

	@Override
	public V put(K key, V value) {
		resize();
		this.items++;
		int index = hash(key);
		while (index >= table.size())
			table.add(null);
		int startingIndex = index;
		int i = 1;

	    while (table.get(index) != null) {
	        if (table.get(index).getKey().equals(key)) {
	            V Value = table.get(index).getValue();
	            table.get(index).setValue(value); 
	            return Value;
	        }
	        index = (startingIndex + i * i) % capacity; 
	        i++; 
	        if (index == startingIndex) {
	            break;
	        }
		}

		table.set(index, new MapEntry<>(key, value));
		return null;
	}

	@Override
	public V remove(K key) {
		int index = hash(key);
		int startingIndex = index;
		int i = 1;
		while(index < table.size() && table.get(index) != null) {
			if(table.get(index).getKey().equals(key)) {
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

	@Override
	public int size() {
		return this.items;
	}

	private int hash(K key) {
		int hashCode = key.hashCode();
		int hashIndex = Math.abs(hashCode) % capacity;
		return hashIndex;
	}
	private void resize() {
		double loadFactor = (double) items / capacity;
		if (loadFactor >= 0.5) {
            int newCapacity = nextPrime(capacity * 2);
            ArrayList<MapEntry<K, V>> newTable = new ArrayList<>(newCapacity);

            // Rehash all existing entries into the new table with the new capacity
            for (MapEntry<K, V> entry : table) {
                if (entry != null) {
                    int newIndex = hash(entry.getKey());
                    while (newIndex >= newCapacity) {
                        newIndex -= newCapacity;
                    }
                    newTable.add(newIndex, entry);
                }
            }

            table = newTable;
            capacity = newCapacity;
        }
	}
	private boolean isPrime(int num) {
		if(num <= 1)
			return false;
		for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
	}
		return true;
	}
	private int nextPrime(int num) {
		while(!isPrime(num)) {
			num++;
		}
		return num;
	}
	

}
