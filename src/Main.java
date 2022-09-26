import java.util.Random;
import java.util.Scanner;

/*Task: make one sorted array of two sorted, without simply merging them and sorting*/
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input size of the first array:");
        int firstArrayLength = inputInteger(in);
        System.out.println("Input size of the second array:");
        int secondArrayLength = inputInteger(in);
        System.out.println("Input random numbers range:");
        int randRange = inputInteger(in);
        int[] arr1 = new int[firstArrayLength];
        int[] arr2 = new int[secondArrayLength];
        int[] arr3 = new int[firstArrayLength + secondArrayLength];
        fillArrayRandomly(arr1, randRange);
        fillArrayRandomly(arr2, randRange);
        bubbleSort(arr1);
        bubbleSort(arr2);
        outputArray(arr1);
        outputArray(arr2);
        mergeSortArrays(arr1, arr2, arr3);
        outputArray(arr3);
    }
    public static void mergeSortArrays(int[] arr1, int[] arr2, int[] arr3){
        int j = 0, k =0;
        for(int i =0; i < arr3.length; i++){
            if(j == arr1.length){
                arr3[i] = arr2[k];
                k++;
            } else if (k == arr2.length) {
                arr3[i] = arr1[j];
                j++;
            }else if(arr1[j] <= arr2[k]){
                arr3[i] = arr1[j];
                j++;
            }else{
                arr3[i] = arr2[k];
                k++;
            }
        }
    }


    public static void fillArrayRandomly(int[] arr, int randRange ){
        Random random = new Random();
        for (int i = 0; i < arr.length; i++){
            arr[i] = random.nextInt(randRange);
        }
    }
    public static void bubbleSort(int[] arr){
        int temp;
        for (int i = 0; i < arr.length; i++){
            for (int j = 1; j < (arr.length) - i; j++){
                if (arr[j-1]> arr[j]){
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
    public static void outputArray(int[] arr){
        System.out.println("\n");
        for (int i = 0; i < arr.length; i++){
            System.out.printf("Arr[%d]:%d\n", i, arr[i]);
        }
    }
    public static int inputInteger(Scanner in) {
        int num = 0;
        boolean isNotCorrect;
        do {
            isNotCorrect = false;
            try {
                num = in.nextInt();
            } catch (Exception ex) {
                System.out.println("Wrong input! try again: ");
                in.next();
                isNotCorrect = true;
            }
        }while(isNotCorrect);
        return num;
    }


}