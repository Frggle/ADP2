package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import sort.NumGenerator;
import sort.algorithms.InsertionSort;
import adt.implementations.AdtArrayImpl;
import adt.interfaces.AdtArray;

public class InsertionSortTests {

    /**
     * Testet 70 identische Elemente
     */
    @Test
    public void testAllEquals() {
        AdtArray inArray = NumGenerator.readNum("testAllEquals_70");
        
        AdtArray tmpArray = AdtArrayImpl.initA();
        for(int i = 0; i < inArray.lengthA(); i++) {
        	tmpArray.setA(tmpArray.lengthA(), inArray.getA(i));
        }
        
        InsertionSort.sort(inArray, 0, inArray.lengthA() - 1);
        
        assertEquals(tmpArray, inArray);
    }
    
    /**
     * Ein aufsteigend sortiertes Array
     */
    @Test
    public void testAlreadySorted() {
        AdtArray inArray = NumGenerator.readNum("testAlreadySorted_30");
        
        AdtArray tmpArray = AdtArrayImpl.initA();
        for(int i = 0; i < inArray.lengthA(); i++) {
        	tmpArray.setA(tmpArray.lengthA(), inArray.getA(i));
        }
        
        InsertionSort.sort(inArray, 0, inArray.lengthA() - 1);
        
        assertEquals(tmpArray, inArray);
    }
    
    /**
     * Ein leeres Array
     */
    @Test
    public void testEmpty() {
        AdtArray inArray = NumGenerator.readNum("testEmpty_0");
        
        AdtArray tmpArray = AdtArrayImpl.initA();
        for(int i = 0; i < inArray.lengthA(); i++) {
        	tmpArray.setA(tmpArray.lengthA(), inArray.getA(i));
        }
        
        InsertionSort.sort(inArray, 0, inArray.lengthA() - 1);
        
        assertEquals(tmpArray, inArray);
    }
    
    /**
     * Ein absteigend sortiertes Array
     */
    @Test
    public void testAlreadySortedDesc() {
        AdtArray inArray = NumGenerator.readNum("testAlreadySortedDesc_30");
        
        AdtArray tmpArray = AdtArrayImpl.initA();
        for(int i = 0; i < inArray.lengthA(); i++) {
        	tmpArray.setA(tmpArray.lengthA(), inArray.getA(i));
        }
        
        InsertionSort.sort(inArray, 0, inArray.lengthA() - 1);
        
        AdtArray reverseInArray = AdtArrayImpl.initA();
        for(int i = 0; i < inArray.lengthA(); i++) {
        	reverseInArray.setA(inArray.lengthA() - 1 - i, inArray.getA(i));
        }
        
        assertEquals(tmpArray, reverseInArray);
    }
}
