package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import sort.MethodPivot;
import sort.NumGenerator;
import sort.algorithms.QuickSort;
import adt.implementations.AdtArrayImpl;
import adt.interfaces.AdtArray;

public class QuickSortTests {
	
	/**
	 * Testet 100 zufaellige Elemente
	 */
	@Test
	public void test100() {
		for(MethodPivot pivot : MethodPivot.values()) {
			AdtArray inArray = NumGenerator.readNum("test100");

			AdtArray tmpArray = AdtArrayImpl.initA();
			for(int i = 0; i < inArray.lengthA(); i++) {
				tmpArray.setA(tmpArray.lengthA(), inArray.getA(i));
			}
			
			QuickSort.sort(inArray, pivot.toString());
			
			assertEquals(inArray, tmpArray);
		}
	}
	
	/**
	 * Testet 70 identische Elemente
	 */
	@Test
	public void testAllEquals() {
		for(MethodPivot pivot : MethodPivot.values()) {
			AdtArray inArray = NumGenerator.readNum("testAllEquals_70");

			AdtArray tmpArray = AdtArrayImpl.initA();
			for(int i = 0; i < inArray.lengthA(); i++) {
				tmpArray.setA(tmpArray.lengthA(), inArray.getA(i));
			}
			
			QuickSort.sort(inArray, pivot.toString());
			
			assertEquals(inArray, tmpArray);
		}
	}
	
	/**
	 * Ein aufsteigend sortiertes Array
	 */
	@Test
	public void testAlreadySortedASC() {
		for(MethodPivot pivot : MethodPivot.values()) {
			AdtArray inArray = NumGenerator.readNum("testAlreadySorted_30");
			
			AdtArray tmpArray = AdtArrayImpl.initA();
			for(int i = 0; i < inArray.lengthA(); i++) {
				tmpArray.setA(tmpArray.lengthA(), inArray.getA(i));
			}
			
			QuickSort.sort(inArray, pivot.toString());
			
			assertEquals(inArray, tmpArray);
		}
	}
	
	/**
	 * Ein leeres Array
	 */
	@Test
	public void testEmpty() {
		for(MethodPivot pivot : MethodPivot.values()) {
			AdtArray inArray = NumGenerator.readNum("testEmpty_0");
			
			AdtArray tmpArray = AdtArrayImpl.initA();
			for(int i = 0; i < inArray.lengthA(); i++) {
				tmpArray.setA(tmpArray.lengthA(), inArray.getA(i));
			}
			
			QuickSort.sort(inArray, pivot.toString());
			
			assertEquals(inArray, tmpArray);
		}
	}
	
	/**
	 * Ein absteigend sortiertes Array
	 */
	@Test
	public void testAlreadySortedDesc() {
		for(MethodPivot pivot : MethodPivot.values()) {
			AdtArray inArray = NumGenerator.readNum("testAlreadySortedDesc_30");
			
			AdtArray tmpArray = AdtArrayImpl.initA();
			for(int i = 0; i < inArray.lengthA(); i++) {
				tmpArray.setA(tmpArray.lengthA(), inArray.getA(i));
			}
			
			QuickSort.sort(inArray, pivot.toString());
			
			AdtArray reverseInArray = AdtArrayImpl.initA();
			for(int i = 0; i < inArray.lengthA(); i++) {
				reverseInArray.setA(inArray.lengthA() - 1 - i, inArray.getA(i));
			}
			
			assertEquals(tmpArray, reverseInArray);
		}
	}
}
