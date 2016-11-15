
/**
 * Dynamic hash table with open addressing
 * @author thomasvarner
 *
 */
public class DynamicHT {

	private final int DEFAULT_TABLE_SIZE = 128;
	private float threshold = 0.75f;
	private int maxSize = 96;
	private int size = 0;
	
	
	public HashEntry[] table;
	
	public DynamicHT() { 
		table = new HashEntry[DEFAULT_TABLE_SIZE];
		for (int i = 0; i < DEFAULT_TABLE_SIZE; i++) { 
			table[i] = null;
		}
	}
	
	public void setThreshold(float threshold) { 
		this.threshold = threshold;
		maxSize = (int) (table.length * threshold);
	}
	
	public int get(int key) { 
		int hash = hashFunction(key);
		int initialHash = -1;
		while (hash != initialHash
				&& (table[hash] == DeletedHashEntry.getUniqueDeletedHashEntry()
				|| table[hash] != null
				&& table[hash].key != key)) { 
			if (initialHash == -1) { 
				initialHash = hash;
			}
			hash = (hash + 1) % table.length; // linear probing
		}
		if (table[hash] == null || hash == initialHash) { 
			return -1;
		} else { 
			return table[hash].value;
		}
	}
	
	public void put(int key, int value) { 
		
	}
	
	private void resize() { 
		
	}
	
	public void remove(int key) { 
		
	}
	
	public int hashFunction(int key) { 
		return key % table.length;
	}
	
	public static class HashEntry { 
		
		public int key;
		public int value;
		
		public HashEntry(int key, int value) { 
			this.key = key;
			this.value = value;
		}
	}
	
	public static class DeletedHashEntry extends HashEntry { 
		
		private static DeletedHashEntry entry = null;
		
		private DeletedHashEntry() { 
			super(-1,-1);
		}
		
		public static DeletedHashEntry getUniqueDeletedHashEntry() { 
			if (entry == null) { 
				entry = new DeletedHashEntry();
			}
			return entry;
		}
	}
}
