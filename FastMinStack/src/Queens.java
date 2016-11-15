
public class Queens {

	int[] placedQueens;
	int size;
	
	public Queens(int n) { 
		
		placedQueens = new int[n];
		size = n;
	}
	
	public boolean place(int row, int column) { 
	
		for (int r = 0; r < row; r++) { 
			if (placedQueens[r] == column 
					|| Math.abs(r - row) == Math.abs(placedQueens[r] - column)) { 
				return false;
			}
		}
		return true;
	}
	
	public void nQueens(int row, int numOfColumns) { 
		
		for (int col = 0; col < numOfColumns; col++) { 
			if (place(row, col)) { 
				placedQueens[row] = col;
				if (row == numOfColumns - 1) { 
					printBoard();
				} else { 
					nQueens(row + 1, numOfColumns);
				}
			}
		}
	}
	
	public void printBoard() { 
		
		for (int i = 0; i < size; i++) { 
			for (int j = 0; j < size; j++) { 
				if (placedQueens[i] == j) { 
					System.out.print("Q ");
				} else { 
					System.out.print("X ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) { 
		
		Queens q = new Queens(6);
		q.nQueens(0, 6);
	}
}
