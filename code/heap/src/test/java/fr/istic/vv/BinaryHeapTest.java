package fr.istic.vv;
import net.jqwik.api.*;

import java.util.Comparator;
import java.util.Random;


public class BinaryHeapTest {

    @Property
    <T> boolean heapTest(@ForAll("binaryHeap") BinaryHeap<T> heap) {
        T last = heap.pop();
        if (heap.count() == 0 ) return true;
        while(heap.count() > 0) {
            if(heap.getComparator().compare(last, last=heap.pop()) > 0) return false;
        }
        return true;
    }

    Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer lElement, Integer rElement) {
            return lElement.compareTo(rElement);
        }
    };

    @Provide
    Arbitrary<BinaryHeap<Integer>> binaryHeap() {
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>(comparator, new Integer[5]);
        Arbitrary<Integer> ints = Arbitraries.integers().between(1, 4);
        return Arbitraries.integers().between(0, 10).map(i -> {
            ints.forEachValue(val -> binaryHeap.push(i));
            return binaryHeap;
        });
    }
}
