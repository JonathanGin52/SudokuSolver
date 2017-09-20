/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jonathan
 */
public class SudokuSolver {

    public static int[][] grid;
    public static int boxLength;
    private static long iterations = 0;

    public static void main(String[] args) {
	Scanner keyboard = new Scanner(System.in);
	String puzzle;
	
	do {
	    puzzle = keyboard.nextLine();
	} while (!checkValidInput(puzzle));
	
	grid = parseGrid(puzzle);
	displayGrid(grid);
	long beginTime = System.currentTimeMillis();
	solveGrid(grid, nextCell(grid));
	if (isGridSolved(grid)) {
	    displayGrid(grid);	
	    System.out.println("Iterations: " + iterations);
	    System.out.println("Time taken: " + (System.currentTimeMillis() - beginTime) + " milliseconds");
	} else {
	    System.out.println("This puzzle has no solution");
	}

    }
    
    private static boolean checkValidInput(String puzzle) {
	double sqrt = Math.sqrt(Math.sqrt(puzzle.length()));
	int x = (int) sqrt;
	if (sqrt * sqrt == x * x) {
	    boxLength = x;
	    return true;
	}
	return false;
    }

    private static int[][] parseGrid(String input) {
	int[][] output = new int[boxLength * boxLength][boxLength * boxLength];
	int i = 0;
	for (int row = 0; row < output.length; row++) {
	    for (int col = 0; col < output[row].length; col++) {
		if (Character.isDigit(input.charAt(i))) {
		    output[row][col] = Character.getNumericValue(input.charAt(i++));
		} else {
		    output[row][col] = (int) input.charAt(i++) - 55;
		}
	    }
	}
	return output;
    }

    private static void displayGrid(int[][] input) {
	for (int i = 1; i < boxLength * boxLength + boxLength; i++) {
	    System.out.print("---");
	}
	System.out.println("---");
	for (int row = 0; row < input.length; row++) {
	    System.out.print("| ");
	    for (int col = 0; col < input[row].length; col++) {
		if (input[row][col] < 10) System.out.print(" ");
		System.out.print((input[row][col] == 0 ? "_" : input[row][col]) + " ");
		if ((col + 1) % boxLength == 0) {
		    System.out.print(" |");
		}
		System.out.print(" ");
	    }
	    if ((row + 1) % boxLength == 0) {
		System.out.println();
		for (int i = 1; i < boxLength; i++) {
		    System.out.print("----------------");
		}
		System.out.println("-");
	    } else {
		System.out.println("");
	    }
	}
	System.out.println("");
    }

    private static boolean isValid(int[][] activeGrid, int row, int col, int value) {
	for (int i = (int) (Math.floor(row / boxLength) * boxLength); i < Math.floor(row / boxLength) * boxLength + boxLength; i++) {
	    for (int j = (int) (Math.floor(col / boxLength) * boxLength); j < Math.floor(col / boxLength) * boxLength + boxLength; j++) {
		if (!(i == row && j == col) && activeGrid[i][j] == value) {
		    return false;
		}
	    }
	}
	for (int i = 0; i < activeGrid.length; i++) {
	    if (activeGrid[row][i] == value && i != col || activeGrid[i][col] == value && i != row) {
		return false;
	    }
	}
	return true;
    }

    private static ArrayList getPossibleMoves(int[][] activeGrid, int row, int col) {
	ArrayList<Integer> list = new ArrayList<>(boxLength * boxLength);
	for (int i = 1; i <= boxLength * boxLength; i++) {
	    if (isValid(activeGrid, row, col, i)) {
		list.add(i);
	    }
	}
	list.trimToSize();
	return list;
    }

    private static int[][] cloneGrid(int[][] board) {
	int[][] newGrid = new int[board.length][board[0].length];
	for (int i = 0; i < board.length; i++) {
	    int[] newState = board[i];
	    int newLength = newState.length;
	    newGrid[i] = new int[newLength];
	    System.arraycopy(newState, 0, newGrid[i], 0, newLength);
	}
	return newGrid;
    }

    private static int[][] placeNum(int[][] activeGrid, int row, int col, int num) {
	int[][] output = cloneGrid(activeGrid);
	output[row][col] = num;
	return output;
    }

    private static int[] nextCell(int[][] activeGrid) {
	for (int row = 0; row < activeGrid.length; row++) {
	    for (int col = 0; col < activeGrid[row].length; col++) {
		if (activeGrid[row][col] == 0) {
		    return new int[]{row, col};
		}
	    }
	}
	return new int[]{-1, -1};
    }

    private static boolean solveGrid(int[][] activeGrid, int[] cell) {
	iterations++;
	if (cell[0] == -1) {
	    grid = activeGrid;
	    return true;
	}
	ArrayList<Integer> options = getPossibleMoves(activeGrid, cell[0], cell[1]);
	for (int i = 0; i < options.size(); i++) {
	    if (solveGrid(placeNum(activeGrid, cell[0], cell[1], options.get(i)), nextCell(placeNum(activeGrid, cell[0], cell[1], options.get(i))))) {
		break;
	    }
	}
	return false; // no options
    }
    
    private static boolean isGridSolved(int[][] activeGrid) {
	for (int row = 0; row < activeGrid.length; row++) {
	    for (int col = 0; col < activeGrid.length; col++) {
		if (!isValid(activeGrid, row, col, activeGrid[row][col])) {
		    System.out.println(row + " " + col);
		    return false;
		}
	    }
	}
	return true;
    }
}
// 010020300004005060070000008006900070000100002030048000500006040000800106008000000
// 0004E00B0GA00000GFB02070000000003670GF00D00000C82001009007063D0BF9C0B10A8000000600E000000051C00FDA00000000000310B0080000C00G000E0D3CA509704000000000800E0FGA915000000CB0000D0A30080600070C0E002400000G060A000F4070003005E000G000000G00000810AC020B10F00000200009