//public class Generate {

	//Generate(char difficulty) {
//	}

	//void fillCell() {
	//}

	//boolean isRowValid() {
	//}

	//boolean isColumnVaild() {
	//}

	//boolean isSquareValid() {
	//}
//}


//public class Solve {
//}

import java.util.Random;
import java.util.Arrays;
import java.lang.reflect.Array;

/** Main function.
 *
 * The the the.
 */
class Main {
	public static void main(String args[]) {
		System.out.println("Hello World!");
		Generate generate = new Generate();
		generate.fillGrid();
	}
}

class Generate {
	int[][] grid = new int[10][10];
	int[] validNums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	//int[] excludedRow = new int[10];
	//int[] excludedColumn = new int[10];
	Random rand = new Random();

	void fillGrid() {
		System.out.println("Filling grid...");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int randomNum = rand.nextInt(9);
				int numChoice = validNums[randomNum];
				grid[i][j] = numChoice;
				Arrays.asList(validNums);
				validNums.remove(randomNum);
			}
		}
		System.out.println("Grid filled!");
		printGrid();
	}


	/* Parses grid and fills cell with random value */
	//void fillGrid() {
	//	System.out.println("Filling grid...");
	//	for (int i = 0; i < 9; i++) {
	//		for (int j = 0; j < 9; j++) {
	//			int randomNum = rand.nextInt(10);
	//			grid[i][j] = randomNum;
	//			if (isValidRow(randomNum, j)) {
	//				System.out.println(String.format("ROW Number at %d %d is %d", i, j, randomNum));
	//				continue;
	//			}
	//			else {
	//				j = j - 1;
	//			}
	//		}
	//		Arrays.fill(excludedRow, 0);	
	//	}
	//	System.out.println("Grid filled!");
	//	printGrid();
	//}

	//boolean isValidRow(int number, int index) {
	//	int count = 0;
	//	for (int k = 0; k <= index; k++) {
	//		int current = excludedRow[k];
	//		if (number == current) {
	//			count = count + 1;
	//			System.out.println(String.format("ROW c: %d\tn: %d\td: %d", current, number, count));
	//			continue;
	//		}
	//		else {
	//			System.out.println(String.format("ROW num %d does not equal curr %d", number, current)); 
	//			continue;
	//		}
	//	}
	//	if (count > 0) {
	//		System.err.println("ROW " + number + " is invalid");
	//		return false;
	//	}
	//	else {
	//		for (int l = 0; l < 9; l++) {
	//			int current = excludedRow[l];
	//			if (excludedRow[l] != 0) {
	//				continue;
	//			}
	//			else {
	//				excludedRow[l] = number;
	//				System.out.println(String.format("ROW excl arr = %d at index %d", number, l));
	//				break;
	//			}
	//		}
	//		return true;
	//	}
	//}

	//boolean isValidColumn(int number, int index) {
	//	int count = 0;
	//	for (int k = 0; k <= index; k++) {
	//		int current = excludedColumn[k];
	//		if (number == current) {
	//			count = count + 1;
	//			System.out.println(String.format("COLUMN c: %d\tn: %d\td: %d", current, number, count));
	//			continue;
	//		}
	//		else {
	//			System.out.println(String.format("COLUMN num %d does not equal curr %d", number, current)); 
	//			continue;
	//		}
	//	}
	//	if (count > 0) {
	//		System.err.println("COLUMN " + number + " is invalid");
	//		return false;
	//	}
	//	else {
	//		for (int l = 0; l < 9; l++) {
	//			int current = excludedColumn[l];
	//			if (excludedColumn[l] != 0) {
	//				continue;
	//			}
	//			else {
	//				excludedColumn[l] = number;
	//				System.out.println(String.format("COLUMN excl arr = %d at index %d", number, l));
	//				break;
	//			}
	//		}
	//		return true;
	//	}
	//}

	void printGrid() {
		for (int m = 0; m < 9; m++) {
			for (int n = 0; n < 9; n++) {
				System.out.print(grid[m][n] + " ");
			}
			System.out.print("\n");
		}
	}
}
