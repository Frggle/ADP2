package sort.algorithms;

import sort.AccessCount;
import adt.interfaces.AdtArray;

public class InsertionSort {
		
    /**
     * Sortiert ein Array aufsteigend in einem bestimmten Bereich.
     * 
     * @param numbers, das, aufsteigend, zu sortierende Array
     * @param startIndex, der StartIndex ab wo sortiert werden soll
     * @param endIndex, der EndIndex bis wo sortiert werden soll
     * @return AdtArray, das destruktiv sortierte Array
     */
	public static void sort(AdtArray numbers, int startIndex, int endIndex) {
		
	    for(int i = startIndex + 1; i <= endIndex; i++) {
			int temp = numbers.getA(i);
			int j = i;
						
			while(j > startIndex && temp < numbers.getA(j - 1)){
			    numbers.setA(j, numbers.getA(j - 1));
			    j--;
			}
			numbers.setA(j, temp);
		}
	}
	
	/**
	 * Sortiert ein Array und misst die Laufzeit in Millisec
	 * 
	 * @param numbers, das, aufsteigend, zu sortierende Array
	 * @param startIndex, der StartIndex ab wo sortiert werden soll
	 * @param endIndex, der EndIndex bis wo sortiert werden soll
	 * @return Long, die Laufzeit in Millisec
	 */
	public static long sortRuntime(AdtArray numbers, int startIndex, int endIndex) {
	    final long timeStart = System.currentTimeMillis(); 
        sort(numbers, startIndex, endIndex);
        final long timeEnd = System.currentTimeMillis(); 
	    return timeEnd - timeStart;
	}
	
	/**
	 * Sortiert ein Array und zaehlt die Lese- und Schreibzugriffe
	 * 
     * @param numbers, das, aufsteigend, zu sortierende Array
     * @param startIndex, der StartIndex ab wo sortiert werden soll
     * @param endIndex, der EndIndex bis wo sortiert werden soll
	 * @return AccessCount, die Zugriffswerte
	 */
	public static AccessCount sortAccessCount(AdtArray numbers, int startIndex, int endIndex) {
	    int readAccessCount = 0;
	    int writeAccessCount = 0;
	    
	    for(int i = startIndex + 1; i <= endIndex; i++) {
            int temp = numbers.getA(i);
            readAccessCount++;
            int j = i;
            
            while(j > startIndex && temp < numbers.getA(j - 1)){
                readAccessCount++;
                numbers.setA(j, numbers.getA(j - 1));
                j--;
                writeAccessCount++;
                readAccessCount++;
            }
            
            numbers.setA(j + 1, temp);
            writeAccessCount++;
        }
	    
		return new AccessCount(readAccessCount, writeAccessCount);
	}
}
