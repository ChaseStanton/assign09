package assign09;

import java.util.ArrayList;
import java.util.List;

public class HashTable<K, V> implements Map<K, V> {
	private ArrayList<MapEntry<K, V>> table;
	private int capacity;
	private int items;
	
	
	public HashTable(){
		int capacity = 11;
		table = new ArrayList<MapEntry<K, V>>();
		for(int i = 0; i < capacity; i++)
		   table.add(null);
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for(int i = 0; i < capacity; i++) {
			table.set(i, null);
		}
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
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
	

}
