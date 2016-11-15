
public class DynamicArray {
	
	private int[] data; 
	
	public DynamicArray() { 
		data = new int[1]; 
	}
	
	public int get(int index) { 
		
		if(index > data.length) {
			return 0; 
		} else { 
			return data[index]; 
		} 
	}
	
	public void put(int index, int value) { 
		if(index >= data.length) {
			int newSize = data.length * 2; 
			if(index >= newSize) { 
				newSize = index * 2; 
			}
			int newData[] = new int[newSize];
			System.arraycopy(data, 0, newData, 0, data.length);
			data = newData; 
			System.out.println("Size of array increased to " + newSize); 
		}
		data[index] = value; 
	}
	
}
