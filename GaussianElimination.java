package LinearAlgebra;

import java.util.Scanner;
// import java.util.List;
// import java.util.ArrayList;

public class GaussianElimination {
    static Scanner mBym = new Scanner(System.in);
    static Scanner matrixInput = new Scanner(System.in);
    static Scanner b = new Scanner(System.in);

    public int getUnknowns() {
        //get dimension
        
        System.out.println("How many unknowns are there? ");
        int unknowns = mBym.nextInt();
        
        return unknowns;
    }

    //parse rows or columns of size unknowns
    public Double[] parseRC(int unknowns) {
        Double[] cVec = new Double[unknowns];


        System.out.print("Enter b: ");
        String bs = "";
        bs = b.nextLine();
        String[] cVecs = bs.split(" ");

        while (cVecs.length != unknowns) {
            System.out.println("Vector must match number of unknowns!");
            System.out.print("Enter b: ");
            bs = b.nextLine();
            cVecs = bs.split(" ");
        }

        int i = 0;
        for (String digit : cVecs) {
            cVec[i] = Double.parseDouble(digit);
            i++;
        }

        System.out.print("[ ");
        for (Double digit : cVec) {
            System.out.print(digit + " ");
        }
        System.out.print("]");
        System.out.println("");

        return cVec;
    }

    public Double[][] buildMatrix(int unknowns) {
        Double[][] matrix = new Double[unknowns][unknowns];

        int rows = 0;

        while (rows < unknowns) {
            matrix[rows] = parseRC(unknowns);
            rows++;
        }

        return matrix;
    }

    public Double[][] buildAugMatrix(int n, Double[][] a, Double[] b) {
        Double[][] augmentedMatrix = new Double[n][n+1];
        int row = 0;  

        while (row < a.length) {
            int column = 0;
            while (column < a[row].length) {
                augmentedMatrix[row][column] = a[row][column];
                column++;
            }

            augmentedMatrix[row][column] = b[row];
            row++;
        }

        return augmentedMatrix;
    }

    public Double[][] getTriangularForm(Double[][] matrix) {
        int row = 0;
        int column = 0;
        while (row < matrix.length) {
            Double[] pivot = matrix[row];
    
            int feederRow = row + 1;
            while (feederRow < matrix.length) {
                Double multiplier = matrix[feederRow][column] / pivot[column];
                int elem = 0;
                while (elem < matrix[feederRow].length) {
                    matrix[feederRow][elem] = matrix[feederRow][elem] - (multiplier * pivot[elem]);
                    elem++;
                }
                feederRow++;
                
            }
            row++;
            column++;
        }

        return matrix;
    }

    public Double[][] solveGE(Double[][] matrix) {
        Double[][] triForm = getTriangularForm(matrix);

        Double[] v = new Double[triForm.length-1];
        int m = matrix.length;
        int n = m;
        Double[] coef = new Double[triForm.length-2];

        int row = m-1;
        int column = 0;

        Double val = 0.0;

        int i = 0;

        while (row > -1) {
            while (column < n -1) {
                val += triForm[row][column];
                column++;
            }
            v[i] = val / triForm[row][column];
            row--;
        }



        return triForm;

    }

    public void printMatrix(int m, int n, Double[][] matrix) {
        int row = 0;
        while (row < m) {
            int column = 0;
            System.out.print("[ ");
            while (column < n) {
                System.out.print(matrix[row][column] + " "); 
                column++;
            }
            System.out.print("]");
            System.out.println("");
            row++;
        } 
    }

    public void printMatrix(int n, Double[] matrix) {
        int column = 0;
        System.out.print("[ ");
        while (column < n) {
            System.out.print(matrix[column] + " "); 
                column++;
        }
        System.out.print("]");
        System.out.println("");
    }

    public static void main(String[] args) {
        GaussianElimination system = new GaussianElimination();
        int elems = system.getUnknowns();

        while (elems < 2) {
            System.out.println("Matrix must be at least 2x2!");
            elems = system.getUnknowns();
        }

        System.out.println("Building Matrix");
        Double[][] a = system.buildMatrix(elems);
        System.out.println("");
        System.out.println("Building Known Column Vector");
        Double[] v = system.parseRC(elems);

        Double[][] augmentedMatrix = system.buildAugMatrix(elems, a, v);
        System.out.println("Matrix: ");
        system.printMatrix(elems, elems, a);
        System.out.println("Augmented Matrix: ");
        system.printMatrix(elems, elems+1, augmentedMatrix);

        System.out.println("Triangular Form: ");
        Double[][] round1 = system.solveGE(augmentedMatrix);
        system.printMatrix(elems, elems+1, round1);


        b.close();
        mBym.close();
        matrixInput.close();
    }
}