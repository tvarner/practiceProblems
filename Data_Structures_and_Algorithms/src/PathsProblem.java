/**
 * 4th Hacker Rank Question: 
 * 
 * Specify the number of paths it would take 
 * to get from the top left corner of a grid (0,0)
 * to the bottom right corner (M - 1, N - 1), whereby
 * the traveler can only traverse cells in the grid with a value of 1.
 * Non traversable cells in the grid have a value of 0.
 * 
 * @author thomasvarner
 *
 */

public class PathsProblem {
	
	static int[][] table;
	static int[][] grid; 
	static int output;
	
	public static int numPaths(int i, int j, int M, int N) { 
		
		if(i == M - 1 && j == N - 1) { 
			return 1;
		}
		
		if(table[i][j] != -1) { 
			return table[i][j];
		} else if(grid[i][j] == 0) { 
			table[i][j] = 0;
		} else if(i == M - 1) { 
			table[i][j] = numPaths(i, j + 1, M, N);
		} else if(j == N - 1) { 
			table[i][j] = numPaths(i + 1, j, M, N);
		} else { 
			table[i][j] = numPaths(i, j + 1, M, N) + numPaths(i + 1, j, M, N);
		}
	
		return table[i][j];
	}

	public static void main(String[] args) { 
		
		// 1. specify grid size
		grid = new int[3][3];
		
		// 2. fill grid with traversable cells [cell = 1]
		for(int i = 0; i < 3; i++) { 
			for(int j = 0; j < 3; j++) { 
				grid[i][j] = 1;
			}
		}
		
		// 3. fill grid with non-traversable cells [cell = 0]
		//grid[1][0] = 0;
		
		// 4. create table to cache previously solved subproblems (same size as grid)
		table = new int[3][3];
		
		// 5. initialize table
		for(int i = 0; i < 3; i++) { 
			for(int j = 0; j < 3; j++) { 
				table[i][j] = -1;
			}
		}
		
		// 6. print results
		System.out.println(numPaths(0,0,3,3));
	}
}
