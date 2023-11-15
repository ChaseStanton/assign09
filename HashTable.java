package assign09;

import java.util.ArrayList;
import java.util.List;

public class HashTable<K, V> implements Map<K, V> {
	private ArrayList<MapEntry<K, V>> table;
	private int capacity;
	private int items;
	
	
	public HashTable(){
		this.capacity = 7;
		this.table = new ArrayList<MapEntry<K, V>>(7);
	}
	
	@Override
	public void clear() {
		this.capacity = 7;
		this.table = new ArrayList<MapEntry<K, V>>(7);
	}

	@Override
	public boolean containsKey(K key) {
		for(MapEntry<K, V> entry : table) {
			if(entry != null && entry.getKey().equals(key))
				return true;
		}
		return false;
	}

	@Override
	public boolean containsValue(V value) {
		for(MapEntry<K, V> entry : table) {
			if(entry != null && entry.getValue().equals(value))
				return true;
		}
		return false;
	}

	@Override
	public List<MapEntry<K, V>> entries() {
		List<MapEntry<K, V>> list = null;
		for(MapEntry<K, V> entry : table) {
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private int hash(K key) {
		int hashCode = key.hashCode();
		int hashIndex = Math.abs(hashCode) % capacity;
		return hashIndex;
	}
	

}
