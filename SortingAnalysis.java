
import java.util.Arrays;
import java.util.Random;

public class SortingAnalysis {

    public static void main(String[] args) {
        int[] sizes = {1000, 5000, 10000}; // Array sizes to test
        for (int size : sizes) {
            System.out.println("Array Size: " + size);
            int[] array = generateRandomArray(size);
            // Measure time for Bubble Sort
            int[] bubbleSortArray = Arrays.copyOf(array, array.length);
            long startTime = System.nanoTime();
            bubbleSort(bubbleSortArray);
            long endTime = System.nanoTime();
            System.out.println("Bubble Sort: " + (endTime - startTime) / 1e6 + " ms");
            // Measure time for Selection Sort
            int[] selectionSortArray = Arrays.copyOf(array, array.length);
            startTime = System.nanoTime();
            selectionSort(selectionSortArray);
            endTime = System.nanoTime();
            System.out.println("Selection Sort: " + (endTime - startTime) / 1e6 + " ms");
            // Measure time for Insertion Sort
            int[] insertionSortArray = Arrays.copyOf(array, array.length);
            startTime = System.nanoTime();
            insertionSort(insertionSortArray);
            endTime = System.nanoTime();
            System.out.println("Insertion Sort: " + (endTime - startTime) / 1e6 + " ms");
            // Measure time for Merge Sort
            int[] mergeSortArray = Arrays.copyOf(array, array.length);
            startTime = System.nanoTime();
            mergeSort(mergeSortArray);
            endTime = System.nanoTime();
            System.out.println("Merge Sort: " + (endTime - startTime) / 1e6 + " ms");
            // Measure time for Quick Sort
            int[] quickSortArray = Arrays.copyOf(array, array.length);
            startTime = System.nanoTime();
            quickSort(quickSortArray, 0, quickSortArray.length - 1);
            endTime = System.nanoTime();
            System.out.println("Quick Sort: " + (endTime - startTime) / 1e6 + " ms");
            System.out.println();
        }
    }
    // Generates an array of random integers

    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(10000); // Random numbers between 0 and 9999
        }
        return array;
    }
    // Bubble Sort Algorithm

    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
    // Selection Sort Algorithm

    public static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
    // Insertion Sort Algorithm

    public static void insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
    // Merge Sort Algorithm

    public static void mergeSort(int[] array) {
        if (array.length < 2) {
            return;
        }
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        mergeSort(left);
        mergeSort(right);
        merge(array, left, right);
    }

    public static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }
    // Quick Sort Algorithm

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    public static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }
}
