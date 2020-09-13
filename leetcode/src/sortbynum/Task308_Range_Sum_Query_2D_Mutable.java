package sortbynum;



public class Task308_Range_Sum_Query_2D_Mutable {
    QuadTreeNode root;
    public Task308_Range_Sum_Query_2D_Mutable(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
        root = new QuadTreeNode(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1);
    }

    public void update(int row, int col, int val) {
        root.update(row, col, val);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return root.sumRegion(row1, col1, row2, col2);
    }
}


class QuadTreeNode {
    int x1;
    int y1;
    int x2;
    int y2;
    int midx; // end of upperLeft
    int midy; // end of upperLeft
    QuadTreeNode upperLeft;
    QuadTreeNode upperRight;
    QuadTreeNode bottomLeft;
    QuadTreeNode bottomRight;
    int sum;

    public QuadTreeNode(int[][] matrix, int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        midx = x1 + (x2 - x1) / 2;
        midy = y1 + (y2 - y1) / 2;
        if(x1 == x2 && y1 == y2) {
            sum = matrix[x1][y1];
        } else {
            // constructor can not return null, so do the coordinate validation here
            upperLeft = new QuadTreeNode(matrix, x1, y1, midx, midy);
            sum = upperLeft.sum;
            if (midy + 1 <= y2) {
                upperRight = new QuadTreeNode(matrix, x1, midy + 1, midx, y2);
                sum += upperRight.sum;
            }
            if (midx + 1 <= x2) {
                bottomLeft = new QuadTreeNode(matrix, midx + 1, y1, x2, midy);
                sum += bottomLeft.sum;
            }
            if (midx + 1 <= x2 && midy + 1 <= y2) {
                bottomRight = new QuadTreeNode(matrix, midx + 1, midy + 1, x2, y2);
                sum += bottomRight.sum;
            }
        }
    }

    public void update(int row, int col, int val) {
        if (x1 > row || x2 < row) return;
        if (y1 > col || y2 < col) return;
        if (x1 == x2 && x1 == row && y1 == y2 && y1 == col) {
            sum = val;
        } else {
            //row, col is in range;
            if (row <= midx && col <= midy) {
                sum -= upperLeft.sum;
                upperLeft.update(row, col, val);
                sum += upperLeft.sum;
            } else if (row <= midx && col > midy) {
                sum -= upperRight.sum;
                upperRight.update(row, col, val);
                sum += upperRight.sum;
            } else if (row > midx && col <= midy) {
                sum -= bottomLeft.sum;
                bottomLeft.update(row, col, val);
                sum += bottomLeft.sum;
            } else {
                sum -= bottomRight.sum;
                bottomRight.update(row, col, val);
                sum += bottomRight.sum;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int newX1 = Math.max(row1, x1);
        int newX2 = Math.min(row2, x2);
        int newY1 = Math.max(col1, y1);
        int newY2 = Math.min(col2, y2);
        if (x1 == newX1 && x2 == newX2 && y1 == newY1 && y2 == newY2) {
            return sum;
        }
        int result = 0;
        if (newX1 <= midx && newY1 <= midy) {
            result += upperLeft.sumRegion(newX1, newY1, newX2, newY2);
        }
        if (newX1 <= midx && newY2 > midy) {
            result += upperRight.sumRegion(newX1, newY1, newX2, newY2);
        }
        if (newX2 > midx && newY1 <= midy) {
            result += bottomLeft.sumRegion(newX1, newY1, newX2, newY2);
        }
        if (newX2 > midx && newY2 > midy) {
            result += bottomRight.sumRegion(newX1, newY1, newX2, newY2);
        }
        return result;
    }
}
