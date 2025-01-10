/*
Author: Matt Farzaneh
Student number: 251370889

Description:
The Configurations class sets up the board for the TicTacToe game. its primary function is to check winning
conditions and use the HashDictionary class to get and store the game configurations
*/

public class Configurations {
	private char[][] board;
	
	// Constructor (initializes and fills spaces with ' ')
	public Configurations (int boardSize, int lengthToWin, int maxLevels) {
		board = new char[boardSize][boardSize];
		for(int i=0; i<boardSize; i++) {
			for(int j=0; j<boardSize; j++) {
				board[i][j] = ' ';
			}
		}
	}
	
	// Creates instance of HashDictionary
	public HashDictionary createDictionary() {
		int tableSize = 7001;
		return new HashDictionary(tableSize);
	}
	
	// Checks if configuration is already in hash table
	public int repeatedConfiguration(HashDictionary hashTable) {
		String config = "";
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board.length; j++) {
				config = config + board[i][j];
			}
		}
		int score = hashTable.get(config);
		// Configuration found
		if(score != -1) {
			return score;
		}
		// Configuration not found
		else {
			return -1;
		}
	}
	
	// Adds a configuration and score in the board
	public void addConfiguration(HashDictionary hashDictionary, int score) {
	    StringBuilder config = new StringBuilder();
	    
	    for (int i = 0; i < board.length; i++) {
	        for (int j = 0; j < board[i].length; j++) {
	            config.append(board[i][j]);
	        }
	    }
	    
	    Data numRecord = new Data(config.toString(), score);
	    // Try to add configuration to hash table
	    try {
	        hashDictionary.put(numRecord);
	    } catch (DictionaryException e) {
	        System.out.println("Configuration already exists in the dictionary.");
	    }
	}
	
	// Puts symbol in coordinates
	public void savePlay(int row, int col, char symbol) {
		board[row][col] = symbol;
	}
	
	// Checks if square is empty
	public boolean squareIsEmpty (int row, int col) {
		return board[row][col] == ' ';
	}
	
	// Checks for if X or O wins
	public boolean wins (char symbol) {
		  // Check rows
	    for (int i = 0; i < board.length; i++) {
	        if (lineCheck(symbol, i, 0, 0, 1)) {
	            return true;
	        }
	    }

	    // Check columns
	    for (int j = 0; j < board[0].length; j++) {
	        if (lineCheck(symbol, 0, j, 1, 0)) {
	            return true;
	        }
	    }

	    // Check main diagonals
	    for (int i = 0; i <= board.length - 3; i++) {
	        if (lineCheck(symbol, i, 0, 1, 1)) {
	            return true;
	        }
	    }
	    for (int j = 1; j <= board[0].length - 3; j++) {
	        if (lineCheck(symbol, 0, j, 1, 1)) {
	            return true;
	        }
	    }

	    // Check anti-diagonals
	    for (int i = 0; i <= board.length - 3; i++) {
	        if (lineCheck(symbol, i, board[0].length - 1, 1, -1)) {
	            return true;
	        }
	    }
	    for (int j = board[0].length - 2; j >= 3 - 1; j--) {
	        if (lineCheck(symbol, 0, j, 1, -1)) {
	            return true;
	        }
	    }

	    return false;
	}

	// Helper method that helps determine if a symbol won
	private boolean lineCheck(char symbol, int startRow, int startCol, int rowDir, int colDir) {
	    int count = 0;

	    while (startRow >= 0 && startRow < board.length && startCol >= 0 && startCol < board[0].length) {
	        if (board[startRow][startCol] == symbol) {
	            count++;
	            if (count >= 3) {
	                return true;
	            }
	        } else {
	            count = 0;
	        }

	        startRow += rowDir;
	        startCol += colDir;
	    }

	    return false;
	}
	
	// Checks if there is a draw
	public boolean isDraw() {
		if(board == null) {
			return false;
		}
		for (int i = 0; i < board.length; i++) {
	        for (int j = 0; j < board[i].length; j++) {
	            if(board[i][j]==' ') {
	            	return false;
	            }
	        }
	    }
		return true;
	}
	
	// Evaluates current boards state
	public int evalBoard() {
		if(wins('O')){
			return 3;
		}
		else if(wins('X')) {
			return 0;
		}
		else if(isDraw()) {
			return 2;
		}
		else {
			return 1;
		}
	}
}
