/*The Activity Selection Problem is a classic example of a Greedy Algorithm. 
It involves selecting the maximum number of non-overlapping activities from a set of activities, each with a given start time and finish time. 
The goal is to maximize the number of activities that can be performed. 
Complexity analysis->
1. Time Complexity: 
    Best Case- O(nlogn)
    Average Case- O(nlogn)
    Worst Case- O(nlogn)
2. Space Complexity: 
    O(n)
*/

import java.util.*;

class ActivitySelection {

    // Merge function to merge two subarrays
    public static void merge(int[] start, int[] finish, int[] index, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] Lstart = new int[n1];
        int[] Lfinish = new int[n1];
        int[] Lindex = new int[n1];
        int[] Rstart = new int[n2];
        int[] Rfinish = new int[n2];
        int[] Rindex = new int[n2];

        for (int i = 0; i < n1; i++) {
            Lstart[i] = start[left + i];
            Lfinish[i] = finish[left + i];
            Lindex[i] = index[left + i];
        }
        for (int i = 0; i < n2; i++) {
            Rstart[i] = start[mid + 1 + i];
            Rfinish[i] = finish[mid + 1 + i];
            Rindex[i] = index[mid + 1 + i];
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (Lfinish[i] <= Rfinish[j]) {
                start[k] = Lstart[i];
                finish[k] = Lfinish[i];
                index[k] = Lindex[i];
                i++;
            } else {
                start[k] = Rstart[j];
                finish[k] = Rfinish[j];
                index[k] = Rindex[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            start[k] = Lstart[i];
            finish[k] = Lfinish[i];
            index[k] = Lindex[i];
            i++;
            k++;
        }

        while (j < n2) {
            start[k] = Rstart[j];
            finish[k] = Rfinish[j];
            index[k] = Rindex[j];
            j++;
            k++;
        }
    }

    // MergeSort function to sort activities by finish times
    public static void mergeSort(int[] start, int[] finish, int[] index, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(start, finish, index, left, mid);
            mergeSort(start, finish, index, mid + 1, right);

            merge(start, finish, index, left, mid, right);
        }
    }

    // Function to print the maximum number of non-overlapping activities
    public static void printMaxActivities(int[] start, int[] finish, int[] index, int n) {
        System.out.print("Selected activities: " + (index[0] + 1) + " ");
        int i = 0;

        for (int j = 1; j < n; j++) {
            if (start[j] >= finish[i]) {
                System.out.print((index[j] + 1) + " ");
                i = j;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of activities: ");
        int n = sc.nextInt();

        int[] start = new int[n];
        int[] finish = new int[n];
        int[] index = new int[n];

        System.out.println("Start Time:");
        for (int i = 0; i < n; i++) {
            System.out.print("Activity " + (i + 1) + ": ");
            start[i] = sc.nextInt();
            index[i] = i; 
        }

        System.out.println("Finish Time:");
        for (int i = 0; i < n; i++) {
            System.out.print("Activity " + (i + 1) + ": ");
            finish[i] = sc.nextInt();
        }

        mergeSort(start, finish, index, 0, n - 1);

        printMaxActivities(start, finish, index, n);

        sc.close();
    }
} 