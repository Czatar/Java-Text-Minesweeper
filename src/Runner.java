import java.util.Scanner;
public class Runner {
	public static void main(String[] args) {
		int difficulty = chooseDifficulty();
		int tempRow;
		int tempColumn;
		boolean flag;

		//create board
		Board game = new Board(difficulty);
		game.printGameBoard();

		//if you lose, it shows.
		while (game.gameStatus() == 0) {
			flag = askForFlag();
			tempRow = chooseRow(difficulty) - 1;
			tempColumn = chooseColumn(difficulty) - 1;

			if (flag) {
				game.placeFlag(tempRow, tempColumn);
				game.printGameBoard();
			}
			else {
				game.checkSpace(tempRow, tempColumn);

				if (!game.squareHasBomb(tempRow, tempColumn)) {
					game.openSpace(tempRow, tempColumn);
					game.printGameBoard();
				}
			}
			game.checkVictory();
		}

		if (game.gameStatus() == 1) {
			game.printBoard();
			System.out.println("You Lose!");
		}
		else if (game.gameStatus() == 2)
			System.out.println("You win!");
		else {
			game.printBoard();
			System.out.println("What did you even do lmao");
			}
	}

	//Makes the user choose a valid row
	public static int chooseRow(int diff) {
		String input;
		Scanner inputSquareRow = new Scanner(System.in);
		System.out.println("Pick a row =>");
		input = inputSquareRow.nextLine();
		boolean goodAnswer = false;
		boolean goodNumber = false;

		while (!goodAnswer) {
			goodAnswer = true;

			while (!goodNumber) {
				goodNumber = true;
				try {
					Integer.parseInt(input);
				}
				catch (NumberFormatException ex){
					System.out.println("Pick a VALID row: ");
					input = inputSquareRow.nextLine();
					goodNumber = false;
				}
			}

			if (diff == 10) {
				if (Integer.parseInt(input) > 8 ||
					Integer.parseInt(input)	< 1) {

					System.out.println("Pick a VALID row: ");
					input = inputSquareRow.nextLine();
					goodAnswer = false;
				}
			} else if (diff == 40) {
				if (Integer.parseInt(input) > 16 ||
					Integer.parseInt(input) < 1) {

					System.out.println("Pick a VALID row: ");
					input = inputSquareRow.nextLine();
					goodAnswer = false;
				}
			} else {
				if (Integer.parseInt(input) >= 24 ||
					Integer.parseInt(input)	< 1) {

					System.out.println("Pick a VALID row: ");
					input = inputSquareRow.nextLine();
					goodAnswer = false;
				}
			}

		}

		inputSquareRow.close();
		return Integer.parseInt(input);
	}

	//Makes the user choose a valid column
	public static int chooseColumn(int diff) {
		String input;
		Scanner inputSquareColumn = new Scanner(System.in);
		System.out.println("Pick a column =>");
		input = inputSquareColumn.nextLine();
		boolean goodAnswer = false;
		boolean goodNumber = false;

		while (!goodAnswer) {
			goodAnswer = true;

			while (!goodNumber) {
				goodNumber = true;
				try {
					Integer.parseInt(input);
				}
				catch (NumberFormatException ex){
					System.out.println("Pick a VALID column: ");
					input = inputSquareColumn.nextLine();
					goodNumber = false;
				}
			}

			if (diff == 10) {
				if (Integer.parseInt(input) > 8 ||
					Integer.parseInt(input)	< 1) {

					System.out.println("Pick a VALID column: ");
					input = inputSquareColumn.nextLine();
					goodAnswer = false;
				}
			} else if (diff == 40) {
				if (Integer.parseInt(input) > 16 ||
					Integer.parseInt(input) < 1) {

					System.out.println("Pick a VALID column: ");
					input = inputSquareColumn.nextLine();
					goodAnswer = false;
				}
			} else {
				if (Integer.parseInt(input) > 24 ||
					Integer.parseInt(input)	< 1) {

					System.out.println("Pick a VALID column: ");
					input = inputSquareColumn.nextLine();
					goodAnswer = false;
				}
			}
		}

		inputSquareColumn.close();
		return Integer.parseInt(input);
	}

	//Makes the user choose a valid difficulty
	public static int chooseDifficulty() {
		Scanner inputDifficulty = new Scanner(System.in);
		System.out.println("Choose a difficulty.");
		System.out.println("Type 'easy' 'medium' or 'hard'.");
		System.out.println("Or, type 'help' for instructions on how to play.");
		String input = inputDifficulty.nextLine();

		boolean goodAnswer = false;
		int bombss = 0;

		while (goodAnswer == false) {
			switch (input) {
				case "easy": goodAnswer = true; bombss = 10; break;
				case "medium": goodAnswer = true; bombss = 40; break;
				case "hard": goodAnswer = true; bombss = 99; break;
				case "help": printHelp();
				default:
					System.out.println("I'll ask again. Choose a difficulty.");
					System.out.println("Type 'easy' 'medium' or 'hard'.");
					System.out.println("Or, type 'help'.");
					input = inputDifficulty.nextLine();
					break;
			}
		}
		inputDifficulty.close();
		return bombss;
	}

	//Prints help print statments
	public static void printHelp() {
		System.out.println("THE RULES");
		System.out.println("Easy difficulty has 10 bombs, medium difficulty has \n40 bombs, and hard difficulty has 99 bombs.");
		System.out.println("Each number represents the number of bombs around \nthe space in all 8 directions.");
		System.out.println("You lose if you clear a space that is actually a \nbomb.");
		System.out.println("You win if the number of flags and blank spaces \ncombined add up to the total number of bombs.");
		System.out.println();
		System.out.println("COORDINATES:");
		System.out.println("Sadly, I'm too lazy to add numbers to help with choosing \na space, so you'll need to squint.");
		System.out.println("Rows increase as you go down and columns increase as \nyou go right.");
		System.out.println("The top left corner would be row 1 and column 1.");
		System.out.println("If you start typing fast, don't forget that the game \nasks for the row first and then the column.");
		System.out.println();
		System.out.println("FLAGS:");
		System.out.println("Each round will ask you if you want to place a flag. \nSaying anything other than 'yes' will be assumed as a 'no'.");
		System.out.println("Attempting to place a flag where there is already a \nflag will remove the flag.");
		System.out.println("Clearing a space with a flag will be treated as \nclearing a space without a flag, so be careful.");
		System.out.println("You don't have to place flags. They are just there \nto help.");
		System.out.println();
		System.out.println("FAST CLEARING:");
		System.out.println("Fast clearing happens when you attempt to clear a \nspace that already shows a number.");
		System.out.println("Fast clearing only works if the number of flags around \nthe number equal the number's value.");
		System.out.println("The remaining unopened spaces around the number are \ncleared if the flags are correctly placed where there are bombs.");
		System.out.println("If a flag is placed where there is not a bomb, you \nwill lose, so be careful.");
		System.out.println();
	}

	//Asks the user if they want to place a flag
	public static boolean askForFlag() {
		Scanner inputFlag = new Scanner(System.in);
		System.out.println("Are you placing a flag? Type 'yes' if you are: ");
		if (inputFlag.nextLine().equals("yes"))
		{
			inputFlag.close();
			return true;
		}
		inputFlag.close();
		return false;
	}
}
