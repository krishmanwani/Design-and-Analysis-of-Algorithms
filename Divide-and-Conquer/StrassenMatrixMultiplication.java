/* 
Strassen’s Matrix Multiplication is an efficient algorithm based on Divide and Conquer paradigm for multiplying two square matrices. 
It reduces the computational complexity compared to the standard matrix multiplication method. 
Complexity analysis->
1. Time Complexity: 
    Best Case- O(n^2.81)
    Average Case- O(n^2.81)
    Worst Case- O(n^2.81)
2. Space Complexity: 
    O(n^2)
*/ 

import java.util.*;

public class StrassenMatrixMultiplication {

    public static void add(int n, int[][] A, int[][] B, int[][] result) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                result[i][j] = A[i][j] + B[i][j];
    }

    public static void subtract(int n, int[][] A, int[][] B, int[][] result) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                result[i][j] = A[i][j] - B[i][j];
    }

    public static void strassen(int n, int[][] A, int[][] B, int[][] C) {
        if (n == 1) {
            C[0][0] = A[0][0] * B[0][0];
            return;
        }

        int n_half = n / 2;
        int[][] A11 = new int[n_half][n_half];
        int[][] A12 = new int[n_half][n_half];
        int[][] A21 = new int[n_half][n_half];
        int[][] A22 = new int[n_half][n_half];
        int[][] B11 = new int[n_half][n_half];
        int[][] B12 = new int[n_half][n_half];
        int[][] B21 = new int[n_half][n_half];
        int[][] B22 = new int[n_half][n_half];
        int[][] C11 = new int[n_half][n_half];
        int[][] C12 = new int[n_half][n_half];
        int[][] C21 = new int[n_half][n_half];
        int[][] C22 = new int[n_half][n_half];
        int[][] M1 = new int[n_half][n_half];
        int[][] M2 = new int[n_half][n_half];
        int[][] M3 = new int[n_half][n_half];
        int[][] M4 = new int[n_half][n_half];
        int[][] M5 = new int[n_half][n_half];
        int[][] M6 = new int[n_half][n_half];
        int[][] M7 = new int[n_half][n_half];
        int[][] temp1 = new int[n_half][n_half];
        int[][] temp2 = new int[n_half][n_half];

        // Dividing the array
        for (int i = 0; i < n_half; i++) {
            for (int j = 0; j < n_half; j++) {
                A11[i][j] = A[i][j];
                A12[i][j] = A[i][j + n_half];
                A21[i][j] = A[i + n_half][j];
                A22[i][j] = A[i + n_half][j + n_half];
                B11[i][j] = B[i][j];
                B12[i][j] = B[i][j + n_half];
                B21[i][j] = B[i + n_half][j];
                B22[i][j] = B[i + n_half][j + n_half];
            }
        }

        // M1 = (A11 + A22) * (B11 + B22)
        add(n_half, A11, A22, temp1);
        add(n_half, B11, B22, temp2);
        strassen(n_half, temp1, temp2, M1);

        // M2 = (A21 + A22) * B11
        add(n_half, A21, A22, temp1);
        strassen(n_half, temp1, B11, M2);

        // M3 = A11 * (B12 - B22)
        subtract(n_half, B12, B22, temp2);
        strassen(n_half, A11, temp2, M3);

        // M4 = A22 * (B21 - B11)
        subtract(n_half, B21, B11, temp2);
        strassen(n_half, A22, temp2, M4);

        // M5 = (A11 + A12) * B22
        add(n_half, A11, A12, temp1);
        strassen(n_half, temp1, B22, M5);

        // M6 = (A21 - A11) * (B11 + B12)
        subtract(n_half, A21, A11, temp1);
        add(n_half, B11, B12, temp2);
        strassen(n_half, temp1, temp2, M6);

        // M7 = (A12 - A22) * (B21 + B22)
        subtract(n_half, A12, A22, temp1);
        add(n_half, B21, B22, temp2);
        strassen(n_half, temp1, temp2, M7);

        // C11 = M1 + M4 − M5 + M7
        add(n_half, M1, M4, temp1);
        subtract(n_half, temp1, M5, temp2);
        add(n_half, temp2, M7, C11);

        // C12 = M3 + M5
        add(n_half, M3, M5, C12);

        // C21 = M2 + M4
        add(n_half, M2, M4, C21);

        // C22 = M1 − M2 + M3 + M6
        subtract(n_half, M1, M2, temp1);
        add(n_half, temp1, M3, temp2);
        add(n_half, temp2, M6, C22);

        // Merging the array
        for (int i = 0; i < n_half; i++) {
            for (int j = 0; j < n_half; j++) {
                C[i][j] = C11[i][j];
                C[i][j + n_half] = C12[i][j];
                C[i + n_half][j] = C21[i][j];
                C[i + n_half][j + n_half] = C22[i][j];
            }
        }
    } 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the size of the matrices (must be a power of 2): ");
        int n = sc.nextInt();

        int[][] A = new int[n][n];
        int[][] B = new int[n][n];
        int[][] C = new int[n][n];

        System.out.println("Elements for matrix A:");
        for (int i = 0; i < n; i++) {
            System.out.print((i+1) + " row: ");
            for (int j = 0; j < n; j++) {
                A[i][j] = sc.nextInt();
            }
        } 
        System.out.println();

        System.out.println("Elements for matrix B:");
        for (int i = 0; i < n; i++) {
            System.out.print((i+1) + " row: ");
            for (int j = 0; j < n; j++) {
                B[i][j] = sc.nextInt();
            }
        }
        System.out.println();
        strassen(n, A, B, C);

        System.out.println("Resultant matrix C:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }

        sc.close();
    }
} 