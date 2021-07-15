package com.telerikacademy.core;

public class GameOfLife {
    public static void main(String[] args) {
        String input = "000111010";

        // Generation #0
        int generationNumber = 0;
        int[] generation0 = convertToArray(input);
        printGeneration(generation0, generationNumber);

        // Generation #1 Test
        generationNumber++;
        int[] generation1 = getNextGeneration(generation0);
        printGeneration(generation1, generationNumber);
        validateGeneration(generation1, generationNumber, "010111111");

        // Generation #2 Test
        generationNumber++;
        int[] generation2 = getNextGeneration(generation1);
        printGeneration(generation2, generationNumber);
        validateGeneration(generation2, generationNumber, "111000101");

        // Generation #3 Test
        generationNumber++;
        int[] generation3 = getNextGeneration(generation2);
        printGeneration(generation3, generationNumber);
        validateGeneration(generation3, generationNumber, "010101000");

        // Generation #4 Test
        generationNumber++;
        int[] generation4 = getNextGeneration(generation3);
        printGeneration(generation4, generationNumber);
        validateGeneration(generation4, generationNumber, "010010000");

        // Generation #5 Test
        generationNumber++;
        int[] generation5 = getNextGeneration(generation4);
        String generation5Str = convertToString(generation5);
        printGeneration(generation5Str, generationNumber);
        validateGeneration(generation5, generationNumber, "000000000");

        //--------------------------------------------------//
//        String str = "111000101";
//        convertToArray(str);
//        int[] arr_toString = new int[] {1,2,3,4,5};
//        int[][] matrix = new int[][] {{1,2,3}, {4,5,6}, {7,8,9}};
//        convertToString(arr_toString);
//        convertToArray(matrix);
//        int[] arr_toMatrix = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
//        convertToMatrix(arr_toMatrix);
//        printGeneration(str, 1);
//        int[] test = new int[] {1,2,3,4,5,6,7,8,9};
//        int[][] matrix_one = new int[][] {{0,0,0},{1,1,1},{0,1,0}};
//        int[][] matrix_two = new int[][] {{0,1,0},{1,1,0},{1,1,1}};
//        System.out.println(getAliveNeighboursCount(1,1,matrix_one));//3
      //  System.out.println();
        int[][] matrix_one = new int[][] {{0,0,0},
                {1,1,1},
                {0,1,0}};
        int[][] matrix_two = new int[][] {{0,1,0},
                {1,1,0},
                {1,1,1}};
        //3
       // System.out.println(getAliveNeighboursCount(1,1,matrix_one));
        //5
       // System.out.println(getAliveNeighboursCount(1,1,matrix_two));

    }

    static int[] mutate(int[][] matrix) {

        int count_ones = 0;
        int[] res = new int[matrix[0].length* matrix[1].length];
        int counter = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[1].length; j++) {
                if (i - 1 >= 0) {
                    if (matrix[i - 1][j] == 1) {
                        count_ones++;
                    }
                }
                if (i - 1 >= 0 && j + 1 < matrix[1].length) {
                    if (matrix[i - 1][j + 1] == 1) {
                        count_ones++;
                    }
                }
                if (j + 1 < matrix[1].length) {
                    if (matrix[i][j + 1] == 1) {
                        count_ones++;
                    }
                }
                if (j + 1 < matrix[1].length && i + 1 < matrix[0].length) {
                    if (matrix[i + 1][j + 1] == 1) {
                        count_ones++;
                    }
                }
                if (i + 1 < matrix[0].length) {
                    if (matrix[i + 1][j] == 1) {
                        count_ones++;
                    }
                }
                if (j - 1 >= 0 && i + 1 < matrix[0].length) {
                    if (matrix[i + 1][j - 1] == 1) {
                        count_ones++;
                    }
                }
                if (j - 1 >= 0) {
                    if (matrix[i][j - 1] == 1) {
                        count_ones++;
                    }
                }
                if (j - 1 >= 0 && i - 1 >= 0) {
                    if (matrix[i - 1][j - 1] == 1) {
                        count_ones++;
                    }
                }
                if (matrix[i][j] == 0 && count_ones == 3) {
                    res[counter] = 1;
                } else if (matrix[i][j] == 1 && count_ones == 0 || count_ones == 1) {
                    res[counter] = 0;
                } else if (matrix[i][j] == 1 && count_ones == 2 || count_ones == 3) {
                    res[counter] = 1;
                } else if (matrix[i][j] == 1 && count_ones > 3) {
                    res[counter] = 0;
                } else {
                    res[counter] = matrix[i][j];

                }
                counter++;
                count_ones = 0;
            }
        }
        return res;
    }
    /**
     * This method converts a String into an array of digits.<br />
     * For example "111000101" will become {1,1,1,0,0,0,1,0,1}
     *
     * @param str The input String
     * @return A one dimensional array of integers.
     */
    static int[] convertToArray(String str) {
        int[] arr = new int[str.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(str.split("")[i]);
        }
        return arr;
        //throw new UnsupportedOperationException("Converting String to integer array is not implemented!");
    }

    /**
     * This method converts a one dimensional array of integers into a String.<br/>
     * For example {1,1,1,0,0,0,1,0,1} will become "111000101"
     *
     * @param array A one-dimensional array of integers.
     * @return String
     */
    static String convertToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(String.valueOf(array[i]));
        }
        System.out.println(sb);
        return sb.toString();
        //throw new UnsupportedOperationException("Converting integer array to String is not implemented!");
    }

    /**
     * This method converts a two-dimensional array of integers into a one-dimensional array of integers.
     * For example:
     * 1 1 1
     * 0 0 0
     * 1 0 1
     * will become {1,1,1,0,0,0,1,0,1}
     *
     * @param matrix A two-dimensional array of integers.
     * @return A one-dimensional array of integers.
     */
    static int[] convertToArray(int[][] matrix) {
        int rows = matrix[0].length;
        int cols = matrix[1].length;
        int[] arr = new int[rows*cols];
        int counter = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                arr[counter] = matrix[i][j];
                counter++;
            }
        }
        return arr;
        //throw new UnsupportedOperationException("Converting two-dimensional to one-dimensional array of integers is not implemented.");
    }

    /**
     * This method converts a one-dimensional array of integers into a two-dimensional array of integers.
     * For example {1,1,1,0,0,0,1,0,1} will become:
     * 1 1 1
     * 0 0 0
     * 1 0 1
     *
     * @param array A one-dimensional array of integers.
     * @return A two-dimensional array of integers.
     */
    static int[][] convertToMatrix(int[] array) {
        boolean isSize = false;
        int counter = 1;
        while(!isSize) {
            if(array.length / counter == counter) {
                isSize = true;
                break;
            }
            counter++;
        }
        //System.out.println(counter);
        int index = 0;
        int[][] matrix = new int[counter][counter];
        for (int i = 0; i < counter; i++) {
            for (int j = 0; j < counter; j++) {
                matrix[i][j] = array[index];
                index++;
            }
        }
        return matrix;
        //throw new UnsupportedOperationException("Converting one-dimensional to two-dimensional array of integers is not implemented.");
    }

    /**
     * This method prints the two-dimensional version of a one-dimensional array.
     * For example, "111000101" will be displayed in the Console as:
     * Generation #1
     * 1 1 1
     * 0 0 0
     * 1 0 1
     *
     * @param generation       The String representation of the generation of cells.
     * @param generationNumber The number of the generation.
     */
    static void printGeneration(String generation, int generationNumber) {
        int[][] matrix = convertToMatrix(convertToArray(generation));
        System.out.printf("Generation #%d\n", generationNumber);
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[1].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        //throw new UnsupportedOperationException("Printing the String representation of a generation is not implemented.");
    }

    /**
     * This method prints the two-dimensional version of a one-dimensional array.
     * For example, {1,1,1,0,0,0,1,0,1} will be displayed in the Console as:
     * Generation #1
     * 1 1 1
     * 0 0 0
     * 1 0 1
     *
     * @param generation       A two-dimensional array representing the generation of cells to mutate.
     * @param generationNumber The number of the generation.
     */
    static void printGeneration(int[] generation, int generationNumber) {
        int[][] matrix = convertToMatrix(generation);
        System.out.printf("Generation #%d\n", generationNumber);
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[1].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        //throw new UnsupportedOperationException("Printing one-dimensional array representation of a generation is not implemented.");
    }

    /**
     * This method takes a one-dimensional array representation of a generation and mutates (transforms) it into a one-dimensional array representation of the next generation.
     *
     * @param generation A one-dimensional array representing the generation of cells to mutate.
     * @return A one-dimensional array representing the generation of cells after mutation.
     */
    static int[] getNextGeneration(int[] generation) {
        return mutate( convertToMatrix(generation));
        //throw new UnsupportedOperationException("Mutating one-dimensional generation is not imlpemented.");
    }

    /**
     * This method takes a two-dimensional array representation of a generation and mutates (transforms) it into a two-dimensional array representation of the next generation.
     *
     * @param generation A two-dimensional array representing the generation of cells to mutate.
     * @return A two-dimensional array representing the generation of cells after mutation.
     */
    static int[][] getNextGeneration(int[][] generation) {
        return convertToMatrix(mutate(generation));
        //throw new UnsupportedOperationException("Mutating two-dimensional generation is not imlpemented.");
    }

    /**
     * This method applies the rules of "Game of Life" and returns whether the cell at position [row,column]
     * will be alive in the next generation.
     * Rules of "Game of Life":
     * 1. Any live cell with 0 or 1 live neighbors becomes dead, because of underpopulation.
     * 2. Any live cell with 2 or 3 live neighbors stays alive, because its neighborhood is just right.
     * 3. Any live cell with more than 3 live neighbors becomes dead, because of overpopulation.
     * 4. Any dead cell with exactly 3 live neighbors becomes alive, by reproduction.
     *
     * @param row        The row position of the cell on which the rules are being applied on.
     * @param column     The column position of the cell on which the rules are being applied on.
     * @param generation The two-dimensional array representing the entire generation of cells.
     * @return 0 if the cell will be dead in the next generation; <br/> 1 if the cell will be alive in the next generation;
     */
    static int applyGameRules(int row, int column, int[][] generation) {
        convertToMatrix(mutate(generation));
        return generation[row][column];
        //  throw new UnsupportedOperationException("Applying the rules of 'Game of Life' on a cell is not implemented.");
    }

    /**
     * Given the position of a cell within the generation this method returns the number of alive neighbours.
     *
     * @param row        The row position of the cell on which the rules are being applied on.
     * @param column     The column position of the cell on which the rules are being applied on.
     * @param generation The two-dimensional array representing the entire generation of cells.
     * @return The number of alive neighbours.
     */
    static int getAliveNeighboursCount(int row, int column, int[][] generation) {
        int count_ones = 0;
        int neighbours = 0;
        for (int i = 0; i < generation[0].length; i++) {
            for (int j = 0; j < generation[1].length; j++) {
                if(i == row && j == column){

                    if (i - 1 >= 0) {
                        if (generation[i - 1][j] == 1) {
                            count_ones++;
                        }
                    }
                    if (i - 1 >= 0 && j + 1 < generation[1].length) {
                        if (generation[i - 1][j + 1] == 1) {
                            count_ones++;
                        }
                    }
                    if (j + 1 < generation[1].length) {
                        if (generation[i][j + 1] == 1) {
                            count_ones++;
                        }
                    }
                    if (j + 1 < generation[1].length && i + 1 < generation[0].length) {
                        if (generation[i + 1][j + 1] == 1) {
                            count_ones++;
                        }
                    }
                    if (i + 1 < generation[0].length) {
                        if (generation[i + 1][j] == 1) {
                            count_ones++;
                        }
                    }
                    if (j - 1 >= 0 && i + 1 < generation[0].length) {
                        if (generation[i + 1][j - 1] == 1) {
                            count_ones++;
                        }
                    }
                    if (j - 1 >= 0) {
                        if (generation[i][j - 1] == 1) {
                            count_ones++;
                        }
                    }
                    if (j - 1 >= 0 && i - 1 >= 0) {
                        if (generation[i - 1][j - 1] == 1) {
                            count_ones++;
                        }
                    }
                    neighbours = count_ones;

                }

                count_ones = 0;

            }
        }
        return neighbours;
        //throw new UnsupportedOperationException("Calculating the number of alive neighbouring cells is not implemented.");
    }

    static void validateGeneration(int[] generation, int generationNumber, String expectedGeneration) {
        String generationStr = convertToString(generation);
        String testResult = "PASS";
        if (!generationStr.equals(expectedGeneration)) {
            testResult = "FAIL";
        }
        System.out.printf("Generation #%d Test: %s%n", generationNumber, testResult);
    }
}