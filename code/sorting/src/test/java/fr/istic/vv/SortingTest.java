package fr.istic.vv;
import net.jqwik.api.*;

import java.util.Arrays;
import java.util.Comparator;

public class SortingTest {

    @Property
    boolean arrayIsSorted(@ForAll Integer[] anArray) {

        Integer[] bubbleCopy,quickCopy,mergeCopy;
        bubbleCopy = anArray.clone();
        quickCopy = anArray.clone();
        mergeCopy = anArray.clone();
        Arrays.sort(anArray);

        Sorting.bubblesort(bubbleCopy, Integer::compareTo);
        boolean bubbleSorted = Arrays.equals(bubbleCopy, anArray);

        Sorting.quicksort(quickCopy, Integer::compareTo);
        boolean quickSorted = Arrays.equals(quickCopy, anArray);

        Sorting.mergesort(mergeCopy, Integer::compareTo);
        boolean mergeSorted = Arrays.equals(mergeCopy, anArray);

        return bubbleSorted && quickSorted && mergeSorted;
    }

    @Provide
    Arbitrary<Integer[]> ArrProvider() {
        Arbitrary<Integer> integerArbitrary = Arbitraries.integers().between(1, 100);
        return integerArbitrary
                .array(Integer[].class).ofMinSize(0).ofMaxSize(30)
                .map(anArray -> {
                    return anArray;
                });
    }
}