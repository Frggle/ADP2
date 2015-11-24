package sort.algorithms;

import sort.AccessCount;
import adt.interfaces.AdtArray;

public class InsertionSort {
		
	public static AdtArray sort(AdtArray numbers, int startIndex, int endIndex) {
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
	
	public static long sortRuntime(AdtArray numbers, int startIndex, int endIndex) {
	    final long timeStart = System.currentTimeMillis(); 
        sort(numbers, startIndex, endIndex);
        final long timeEnd = System.currentTimeMillis(); 
	    return timeEnd - timeStart;
	}
	
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
