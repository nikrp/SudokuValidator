import java.io.File;
import java.util.Scanner;

public class SudokuChecker {
    public static void main(String[] args) {
        String[] paths = {"src/SudokuBoard1.txt", "src/SudokuBoard2.txt", "src/SudokuBoard3.txt"};

        for (String path : paths) {
            result(path);
        }
    }

    public static void result(String filePath) {
        Scanner input;
        final String FILE = filePath;
        File file;
        String[][] rowsString = new String[9][9];
        int[][] rowsInts= new int[9][9];
        try {
            file = new File(FILE); input = new Scanner(file);
            int row = 0;
            while (input.hasNextLine()) {
                String line = input.nextLine();
                try {
                    rowsString[row] = line.split(",");
                    for (int i=0;i<9; i++)
                        rowsInts[row][i] = Integer.parseInt(rowsString[row][i]);
                    row++;
                } catch (ArrayIndexOutOfBoundsException aioobe) {

                }

            } input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        boolean valid = validator(rowsInts);

        if (valid) {
            System.out.print("\033[1;32m");
            printBoard(rowsInts);
            System.out.println("\033[0m");
        } else {
            System.out.print("\033[1;31m");
            printBoard(rowsInts);
            System.out.println("\033[0m");
        }
    }

    public static void printBoard (int[][] b) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col<9; col++) {
                System.out.print(b[row][col]);
                if (col<8)
                    System.out.print(", ");
            }
            System.out.println();
        }

    }

    public static boolean validator(int[][] rows) {
        boolean[] found = new boolean[9];

        for (int[] row : rows) {
            for (int j = 0; j < 9; j++) {
                if (found[row[j] - 1]) {
                    return false;
                }
                found[row[j] - 1] = true;
            }
            found = new boolean[9];
        }

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < 9; j++) {
                if (found[rows[j][i] - 1]) {
                    return false;
                }
                found[rows[j][i] - 1] = true;
            }
            found = new boolean[9];
        }

        for (int gridRow = 0; gridRow < 3; gridRow++) {
            for (int gridCol = 0; gridCol < 3; gridCol++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        int rowIndex = gridRow * 3 + i;
                        int colIndex = gridCol * 3 + j;
                        if (found[rows[rowIndex][colIndex] - 1]) {
                            return false;
                        }
                        found[rows[rowIndex][colIndex] - 1] = true;
                    }
                }
                found = new boolean[9];
            }
        }

        return true;
    }
}