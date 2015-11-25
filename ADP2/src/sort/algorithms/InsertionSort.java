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
	public static AdtArray sort(AdtArray numbers, int startIndex, int endIndex) {
		if(numbers.lengthA() == 0) {
		    return numbers;
		}
	    for(int i = startIndex; i < endIndex; i++) {
			int temp = numbers.getA(i);
			int j;
			
			for(j = i - 1; j >= 0 && temp < numbers.getA(j); j--) {
				numbers.setA(j + 1, numbers.getA(j));
			}
			numbers.setA(j + 1, temp);
		}
	    	    
		return numbers;
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
	    
	    for(int i = startIndex; i < endIndex; i++) {
            int temp = numbers.getA(i);
            readAccessCount++;
            int j;
            
            for(j = i - 1; j >= 0 && temp < numbers.getA(j); j--) {
                numbers.setA(j + 1, numbers.getA(j));
                writeAccessCount++;
                readAccessCount++;
            }
            numbers.setA(j + 1, temp);
            writeAccessCount++;
        }
	    
		return new AccessCount(readAccessCount, writeAccessCount);
	}
}
