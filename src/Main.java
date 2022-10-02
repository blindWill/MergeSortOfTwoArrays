import java.util.Random;
import java.util.Scanner;

/*
Second Task:
Third Task: make one sorted array of two sorted, without simply merging them and sorting*/
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean isContinue = true;
        int chosenTask;
        do {
            System.out.println("#1 - Find monthly average temperature\n" +
                    "#2 - Replace the worst by the arithmetic average of all other estimates\n" +
                    "#3 - Make one sorted array of two sorted\n" +
                    "# - Any number to exit");
            chosenTask = inputInteger(in);
            switch (chosenTask){
                case 1:
                    break;
                case 2:
                    performSecondTask(in);
                    break;
                case 3:
                    performThirdTask(in);
                    break;
                default:
                    isContinue = false;
                    break;
            }

        }while(isContinue);
    }

    public static void performThirdTask(Scanner in){
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


    public static void fillArrayRandomly(int[] arr, int randRange){
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
        System.out.println();
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

    public static void performSecondTask(Scanner in){
        System.out.println("Input size of the array with grades(in range [2..100]):");
        int arrayLength = inputNumberInCertainRange(in, 2,100);
        int[] gradesArray = new int[arrayLength];
        fillArrayManually(gradesArray, in);
        bubbleSort(gradesArray);
        System.out.print("Initial array:");
        outputArray(gradesArray);
        boolean isFaked = fakeGrades(gradesArray);
        if (isFaked){
            System.out.print("Array with replaced grade/s:");
            outputArray(gradesArray);
        }else{
            System.out.println("There is no need to fake grades");
        }
    }

    public static void fillArrayManually(int[] gradesArray, Scanner in){
        for (int i = 0; i < gradesArray.length; i++){
            System.out.printf("Input A[%d]:", i);
            gradesArray[i] = inputNumberInCertainRange(in, 0, 10);
        }

    }
    public static boolean fakeGrades(int[] gradesArray){
        boolean isFaked = false;
        int minElem = findMinElem(gradesArray);
        int secondMinElem = findSecondMinElem(gradesArray, minElem);
        if (minElem < secondMinElem - 2){
            isFaked = true;
            int arithmeticMean = findArithmeticMean(gradesArray, minElem);
            replaceGrade(gradesArray, minElem, arithmeticMean);
        }
        return isFaked;
    }

    public static void replaceGrade(int[] gradesArray, int minElem, int arithmeticMean){
        for (int i = 0; i < gradesArray.length; i++){
            if (gradesArray[i] == minElem){
                gradesArray[i] = arithmeticMean;
            }
        }
    }
    public static int findArithmeticMean(int[] gradesArray, int minElem){
        int elemSum = 0;
        int counter = 0;
        for (int j : gradesArray) {
            if (j != minElem) {
                elemSum += j;
                counter++;
            }
        }
        return elemSum/counter;
    }
    public static int findMinElem(int[] gradesArray){
        int minElem = 10;
        for (int j : gradesArray) {
            if (minElem > j) {
                minElem = j;
            }
        }
        return minElem;
    }

    public static int findSecondMinElem(int[] gradesArray, int minElem){
        int secondMinElem = 10;
        for (int j : gradesArray) {
            if ((secondMinElem > j) && (j != minElem)) {
                secondMinElem = j;
            }
        }
        return secondMinElem;
    }

    public static int inputNumberInCertainRange(Scanner in, int min, int max) {
        int num = 0;
        boolean isNotCorrect;
        do {
            isNotCorrect = false;
            try {
                num = in.nextInt();
                if (num > max || num < min) {
                    throw new Exception();
                }
            } catch (Exception ex) {
                System.out.println("Wrong input! try again: ");
                in.next();
                isNotCorrect = true;
            }
        }while(isNotCorrect);
        return num;
    }

}