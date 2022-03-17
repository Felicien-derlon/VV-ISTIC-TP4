package fr.istic.vv;
import java.util.Arrays;
import java.util.Comparator;

public class Sorting {

    public static <T>  T[] bubblesort(T[] array, Comparator<T> comparator) {
        int n = array.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator) {
        quick(array, 0, array.length-1, comparator);
        return array;
    }

    // method for quick sort algorithm
    private static <T> void quick(T[] array, int start, int end, Comparator<T> comparator) {
        if (start < end)
        {
            int p = partition(array, start, end, comparator);
            quick(array, start, p - 1, comparator);
            quick(array, p + 1, end, comparator);
        }
    }

    // method for quick sort algorithm
    private static <T> int partition (T[] array, int start, int end, Comparator<T> comparator) {
        T pivot = array[end];
        int i = (start - 1);

        for (int j = start; j <= end - 1; j++)
        {
            if (comparator.compare(array[j], pivot) < 0)
            {
                i++;
                T t = array[i];
                array[i] = array[j];
                array[j] = t;
            }
        }
        T t = array[i+1];
        array[i+1] = array[end];
        array[end] = t;
        return (i + 1);
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        mergeSort(array, array.length, comparator);
        return array;
    }

    // method for merge sort algorithm
    public static <T> void mergeSort(T[] array, int n, Comparator<T> comparator) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        T[] left = Arrays.copyOfRange(array, 0, mid);
        T[] right = Arrays.copyOfRange(array, mid, array.length);
        mergeSort(left, mid, comparator);
        mergeSort(right, n - mid, comparator);

        merge(array, left, right, mid, n - mid, comparator);
    }

    // method for merge sort algorithm
    public static <T> void merge(T[] array, T[] left, T[] right, int leftIndex, int rightIndex, Comparator<T> comparator) {
        int i = 0, j = 0, k = 0;
        while (i < leftIndex && j < rightIndex) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                array[k++] = left[i++];
            }
            else {
                array[k++] = right[j++];
            }
        }
        while (i < leftIndex) {
            array[k++] = left[i++];
        }
        while (j < rightIndex) {
            array[k++] = right[j++];
        }
    }

}
