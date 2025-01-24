/* 
Merge Sort is a popular algorithm based on the Divide and Conquer paradigm. 
It divides the input array into two halves, recursively sorts them, and then merges the sorted halves into a single sorted array. 
Complexity analysis->
1. Time Complexity: 
    Best Case- O(nlogn)
    Average Case- O(nlogn)
    Worst Case- O(nlogn)
2. Space Complexity: 
    O(n)
*/ 

import java.util.*;

public class MergeSort {
    //Merges two subarrays in sorted format
    public static void merge(int arr[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        //Temporary arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; i++){
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; j++){
            R[j] = arr[m + 1 + j];
        }

        int i = 0, j = 0;
        int k = l;

        //Merging temporary arrays back into the main array in sorted format 
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        //Merging remaining elements in the temporary arrays if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    //Dividing the two arrays and calling merge function
    public static void mergeSort(int arr[], int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;

            //Arrays are divided
            mergeSort(arr, l, m); 
            mergeSort(arr, m + 1, r);
            
            //Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    //Main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int n = sc.nextInt();

        int arr[] = new int[n];

        System.out.print("Enter the array: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("Given array is:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        mergeSort(arr, 0, n - 1);

        System.out.println("Sorted array is:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        } 
        System.out.println();

        sc.close();
    }
} 