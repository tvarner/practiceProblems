import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


public class Knights {
	
	boardNode[][] board;
	
	public Knights(int boardLength) { 
		board = new boardNode[boardLength][boardLength];
		createBoard();
		addNeighbors();
		//System.out.println(findDistance(board[i1][j1], board[i2][j2]));
	}
	
	public void createBoard() { 
		for (int i = 0; i < board.length; i++) { 
			for (int j = 0; j < board.length; j++) { 
				board[i][j] = new boardNode(i, j, 0);
			}
		}
	}
	
	public int findDistance(int x1, int y1, int x2, int y2) { 
		return findDistance(board[x1][y1], board[x2][y2]);
	}
	
	public int findDistance(boardNode start, boardNode end) { 
		
		Queue<boardNode> currLevel = new LinkedList<boardNode>();
		Queue<boardNode> nextLevel = new LinkedList<boardNode>();
		currLevel.add(start);
		int numMoves = 0;
		
		while (!currLevel.isEmpty()) { 
			Iterator<boardNode> iter = currLevel.iterator();
			while (iter.hasNext()) { 
				boardNode curr = iter.next();
				if (curr.equals(end)) { 
					return numMoves;
				}
				for (boardNode node: curr.neighbors) { 
					nextLevel.add(node);
				}
			}		
			currLevel = nextLevel;
			nextLevel = new LinkedList<boardNode>();
			numMoves++;
		}
		return -1;
	}
	
	public void addNeighbors() { 
		
		for (int i = 0; i < board.length; i++) { 
			for (int j = 0; j < board.length; j++) { 				
				//up left
				addNeighbor(board, i, j, -2, -1);
				//left up
				addNeighbor(board, i, j, -1, -2);
				//up right
				addNeighbor(board, i, j, -2, 1);
				//right up
				addNeighbor(board, i, j, -1, 2);
				//down right
				addNeighbor(board, i, j, 2, 1);
				//right down
				addNeighbor(board, i, j, 1, 2);
				//down left
				addNeighbor(board, i, j, 2, -1);
				//left down
				addNeighbor(board, i, j, 1, -2);
			}
		}
	}

	public void addNeighbor(boardNode[][] b, int i, int j, int row, int col) { 
		if (b[i][j].row + row >= 0
				&& b[i][j].row + row < board.length 
				&& board[i][j].column + col >= 0
				&& board[i][j].column + col < board.length) { 
			b[i][j].neighbors.add(b[i + row][j + col]);
		}
	}
	
	public void kTour(int i, int j) { 
		try {
			kTour(board[i][j], 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void kTour(boardNode n, int move_count) throws Exception { 
		// place knight
		n.value = move_count; 
		// if last cell reached, print output and return 
		if (move_count == board.length * board.length) { 
			printBoard();
			throw new Exception("solution found!");
		} else { 
			// otherwise, find a neighbor that hasn't been visited
			// (has value of 0) and recurse on that cell with 
			// incremented move count
			for (boardNode v: n.neighbors) { 
				//printBoard();
				if (v.value == 0) kTour(v, move_count + 1);
			}
		}
		// if all neighbors have been checked, and there are no valid
		// moves to make, set cell back to zero (clean up) and backtrack (return)
		n.value = 0;
	}
	
	public void printBoard() { 
		for (int i = 0; i < board.length; i++) { 
			for (int j = 0; j < board.length; j++) { 
				System.out.print(board[i][j].value + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public class boardNode { 
		
		int row; 
		int column;
		int value;
		ArrayList<boardNode> neighbors = new ArrayList<boardNode>();
		
		public boardNode(int row, int column, int value) { 
			this.row = row; 
			this.column = column;
		}
	}
	
	public static void main(String[] args) { 
		
		Knights k = new Knights(8);
		System.out.print(k.findDistance(0, 0, 7, 4));
	}
}
