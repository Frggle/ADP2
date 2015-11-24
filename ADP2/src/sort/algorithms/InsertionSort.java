package sort.algorithms;

import adt.interfaces.AdtArray;

public class InsertionSort {
	
	public InsertionSort() {
	}
	
	public static AdtArray sort(AdtArray numbers, int startIndex, int endIndex) {
		final long timeStart = System.currentTimeMillis(); 
		for(int i = startIndex; i < endIndex; i++) {
			int temp = numbers.getA(i);
			int j;
			
			for(j = i - 1; j >= 0 && temp < numbers.getA(j); j--) {
				numbers.setA(j + 1, numbers.getA(j));
			}
			numbers.setA(j + 1, temp);
		}
        final long timeEnd = System.currentTimeMillis(); 
		System.err.println("Verlaufszeit der Schleife: " + (timeEnd - timeStart) + " Millisek.");
		return numbers;
	}
	
	public long sortRuntime() {
		return 0;
	}
	
	public Object sortAccessCount() {
		return null;
	}
}
