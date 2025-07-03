package DataStructures;

import java.util.Scanner;

public class GFG {

    // Function to print an array
    static void printArray(int arr[])
    {

        // Iterating and printing the array
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    // Function to implement the
    // quadratic probing
    static void hashing(int table[], int tsize,
                        int arr[], int N)
    {

        // Iterating through the array
        for (int i = 0; i < N; i++) {

            // Computing the hash value
            int hv = (arr[i]*3) % 17;
            System.out.println(hv);

            // Insert in the table if there
            // is no collision
            if (table[hv] == -1)
                table[hv] = arr[i];
            else {

                // If there is a collision
                // iterating through all
                // possible quadratic values
                for (int j = 0; j < tsize; j++) {

                    // Computing the new hash value
                    int t = (hv + j * j) % 19;
                    if (table[t] == -1) {

                        // Break the loop after
                        // inserting the value
                        // in the table
                        table[t] = arr[i];
                        break;
                    }
                }
            }
        }

        printArray(table);
    }

    // Driver code
    public static void main(String args[])
    {
        int arr[] = { 12, 44, 13, 88, 23, 94, 11, 39, 20, 16, 5 };
        for(int i=0; i<11; i++){
            System.out.println((3*arr[i])%17);
        }
        int N = 7;

        // Size of the hash table
        int L = 7;
        int hash_table[] = new int[19];

        // Initializing the hash table
        for (int i = 0; i < 19; i++) {
            hash_table[i] = -1;
        }

        // Quadratic probing
        hashing(hash_table, 19, arr, 11);
    }
}