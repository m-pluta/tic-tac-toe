import java.util.Scanner;
class Main {
	public static void main(String[] args) {
		String[][] Matrix = { { " ", " ", " " }, { " ", " ", " " }, { " ", " ", " " } };
		int empty = 9;
		String turn = "X"; // Initialising
		Scanner scn = new Scanner(System.in); // Creating a scanner
		show(Matrix); // Displays the empty starting grid
		while (empty > 0) { // while there are empty cells in the grid
			while (true) { // repeats until an empty cell is selected
				System.out.println("\u001b[34mPlayer " + turn + " - Enter coordinates: \u001b[37m");
				System.out.print("\u001b[33mx: \u001b[37m");
				int x = Integer.parseInt(scn.nextLine());
				System.out.print("\u001b[35my: \u001b[37m"); // user input
				int y = Integer.parseInt(scn.nextLine());
				if (isValid(Matrix, x, y)) {
					if (isEmpty(Matrix, x, y)) { // if the user selected an empty slot
						Matrix[x - 1][y - 1] = turn; // fills the slot
						break; // exits to allow other player to make a move
					} else {
						System.out.println("\u001b[31mThis cell is already taken\u001b[37m"); // if the cell is taken already
					}
				} else {
					System.out.println("\u001b[31mThis is not a valid input\u001b[37m");
				}
			}
			if (hasWon(Matrix, turn)) { // if the current player has won
				System.out.println("\u001b[32mPlayer " + turn + " has won!");
				break; // exits game loop
			} else {
				show(Matrix); // if not then show current grid
			}

			if (turn.equals("X")) {
				turn = "O";
			} else { // switching turns
				turn = "X";
			}
			empty--; // 1 less empty cell
		}
		if (empty == 0) {
			System.out.println("\u001b[31mGame over, it ended in a draw."); // if there is no more empty cells
		}
	}
	public static void show(String[][] Mtx) { // diplays the grid with labelling each axis
		System.out.println("   \u001b[33mx  1     2     3");
		System.out.println("\u001b[35my \u001b[37m╔═════╦═════╦═════╗");
		System.out.println("\u001b[35m1 \u001b[37m║  " + Mtx[0][0] + "  ║  " + Mtx[1][0] + "  ║  " + Mtx[2][0] + "  ║");
		System.out.println("  ╠═════╬═════╬═════╣");
		System.out.println("\u001b[35m2 \u001b[37m║  " + Mtx[0][1] + "  ║  " + Mtx[1][1] + "  ║  " + Mtx[2][1] + "  ║");
		System.out.println("  ╠═════╬═════╬═════╣");
		System.out.println("\u001b[35m3 \u001b[37m║  " + Mtx[0][2] + "  ║  " + Mtx[1][2] + "  ║  " + Mtx[2][2] + "  ║");
		System.out.println("  ╚═════╩═════╩═════╝");
	}
	public static boolean isEmpty(String[][] Mtx, int x, int y) {
		return Mtx[x - 1][y - 1] == " "; // checks if cell is stil empty
	}
	public static boolean isValid(String[][] Mtx, int x, int y) {
		return  x - 1 < Mtx.length && y - 1 < Mtx[0].length; 
	}
	public static boolean hasWon(String[][] Mtx, String turn) {
		for (int i = 0; i < 3; i++) {
			if (Mtx[i][0] == turn && Mtx[i][1] == turn && Mtx[i][2] == turn) { // checks each column
				showWin(Mtx, i, 0, i, 1, i, 2);
				return true;
			}
			if (Mtx[0][i] == turn && Mtx[1][i] == turn && Mtx[2][i] == turn) { // checks each row
				showWin(Mtx, 0, i, 1, i, 2, i);
				return true;
			}
		}
		if (Mtx[0][0] == turn && Mtx[1][1] == turn && Mtx[2][2] == turn) { // checks y=-x diagonal
			showWin(Mtx, 0, 0, 1, 1, 2, 2);
			return true;
		}
		if (Mtx[0][2] == turn && Mtx[1][1] == turn && Mtx[2][0] == turn) { // checks y=x diagonal
			showWin(Mtx, 0, 2, 1, 1, 2, 0);
			return true;
		}
		return false;
	}
	public static void showWin(String[][] Mtx, int x1, int y1, int x2, int y2, int x3, int y3) {
		Mtx[x1][y1] = "\u001b[32m" + Mtx[x1][y1] + "\u001b[37m"; 	// given the three points that are in a row it changes
		Mtx[x2][y2] = "\u001b[32m" + Mtx[x2][y2] + "\u001b[37m";	// the colour of those cells
		Mtx[x3][y3] = "\u001b[32m" + Mtx[x3][y3] + "\u001b[37m";
		show(Mtx); // shows updated grid
	}
}