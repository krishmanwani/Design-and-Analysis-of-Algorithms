/* 
Quick Sort is a highly efficient sorting algorithm that follows the Divide and Conquer paradigm. 
It selects a pivot element, partitions the array into two subarrays (elements less than the pivot and elements greater than the pivot), and then recursively sorts the subarrays.
Complexity analysis->
1. Time Complexity: 
    Best Case- O(nlogn)
    Average Case- O(nlogn)
    Worst Case- O(n^2)
2. Space Complexity: 
    O(1)
*/ 

import java.util.*;

public class QuickSort {
    //Rearrange arrays on the basis of the pivot element
    public static int partition(int[] arr, int p, int r) {
        int x = arr[r]; // last element is taken as pivot element
        int i = p - 1;

        for (int j = p; j <= r - 1; j++) {
            if (arr[j] <= x) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        int temp = arr[i + 1];
        arr[i + 1] = arr[r];
        arr[r] = temp;

        return i + 1; //Partition index
    }

    //Calling partition function and dividing the arrays
    public static void quickSort(int[] arr, int p, int r) {
        if (p < r) {
            int q = partition(arr, p, r);
            quickSort(arr, p, q - 1);
            quickSort(arr, q + 1, r);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.print("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("Given array is:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        quickSort(arr, 0, n - 1);

        System.out.println("\nSorted array is:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        sc.close();
    }
} 