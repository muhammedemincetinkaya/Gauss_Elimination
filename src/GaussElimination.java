import java.util.Scanner;
public class GaussElimination {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of equations:");                 //We need to know the number of equation to initiate the arrays that will hold the matrix,
        int n = scanner.nextInt();                                          //because we need to know the size of the array(thus the size of the arrays).

        double[][] matrix = new double[n][n + 1];                           //Initiate the arrays that will hold the matrix.The matrix is [n][n + 1] in size because
        System.out.println("Enter the matrix");                             //it will also hold the right-hand side of the equations.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                matrix[i][j] = scanner.nextDouble();                        //Place the contents of the matrix to the array.
            }
        }

        for (int i = 0; i < n; i++) {
            double max = Math.abs(matrix[i][i]);
            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(matrix[k][i]) > max) {
                    max = Math.abs(matrix[k][i]);
                    maxRow = k;
                }
            }

            double[] temp = matrix[maxRow];
            matrix[maxRow] = matrix[i];
            matrix[i] = temp;

            for (int k = i + 1; k < n; k++) {
                double c = -matrix[k][i] / matrix[i][i];
                for (int j = i; j <= n; j++) {
                    if (i == j) {
                        matrix[k][j] = 0;
                    } else {
                        matrix[k][j] += c * matrix[i][j];
                    }
                }
            }
        }


        double[] solution = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            solution[i] = matrix[i][n] / matrix[i][i];
            for (int k = i - 1; k >= 0; k--) {
                matrix[k][n] -= matrix[k][i] * solution[i];
            }
        }


        System.out.println("Solution:");
        for (int i = 0; i < n; i++) {
            System.out.printf("x%d = %.2f\n", i + 1, solution[i]);
        }

        scanner.close();
    }
}