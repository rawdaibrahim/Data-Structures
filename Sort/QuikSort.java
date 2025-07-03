package DataStructures.Sort;

public class QuikSort {
    public void swamp(int[] arr, int i , int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int partition(int[] arr , int low , int high){
        int pivot = arr[high];

        int i = low - 1;

        for (int j = low; j <= high - 1; j++){

            if (arr[j] < pivot){
                i++;
                swamp(arr , i , j);
            }
            swamp(arr ,i+1 , high);
            return (i+1);
        }
        return 0;
    }
}
