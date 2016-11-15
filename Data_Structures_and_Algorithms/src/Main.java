import java.awt.Point;
import java.util.*; 

public class Main {
	
	static char[] str = {'a','g','c','g','g','c','c','e','e'};
	// function takes a char array as input.
	// modifies it to remove duplicates and adds a 0 to mark the end
	// of the unique chars in the array.
	public static void removeDuplicates(char[] str) {
	  if (str == null) return; // if the array does not exist..nothing to do return.
	  int len = str.length; // get the array length.
	  if (len < 2) return; // if its less than 2..can't have duplicates..return.
	  int tail = 1; // number of unique char in the array.
	  // start at 2nd char and go till the end of the array.
	  for (int i = 1; i < len; ++i) { 
	    int j;
	    // for every char in outer loop check if that char is already seen.
	    // char in [0,tail) are all unique.
	    for (j = 0; j < tail; ++j) {
	      if (str[i] == str[j]) break; // break if we find duplicate.
	    }
	    // if j reachs tail..we did not break, which implies this char at pos i
	    // is not a duplicate. So we need to add it our "unique char list"
	    // we add it to the end, that is at pos tail.
	    if (j == tail) {
	      str[tail] = str[i]; // add
	      ++tail; // increment tail...[0,tail) is still "unique char list"
	    }
	  }
	  str[tail] = 0; // add a 0 at the end to mark the end of the unique char.
	}
	
	
	 static HashMap<String, Transaction> transactions = new HashMap<String, Transaction>();
	    static ArrayList<String> suspisiciousList = new ArrayList<String>();

	    static String[] getSuspiciousList(String[] transactions) {
	        for(String transaction: transactions) { 
	            if(transactions.con)
	        }
	    }

	    public class Transaction { 
	        String name; 
	        String location;
	        int dollarAmount;
	        int time;
	        
	        public Transaction(String name, String location, int dollarAmount, int time ) { 
	            this.name = name; 
	            this.location = location;
	            this.dollarAmount = dollarAmount;
	            this.time = time;
	        }
	    }
	
	public static int[] mergeSort(int[] input) {  
		
		/**
		 * Base case: array composed of only one element 
		 */
		if(input.length <= 1) { 
			return input; 
		}
		
		/**
		 * divide input array into left and right subarrays half 
		 * the size of input
		 */
		int[] left = new int[input.length / 2];
		int[] right = new int[input.length - left.length];
		System.arraycopy(input, 0, left, 0, left.length);
		System.arraycopy(input, left.length, right, 0, right.length);
		
		/**
		 * make recursive call until left and right subarrays
		 * are composed of single (or no) elements --> until
		 * base case is reached
		 */
		mergeSort(left);
		mergeSort(right);
		
		/**
		 * merge left and right subarrays in sorted order
		 */
		return merge(left, right, input);
	}
	
	public static int[] merge(int[] left, int[] right, int[] result) { 
		
		int index_left = 0, index_right = 0, index_result = 0; 
		
		/**
		 * iteratively compare the first element of the left
		 * and right subarrays, and interatively add the smaller 
		 * of the two elements to the orig. array. Do this while they
		 * both empty 
		 */
		while (index_left < left.length && index_right <right.length) { 
			if (left[index_left] < right[index_right]) { 
				result[index_result] = left[index_left];
				index_left++;
			} else { 
				result[index_result] = right[index_right];
				index_right++;
			}
			index_result++;
		}
		
		/**
		 * after the above while loop, either the left or right 
		 * subarray will be empty. Whichever one is not empty, 
		 * concatenate that array to the end of the orig. array 
		 */
		if (index_left < left.length) { 
			System.arraycopy(left, index_left, result, index_result, 
					left.length - index_left);
		}
		if (index_right < right.length) { 
			System.arraycopy(right, index_right, result, index_result, 
					right.length - index_right);
		}
		
		/**
		 * return modified array
		 */
		return result;
	}

	public static int[] concatenateElemArray(int a, int[] b) { 
		int[] c = new int[1 + b.length];
		c[0] = a; 
		for (int i = 0; i < b.length; i++) { 
			c[i + 1] = b[i];
		}
		return c;
	}
	
	/**
	 * Randomized to avoid O(n^2) running time in the event that 
	 * the input array is already sorted (or reversed sorted).
	 * @param input
	 * @param p
	 * @param q
	 * @return
	 */
	public static int[] quickSort(int[] input, int p, int q) { 
		if (p < q) { 
			int pivot = randomPartition(input, p, q); 
			quickSort(input, p, pivot - 1);
			quickSort(input, pivot + 1, q);
		}
		return input; 
	}
	
	
	/**
	 * By randomly choosing a pivot, we asymptotically guarantee 
	 * a quicksort run time of O(n lg n). Makes sure a fraction of the 
	 * inputs is on both sides (instead of just 1 element on one side and
	 * the remaining elements on the other).
	 * @param input
	 * @param first_index
	 * @param last_index
	 * @return
	 */
	public static int randomPartition(int[] input, int first_index, int last_index) { 
		
		// pick random index
		int random_index = first_index + 
				(int)(Math.random() * ((last_index - first_index) + 1));
		System.out.println(random_index);
		// swap first element with random element in list 
		exchange(input, first_index, random_index);
		System.out.println("(" + first_index + ", " + last_index + ")");
		
		// assign the pivot to be first element (which 
		// was randomized above)
		int pivot = input[first_index];
		int i = first_index;
		
		// place all elements larger than pivot on right side of array, 
		// and all those less than pivot on left side of array
		for (int j = i + 1; j <= last_index; j++) { 
			if (input[j] <= pivot) { 
				i++; 
				input = exchange(input, i, j);
			}
		}
		
		// swap first element (pivot) with the last element 
		// less than or equal to pivot. Ideally, this will place
		// pivot element in or near the middle of the list on average
		// with all the elems l.t pivot on left side, and all elems.
		// g.t. pivot on right side
		input = exchange(input, first_index, i);
		
		// return INDEX of pivot
		return i; 
	}
	
	public static int[] exchange(int[] input, int i, int j) { 
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
		return input; 
	}
	
	public static int[] selectionSort(int[] input, int k) { 
		
		if (k > input.length) { 
			k = input.length; 
		}
		
		for (int i = 0; i < k; i++) { 
			int min = input[i]; 
			for (int j = i + 1; j < input.length; j++) { 
				if (input[j] < min) { 
					min = input[j];
				}
				if (min != input[i]) { 
					exchange(input, i, j); 
				}
			}
		}
		
		int[] output = new int[k]; 
		System.arraycopy(input, 0, output, 0, k); 
		return output;
	}
	
	/**
	 * O(n^2)
	 * @param str
	 * @return
	 */
	public static String myRemoveDuplicates(String str) {
		
		char[] word = str.toCharArray(); 
		StringBuilder s = new StringBuilder(); 
		
		/*
		 * for k distance duplicate removal, iterate from j=i+1 to k (instead
		 * of word.length)
		 */
		for (int i = 0; i < word.length; i++) { 
			for (int j = i + 1; j < word.length; j++) { 
				if (word[i] == word[j]) { 
					word[j] = ' ';
				}
			}
		}
		
		for (int i = 0; i < word.length; i++) { 
			if (word[i] != ' ') { 
				s.append(word[i]);
			}
		}
		
		return s.toString(); 
	}
	
	public static int binarySearch(int[] arr, int k, int i, int j) { 
		
		/*
		 * base case: no more elements to consider
		 */
		if (i > j) { 
			return -1; 
		}
		/*
		 * find middle index
		 */
		int mid = (i + j) / 2; 
		/*
		 * if middle = k, return k
		 */
		System.out.println("mid " + arr[mid]);
		System.out.println("i " + arr[i]);
		System.out.println("j " + arr[j]);
		if (arr[mid] == k) { 
			return arr[mid]; 
		} else if (arr[mid] < k) { 
			/*
			 * recurse on right sub array
			 */
			System.out.println("go right");
			return binarySearch(arr, k, mid + 1, j);
		} else { 
			/*
			 * recurse on left sub array
			 */
			System.out.println("go left");
			return binarySearch(arr, k, i, mid - 1);
		}
	}
	
	public static int[] countingSort(int[] arr, int k) { 
		
		int buckets[] = new int[k];
		
		/*
		 * initialize elements in buckets (each bucket) to 0
		 */
		for (int i: buckets) { 
			i = 0;
		}
		
		/*
		 * (1) for each element encountered in arr, increment the 
		 * corresponding index (bucket) in buckets to count the 
		 * # of occurrences of each element in arr 
		 */
		for (int i = 0; i < arr.length; i++) { 
			buckets[arr[i]]++;
		}
		
		/*
		 * (2) count the number of elements in arr less than or equal 
		 * to a given element. Do this by computing a running sum over
		 * each bucket in buckets
		 */
		for (int i = 1; i < k; i++) { 
			buckets[i] += buckets[i - 1];
		}
		
		/*
		 * (3) initialize output array: sorted array
		 */
		int[] sorted_arr = new int[arr.length];
		
		/*
		 * (4) fill out sorted array: starting from the end of input arr, 
		 * buckets[arr[i]] will map to the correct sorted index in sorted arr.
		 * Place the element accordingly and decrement the count value in buckets.
		 * As stated above, the count value in buckets after counting the number
		 * of elems <= the elem in the current bucket will map elemts successively
		 * in the sorted arr until (of course it's) empty, at which time the bucket
		 * is never referenced again, bc all of the elems the bucket has accounted 
		 * for have already been scanned and placed
		 */
		
		for (int i = arr.length - 1; i >= 0; i--) { 
			sorted_arr[--buckets[arr[i]]] = arr[i];
		}		
		
		/*
		 * return sorted array 
		 */
		return sorted_arr;
	}
	
	public static int[] bucketSort(int[] arr, int maxValue) { 
		
		int[] bucketList = new int[maxValue + 1];
		int[] output = new int[arr.length];
		
		for (int i = 0; i < bucketList.length; i++) { 
			bucketList[i] = 0;
		}
		
		for (int i = 0; i < arr.length; i++) { 
			bucketList[arr[i]]++;
		}
		
		int outPos = 0;
		for (int i = 0; i < bucketList.length; i++) { 
			for (int j = 0; j < bucketList[i]; j++) { 
				output[outPos] = i;
				outPos++;
			}
		}
		
		return output; 
 	}
	
	public static int[] wiggleSort(int[] input) {
		
		// sort
		int[] output = quickSort(input, 0, input.length - 1);
		
		// swap
		for (int i = 0; i < output.length; i += 2) { 
			if (i + 1 < output.length) { 
				int temp = output[i]; 
				output[i] = output[i + 1]; 
				output[i + 1] = temp;
			}
		}
		
		return output;
	}
	
	public static boolean isPrime(int num) { 
		
		if (num < 2) { 
			return false;
		}
		
		int sqrt = (int) Math.sqrt(num);
	
		int i = 2;
		while (i <= sqrt) {
			if (num % i == 0) { 
				return false;
			}
			i++;
		}
		return true;
	}
	
	public static ArrayList<Integer> generateKPrimeNumbers(int max) { 
		
		// Create boolean array of flags to represent numbers 0 to n
		boolean[] flags = new boolean[max + 1];
		
		// Initialize flags to true (is prime) for all numbers
		// except for first 2 numbers (0 and 1)
		for (int i = 0; i < flags.length; i++) { 
			if (i == 0 | i == 1) { 
				flags[i] = false;
			} else { 
				flags[i] = true;
			}
		}
		
		// first prime number
		int prime = 2;
		
		while (prime <= Math.sqrt(max)) { 
			// Cross off remaining multiples of prime
			crossOff(flags, prime);
			
			// Find next value which is true
			prime = getNextPrime(flags, prime);
			
			if (prime >= flags.length) { 
				break;
			}
		}
		
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for (int i = 0; i < flags.length; i++) { 
			if (flags[i]) { 
				primes.add(i);
			}
		}
		return primes;
	}
	
	public static void crossOff(boolean[] flags, int prime) { 
		/*
		 * Cross off remaining multiples of prime. We can start with 
		 * (prime * prime), because if we have k * prime, where 
		 * k < prime, this value would have already been crossed off
		 * in a prior iteration
		 */
		for (int i = prime * prime; i < flags.length; i += prime) { 
			flags[i] = false;
		}
	}
	
	public static int getNextPrime(boolean[] flags, int prime) { 
		// starting at index after prev. prime, find next value 
		// in flags which is true
		int next = prime + 1;
		while (next < flags.length && !flags[next]) { 
			next++;
		}
		return next;
	}
	
	public static boolean linearTimeDuplicateCheck(int[] arr, int k) { 
		
		if (arr.length <= 1) { 
			return false;
		}
		
		Map<Integer, Integer> elements = new HashMap<Integer, Integer>();
		elements.put(arr[0], 0);
		for (int i = 1; i < arr.length; i++) { 
			if (elements.containsKey(arr[i])) { 
				if (i - elements.get(arr[i]) <= k) { 
					return true;
				}
			} else { 
				elements.put(arr[i], i);
			}
		}
		return false;
	}
	
	public static int quickSelect(int[] arr, int p, int q, int k) { 
		
		if (p == q) { 
			return arr[p];
		}
		
		int pivot_index = randomPartition(arr, p, q);
		
		if (pivot_index == k) { 
			return arr[k];
		} else if (k < pivot_index) { 
			return quickSelect(arr, p, pivot_index - 1, k);
		} else { 
			return quickSelect(arr, pivot_index + 1, q, k);
		}
	}
	
	public static int findMedian(int[] arr) { 
		return quickSelect(arr, 0, arr.length - 1, arr.length / 2);
	}
	
	public static int knapsackProb(int[] item_values, int[] item_weights, 
			int max_capacity, int item_index, int[][] table) { 
		
		if (max_capacity <= 0 || item_index < 0) { 
			return 0;
		}
		
		if (table[item_index][max_capacity] != Integer.MAX_VALUE) { 
			return table[item_index][max_capacity];
		} else if (item_weights[item_index] > max_capacity) { 
			return knapsackProb(item_values, item_weights, max_capacity, 
					item_index - 1, table);
		} else {  
			table[item_index][max_capacity] = Math.max(knapsackProb(item_values, item_weights, 
					max_capacity - item_weights[item_index], item_index - 1, table) + 
						item_values[item_index], knapsackProb(item_values, item_weights, 
							max_capacity, item_index - 1, table));
			return table[item_index][max_capacity];
		}
	}
	
	/**
	 * Bottom up: use solutions to smaller subproblems to solve larger problem
	 * 
	 * Let LIS[i] = length of LIS which includes element seq[i] as last element
	 * 
	 * Recurrence: 
	 * LIS[i] = 1 + max{LIS[j]}, for all i < j, and where A[i] < A[j]
	 * (larger problem) = 1 + max{(sub problem)}
	 * 
	 * O(n^2) run time
	 * 
	 * @param seq
	 * @return
	 */
	public static int LIS(int[] seq) { 
		
		/*
		 * L[seq.length] will hold results of subproblems. where L[j] is 
		 * LIS where last element is seq[j]
		 * 
		 * L[0] is set to 1, since it represents a seq composed of one element
		 */
		int[] L = new int[seq.length];
		L[0] = 1;
		
		/* Build table (or array in this case)
		 * start at second element in L
		 * 
		 * Fill in L iteratively beginning at seq[0]
		 * and checking whether seq[i] is greater than seq[j] while also increasing
		 * the max LIS found so far, if it meets the above requirement with the 
		 * addition of seq[i].
		 */
		for (int i = 1; i < L.length; i++) { 
			int maxn = 0;
			for (int j = 0; j < i; j++) { 
				if (seq[j] < seq[i] && L[j] > maxn) { 
					maxn = L[j];
				}
			}
			L[i] = maxn + 1;
		}
		
		/*
		 * returns the max of this table
		 */
		int maxi = 0;
		for (int i = 0; i < L.length; i++) { 
			if (L[i] > maxi) { 
				maxi = L[i];
			}
		}
		
		return maxi;
	}
	
	/**
	 * S(i) = max{S(i - 1) + seq[i], seq[i]}
	 * 
	 * 
	 * O(n)
	 * @param seq
	 * @return
	 */
	public static int maxContSubsequence(int[] seq) { 
		
		/*
		 * instantiate table, and first element of table
		 */
		int[] M = new int[seq.length];
		M[0] = seq[0];
		
		/*
		 * Build table bottom up
		 * 
		 * Recurrence: S(i) = max{S(i - 1) + seq[i], seq[i]}
		 */
		for (int i = 1; i < M.length; i++) { 
			if (seq[i] + M[i - 1] > 0) { 
				if (M[i - 1] < 0) { 
					M[i] = seq[i];
				} else { 
					M[i] = seq[i] + M[i - 1];
				}
			} else { 
				M[i] = M[i - 1];
			}
		}
		
		/*
		 *  return max of table 
		 */
		int max = M[0];
		for (int i = 1; i < M.length; i++) { 
			if (M[i] > max) { 
				max = M[i];
			}
		}
		
		return max;
	}
	
	
	
	static int[][] table; 
	/**
	 * assumes square matrix
	 * @param arr
	 * @param i
	 * @param j
	 * @return
	 */
	public static int LIS2D(int[][] arr, int i, int j) { 
			
		ArrayList<Integer> values = new ArrayList<Integer>();
		
		if (table[i][j] != Integer.MIN_VALUE) { 
			return table[i][j];
		} 
		
		if (i + 1 < arr.length) { 
			if (arr[i + 1][j] >= arr[i][j]) { 
				values.add(LIS2D(arr, i + 1, j));
			} else { 
				LIS2D(arr, i + 1, j);
			}
		}
		if (i - 1 > arr.length) { 
			if (arr[i - 1][j] >= arr[i][j]) { 
				values.add(LIS2D(arr, i - 1, j));
			} else { 
				LIS2D(arr, i - 1, j);
			}
		}
		if (j + 1 < arr.length) { 
			if (arr[i][j + 1] >= arr[i][j]) { 
				values.add(LIS2D(arr, i, j + 1)); 
			} else { 
				LIS2D(arr, i, j + 1);
			}
		}
		if (j - 1 < arr.length) { 
			if (arr[i][j - 1] >= arr[i][j]) { 
				values.add(LIS2D(arr, i, j - 1));
			} else { 
				LIS2D(arr, i, j - 1);
			}
		}
		
		if (!values.isEmpty()) { 
			int max = Integer.MIN_VALUE;
			for (Integer value: values) { 
				if (value > max) { 
					max = value;
				}
			}
			table[i][j] = 1 + max;
		} else { 
			table[i][j] = 1;
		}
		return table[i][j];
	}
	
	public static int buySellStock(int[] sp, int i, int j) { 
		
		if (sp.length == 0 || sp.length == 1) return 0;
		
		int mid = (i + j) / 2;
		int left_s = buySellStock(sp, i, mid);
		int right_s = buySellStock(sp, mid + 1, j);
		
		int left_min = sp[0];
		for (int k = 0; k < mid; k++) { 
			left_min = Math.min(left_min, sp[k]);
		}
		
		int right_max = sp[mid];
		for (int k = mid; k < j; k++) { 
			right_max = Math.max(right_max, sp[k]);
		}
		
		int mid_s = right_max - left_min;
		return Math.max(mid_s, Math.max(left_s, right_s));
	}
	
	public static int robotPaths(int r_x, int r_y, int d_x, 
			int d_y, int[][] grid) { 
		
		if (r_x == d_x - 1 && r_y == d_y - 1) { 
			return 1;
		}
		
		if (grid[r_y][r_x] != Integer.MAX_VALUE) { 
			return grid[r_y][r_x];
		} else if (r_x == d_x - 1) { 
			grid[r_y][r_x] = robotPaths(r_x, r_y + 1, d_x, d_y, grid);
		} else if (r_y == d_y - 1) { 
			grid[r_y][r_x] = robotPaths(r_x + 1, r_y, d_x, d_y, grid);
		} else { 
			grid[r_y][r_x] = robotPaths(r_x + 1, r_y, d_x, d_y, grid) + 
					robotPaths(r_x, r_y + 1, d_x, d_y, grid);
		}
		return grid[r_y][r_x];
	}
	
	
    static Integer minDifference = Integer.MAX_VALUE;

    static int getMinTimeDifference(String[] times) {
        minDifference = getTimeDifference(times[0], times[1]);
        for(int i = 0; i < times.length; i++) {
            int start = 1;
            for(int j = start; j < times.length; j++) { 
                int timeDifference = getTimeDifference(times[i], times[j]);
                if(timeDifference < minDifference) {minDifference = timeDifference;}
            }
            start++;
        }
        System.out.println(minDifference);
		return minDifference;
    }

    static String[] gsl(String[] transactions) { 
    	
    	
    }
    
    static int getTimeDifference(String time1, String time2) { 
        String hours1 = "";
        String hours2 = "";
        String minutes1 = "";
        String minutes2 = "";
        int h1; 
        int h2; 
        int m1;
        int m2;
        int _h1;
        int _h2;
        int _m1;
        int _m2;        
        int minDiff; 
        int hourDiff;
        
        for(int i = 0; i < time1.length(); i++) { 
            if(i < 2) { 
                hours1 = hours1 + time1.charAt(i);
                hours2 = hours2 + time2.charAt(i);
            } else if (i > 2) { 
                minutes1 = minutes1 + time1.charAt(i);
                minutes2 = minutes2 + time2.charAt(i);
            }
        }
        _h1 = Integer.parseInt(hours1); 
        _h2 = Integer.parseInt(hours2);
        _m1 = Integer.parseInt(minutes1);
        _m2 = Integer.parseInt(minutes2);
   
        if(_h1 > _h2) {
            h1 = _h2;
            h2 = _h1;
        } else { 
            h1 = _h1;
            h2 = _h2;
        }
        if(m2 > m1 + 12) { 
            minDiff = m1 + 60 - m2 + 12;
        } else { 
            minDiff = m2 - m1;
        }
        if(h2 > h1 + 12) { 
            hourDiff = h1 + 24 - h2 - 1;
        } else { 
            hourDiff = h2 - h1;
        }
        return (hourDiff * 60) + (minDiff);
    }

	
	
	
	
	public static int numPaths(int[][] a, int M, int N, int i, int j) { 
		
		if(i == M - 1 && j == N - 1) { 
			return 1;
		}
		
		if(i == M - 1) { 
			
		}
	}
	
	public static int LCS(char[] x, char[] y) { 

		int[][] table = new int[x.length + 1][y.length + 1]; 
		for(int i = 0; i <= x.length; i++) { 
			table[i][0] = 0; 
		}
		for(int j = 0; j <= y.length; j++) { 
			table[0][j] = 0; 
		}
		for(int i = 1; i <= x.length; i++) { 
			for(int j = 1; j <= y.length; j++) { 
				if(x[i-1] == y[j-1]) { 
					table[i][j] = table[i - 1][j - 1] + 1;
				} else { 
					table[i][j] = Math.max(table[i][j - 1], table[i - 1][j]); 
				}
			}
		}
		
		for(int i = 0; i <= x.length; i++) { 
			for(int j = 0; j <= y.length; j++) {
				System.out.print(table[i][j]);
			}
			System.out.println();
		}
	
		//backtrackLCS(table, x, y, x.length, y.length); 
		String lcs = backtrackLCS(table, x, y, x.length, y.length); 
		System.out.println(lcs);
		return table[x.length][y.length];
	}
	
	public static String backtrackLCS(int[][] table, char[] x, char[] y, int i, int j) { 
		
		if(i == 0 || j == 0) { // Base case: one or both strings empty
			return "";
		} else if(x[i-1] == y[j-1]) { 
			return new String(backtrackLCS(table, x, y, i - 1, j - 1) + x[i]); 
		} else { 
			if(table[i][j - 1] > table[i - 1][j]) { 
				return backtrackLCS(table, x, y, i, j - 1);
			} else { 
				return backtrackLCS(table, x, y, i - 1, j);
			}
		}
	}
	
	public static int change(int cents, int index, int[] changeArray) { 
		
		if(cents == 0) { 
			return 1; 
		}
		if(cents < 0) { 
			return 0; 
		}
		if(index < 0) { 
			return 0; 
		}
		
		return change(cents, index - 1, changeArray) + 
				change(cents - changeArray[index], index, changeArray); 
	}
	
	/**
	 * 
	 * C(P) = min{C(P - vi)} + 1; where vi <= P
	 * O(nk)
	 * @param coin_values
	 * @param sum
	 * @return
	 */
	public static int df(int[] coin_values, int sum) { 
		
		/*
		 * instantiate table
		 * table --> holds min change result for values 0 to sum
		 * 
		 */
		int[] mins = new int[sum + 1];
		mins[0] = 0;
		
		for (int i = 1; i < mins.length; i++) { 
			ArrayList<Integer> values = new ArrayList<Integer>();
			for (int j = 0; j < coin_values.length; j++) { 
				if (i >= coin_values[j]) { 
					values.add(mins[i - coin_values[j]]);
				}
			}
			int min = Integer.MAX_VALUE;
			for (Integer v: values) { 
				if (v < min) { 
					min = v;
				}
			}
			mins[i] = min + 1;
		}
		
		return mins[sum];
	}
	
	static Integer[][] coin_table;
	
	public static int minChange2(int[] coin_values, int sum, int i) { 
		
		if (sum == 0) return 0;
		if (sum < 0 || i == 0) return 500;
		
		if (coin_table[sum][i] != null) return coin_table[sum][i];
		
		coin_table[sum][i] = Math.min(minChange2(coin_values, sum - coin_values[i - 1], i) + 1, 
				minChange2(coin_values, sum, i - 1));
		
		return coin_table[sum][i];
	}
	
	/*
	 * recurse and memoize
	 * 
	 * f(sum, n) = f(sum - vn, n) + f(sum, n-1)
	 */
	public static int changeWays(int sum, int change_index, 
			int[] change_arr, int[][] sol_arr) { 
		
		/*
		 * base cases
		 */
		if (sum == 0) { 
			return 1;
		}
		if (sum < 0 || change_index < 0) { 
			return 0;
		}
		
		if (sol_arr[change_index][sum] != Integer.MAX_VALUE) { 
			return sol_arr[change_index][sum];
		} else { 
			sol_arr[change_index][sum] = changeWays(sum - change_arr[change_index], 
					change_index, change_arr, sol_arr) + 
					changeWays(sum, change_index - 1, change_arr, sol_arr);
			return sol_arr[change_index][sum];
		}
	}
	
	static Integer[][] change_table;
	
	public static int changeWays2(int[] coin_values, int sum, int i) { 
		
		if (sum == 0) return 1;
		if (i == 0 || sum < 0) return 0;
		
		if (change_table[sum][i] != null) return change_table[sum][i];
		
		change_table[sum][i] = changeWays2(coin_values, sum - coin_values[i - 1], i) + 
				changeWays2(coin_values, sum, i - 1);
		
		return change_table[sum][i];
	}
	
	public static Set<String> generateParens(int remaining) { 
		Set<String> set = new HashSet<String>();
		if(remaining == 0) { 
			set.add("");
		} else { 
			Set<String> prev = generateParens(remaining - 1);
			for(String str: prev) { 
				for(int i =0; i <str.length(); i++) { 
					if(str.charAt(i) == '(') { 
						String s = insertInside(str, i);
						set.add(s);
					}
				}
				if(!set.contains("()" + str)) {
					set.add("()" + str);
				}
			}
		}
		return set; 
	}
	
	public static String insertInside(String str, int leftIndex) { 
		String left = str.substring(0,  leftIndex + 1); 
		String right = str.substring(leftIndex + 1,  str.length());
		return left + "()" + right; 
	}
	
	public static ArrayList<String> getPermutations(String original) { 
		
		if(original == null) { 
			return null; 
		}
		
		ArrayList<String> permutations = new ArrayList<String>(); 
		if (original.length() == 0) { //Base Case
			permutations.add(""); 
			return permutations; 
		}
		
		char first = original.charAt(0); // get the first character
		String remainder = original.substring(1); // remove the 1st character
		ArrayList<String> words = getPermutations(remainder);
		
		for(String word: words) { 
			for(int j = 0; j <= word.length(); j++) { 
				String s = insertCharAt(word, first, j); 
				permutations.add(s); 
			}
			
		}
		return permutations;
	}
	
	public static void printChars(int i) { 
		
		if (i == 256) { 
			return;
		} else { 
			char c = (char) i;
			System.out.println(i + " = " + c);
			printChars(++i);
		}
	}
	
	
	public static String insertCharAt(String string, char c, int i) { 
		String start = string.substring(0, i); 
		String end = string.substring(i); 
		return start + c + end; 
	}
	
	
	public static ArrayList<String> getPermutations2(String str) { 
		
		if (str.length() == 1) { 
			ArrayList<String> lastElem = new ArrayList<String>();
			lastElem.add(str);
			return lastElem;
		}
		
		String first = str.substring(0, 1);
		String remainder = str.substring(1);
		ArrayList<String> perms1 = getPermutations2(remainder);
		ArrayList<String> perms2 = new ArrayList<String>();
		for (String s: perms1) { 
			for (int i = 0; i <= s.length(); i++) { 
				String perm = insertElem(s, first, i);
				perms2.add(perm);
			}
		}
		return perms2;
	}
	
	public static String insertElem(String str, String elem, int i) { 
		if ( i == str.length()) { 
			return str + elem;
		} else { 
			String first = str.substring(0, i);
			String last = str.substring(i);
			return first + elem + last;
		}
	}
	
	
	
	public static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set) { 
		 
		ArrayList<ArrayList<Integer>> allSubsets = new ArrayList<ArrayList<Integer>>(); 
		int first_item = set.get(0); 
		ArrayList<Integer> emptySet = new ArrayList<Integer>(); 
		ArrayList<Integer> firstSet = new ArrayList<Integer>();
		firstSet.add(first_item); 
		allSubsets.add(emptySet); 
		allSubsets.add(firstSet); 
		
		for(int i = 1; i < set.size(); i++) { 
			ArrayList<ArrayList<Integer>> moreSubsets = new ArrayList<ArrayList<Integer>>(); 

			for(ArrayList<Integer> subset: allSubsets) { 
				moreSubsets.add(new ArrayList<Integer>(subset)); 
			}
			
			for(ArrayList<Integer> subset: moreSubsets) { 
				subset.add(set.get(i)); 
			}
			
			allSubsets.addAll(moreSubsets); 
		}
		
		return allSubsets; 
	}
	
	public static ArrayList<ArrayList<Integer>> getSubsets2(ArrayList<Integer> set) { 
		
		ArrayList<ArrayList<Integer>> allSubsets = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> emptySet = new ArrayList<Integer>();
		allSubsets.add(emptySet);
		
		for (Integer elem: set) { 
			ArrayList<ArrayList<Integer>> moreSubsets = new ArrayList<ArrayList<Integer>>();
			
			for (ArrayList<Integer> ss: allSubsets) { 
				if (ss.isEmpty()) { 
					moreSubsets.add(new ArrayList<Integer>());
				} else { 
					ArrayList<Integer> copySS = new ArrayList<Integer>();
					for (Integer i: ss) { 
						copySS.add(i);
					}
					moreSubsets.add(copySS);
				}
			}
			for (ArrayList<Integer> ss: moreSubsets) { 
				ss.add(elem);
				allSubsets.add(ss);
			}
		}
		return allSubsets;
	}
	
	
	public static int[][] makeZeros(int[][] input, int size) { 
		int length = input.length; 
		int height = size / length; 
		HashSet<Integer> skip = new HashSet<Integer>(); 
		
		for(int i = 0; i < length; i++) { 
			for(int j = 0; j < height; j++) {
				if(skip.contains(j)) { 
					continue; 
				}
				if(input[i][j] == 0) { 
					for(int x = 0; x < length; x++) { 
						input[x][j] = 0; 
					}
					for(int y = 0; y < height; y++) { 
						input[i][y] = 0; 
					}
					skip.add(j); 
					break;
				}
			}
		}
		return input; 
	}
	
	public static int[][] matrixRotate(int[][] input) { 
		int last = input.length - 1; 
		int first = 0; 
		
		for(int i = 0; i < input.length / 2; i++) { 
			for(int j = first; j < last; j++) { 
				int temp1 = input[first][first + j]; 
				int temp2 = input[first + j][last]; 
				int temp3 = input[last][last - j]; 
				int temp4 = input[last - j][first]; 
			
				input[first][first + j] = temp4; 
				input[first + j][last] = temp1; 
				input[last][last - j] = temp2; 
				input[last - j][first] = temp3;
			}
			first++; 
			last--; 
		}
		return input; 
	}
	
	public static String spaceReplace(String s) { 
		char[] input = s.toCharArray(); 
		StringBuilder output = new StringBuilder(); 
		
		for(char c: input) { 
			if(c == ' ') { 
				output.append("%20"); 
			} else { 
				output.append(c); 
			}	
		}
		return output.toString(); 
	}
	
	public static boolean checkAnagram(String s1, String s2) { 
		
		char[] s1_array = s1.toCharArray(); 
		char[] s2_array = s2.toCharArray();
		
		HashMap<Character, Integer> letter_count1 = 
				new HashMap<Character, Integer>();
		HashMap<Character, Integer> letter_count2 = 
				new HashMap<Character, Integer>();
		
		for (char c: s1_array) { 
			if (!letter_count1.containsKey(c)) { 
				letter_count1.put(c, 0);
			} else { 
				letter_count1.put(c, letter_count1.get(c) + 1);
			}
		}
		
		for (char c: s2_array) { 
			if (!letter_count2.containsKey(c)) { 
				letter_count2.put(c, 0);
			} else { 
				letter_count2.put(c, letter_count2.get(c) + 1);
			}
		}
		
		for (char c: s2_array) { 
			if (letter_count1.get(c) != letter_count2.get(c)) { 
				return false; 
			}
		}
		
		return true; 
		
		/*
		if(s1.length() != s2.length()) { 
			return false; 
		}
		char[] s1_array = s1.toCharArray(); 
		char[] s2_array = s2.toCharArray(); 
		int count = 0; 
		
		for(int i = 0; i < s1_array.length; i++) { 
			for(int j = 0; j < s2_array.length; j++) { 
				if(s1_array[i] == s2_array[j]) { 
					count++;
					s2_array[j] = '1'; 
					break; 
				}
			}
		}
		if(count == s1.length()) { 
			return true; 
		} else { 
			return false; 
		}
		*/
	}
	
	public static boolean checkAnagram2(String str1, String str2) { 
		
		char[] arr1 = str1.toCharArray();
		char[] arr2 = str2.toCharArray();
		
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		
		if (Arrays.equals(arr1, arr2)) { 
			return true;
		} else { 
			return false;
		}
	}
	
	
	public static String reverseWords2(String s) { 
		
		String text = reverseString(s, 0, s.length() - 1);
		int left_index = 0;
		
		for (int i = 0; i <= text.length(); i++) { 
			if (i == text.length() || text.charAt(i) == ' ') { 
				text = reverseString(text, left_index, i - 1);
				left_index = i + 1;
				i++;
			}
		}	
		
		return text;
	}
	
	public static String reverseString(String s, int first_index, int last_index) { 
		char[] s_array = s.toCharArray(); 
		int first = first_index; 
		int last = last_index;
		
		for (int i = first_index; i <= (first_index + last_index) / 2; i++) { 
			char temp = s_array[last];
			s_array[last] = s_array[first];
			s_array[first] = temp;
			
			last--;
			first++;
		}
		
		return new String(s_array); 
	}
	
	
	
	
	
	public static String reverseString2(String str, int i, int j) { 	
		char[] arr = str.toCharArray();
		while (i < j) { 
			char temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}
		return new String(arr);
	}
	
	public static int atoi(String number) { 
		
		boolean neg = false;
		int total = 0;
		
		HashMap<Character, Integer> num_index = new HashMap<Character, Integer>();
		num_index.put('0', 0); 
		num_index.put('1', 1);
		num_index.put('2', 2);
		num_index.put('3', 3);
		num_index.put('4', 4);
		num_index.put('5', 5);
		num_index.put('6', 6);
		num_index.put('7', 7);
		num_index.put('8', 8);
		num_index.put('9', 9);
		
		for (int i = 0; i < number.length(); i++) { 
			if (i == 0) { 
				if (number.charAt(i) == '-') { 
					neg = true;
				} else { 
					total = (10 * total) + num_index.get(number.charAt(i));
				}
			} else { 
				total = (10 * total) + num_index.get(number.charAt(i));
			}
		}
		
		if(neg) { 
			total = total * -1; 
		}
		
		return total;
	}
	
	public static int factorialZeros(int number) { 
		
		if(number == 5) return 1; 
		if(number < 0) return -1; 
		int count = 0; 
		
		
		for (int j = 5; number / j >= 1; j*= 5) { 
			count += number / j;
		}
		
		return count;
	}
	
	public static int[] arrayShuffle(int[] arr) { 
		
		Random r = new Random();
		
		for (int i = 0; i < arr.length; i++) { 
			int rand = r.nextInt(arr.length - i) + i;
			//int rand = (int)(Math.random()*(((arr.length - 1) - i) + 1)); 
			int temp = arr[i];
			arr[i] = arr[rand];
			arr[rand] = temp;
		}
		
		return arr;
	}
	
	
	public static int buySellStockDC2(int[] arr) { 
		
		int[] deltas = new int[arr.length];
		deltas[0] = 0;
		
		for (int i = 1; i < arr.length; i++) { 
			deltas[i] = arr[i] - arr[i - 1];
		}
		
		return maxSubArray(deltas);
	}
	
	public static int maxSubArray(int[] arr) { 
		
		int[] M = new int[arr.length];
		M[0] = Math.max(arr[0], 0);
		
		for (int i = 1; i < M.length; i++) { 	
			M[i] = Math.max(M[i - 1] + arr[i], 0);
		}
		
		int max = M[0];
		for (int i = 1; i < M.length; i++) { 
			if (M[i] > max) { 
				max = M[i];
			}
		}
		
		return max;
	}
	
	
	public static int buySellStockDC(int[] arr, int i, int j) { 
		
		if (i > j) { 
			return 0;
		}
		
		if (i == j) { 
			return Math.max(arr[0], 0); 
		}
		
		int mid = (i + j) / 2;
		int left_stocks = buySellStockDC(arr, i, mid);
		int right_stocks = buySellStockDC(arr, mid + 1, j);
		
		int left_min = arr[0];
		for (int k = 0; k < mid; k++) { 
			if (arr[k] < left_min) { 
				left_min = arr[k];
			}
		}
		
		int right_max = arr[mid];
		for (int k = mid; k <= j; k++) { 
			if (arr[k] > right_max) { 
				right_max = arr[k];
			}
		}
		
		if(right_max < left_min) { 
		
		int mid_stocks = right_max - left_min;
		
		
		return Math.max(left_stocks, Math.max(mid_stocks, right_stocks));
	}
	
	public static boolean hasSubstring(String s, String ss) { 
		
		for (int i = 0; i <= s.length() - ss.length(); i++) { 
			if (i == (s.length() - ss.length())) { 
				if (s.substring(i).equals(ss)) { 
					return true;
				}
			} else { 
				if (s.substring(i, ss.length() + i).equals(ss)) { 
					return true;
				}
			}
		}
		return false;
	}
	
	public class myComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer x, Integer y) {
			// TODO Auto-generated method stub
			return y - x;
		} 
		
	}
	
	public static char[] findNextHighNum(Integer num) { 
		
			char[] number = num.toString().toCharArray();
			
			for (int i = number.length - 2; i >= 0; i--) { 
				if (number[i] > number[i + 1]) { 
					continue;
				} else { 
					int j = number.length - 1;
					while (number[j] < number[i]) { 
						j--;
					}
					char temp = number[i];
					number[i] = number[j];
					number[j] = temp;
					Arrays.sort(number, i + 1, number.length);
					return number;
				}
			}
			return number;
	}
	
	public void printTree(TreeNode tmpRoot) {

        Queue<TreeNode> currentLevel = new LinkedList<TreeNode>();
        Queue<TreeNode> nextLevel = new LinkedList<TreeNode>();

        currentLevel.add(tmpRoot);

        while (!currentLevel.isEmpty()) {
            Iterator<TreeNode> iter = currentLevel.iterator();
            while (iter.hasNext()) {
                TreeNode currentNode = iter.next();
                if (currentNode.left != null) {
                    nextLevel.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    nextLevel.add(currentNode.right);
                }
                System.out.print(currentNode.value + " ");
            }
            System.out.println();
            currentLevel = nextLevel;
            nextLevel = new LinkedList<TreeNode>();

        }

    }
	
	
	
	public static void findNextPali(int[] num) { 
		
	int[] left = getLeftNum(num);
	int[] right = getRightNum(num);
	
	
	}
	
    private static final Map<Character, Character> brackets = new HashMap<Character, Character>();
    static {
        brackets.put('[', ']');
        brackets.put('{', '}');
        brackets.put('(', ')');
    }  
	
    public static boolean isBalanced(String str) {
        if (str.length() == 0) {
            throw new IllegalArgumentException("String length should be greater than 0");
        }
        // odd number would always result in false
        if ((str.length() % 2) != 0) {
            return false;
        }

        final Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < str.length(); i++) {
            if (brackets.containsKey(str.charAt(i))) {
                stack.push(str.charAt(i));
            } else if (stack.empty() || (str.charAt(i) != brackets.get(stack.pop()))) {
                return false;
            } 
        }
        return true;
    } 
	
	public static int[] getLeftNum(int[] num) { 

		int last= 0;
		if (num.length % 2 != 0) { 
			last = (num.length / 2) - 1;
		} else { 
			last = num.length / 2;
		}
		int[] left = new int[last + 1];
		for (int i = 0; i <= last; i++) { 
			left[i] = num[i];
		}
		return left;
	}
	
	public static int[] getRightNum(int[] num) { 
		
		int first = 0;
		if (num.length % 2 != 0) { 
			first = (num.length / 2) + 1;
		} else { 
			first = (num.length / 2);
		}
		int[] right = new int[first + 1];
		for (int i = first; i <= right.length; i++ ) { 
			right[i] = num[i];
		}
		return right
	}
	
	public static boolean getBit(int num, int i) { 
		return (num & (1 << i)) != 0;
	}
	
	public static int setBit(int num, int i) { 
		return num | (1 << i);
	}
	
	public static int clearBit(int num, int i) { 
		int mask = ~(1 << i);
		return num & mask;
	}
	
	public static int clearBitMSthroughI(int num, int i) { 
		int mask = (1 << i) - 1;
		return num & mask;
	}
	
	public static int clearBitsIthrough0(int num, int i) { 
		int mask = ~((1 << (i + 1)) - 1);
		return num ^ mask;
	}
	
	public static int reverseBits(int num) { 
		int mask = ~0;
		return num ^ mask;
	}
	
	/**
	 * Given: 
	 * (1) driver1 must start at point A and end at point B
	 * (2) driver2 must start at point C and end at point D
	 * 
	 * Thus, the detour path for driver1 is ACDB, and the detour 
	 * path for driver2 is CABD. 
	 * 
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @return
	 */
	public static double getBestDetour(Point a, Point b, Point c, Point d) { 
		
		double distanceAtoB = getDistance(a, b);
		double distanceCtoD = getDistance(c, d);
		double distanceAtoC = getDistance(a, c);
		double distanceBtoD = getDistance(b, d);
		
		double detourAB = distanceAtoC + distanceCtoD
				+ distanceBtoD;
		double detourCD = distanceAtoC + distanceAtoB
				+ distanceBtoD;
		
		return Math.min(detourAB, detourCD);
	}
	
	public static double getDistance(Point a, Point b) { 
		/*
		 * Calculate distance d = ((x2-x1)^2 + (y2-y1)^2))^0.5
		 */
		double distance = Math.sqrt((b.getX() - a.getX()) * 
				(b.getX() - a.getX()) + (b.getY() - a.getY()) * 
					(b.getY() - a.getY()));
		return distance;
	}
	
	public static int findLongestPali() { 
		
	}
	
	public static void strtok(String str) { 
		
	}
	
	public static String findSmallestPali(String str, LinkedList<Character> list, int i) { 
		
		if (list.size() == str.length()) { 
			StringBuilder sb = new StringBuilder();
			while(!list.isEmpty()) { 
				sb.append(list.removeFirst());
			}
			String first = new String(sb);
			return first + str;
		}
		
		if (list.size() == 0) { 
			if (checkPalindrome(str)) { 
				return str;
			}
		}
		
		list.add(str.charAt(str.length() - i));
		StringBuilder sb = new StringBuilder();
		Iterator<Character> iter = list.iterator();
		while (iter.hasNext()) { 
			char c = iter.next();
			sb.append(c);
		}
		String first = new String(sb);
		if (checkPalindrome(first + str)) { 
			return first + str;
		} else { 
			i++;
			return findSmallestPali(str, list, i);
		}
	}
	
	public static boolean checkPalindrome(String str) { 
		
		LinkedList<Character> stack = new LinkedList<Character>();
		String first = str.substring(0, str.length() / 2);
		String second;
		if (str.length() % 2 == 0) { 
			second = str.substring(str.length() / 2);
		} else { 
			second = str.substring((str.length() / 2) + 1);
		}
		char[] arr1 = first.toCharArray();
		for (char c: arr1) { 
			stack.addFirst(c);
		}
		StringBuilder sb = new StringBuilder();
		Iterator<Character> iter = stack.iterator();
		while (iter.hasNext()) { 
			char c = iter.next();
			sb.append(c);
		}
		String first2 = new String(sb);
		if (first2.equals(second)) { 
			return true;
		} else { 
			return false;
		}
	}
	
	
	public static void giveBookRange(int d, int k) { 
		
		int count = 0;
		int currNumber = d;
		
		while (count <= k) { 
			count = count + countDigitInNumber(d, currNumber);
			if (count <= k) {  
				currNumber++;
			}
		}
		
		System.out.println("low: " + d);
		System.out.println("high " + (currNumber - 1));
	}
	
	
	public static int countDigitInNumber(Integer digit, Integer num) { 
		
		int count = 0;
		char[] number = num.toString().toCharArray();
		char[] dig = digit.toString().toCharArray();
		
		for (char c: number) { 
			if (dig[0] == c) { 
				count++;
			}
		}
		return count;
	}
	
	public static int[] inPlaceSwap(int[] arr, int i, int j) { 
		
		arr[j] = arr[i] - arr[j];
		arr[i] = arr[i] - arr[j];
		arr[j] = arr[i] + arr[j];
		return arr;
	}
	
	public static void MM(char[] correct, char[] guess) { 
		
		LinkedList<Character> list1 = new LinkedList<Character>();
		LinkedList<Character> list2 = new LinkedList<Character>();
		int hits = 0;
		int ph = 0;
		
		for (int i = 0; i < correct.length; i++) { 
			if (correct[i] == guess[i]) { 
				hits++;
			} else { 
				list1.add(correct[i]);
				list2.add(guess[i]);
			}
		}
		
		Iterator<Character> iter = list1.iterator();
		while (iter.hasNext()) { 
			Character c = iter.next();
			if (list2.contains(c)) { 
				ph++;
				list2.remove(c);
			}
		}
		
		System.out.println("hits: " + hits);
		System.out.println("pseudo-hits: " + ph);
	}
	
	public static int maxConSeq(int[] arr) { 
		
		int[] sums = new int[arr.length];
		sums[0] = Math.max(arr[0], 0);
		
		for (int i = 1; i < arr.length; i++) { 
			sums[i] = Math.max(sums[i - 1] + arr[i], 0);
		}
		
		int max = sums[0];
		for (int i = 1; i < sums.length; i++) { 
			if (sums[i] > max) { 
				max = sums[i];
			}
		}
		
		return max;
	}
	
	public static String segmentString(String input, Set<String> dict) { 
		
		if (dict.contains(input)) return input;
		
		int length = input.length();
		
		for (int i = 1; i < length; i++) { 
			
			String prefix = input.substring(0, i);
			if (dict.contains(prefix)) { 
				String suffix = input.substring(i);
				String segSuffix = segmentString(suffix, dict);
				if (segSuffix != null) { 
					return prefix + " " + segSuffix;
				}
			}
		}
		
		return null;
	}
	
	public static ArrayList<ArrayList<Integer>> threeSUM(int[] arr, int num) { 
		
		ArrayList<ArrayList<Integer>> triplets = new ArrayList<ArrayList<Integer>>();
		Arrays.sort(arr);	
		for (int i = 0; i < arr.length - 2; i++) { 
			
			int j = i + 1;
			int k = arr.length - 1;
			int c = num - arr[i];
			
			while (j < k) { 
				
				int twoSum = arr[j] + arr[k];
				if (twoSum > c) { 
					k--;
				} else if (twoSum < c) { 
					j++;
				} else { 
					ArrayList<Integer> triplet = new ArrayList<Integer>();
					triplet.add(arr[i]);
					triplet.add(arr[j]);
					triplet.add(arr[k]);
					triplets.add(triplet);
					j++;
					k--;
				}
			}
		}
		
		return triplets;
	}
	

	static int[][] dp;
	static String x;
	static String y;
	
	public static int editDistance(int i, int j) { 

		if (i == 0) return j;
		if (j == 0) return i;
		
		if (dp[i][j] != Integer.MAX_VALUE) return dp[i][j]; 
		int diff = 0;
		if (x.charAt(i - 1) != y.charAt(j - 1)) { 
			diff = 1;
		}
		dp[i][j] = Math.min(editDistance(i - 1, j - 1) + diff, 
				Math.min(editDistance(i - 1, j), 
						editDistance(i, j - 1)) + 1);
		return dp[i][j];
		
	}
	
	public int[][] rotMatrix(int[][] arr) { 
		
		int i = 0;
		int j = arr.length - 1;
		
		while (i < j) { 
			
			for ()
			
		}
		
	}
	
	public static int bSearch(int[] arr, int k) { 
		int index = 0; 
		int exp = 0;
		
		while (true) { 
			try { 
				if (arr[index] == k) { 
					return index;
				} else if (arr[index] < k) { 
					index = (int) Math.pow(2, exp);
					exp++;
				} else { 
					break;
				}
			} catch (IndexOutOfBoundsException e) { 
				break;
			}
		}
		
		int left = (index / 2) + 1;
		int right = index - 1;
		int mid = left + (right - left) / 2;
		
		while (left <= right) { 
			try { 
				mid = left + (right - left) / 2;
				if (arr[mid] == k) { 
					return mid;
				} else if (arr[mid] < k) { 
					left = mid + 1;
				} else { 
					right = mid - 1;
				}
			} catch (IndexOutOfBoundsException e) { 
				right = mid - 1;
			}
		}
		return -1;
	}
	
	public int reverseBits2(int num) { 
		return num ^ ~0;
	}
	
	public static void main(String[] args) { 
		int[] arr = {1,1,2,3,4,4,5,6,7,8,8,9};
		System.out.print(bSearch(arr, 9));
		
		/*
		int x = 4;
		System.out.println(reverseBits(x));
		
		/*
		x = "ABCD";
		y = "RBBD";
		dp = new int[x.length() + 1][y.length() + 1];
		for (int i = 0; i < x.length() + 1; i++) { 
			for (int j = 0; j < y.length() + 1; j++) { 
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		
		System.out.println(editDistance(x.length(), y.length()));
		
		/*
		System.out.println((4 / 3) * 3);
		
		/*
		int[] coin_values = new int[6];
		coin_values[0] = 1;
		coin_values[1] = 5;
		coin_values[2] = 10;
		coin_values[3] = 25;
		coin_values[4] = 50;
		coin_values[5] = 100;
		
		int sum = 100;
		
		change_table = new Integer[sum + 1][coin_values.length + 1];
		
		for (int i = 0; i < sum + 1; i++) { 
			for (int j = 0; j < coin_values.length + 1; j++) { 
				change_table[i][j] = null;
			}
		}
		
		System.out.println(changeWays2(coin_values, sum, coin_values.length));
		//System.out.println(minChange(coin_values, sum));
		/*
		int[] arr = {10, 5, 6, 3, 2, 8, 7, 1, 4, 9};
		ArrayList<ArrayList<Integer>> triplets = threeSUM(arr, 10);
		
		for (ArrayList<Integer> al: triplets) { 
			for (Integer i: al) { 
				System.out.print(i + " ");
			}
			System.out.println();
		}
		
		/*
		HashSet<String> dict = new HashSet<String>();
		dict.add("this");
		dict.add("is");
		//dict.add("awesome");
		dict.add("a");
		dict.add("we");
		dict.add("some");
		dict.add("awe");
		dict.add("so");
		dict.add("me");

		String output = segmentString("thisisawesome", dict);
		System.out.println(output);
		
		/*
		int[] arr = {3,8,-2,4,-14,7,8};
		System.out.println(maxConSeq(arr));
		
		/*
		char[] correct = {'g', 'r', 'g', 'b'};
		char[] guess = {'g', 'b', 'r', 'y'};
		MM(correct, guess);
		
		//System.out.println(factorialZeros(4617));
		/*
		int[] input = new int[5];
		input[0] = 1;
		input[1] = 2;
		input[2] = 3;
		input[3] = 4;
		input[4] = 5;

		input = inPlaceSwap(input, 3, 1);
		for (int i: input) { 
			System.out.print(i);
		}
		
		//System.out.println(countDigitInNumber(4,14));
		//giveBookRange(1,4);
		/*
		LinkedList<Character> stack = new LinkedList<Character>();
		System.out.println(findSmallestPali("abb", stack, 1));
		//System.out.println(checkPalindrome("aacaa"));
		/*
		System.out.println(checkAnagram2("buddy", "ybddu"));
		
		/*
		String output = reverseString2("thomas", 2, 4);
		System.out.println(output);
		
		/*
		printChars(0);
		/*
		ArrayList<String> perms = getPermutations2("abcde");
		for (String s: perms) { 
			System.out.println(s);
		}
		
		/*
		ArrayList<Integer> set = new ArrayList<Integer>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(4);
		set.add(5);

		ArrayList<ArrayList<Integer>> subsets = getSubsets(set);
		
		for (ArrayList<Integer> ss: subsets) { 
			if (ss.isEmpty()) { 
				System.out.println("empty set");
			} else { 
				for (Integer elem: ss) { 
					System.out.print(elem + " ");
				}
				System.out.println();
			}
		}
		
		
		
		/*
		ArrayList<String> perms = getPermutations("abcd");
		
		for (String s: perms) { 
			System.out.println(s);
		}
		
		/*
		char[] output = findNextHighNum(59721);
		
		for (char c: output) { 
			System.out.print(c);
		}
		
		/*
		String s = "thomas";
		String ss = "mas";
		
		System.out.println(hasSubstring(s, ss));
		
		
		/*
		int[] arr = {3,5,7,-2,13,3,4,2,18};
		//          {0,2,3,-9,15,-10,1,-2,16}
				//  {0,2,5, 0,15,  5,6, 4,20}	
		System.out.println(buySellStockDC2(arr));
		
		//System.out.println(maxSubArray(arr));
		
		/*
		int[] input = {1,2,3,4,5};
		int[] output = arrayShuffle(input);
		
		for(int i: output) { 
			System.out.print(i);
		}
		
		
		//System.out.print(factorialZeros(29));
		//System.out.println(reverseString("thomas", 0, 3));
		//System.out.println(checkAnagram("osrs irkc", "rick ross"));
		
		/*
		int[][] matrix = { {5, 6, 3, 9}, 
						   {4, 7, 5, 17},
						   {9, 8, 9, 10},
						   {2, 11, 13, 11}
						  };
		
		table = new int[4][4];
		
		for (int i = 0; i < 4; i++) { 
			for (int j = 0; j < 4; j++) { 
				table[i][j] = Integer.MIN_VALUE;
			}
		}
		
		System.out.println(LIS2D(matrix, 0, 0));
		
		/*
		int r_x = 0, r_y = 0;
		int d_x = 6, d_y = 5;
		int[][] grid = new int[d_y][d_x];
		
		for (int i = 0; i < d_y; i++) { 
			for (int j = 0; j < d_x; j++) { 
				grid[i][j] = Integer.MAX_VALUE;
			}
		}
		
		System.out.println(robotPaths(r_x, r_y, d_x, d_y, grid));
		
		/*
		int[] item_weights = new int[5];
		int[] item_values = new int[5];
		
		item_weights[0] = 5;
		item_weights[1] = 10;
		item_weights[2] = 4;
		item_weights[3] = 8;
		item_weights[4] = 12;
		
		item_values[0] = 7;
		item_values[1] = 9;
		item_values[2] = 6;
		item_values[3] = 10;
		item_values[4] = 11;
		int max_capacity = 18;

		int[][] table = new int[item_weights.length][max_capacity + 1];
		int item_index = item_values.length - 1;
		
		for (int i = 0; i < item_weights.length; i++) { 
			for (int j = 0; j < max_capacity + 1; j++) { 
				table[i][j] = Integer.MAX_VALUE;
			}
		}
		
		System.out.println(knapsackProb(item_values, item_weights, 
				max_capacity, item_index, table));
		
		/*
		int[] change_arr = new int[6];
		change_arr[0] = 1;
		change_arr[1] = 5; 
		change_arr[2] = 10; 
		change_arr[3] = 25;
		change_arr[4] = 50;
		change_arr[5] = 100;
		
		int[][] sol_arr = new int[change_arr.length][100 + 1];
		
		for (int i = 0; i < change_arr.length; i++) { 
			for (int j = 0; j < 100 + 1; j++) { 
				sol_arr[i][j] = Integer.MAX_VALUE;
			}
		}
		
		
		System.out.println(changeWays(100, change_arr.length - 1, 
				change_arr, sol_arr));
		
		/*
		int[] coins = new int[4];
		coins[0] = 1; 
		coins[1] = 4;
		coins[2] = 6;
		coins[3] = 10;
		
		System.out.println(minChange(coins, 12));
		
		
		
		/*
		int[] input = new int[8];
		input[0] = -2;
		input[1] = -7;
		input[2] = -3;
		input[3] = -4;
		input[4] = -8;
		input[5] = -9;
		input[6] = -5;
		input[7] = -12;
		
		System.out.println(maxContSubsequence(input));
		
		/*
		ArrayList<Integer> values = new ArrayList<Integer>();
		
		values.add(1);
		values.add(14);
		values.add(6);
		values.add(15);
		values.add(4);
		values.add(11);
		values.add(10);
		values.add(5);
		values.add(9);
		values.add(2);
		values.add(7);
		values.add(12);
		values.add(13);
		values.add(3);
		values.add(8);
		
		AVLTree avl = new AVLTree(values);
		//avl.bstSort(avl.root);
		avl.printBST(avl.root);
		/*
		int[] arr = new int[8];
		arr[0] = 6; 
		arr[1] = 4;
		arr[2] = 0;
		arr[3] = 4;
		arr[4] = 9;
		arr[5] = 3;
		arr[6] = 14;
		arr[7] = 6;
		
		System.out.println(linearTimeDuplicateCheck(arr, 5));
		/*
		boolean prime = isPrime(8);
		System.out.println(prime);
		
		/*
		ArrayList<Integer> primes = generateKPrimeNumbers(15);
		for(int i: primes) { 
			System.out.println(i);
		}
		
		/*
		String input = new String(str);
		for(char c: str) { 
			System.out.print(c);
		}
		System.out.println();
		System.out.println(myRemoveDuplicates(input));
		
		*/
		/*
		int[] input = new int[9];
		input[0] = 9;
		input[1] = 2;
		input[2] = 4;
		input[3] = 7; 
		input[4] = 1;
		input[5] = 8;
		input[6] = 5;
		input[7] = 6;
		input[8] = 10; 
		//input[9] = 3;
		//input[10] = 11;
		
		//int[] output = concatenateElemArray(6, input);
		//int[] output = quickSort(input, 0, input.length - 1);
		//int[] output = wiggleSort(input);
		for(int i: input) { 
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println(findMedian(input));
		//System.out.println(binarySearch(output, 2, 0, output.length - 1));
		/*
		Set<String> perms = generateParens(3);
		for(String s: perms) { 
			System.out.println(s);
		}

		/*
		char[] x = {'a','g','c','a','t'};
		char[] y = {'g','a','c'};
		
		System.out.println(LCS(y,x));
		/*
		int[] changeArray = {1,5,10,25,50,100}; 
		int index = changeArray.length - 1; 
		
		int output = change(100, index, changeArray); 
		
		System.out.println(output); 
		
		/*
		String input = "abcd";

		ArrayList<String> permutations = getPermutations(input); 
		for(String s: permutations) { 
			System.out.println(s); 
		}
		*/
	    /*
		int[][] input = {{0,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}}; 
		
		
		for (int i = 0; i < 4; i++) {
		    for (int j = 0; j < 4; j++) {
		        System.out.print(input[i][j] + " ");
		    }
		    System.out.print("\n");
		}
		int[][] output = makeZeros(input, 16); 
		System.out.println(); 
		for (int i = 0; i < 4; i++) {
		    for (int j = 0; j < 4; j++) {
		        System.out.print(output[i][j] + " ");
		    }
		    System.out.print("\n");
		}
		*/
		
		/*
		String input = "kiss  my ass"; 
		
		String output = spaceReplace(input); 
		System.out.println(output); 
		*/
		
		/*
		String input = "ass"; 
		String output = reverseString(input); 
		
		System.out.println("input: " + input); 
		System.out.println("output: " + output); 
		*/
		
		/*
		Random r = new Random();
	      SkipList S = new SkipList();

	      int i;


	      for ( i = 0; i < 40; i++ )
	      {
	         S.put("" + r.nextInt(100),  r.nextInt(100) );
	      }

	    S.printHorizontal();
	    System.out.println("------");
	    S.printVertical();
	    */
	}
	
}
