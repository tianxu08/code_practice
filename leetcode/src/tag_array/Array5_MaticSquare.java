package tag_array;

import ds.Debug;

public class Array5_MaticSquare {
    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    /**
     * Magic square:
     *
     *
     *
     * Odd number magic square:
     * In any magic square, the first number i.e.
     * 1 is stored at position (n/2, n-1).
     * Let this position be (i,j).
     * The next number is stored at position (i-1, j+1)
     * where we can consider each row & column as circular array i.e. they wrap around.
     *
     *
     * Three conditions hold:
     * 1. The position of next number
     *    i = i - 1, j = j + 1 use the wrap.
     *
     * 2. If the magic square already contains a number at the calculated position,
     *    i = i + 1, j = j - 2;
     *
     * 3. If the calculated row position is -1 & calculated column position is n, the new position would be: (0, n-2).
     *    (-1, n) => (0, n - 2) reduce to 2
     *
     * Example:
     * Magic Square of size 3
     * ----------------------
     *  2  7  6
     *  9  5  1
     *  4  3  8
     *
     * Steps:
     * 1. position of number 1 = (3/2, 3-1) = (1, 2)
     * 2. position of number 2 = (1-1, 2+1) = (0, 0)
     * 3. position of number 3 = (0-1, 0+1) = (3-1, 1) = (2, 1)
     * 4. position of number 4 = (2-1, 1+1) = (1, 2)
     *    Since, at this position, 1 is there. So, apply condition 2.
     *    new position=(1+1,2-2)=(2,0)
     * 5. position of number 5=(2-1,0+1)=(1,1)
     * 6. position of number 6=(1-1,1+1)=(0,2)
     * 7. position of number 7 = (0-1, 2+1) = (-1,3) // this is tricky, see condition 3
     *    new position = (0, 3-2) = (0,1)
     * 8. position of number 8=(0-1,1+1)=(-1,2)=(2,2) //wrap around
     * 9. position of number 9=(2-1,2+1)=(1,3)=(1,0) //wrap around
     *
     *
     */

    public static void test1() {
        int n = 3;
        int[][] matrix = t1_magicSquare_Odd(n);
        Debug.printMatrix(matrix);
        boolean isMagicSquare = t0_isMagicSquare(matrix);
        System.out.println("test = " + isMagicSquare);
    }
    public static int[][] t1_magicSquare_Odd(int n) {
        int[][] magicSquare = new int[n][n];
        int i = n / 2;
        int j = n - 1;

        int num = 1;
        while (num <= n * n) {
            // wrap the i and j, make sure that they are in bound
            if (i == -1 && j == n) {
                // 3rd condition
                i = 0;
                j = n - 2;
            } else {
                if (i == -1) {
                    i = n - 1;
                }
                if (j == n) {
                    j = 0;
                }
            }

            if (magicSquare[i][j] != 0) {
                // 2nd condition
                i = i + 1;
                j = j - 2;
            } else {
                // 1st condition
                // set the matrix
                magicSquare[i][j] = num;
                num ++;
                i = i - 1;
                j = j + 1;
            }
        }
        return magicSquare;
    }

    /**
     *
     *
     * define an 2-D array of order n*n
     *     // fill array with their index-counting
     *     // starting from 1
     *     for ( i = 0; i<n; i++)
     *     {
     *         for ( j = 0; j<n; j++)
     *             // filling array with its count value
     *             // starting from 1;
     *             arr[i][j] = (n*i) + j + 1;
     *     }
     *
     *     // change value of Array elements
     *     // at fix location as per rule
     *     // (n*n+1)-arr[i][j]
     *     // Top Left corner of Matrix
     *    // (order (n/4)*(n/4))
     *     for ( i = 0; i<n/4; i++)
     *     {
     *         for ( j = 0; j<n/4; j++)
     *             arr[i][j] = (n*n + 1) - arr[i][j];
     *     }
     *
     *     // Top Right corner of Matrix
     *     // (order (n/4)*(n/4))
     *     for ( i = 0; i< n/4; i++)
     *     {
     *         for ( j = 3* (n/4); j<n; j++)
     *             arr[i][j] = (n*n + 1) - arr[i][j];
     *     }
     *
     *     // Bottom Left corner of Matrix
     *     // (order (n/4)*(n/4))
     *     for ( i = 3* n/4; i<n; i++)
     *     {
     *         for ( j = 0; j<n/4; j++)
     *             arr[i][j] = (n*n + 1) - arr[i][j];
     *     }
     *
     *     // Bottom Right corner of Matrix
     *    // (order (n/4)*(n/4))
     *     for ( i = 3* n/4; i<n; i++)
     *     {
     *         for ( j = 3* n/4; j<n; j++)
     *             arr[i][j] = (n*n + 1) - arr[i][j];
     *     }
     *
     *     // Centre of Matrix (order (n/2)*(n/2))
     *     for ( i = n/4; i<3* n/4; i++)
     *     {
     *         for ( j = n/4; j<3* n/4; j++)
     *             arr[i][j] = (n*n + 1) - arr[i][j];
     *     }
     */

    public static void test2() {
        int n = 4;
        int[][] matrix = t2_magicSquare_DoublyEven(n);
        Debug.printMatrix(matrix);
    }
    // Function for calculating Magic square
    public static int[][] t2_magicSquare_DoublyEven(int n)
    {
        int[][] magicSquare = new int[n][n];
        int i, j;

        // filling matrix with its count value
        // starting from 1;
        for ( i = 0; i < n; i++) {
            for (j = 0; j < n; j++)
                magicSquare[i][j] = (n * i) + j + 1;
        }

        System.out.println("1 >>>>>>>>>>>>>>>>>>>");
        Debug.printMatrix(magicSquare);

        // change value of Array elements at fix location as per rule
        // (n*n+1)-arr[i][j]
        // Top Left corner of Matrix
        // (order (n/4)*(n/4))
        for ( i = 0; i < n/4; i++) {
            for (j = 0; j < n / 4; j++) {
                System.out.println("i = " + i + " j = " + j);
                magicSquare[i][j] = (n * n + 1) - magicSquare[i][j];
            }
        }

        System.out.println("2 >>>>>>>>>>>>>>>>>>>");
        Debug.printMatrix(magicSquare);
        // Top Right corner of Matrix
        // (order (n/4)*(n/4))
        for ( i = 0; i < n/4; i++)
            for ( j = 3 * (n/4); j < n; j++)
                magicSquare[i][j] = (n*n + 1) - magicSquare[i][j];

        System.out.println("3 >>>>>>>>>>>>>>>>>>>");
        Debug.printMatrix(magicSquare);

        // Bottom Left corner of Matrix
        // (order (n/4)*(n/4))
        for ( i = 3 * n/4; i < n; i++)
            for ( j = 0; j < n/4; j++)
                magicSquare[i][j] = (n*n+1) - magicSquare[i][j];
        System.out.println("4 >>>>>>>>>>>>>>>>>>>");
        Debug.printMatrix(magicSquare);


        // Bottom Right corner of Matrix
        // (order (n/4)*(n/4))
        for ( i = 3 * n/4; i < n; i++)
            for ( j = 3 * n/4; j < n; j++)
                magicSquare[i][j] = (n*n + 1) - magicSquare[i][j];

        System.out.println("5 >>>>>>>>>>>>>>>>>>>");
        Debug.printMatrix(magicSquare);


        // Centre of Matrix (order (n/2)*(n/2))
        for ( i = n/4; i < 3 * n/4; i++)
            for ( j = n/4; j < 3 * n/4; j++)
                magicSquare[i][j] = (n*n + 1) - magicSquare[i][j];
        System.out.println("6 >>>>>>>>>>>>>>>>>>>");
        Debug.printMatrix(magicSquare);


        return magicSquare;
    }


    // https://rosettacode.org/wiki/Magic_squares_of_singly_even_order#Java


    public static void test3() {
        int n = 6;
        int[][] matrix = t3_magicSquareSinglyEven(n);
        Debug.printMatrix(matrix);
    }


    public static int[][] t3_magicSquareSinglyEven(int n) {
        if (n < 6 || (n - 2) % 4 != 0)
            throw new IllegalArgumentException("base must be a positive "
                    + "multiple of 4 plus 2");

        int size = n * n;
        int halfN = n / 2;
        int subSquareSize = size / 4;

        System.out.println("!!! subSquareSize: " + subSquareSize);

        int[][] subSquare = t1_magicSquare_Odd(halfN);
        System.out.println("------------------");
        Debug.printMatrix(subSquare);
        int[] quadrantFactors = {0, 2, 3, 1};
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int quadrant = (i / halfN) * 2 + (j / halfN);
                // how to calculate the quadrant ???
//                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
//                System.out.println("i = " + i + " : " + "j = " + j);
//                System.out.println("!!!!!!!! quadrant: " + quadrant);
//                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
                result[i][j] = subSquare[i % halfN][j % halfN];
                result[i][j] += quadrantFactors[quadrant] * subSquareSize;
            }
        }

        System.out.println(">>>>>>>>>>>>>>>>>>");
        Debug.printMatrix(result);

        int nColsLeft = halfN / 2;
        int nColsRight = nColsLeft - 1;

        System.out.println(">>> " + nColsLeft);
        System.out.println("<<< " + nColsRight);
        System.out.println("### " + halfN);

        for (int i = 0; i < halfN; i++) {
            for (int j = 0; j < n; j++) {
                if (j < nColsLeft || j >= n - nColsRight
                        || (j == nColsLeft && i == nColsLeft)) {
                    if (j == 0 && i == nColsLeft)
                        continue;

                    int tmp = result[i][j];
                    result[i][j] = result[i + halfN][j];
                    result[i + halfN][j] = tmp;
                }
            }
        }

        return result;
    }


    public static boolean t0_isMagicSquare(int[][] matrix) {
        int n = matrix.length;
        int lineSum = (n * n + 1) * n / 2;
        int diagonalSum = 0;
        int antiDiagonalSum = 0;

        for (int i = 0; i < n; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += matrix[i][j];
                colSum += matrix[j][i];
                if (i == j) {
                    diagonalSum += matrix[i][j];
                }
                if (i + j == n - 1) {
                    antiDiagonalSum += matrix[i][j];
                }
            }
            if (rowSum != lineSum) {
                return false;
            }
            if (colSum != lineSum) {
                return false;
            }
        }
        if (antiDiagonalSum != lineSum || diagonalSum != lineSum) {
            return false;
        }
        return true;
    }
}
