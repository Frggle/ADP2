package sort.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import sort.AccessCount;
import sort.MethodPivot;
import adt.implementations.AdtArrayImpl;
import adt.interfaces.AdtArray;

public class QuickSort {

    /**
     * 
     * @param numbers
     * @param pivot
     * @return
     */
    private static int getPivot(AdtArray numbers, String pivot) {
        if(MethodPivot.valueOf(pivot) == MethodPivot.LEFT) {
            return numbers.getA(0);
        } else if (MethodPivot.valueOf(pivot) == MethodPivot.RIGHT) {
            return numbers.getA(numbers.lengthA() - 1);
        } else if (MethodPivot.valueOf(pivot) == MethodPivot.MEDIANOF3) {
            int left = numbers.getA(0);
            int right = numbers.getA(numbers.lengthA() - 1);
            int median = numbers.getA(numbers.lengthA() / 2);
            List<Integer> list = new ArrayList<Integer>();
            list.add(left);
            list.add(right);
            list.add(median);
            Collections.sort(list);
            return list.get(1);
        } else {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            return numbers.getA(random.nextInt(0, numbers.lengthA()));
        }
    }
    
    /**
     * 
     * @param numbers
     * @param pivot
     * @return
     */
	public static AdtArray sort(AdtArray numbers, String pivot) {
	   
	    AdtArray result = AdtArrayImpl.initA();
	    sortRec(numbers, result, pivot);
	    
	    return result;
	}
	
	private static void sortRec(AdtArray numbers, AdtArray result, String pivot) {
	    int _pivot = getPivot(numbers, pivot);
	    
	    AdtArray leftArray = AdtArrayImpl.initA();
        AdtArray rightArray = AdtArrayImpl.initA();
        
        for(int i = 0; i < numbers.lengthA(); i++) {
            int elem = numbers.getA(i);
            if(elem <= _pivot) {
                leftArray.setA(leftArray.lengthA(), elem);
            } else {
                rightArray.setA(rightArray.lengthA(), elem);
            }
        }
        
        if(leftArray.lengthA() < 12) {
            leftArray = InsertionSort.sort(leftArray, 1, leftArray.lengthA());
            
            for(int i = 0; i < leftArray.lengthA(); i++) {
                result.setA(result.lengthA(), leftArray.getA(i));
            }
        } else {
            sortRec(leftArray, result, pivot);
        }
        if(rightArray.lengthA() < 12) {
            rightArray = InsertionSort.sort(rightArray, 1, rightArray.lengthA());
            
            for(int i = 0; i < rightArray.lengthA(); i++) {
                result.setA(result.lengthA(), rightArray.getA(i));
            }
        } else {
            sortRec(rightArray, result, pivot);
        }
	}
	
	/**
	 * 
	 * @param numbers
	 * @param pivot
	 * @return
	 */
	public static long sortRuntime(AdtArray numbers, MethodPivot pivot) {
		return 0;
	}
	
	/**
	 * 
	 * @param numbers
	 * @param pivot
	 * @return
	 */
	public static AccessCount sortAccessCount(AdtArray numbers, MethodPivot pivot) {
		return null;
	}
}
