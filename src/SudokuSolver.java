public class SudokuSolver {

    public static final int GRID_SIZE = 9;

    public static void main(String[] args) {


        int[][] board = {
                {0, 0, 9, 0, 6, 0, 0, 0, 0},
                {0, 0, 0, 3, 0, 0, 0, 1, 0},
                {0, 4, 5, 0, 1, 0, 0, 0, 6},
                {0, 0, 0, 0, 0, 8, 2, 0, 0},
                {0, 6, 1, 0, 3, 0, 0, 0, 5},
                {7, 0, 0, 0, 0, 0, 0, 0, 0},
                {9, 0, 0, 0, 4, 0, 0, 0, 0},
                {0, 7, 4, 2, 0, 0, 5, 0, 0},
                {3, 0, 0, 0, 0, 0, 0, 0, 7}
        };

        if (solveSudoku(board)) {
            System.out.println("Solved!\n");
        } else {
            System.out.println("Unsolvable");
        }

        printBoard(board);
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if(row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int col = 0; col <GRID_SIZE; col++) {
                if(col % 3 == 0 && col != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }

    private static boolean isNumberInRow(int[][] board, int number, int row) {

        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInCol(int[][] board, int number, int col) {

        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(int[][] board, int number, int row, int col) {

        int boxRow = row - row % 3;
        int boxCol = col - col % 3;

        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPlacement(int[][] board, int number, int row, int col) {
        return !isNumberInRow(board, number, row) &&
                !isNumberInCol(board, number, col) &&
                !isNumberInBox(board, number, row, col);
    }

    private static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int number = 1; number <= GRID_SIZE; number++) {
                        if (isValidPlacement(board, number, row, col)) {
                            board[row][col] = number;

                            if (solveSudoku(board)) {
                                return true;
                            } else {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
