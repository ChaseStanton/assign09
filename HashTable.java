package assign09;

import java.util.ArrayList;
import java.util.List;

public class HashTable<K, V> implements Map<K, V> {
	private ArrayList<MapEntry<K, V>> table;
	private int items;
	private int capacity;

	//MAKE SURE LOAD FACTOR IS LESS THAN .5

	public HashTable() {
		this.items = 0;
		this.capacity = 11;
		table = new ArrayList<MapEntry<K, V>>();        
		for (int i = 0; i < capacity; i++) {
            table.add(null);
        }	
        }

	@Override
	public void clear() {
		this.table = new ArrayList<MapEntry<K, V>>(capacity);
		
	}

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
		int index = hash(key);
		while (index >= table.size())
			table.add(null);
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
	            for (int i = 0; i < newCapacity; i++) {
	                newTable.add(null);
	            }

	            for (MapEntry<K, V> entry : table) {
	                if (entry != null) {
	                    int newIndex = rehash(entry.getKey(), newCapacity);
	                    int i = 1;
	                    while (newTable.get(newIndex) != null) {
	                        newIndex = (newIndex + i * i) % newCapacity;
	                        i++;
	                    }
	                    newTable.set(newIndex, entry);
	                }
	            }

	            table = newTable;
	            capacity = newCapacity;
	        }
	    }

	    private int rehash(K key, int newCapacity) {
	        int hashCode = key.hashCode();
	        int hashIndex = Math.abs(hashCode) % newCapacity;
	        return hashIndex;
	    }
	private boolean isPrime(int num) {
		if(num <= 1)
			return false;
		for (int i = 2; i * i < num; i++) {
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
