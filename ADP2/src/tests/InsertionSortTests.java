package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import adt.implementations.AdtArrayImpl;
import adt.interfaces.AdtArray;
import sort.NumGenerator;
import sort.algorithms.InsertionSort;

public class InsertionSortTests {

    /**
     * Testet 70 identische Elemente
     */
    @Test
    public void testAllEquals() {
        AdtArray inArray = NumGenerator.readNum("testAllEquals_70");
        AdtArray unsortArray = inArray;
        AdtArray sortArray = InsertionSort.sort(inArray, 1, inArray.lengthA());
        
        assertEquals(unsortArray, sortArray);
    }
    
    /**
     * Ein aufsteigend sortiertes Array
     */
    @Test
    public void testAlreadySorted() {
        AdtArray inArray = NumGenerator.readNum("testAlreadySorted_30");
        AdtArray unsortArray = inArray;
        AdtArray sortArray = InsertionSort.sort(inArray, 1, inArray.lengthA());
        
        assertEquals(unsortArray, sortArray);
    }
    
    /**
     * Ein leeres Array
     */
    @Test
    public void testEmpty() {
        AdtArray inArray = NumGenerator.readNum("testEmpty_0");
        AdtArray unsortArray = inArray;
        AdtArray sortArray = InsertionSort.sort(inArray, 1, inArray.lengthA());
        
        assertEquals(unsortArray, sortArray);
    }
    
    /**
     * Ein absteigend sortiertes Array
     */
    @Test
    public void testAlreadySortedDesc() {
        AdtArray inArray = NumGenerator.readNum("testAlreadySortedDesc_30");
        AdtArray tmpArray = AdtArrayImpl.initA();
        AdtArray sortArray = InsertionSort.sort(inArray, 1, inArray.lengthA());
        
       for(int i = 0; i < sortArray.lengthA(); i++) {
           tmpArray.setA(sortArray.lengthA() - 1 - i, sortArray.getA(i));
           System.err.println(tmpArray.getA(sortArray.lengthA() - 1 - i) + " - " + sortArray.getA(i));
       }
     
        assertEquals(tmpArray, sortArray);
    }

}
