package LinearAlgebra;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

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
    public Integer[] parseRC(int unknowns) {
        Integer[] cVec = new Integer[unknowns];


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
            cVec[i] = Integer.parseInt(digit);
            i++;
        }

        System.out.print("[ ");
        for (Integer digit : cVec) {
            System.out.print(digit + " ");
        }
        System.out.print("]");
        System.out.println("");

        return cVec;
    }

    public Integer[][] buildMatrix(int unknowns) {
        Integer[][] matrix = new Integer[unknowns][unknowns];

        int rows = 0;

        while (rows < unknowns) {
            matrix[rows] = parseRC(unknowns);
            rows++;
        }

        return matrix;
    }

    public static void main(String[] args) {
        GaussianElimination system = new GaussianElimination();
        int elems = system.getUnknowns();

        while (elems < 2) {
            System.out.println("Matrix must be at least 2x2!");
            elems = system.getUnknowns();
        }

        System.out.println("Building Matrix");
        system.buildMatrix(elems);
        System.out.println("");
        System.out.println("Building Known Column Vector");
        system.parseRC(elems);

        b.close();
        mBym.close();
        matrixInput.close();
    }
}