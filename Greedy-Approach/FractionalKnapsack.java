/* 
The Fractional Knapsack Problem is a variation of the knapsack problem where items can be divided into smaller parts. 
This allows taking fractions of items rather than just whole items, making it possible to maximize the total value of items carried in the knapsack.
The basic idea of the greedy approach is to calculate the ratio profit/weight for each item and sort the item on the basis of this ratio. 
Then take the item with the highest ratio and add them as much as we can (can be the whole element or a fraction of it).
Complexity analysis->
1. Time Complexity: 
    Best Case- O(nlogn)
    Average Case- O(nlogn)
    Worst Case- O(nlogn)
2. Space Complexity: 
    O(n)
*/ 

import java.util.Scanner;

public class FractionalKnapsack {
    // Merge function for merge sort
    public static void merge(int[] w, float[] ratio, int[] v, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] Lw = new int[n1], Rw = new int[n2], Lv = new int[n1], Rv = new int[n2];
        float[] Lratio = new float[n1], Rratio = new float[n2];

        for (int i = 0; i < n1; i++) {
            Lw[i] = w[left + i];
            Lratio[i] = ratio[left + i];
            Lv[i] = v[left + i];
        }
        for (int i = 0; i < n2; i++) {
            Rw[i] = w[mid + 1 + i];
            Rratio[i] = ratio[mid + 1 + i];
            Rv[i] = v[mid + 1 + i];
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (Lratio[i] >= Rratio[j]) {
                w[k] = Lw[i];
                ratio[k] = Lratio[i];
                v[k] = Lv[i];
                i++;
            } 
            else {
                w[k] = Rw[j];
                ratio[k] = Rratio[j];
                v[k] = Rv[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            w[k] = Lw[i];
            ratio[k] = Lratio[i];
            v[k] = Lv[i];
            i++;
            k++;
        }

        while (j < n2) {
            w[k] = Rw[j];
            ratio[k] = Rratio[j];
            v[k] = Rv[j];
            j++;
            k++;
        }
    }

    // MergeSort function
    public static void mergeSort(int[] w, float[] ratio, int[] v, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(w, ratio, v, left, mid);
            mergeSort(w, ratio, v, mid + 1, right);

            merge(w, ratio, v, left, mid, right);
        }
    }

    public static void fractionalKnapsack(int W, int[] w, int[] v, int n) {
        float[] x = new float[n];
        float[] ratio = new float[n];

        // Calculating Ratios 
        for (int i = 0; i < n; i++) {
            ratio[i] = (float) v[i] / w[i];
        }
        
        // Sorting according to the ratios
        mergeSort(w, ratio, v, 0, n - 1);

        for (int i = 0; i < n; i++) {
            x[i] = 0;
        }

        int weight = 0;
        int i = 0;

        // Picking up the elements
        while (i < n && weight <= W) {
            if ((weight + w[i]) <= W) {
                x[i] = 1;
            } 
            else {
                x[i] = (float) (W - weight) / w[i];
            }

            System.out.println("Item with profit" + v[i] + " is picked " + x[i]);
            weight += x[i] * w[i];
            i++;
        }

        float profit = 0;
        for (i = 0; i < n; i++) {
            profit += (v[i] * x[i]);
        }

        System.out.println("Total Profit: " + profit);
    } 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = sc.nextInt();

        int[] w = new int[n];
        int[] v = new int[n];

        System.out.print("Enter the weights: ");
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
        }

        System.out.print("Enter the values: ");
        for (int i = 0; i < n; i++) {
            v[i] = sc.nextInt();
        }

        System.out.print("Enter the total capacity of the knapsack: ");
        int W = sc.nextInt();

        fractionalKnapsack(W, w, v, n);

        sc.close();
    }
} 