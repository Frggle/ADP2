package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import sort.NumGenerator;
import sort.algorithms.QuickSort;
import sort.algorithms.QuickSort;
import adt.implementations.AdtArrayImpl;
import adt.interfaces.AdtArray;

public class QuickSortTests {
    /**
     * Testet 70 identische Elemente
     */
    @Test
    public void testAllEqualsANDRandom() {
        AdtArray inArray = NumGenerator.readNum("testAllEquals_70");
        
        AdtArray tmpArray = AdtArrayImpl.initA();
        for(int i = 0; i < inArray.lengthA(); i++) {
        	tmpArray.setA(tmpArray.lengthA(), inArray.getA(i));
        }
        
        QuickSort.sort(inArray, "RANDOM");
        
        assertEquals(inArray, tmpArray);
    }
    
    /**
     * Testet 70 identische Elemente
     */
    @Test
    public void testAllEqualsANDMedianOf3() {
        AdtArray inArray = NumGenerator.readNum("testAllEquals_70");
        
        AdtArray tmpArray = AdtArrayImpl.initA();
        for(int i = 0; i < inArray.lengthA(); i++) {
        	tmpArray.setA(tmpArray.lengthA(), inArray.getA(i));
        }
        
        QuickSort.sort(inArray, "MEDIANOF3");
        
        assertEquals(inArray, tmpArray);
    }
    
    /**
     * Ein aufsteigend sortiertes Array
     */
    @Test
    public void testAlreadySortedANDRandom() {
        AdtArray inArray = NumGenerator.readNum("testAlreadySorted_30");
        
        AdtArray tmpArray = AdtArrayImpl.initA();
        for(int i = 0; i < inArray.lengthA(); i++) {
        	tmpArray.setA(tmpArray.lengthA(), inArray.getA(i));
        }
        
        QuickSort.sort(inArray, "RANDOM");
        
        assertEquals(inArray, tmpArray);
    }
    
    /**
     * Ein aufsteigend sortiertes Array
     */
    @Test
    public void testAlreadySortedANDMedianOf3() {
        AdtArray inArray = NumGenerator.readNum("testAlreadySorted_30");
        
        AdtArray tmpArray = AdtArrayImpl.initA();
        for(int i = 0; i < inArray.lengthA(); i++) {
        	tmpArray.setA(tmpArray.lengthA(), inArray.getA(i));
        }
        
        QuickSort.sort(inArray, "MEDIANOF3");
        
        assertEquals(inArray, tmpArray);
    }
    
    @Test
    public void test() {
    	NumGenerator.sortNum("test", 30);
    	
    	AdtArray array = NumGenerator.readNum("test");
//    	QuickSort_new.sort(array, "MEDIANOF3");
//    	QuickSort_new.sort(array, "RANDOM");
    	QuickSort.sort(array, "LEFT");
//    	QuickSort_new.sort(array, "RIGHT");
    	for(int i = 0; i < array.lengthA(); i++) {
    	    System.err.print(array.getA(i) + " - ");
    	}
    	System.err.println();
    }
    
    /**
     * Ein leeres Array
     */
    @Test
    public void testEmptyANDRandom() {
        AdtArray inArray = NumGenerator.readNum("testEmpty_0");
        
        AdtArray tmpArray = AdtArrayImpl.initA();
        for(int i = 0; i < inArray.lengthA(); i++) {
        	tmpArray.setA(tmpArray.lengthA(), inArray.getA(i));
        }
        
        QuickSort.sort(inArray, "RANDOM");
        
        assertEquals(inArray, tmpArray);
    }
    
    /**
     * Ein leeres Array
     */
    @Test
    public void testEmptyANDMedianOf3() {
        AdtArray inArray = NumGenerator.readNum("testEmpty_0");

        AdtArray tmpArray = AdtArrayImpl.initA();
        for(int i = 0; i < inArray.lengthA(); i++) {
        	tmpArray.setA(tmpArray.lengthA(), inArray.getA(i));
        }
        
        QuickSort.sort(inArray, "MEDIANOF3");
        
        assertEquals(inArray, tmpArray);
    }
    
    /**
     * Ein absteigend sortiertes Array
     */
    @Test
    public void testAlreadySortedDescANDRandom() {
        AdtArray inArray = NumGenerator.readNum("testAlreadySortedDesc_30");
        
        AdtArray tmpArray = AdtArrayImpl.initA();
        for(int i = 0; i < inArray.lengthA(); i++) {
        	tmpArray.setA(tmpArray.lengthA(), inArray.getA(i));
        }
        
        QuickSort.sort(inArray, "RANDOM");
        
        AdtArray reverseInArray = AdtArrayImpl.initA();
        for(int i = 0; i < sortArray.lengthA(); i++) {
        	reverseInArray.setA(sortArray.lengthA() - 1 - i, sortArray.getA(i));
        }
        
        assertEquals(tmpArray, reverseInArray);
    }

    /**
     * Ein absteigend sortiertes Array
     */
    @Test
    public void testAlreadySortedDescANDMedianOf3() {
        AdtArray inArray = NumGenerator.readNum("testAlreadySortedDesc_30");
        
        AdtArray tmpArray = AdtArrayImpl.initA();
        for(int i = 0; i < inArray.lengthA(); i++) {
        	tmpArray.setA(tmpArray.lengthA(), inArray.getA(i));
        }
        
        AdtArray sortArray = QuickSort.sort(inArray, "MEDIANOF3");
        
        AdtArray reverseInArray = AdtArrayImpl.initA();
        for(int i = 0; i < sortArray.lengthA(); i++) {
        	reverseInArray.setA(sortArray.lengthA() - 1 - i, sortArray.getA(i));
        }
        
        assertEquals(tmpArray, reverseInArray);
    }

}
