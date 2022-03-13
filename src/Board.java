public class Board {
	private int numBombs;
	private String[][] board;
	private String[][] gameBoard;
	private String status = "none";

	//Creates the board and hidden board
	public Board(int diff) {
		numBombs = diff;

		if (diff == 10) {
			board = new String[8][8];
			gameBoard = new String[8][8];
		}
		else if(diff == 40) {
			board = new String[16][16];
			gameBoard = new String[16][16];
		}
		else {
			board = new String[24][24];
			gameBoard = new String[24][24];
		}

		for (int i  = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = "0";
				gameBoard[i][j] = "#";
			}
		}

		this.placeBombs();
		this.placeNumbers();
	}


	//Checks to see if a square has a bomb
	public boolean squareHasBomb(int row, int col) {
		if (board[row][col].equals("*"))
			return true;
		return false;
	}
	//Returns a value that indicates if the game is lost, won, or in progress.
	public int gameStatus() {
		switch (status) {
		case "none": return 0;
		case "lose": return 1;
		case "win": return 2;
		default: return 3;
		}
	}

	//Places a flag at a location.
	public void placeFlag(int row, int col) {
		if (gameBoard[row][col].equals("*"))
			gameBoard[row][col] = "#";
		else if (!gameBoard[row][col].equals("#")) {
		} else
			gameBoard[row][col] = "*";
	}

	//Checks to see if you won the game.
	public void checkVictory() {
		int count = 0;
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (gameBoard[row][col].equals("*") || gameBoard[row][col].equals("#")) {
					count++;
				}
			}
		}
		if (count == numBombs)
			status = "win";
	}

	//Places bombs when the game is created.
	public void placeBombs() {
		int tempx = (int)(Math.random() * board.length);
		int tempy = (int)(Math.random() * board[tempx].length);
		for (int i = 0; i < numBombs; i++) {
			while (squareIsTaken(tempx,tempy)) {
				tempx = (int)(Math.random() * board.length);
				tempy = (int)(Math.random() * board[tempx].length);
			}
			board[tempx][tempy] = "*";
		}
	}

	//Checks to see if a space is empty before placing bombs
	private boolean squareIsTaken(int x, int y) {
		if (!board[x][y].equals("0"))
			return true;
		return false;
	}

	//Places numbers according to how many numbers are around the space
	public void placeNumbers() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (!(board[i][j].equals("*")))
					board[i][j] = Integer.toString(decideNumber(i,j));
			}
		}
	}

	//Counts the number of bombs around a space
	private int decideNumber(int row, int col) {
		int num = 0;

			if (!(row - 1 < 0) && !(col - 1 < 0)) {
				if (board[row - 1][col - 1].equals("*"))
					num++;
			}
			if (!(row - 1 < 0)) {
				if (board[row - 1][col].equals("*"))
					num++;
			}
			if (!(row - 1 < 0) && !(col + 1 == board[row].length)) {
				if (board[row - 1][col + 1].equals("*"))
					num++;
			}
			if (!(col + 1 == board[row].length)) {
				if (board[row][col + 1].equals("*"))
					num++;
			}
			if (!(row + 1 == board.length) && !(col + 1 == board[row].length)) {
				if (board[row + 1][col + 1].equals("*"))
					num++;
			}
			if (!(row + 1 == board.length)) {
				if (board[row + 1][col].equals("*"))
					num++;
			}
			if (!(row + 1 == board.length) && !(col - 1 < 0)) {
				if (board[row + 1][col - 1].equals("*"))
					num++;
			}
			if (!(col - 1 < 0)) {
				if (board[row][col - 1].equals("*"))
					num++;
			}

		return num;
	}


	//Uses recursion to open the space if an empty space is chosen
	public void openSpace(int row, int col) {
		switch (gameBoard[row][col]) {
		case "1": numberCheck(row, col, 1); break;
		case "2": numberCheck(row, col, 2); break;
		case "3": numberCheck(row, col, 3); break;
		case "4": numberCheck(row, col, 4); break;
		case "5": numberCheck(row, col, 5); break;
		case "6": numberCheck(row, col, 6); break;
		case "7": numberCheck(row, col, 7); break;
		case "8": numberCheck(row, col, 8); break;
		default: break;
		}

		gameBoard[row][col] = board[row][col];

		if (gameBoard[row][col].equals("0") || gameBoard[row][col].equals("*")) {

			if (!(row - 1 < 0) && !(col - 1 < 0)) {
				if (gameBoard[row - 1][col - 1].equals("#")) {
					this.openSpace(row - 1, col - 1);
				}
			}
			if (!(row - 1 < 0)) {
				if (gameBoard[row - 1][col].equals("#")) {
					this.openSpace(row - 1, col);
				}
			}
			if (!(row - 1 < 0) && !(col + 1 == board[row].length)) {
				if (gameBoard[row - 1][col + 1].equals("#")) {
					this.openSpace(row - 1, col + 1);
				}
			}
			if (!(col + 1 == board[row].length)) {
				if (gameBoard[row][col + 1].equals("#")) {
					this.openSpace(row, col + 1);
				}
			}
			if (!(row + 1 == board.length) && !(col + 1 == board[row].length)) {
				if (gameBoard[row + 1][col + 1].equals("#")) {
					this.openSpace(row + 1, col + 1);
				}
			}
			if (!(row + 1 == board.length)) {
				if (gameBoard[row + 1][col].equals("#")) {
					this.openSpace(row + 1, col);
				}
			}
			if (!(row + 1 == board.length) && !(col - 1 < 0)) {
				if (gameBoard[row + 1][col - 1].equals("#")) {
					this.openSpace(row + 1, col - 1);
				}
			}
			if (!(col - 1 < 0)) {
				if (gameBoard[row][col - 1].equals("#")) {
					this.openSpace(row, col - 1);
				}
			}

		}
	}

	//Checks to see if a space has a bomb
	public void checkSpace(int row, int column) {
		if (squareHasBomb(row,column)) {
			status = "lose";
		}
	}

	//Checks to see how many flags are around the chosen space to chord
	public void numberCheck(int row, int col, int bombs) {
		int check = 0;
		boolean safe = true;

		if (!(row - 1 < 0) && !(col - 1 < 0)) {
			if (gameBoard[row - 1][col - 1].equals("*")) {
				check++;
				if (!gameBoard[row - 1][col - 1].equals(board[row - 1][col - 1])) {
					safe = false;
				}
			}
		}
		if (!(row - 1 < 0)) {
			if (gameBoard[row - 1][col].equals("*")) {
				check++;
				if (!gameBoard[row - 1][col].equals(board[row - 1][col])) {
					safe = false;
				}
			}
		}
		if (!(row - 1 < 0) && !(col + 1 == board[row].length)) {
			if (gameBoard[row - 1][col + 1].equals("*")) {
				check++;
				if (!gameBoard[row - 1][col + 1].equals(board[row - 1][col + 1])) {
					safe = false;
				}
			}
		}
		if (!(col + 1 == board[row].length)) {
			if (gameBoard[row][col + 1].equals("*")) {
				check++;
				if (!gameBoard[row][col + 1].equals(board[row][col + 1])) {
					safe = false;
				}
			}
		}
		if (!(row + 1 == board.length) && !(col + 1 == board[row].length)) {
			if (gameBoard[row + 1][col + 1].equals("*")) {
				check++;
				if (!gameBoard[row + 1][col + 1].equals(board[row + 1][col + 1])) {
					safe = false;
				}
			}
		}
		if (!(row + 1 == board.length)) {
			if (gameBoard[row + 1][col].equals("*")) {
				check++;
				if (!gameBoard[row + 1][col].equals(board[row + 1][col])) {
					safe = false;
				}
			}
		}
		if (!(row + 1 == board.length) && !(col - 1 < 0)) {
			if (gameBoard[row + 1][col - 1].equals("*")) {
				check++;
				if (!gameBoard[row + 1][col - 1].equals(board[row + 1][col - 1])) {
					safe = false;
				}
			}
		}
		if (!(col - 1 < 0)) {
			if (gameBoard[row][col - 1].equals("*")) {
				check++;
				if (!gameBoard[row][col - 1].equals(board[row][col - 1])) {
					safe = false;
				}
			}
		}

		if (check == bombs && safe)
			numberOpen(row, col);
		if (check == bombs && !safe)
			status = "lose";
	}

	//Opens empty space
	public void numberOpen(int row, int col) {

		if (!(row - 1 < 0) && !(col - 1 < 0)) {
			if (gameBoard[row - 1][col - 1].equals("#")) {
				gameBoard[row - 1][col - 1] = board[row - 1][col - 1];
			}
			if (gameBoard[row - 1][col - 1].equals("0")) {
				openSpace(row - 1, col - 1);
			}
		}
		if (!(row - 1 < 0)) {
			if (gameBoard[row - 1][col].equals("#")) {
				gameBoard[row - 1][col] = board[row - 1][col];
			}
			if (gameBoard[row - 1][col].equals("0")) {
				openSpace(row - 1, col);
			}
		}
		if (!(row - 1 < 0) && !(col + 1 == board[row].length)) {
			if (gameBoard[row - 1][col + 1].equals("#")) {
				gameBoard[row - 1][col + 1] = board[row - 1][col + 1];
			}
			if (gameBoard[row - 1][col + 1].equals("0")) {
				openSpace(row - 1, col + 1);
			}
		}
		if (!(col + 1 == board[row].length)) {
			if (gameBoard[row][col + 1].equals("#")) {
				gameBoard[row][col + 1] = board[row][col + 1];
			}
			if (gameBoard[row][col + 1].equals("0")) {
				openSpace(row, col + 1);
			}
		}
		if (!(row + 1 == board.length) && !(col + 1 == board[row].length)) {
			if (gameBoard[row + 1][col + 1].equals("#")) {
				gameBoard[row + 1][col + 1] = board[row + 1][col + 1];
			}
			if (gameBoard[row + 1][col + 1].equals("0")) {
				openSpace(row + 1, col + 1);
			}
		}
		if (!(row + 1 == board.length)) {
			if (gameBoard[row + 1][col].equals("#")) {
				gameBoard[row + 1][col] = board[row + 1][col];
			}
			if (gameBoard[row + 1][col].equals("0")) {
				openSpace(row + 1, col);
			}
		}
		if (!(row + 1 == board.length) && !(col - 1 < 0)) {
			if (gameBoard[row + 1][col - 1].equals("#")) {
				gameBoard[row + 1][col - 1] = board[row + 1][col - 1];
			}
			if (gameBoard[row + 1][col - 1].equals("0")) {
				openSpace(row + 1, col - 1);
			}
		}
		if (!(col - 1 < 0)) {
			if (gameBoard[row][col - 1].equals("#")) {
				gameBoard[row][col - 1] = board[row][col - 1];
			}
			if (gameBoard[row][col - 1].equals("0")) {
				openSpace(row, col - 1);
			}
		}

	}


	//Prints the board when you lose or when you win
	public void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}

			System.out.println();
		}
	}

	//Prints the board with hidden spaces
	public void printGameBoard() {
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				System.out.print(gameBoard[i][j] + " ");
			}

			System.out.println();
		}
	}
}
