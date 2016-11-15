
public class HT {
	
	private final static int TABLE_SIZE = 128;
	LinkedHashEntry[] table;
	
	public HT() { 
		table = new LinkedHashEntry[TABLE_SIZE];
		for (int i = 0; i < TABLE_SIZE; i++) { 
			table[i] = null;
		}
	}
	
	public int get(int key) { 
		int hash = hashFunction(key);
		if (table[hash] == null) { 
			return -1;
		} else { 
			LinkedHashEntry entry = table[hash];
			while (entry != null && entry.key != key) { 
				entry = entry.next;
			}
			if (entry == null) { 
				return -1;
			} else { 
				return entry.value;
			}
		}
	}
	
	public void put(int key, int value) { 
		int hash = hashFunction(key);
		if (table[hash] == null) { 
			table[hash] = new LinkedHashEntry(key, value);
		} else { 
			LinkedHashEntry entry = table[hash];
			while (entry.next != null && entry.key != key) { 
				entry = entry.next;
			}
			if (entry.key == key) { 
				entry.value = value;
			} else { 
				entry.next = new LinkedHashEntry(key, value);
			}
		}
	}
	
	public void remove(int key) { 
		int hash = hashFunction(key);
		if (table[hash] == null) { 
			return;
		} else { 
			LinkedHashEntry entry = table[hash];
			LinkedHashEntry prev = null;
			while (entry != null && entry.key != key) { 
				prev = entry;
				entry = entry.next;
			}
			if (entry.key == key) { 
				if (prev == null) { 
					table[hash] = entry.next;
				} else { 
					prev.next = entry.next;
				}
			}
		}
	}
	
	public int hashFunction(int key) { 
		return key % TABLE_SIZE;
	}
	
	public class LinkedHashEntry { 
		
		int key; 
		int value; 
		LinkedHashEntry next; 
		
		public LinkedHashEntry(int key, int value) { 
			this.key = key;
			this.value = value;
			next = null;
		}
		
	}
}
