package assign09;

import java.util.ArrayList;
import java.util.List;

public class HashTable<K, V> implements Map<K, V> {
	private ArrayList<MapEntry<K, V>> table;
	private int items;
	private int capacity = 7;
	
	
	public HashTable(){
		this.table = new ArrayList<MapEntry<K, V>>(capacity);
	}

	@Override
	public void clear() {
		this.items = 0;
		this.table = new ArrayList<MapEntry<K, V>>(capacity);
	}

	@Override
	public boolean containsKey(K key) {
		if(this.table.get(hash(key)) == null){
			return false;
		}
		return this.table.get(hash(key)).getKey().equals(key);
	}

	@Override
	public boolean containsValue(V value) {
		for (MapEntry<K, V> entry : this.table) {
			if (entry.getValue().equals(value))
				return true;
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
		this.items++;
		int index = hash(key);
		while(index >= table.size())
			table.add(null);
	    int startingIndex = index;
	    int i = 1;

	    while (table.get(index) != null) {
	        if (table.get(index).getKey().equals(key)) {
	            V previousValue = table.get(index).getValue();
	            table.get(index).setValue(value); 
	            return previousValue;
	        }
	        index = (startingIndex + i * i) % capacity; 
	        i++; 
	        if (index == startingIndex) {
	        	capacity *= 2;
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
		while(index >= table.size() || table.get(index) != null) {
			if(table.get(index) != null && table.get(index).getKey().equals(key)) {
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

}
