import java.util.Comparator;
import java.util.PriorityQueue;


public class FastMedian {

	public PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
	public PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11, new myComparator());
	public int numOfElems = 0;
	
	
	public FastMedian() {}
	
	public void add(Integer num) { 
		maxHeap.add(num);
		if (numOfElems % 2 == 0) { 
			if (minHeap.isEmpty()) { 
				numOfElems++;
				return;
			} else if (maxHeap.peek() > minHeap.peek()) { 
				Integer maxHeapRoot = maxHeap.poll();
				Integer minHeapRoot = minHeap.poll();
				maxHeap.add(minHeapRoot);
				minHeap.add(maxHeapRoot);
			}
		} else { 
			minHeap.add(maxHeap.poll());
		}
		numOfElems++;
	}
	
	public Integer getMedian() { 
		if (numOfElems % 2 == 0) { 
			return (minHeap.peek() + maxHeap.peek()) / 2;
		} else { 
			return maxHeap.peek();
		}
	}
	
	public class myComparator implements Comparator<Integer> { 
		
		@Override
		public int compare(Integer o1, Integer o2) { 
			return o2 - o1;
		}
	}
	
	
	
	public static void main(String[] args) { 
		
		FastMedian fs = new FastMedian();
		fs.add(5);
		fs.add(4);
		fs.add(9);
		fs.add(2);
		fs.add(3);
		fs.add(6);
		fs.add(1);
		fs.add(8);
		fs.add(7);
		fs.add(10);
		fs.add(11);
		
		System.out.println(fs.getMedian());
	}
}
