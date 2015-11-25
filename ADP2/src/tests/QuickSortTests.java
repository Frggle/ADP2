package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import sort.NumGenerator;
import sort.algorithms.QuickSort;
import adt.interfaces.AdtArray;

public class QuickSortTests {
    /**
     * Testet 70 identische Elemente
     */
    @Test
    public void testAllEqualsANDRandom() {
        AdtArray inArray = NumGenerator.readNum("testAllEquals_70");
        AdtArray sortArray = QuickSort.sort(inArray, "RANDOM");
        
        assertEquals(inArray, sortArray);
    }
    
    /**
     * Testet 70 identische Elemente
     */
    @Test
    public void testAllEqualsANDMedianOf3() {
        AdtArray inArray = NumGenerator.readNum("testAllEquals_70");
        AdtArray sortArray = QuickSort.sort(inArray, "MEDIANOF3");
        
        assertEquals(inArray, sortArray);
    }
    
    /**
     * Ein aufsteigend sortiertes Array
     */
    @Test
    public void testAlreadySortedANDRandom() {
        AdtArray inArray = NumGenerator.readNum("testAlreadySorted_30");
        AdtArray sortArray = QuickSort.sort(inArray, "RANDOM");
        
        assertEquals(inArray, sortArray);
    }
    
    /**
     * Ein aufsteigend sortiertes Array
     */
    @Test
    public void testAlreadySortedANDMedianOf3() {
        AdtArray inArray = NumGenerator.readNum("testAlreadySorted_30");
        AdtArray sortArray = QuickSort.sort(inArray, "MEDIANOF3");
        
        assertEquals(inArray, sortArray);
    }
    
    /**
     * Ein leeres Array
     */
    @Test
    public void testEmptyANDRandom() {
        AdtArray inArray = NumGenerator.readNum("testEmpty_0");
        AdtArray sortArray = QuickSort.sort(inArray, "RANDOM");
        
        assertEquals(inArray, sortArray);
    }
    
    /**
     * Ein leeres Array
     */
    @Test
    public void testEmptyANDMedianOf3() {
        AdtArray inArray = NumGenerator.readNum("testEmpty_0");
        AdtArray sortArray = QuickSort.sort(inArray, "MEDIANOF3");
        
        assertEquals(inArray, sortArray);
    }
    
    /**
     * Ein absteigend sortiertes Array
     */
    @Test
    public void testAlreadySortedDescANDRandom() {
        AdtArray inArray = NumGenerator.readNum("testAlreadySortedDesc_30");
        AdtArray sortArray = QuickSort.sort(inArray, "RANDOM");
        
        for(int i = 0; i < inArray.lengthA(); i++) {
            System.err.println(inArray.getA(i) + " - " + sortArray.getA(i));
        }
        
        assertEquals(inArray, sortArray);
    }

    /**
     * Ein absteigend sortiertes Array
     */
    @Test
    public void testAlreadySortedDescANDMedianOf3() {
        AdtArray inArray = NumGenerator.readNum("testAlreadySortedDesc_30");
        AdtArray sortArray = QuickSort.sort(inArray, "MEDIANOF3");
        
        assertEquals(inArray, sortArray);
    }

}
