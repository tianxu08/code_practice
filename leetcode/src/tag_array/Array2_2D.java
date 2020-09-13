

package tag_array;

import ds.*;

import java.util.*;
public class Array2_2D {
    public static void main(String[] args) {
//        test2();
//        test4();

    }

    /**
     * 2D array
     *
     * 1 Spiral Order Traverse
     * 2 Spiral Order Generate
     * 3 Rotate Matrix
     * 4 Set Matrix Zeroes
     * 5 Magic square => in Array5_Magic_Square
     *
     */

    /**
     * task 1
     * Traverse an M * N 2D array in spiral order clock-wise starting from the top left corner. Return the list of traversal sequence.
     * Assumptions
     *
     * The 2D array is not null and has size of M * N where M >= 0 && N >= 0
     * Examples
     * { {1,  2,  3},
     *   {4,  5,  6},
     *   {7,  8,  9} }
     * the traversal sequence is [1, 2, 3, 6, 9, 8, 7, 4, 5]
     */

    // lb = 0, rb = cLen - 1
    // tb = 0, bb = rLen - 1
    // left -> right j = lb, j => rb, tb++
    // up -> down i = ub, i=> bb, rb--
    // right -> left j = rb, j => lb, bb--
    // down -> up i = bb, i => tb, lb ++

    public static void test1() {
        int[][] matrix = {
                {1,  2,  3},
                {4,  5,  6},
                {7,  8,  9}
        };
        List<Integer> res = t1_clockwiseOrder(matrix);
        System.out.println(res);
    }
    public static List<Integer> t1_clockwiseOrder(int[][] matrix) {
        int rLen = matrix.length;
        int cLen = matrix[0].length;
        int leftB = 0, rightB = cLen - 1, topB = 0, bottomB = rLen - 1;
        ArrayList<Integer> list = new ArrayList<>();
        while (leftB <= rightB && topB <= bottomB) {
            // left -> right
            for (int j = leftB; j <= rightB; j++) {
                list.add(matrix[topB][j]);
            }
            topB ++;
            // top -> bottom
            for (int i = topB; i <= bottomB; i++) {
                list.add(matrix[i][rightB]);
            }
            rightB --;
            // right -> left
            for (int j = rightB; j >= leftB; j--) {
                list.add(matrix[bottomB][j]);
            }
            bottomB --;
            // bottom -> top
            for (int i = bottomB; i >= topB; i--) {
                list.add(matrix[i][leftB]);
            }
            leftB ++;
        }
        return list;
    }

    public static void test2() {
        int M = 1, N = 4;
        int[][] res = t2_spiralGenerate(M, N);
        Debug.printMatrix(res);
    }
    public static int[][] t2_spiralGenerate(int M, int N) {
        int[][] matrix = new int[M][N];
        int leftB = 0, rightB = N - 1, topB = 0, bottomB = M - 1;
        int element = 1;
        while (leftB <= rightB && topB <= bottomB) {
            // left -> right
            for (int j = leftB; j <= rightB; j++) {
                System.out.println("1 element = " + element);
                matrix[topB][j] = element;
                element++;
            }
            topB ++;

            // top -> bottom
            for (int i = topB; i <= bottomB; i++) {
                System.out.println("2 element = " + element);
                matrix[i][rightB] = element++;
            }
            rightB --;

            if (leftB <= rightB && topB <= bottomB) {
                // right -> left
                for (int j = rightB; j >= leftB; j--) {
                    System.out.println("3 element = " + element);
                    matrix[bottomB][j] = element;
                    element++;
                }
                bottomB --;
            }

            if (topB <= bottomB && leftB <= rightB) {
                // bottom -> top
                for (int i = bottomB; i >= topB; i--) {
                    System.out.println("4 element = " + element);
                    matrix[i][leftB] = element;
                    element++;
                }
                leftB ++;
            }
        }
        return matrix;
    }

    /**
     * Given a m x n matrix, if an element is 0, set its entire row and column to 0.
     *
     * E.g.    Input: Matrix =  [
     *
     *                                      [1, 1, 1, 1, 0],
     *
     *                                      [0, 1, 1, 0, 1],
     *
     *                                      [1, 1, 1, 0, 1],
     *
     *                                      [1, 1, 1, 1, 1]
     *
     *                                  ]
     *
     * Output: Matrix = [
     *
     *                              [0, 0, 0, 0, 0],
     *
     *                              [0, 0, 0, 0, 0],
     *
     *                              [0, 0, 0, 0, 0],
     *
     *                              [0, 1, 1, 0, 0],
     *
     *                      ]
     */

    public static  void test4() {
        int[][] matrix = {
                {1, 1, 1, 1, 0},
                {0, 1, 1, 0, 1},
                {1, 1, 1, 0, 1},
                {1, 1, 1, 1, 1}
        };
        t4_setZero(matrix);
    }
    public static void t4_setZero(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return ;
        }

        int rL = matrix.length;
        int cL = matrix[0].length;

        boolean[] rPos = new boolean[rL];
        boolean[] cPos = new boolean[cL];

        for (int i = 0; i < rL; i++) {
            for (int j = 0; j < cL; j++) {
                if (matrix[i][j] == 0) {
                    rPos[i] = true;
                    cPos[j] = true;
                }
            }
        }

        for (int i = 0; i < rL; i++) {
            if (rPos[i]) {
                for (int j = 0; j < cL; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j = 0; j < cL; j++) {
            if (cPos[j]) {
                for (int i = 0; i < rL; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        Debug.printMatrix(matrix);
    }



}