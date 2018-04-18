package leetCode;

public class RotateMatrix {
    public void rotate(int[][] matrix) {
        int N = matrix.length;

        int[] buffer = new int[N]; // used to buffer top row

        // peeling onion
        for (int layer = 0; layer < N/2; layer++) {
            // buffer top
            int first = layer;
            int last = N-1-layer;

            for (int i = first; i <= last; i++ ) {
                buffer[i] = matrix[layer][i];
            }

            // 1. left => top (left will be empty after removal)
            // [x, layer] => [layer, N-1-x]
            // x range from first to last. Should start from "first" (the one where left and top intersect)
            for(int x = first; x <= last; x++) {
                matrix[layer][N-1-x] = matrix[x][layer];
            }

            // bottom => left (bottom will be empty after removal)
            // [N-1-layer, y] => [y, layer]
            // y range from first to last. Should start from "first" (the one where left and top intersect)
            for(int y = first; y <= last; y++) {
                matrix[y][layer] = matrix[N-1-layer][y];
            }

            // right => bottom (right will be empty after removal)
            // [x, N-1-layer] -> [N-1-layer, N-1-x]
            // x range from last to first.  Should always start moving the element that intersects, otherwise, it will be overriden.
            for(int x = last; x >= first; x--) {
                matrix[N-1-layer][N-1-x] = matrix[x][N-1-layer];
            }

            // buffered top => right
            for(int y = first; y <= last; y++) {
                matrix[y][N-1-layer] = buffer[y];
            }
        }
    }

    public void rotate2(int[][] matrix) {
        int N = matrix.length;

        int[] buffer = new int[N]; // used to buffer top row

        // peeling onion
        for (int layer = 0; layer < N/2; layer++) {

            int first = layer;
            int last = N-1-layer;

            for(int i = first; i <= last; i++) {

                // buffer top
                buffer[i] = matrix[layer][i];

                // 1. left => top (left will be empty after removal)
                // [i, layer] => [layer, N-1-i]
                // Should start from removing "matrix[first][layer]" to top. (the one where left and top intersect)
                matrix[layer][N-1-i] = matrix[i][layer];
                System.out.println("left to top");
                display(matrix);

                // bottom => left (bottom will be empty after removal)
                // [N-1-layer, i] => [i, layer]
                // Should start from removing "matrix[N-1-layer][first]" to left (the one where left and top intersect)
                matrix[i][layer] = matrix[N-1-layer][i];
                System.out.println("bottom to left");
                display(matrix);

                // right => bottom (right will be empty after removal)
                // [N-1-x, N-1-layer] -> [x, N-1-x]
                // Should always start removing "matrix[last][N-1-layer]", otherwise, it will be overriden.
                matrix[N-1-layer][i] = matrix[N-1-i][N-1-layer];
                System.out.println("right to bottom");
                display(matrix);

                // buffered top => right
                matrix[i][N-1-layer] = buffer[i];
                System.out.println("top to right");
                display(matrix);
            }

        }
    }

    public void display(int[][] matrix) {
        for(int i = 0; i<matrix.length; i++) {
            for(int j = 0; j< matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println(" ");
        }
    }

    public static void main(String[] args) {
        RotateMatrix myclass = new RotateMatrix();
        int[][] matrix = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};
        myclass.rotate(matrix);
//        myclass.display(matrix);
    }
}

