package DataStructures.Sort;

import java.util.Arrays;

public class MergeSort {
    public static void mergeSort(int[] arr){
        int n = arr.length;

        if (n < 2) return;

        int mid = n/2 ;
        int[] l = Arrays.copyOfRange(arr, 0 , mid);
        int[] R = Arrays.copyOfRange(arr, mid , n);

        mergeSort(l);
        mergeSort(R);

        merge(arr , l , R);
    }

    public static void merge(int[] arr, int[] L, int[] R){
        int i = 0 , j =0 , k = 0 ;
        int l = L.length;
        int r = R.length;

        while (i < l && j < r){
            if (L[i] <= R[j]){
                arr[k]= L[i];
                i++;
            }else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }


        while (i < l) {
            arr[k] = L[i];
            k++;
            i++;
        }

        while (j < r) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        int[] arr = {13, 44, 83, 3, 12 , 9};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
