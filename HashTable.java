package assign09;

import java.util.ArrayList;
import java.util.List;

public class HashTable<K, V> implements Map<K, V> {
	private ArrayList<MapEntry<K, V>> table;
	private int items;
	
	
	public HashTable(){
		this.table = new ArrayList<MapEntry<K, V>>(7);
	}
	
	@Override
	public void clear() {
		this.items = 0;
		this.table = new ArrayList<MapEntry<K, V>>(7);
	}

	@Override
	public boolean containsKey(K key) {
		int index = hash(key);
		int startingIndex = index;
		int i = 1;
		
		while(table.get(index) != null) {
			if(table.get(index).getKey().equals(key))
				return true;
			index = (startingIndex + i * i) % capacity;
			i++;
			if(startingIndex == index)
				break;
		}
		return false;
	}

	@Override
	public boolean containsValue(V value) {
		for(MapEntry<K, V> entry : this.table) {
			if(entry.getValue().equals(value))
				return true;
		}
		return false;
	}

	@Override
	public List<MapEntry<K, V>> entries() {
		List<MapEntry<K, V>> list = new ArrayList<>();
		for(MapEntry<K, V> entry : this.table) {
			if(entry != null)
				list.add(entry);
		}
		return list;
	}

	@Override
	public V get(K key) {
		int index = hash(key);
		int startingIndex = index;
		int i = 1;
		
		while(table.get(index) != null) {
			if(table.get(index).getKey().equals(key))
				return table.get(index).getValue();
			index = (startingIndex + i * i) % capacity;
			i++;
			if(startingIndex == index)
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
		this.items += 1;
		V replacedValue;
		int hashIndex = hash(key);
		if(this.table.get(hashIndex) != null){
			replacedValue = this.table.get(hashIndex).getValue();
			this.table.set(hashIndex, new MapEntry<K,V>(key, value));
			return replacedValue;
		}
		this.table.set(hashIndex, new MapEntry<K,V>(key, value));
			return null;
	}

	@Override
	public V remove(K key) {
		int index = hash(key);
		int startingIndex = index;
		int i = 1;
		while(table.get(index) == null) {
			if(table.get(index).getKey().equals(key)) {
				V value = table.get(index).getValue();
				table.set(index, null);
				this.items--;
				return value;
			}
			index = (startingIndex + i * i) % capacity;
			i++;
			if(index == startingIndex)
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
