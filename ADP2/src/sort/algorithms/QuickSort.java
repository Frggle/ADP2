package sort.algorithms;

import java.util.concurrent.ThreadLocalRandom;
import sort.MethodPivot;
import adt.interfaces.AdtArray;

public class QuickSort {
	/**
	 * Wenn der Pivot mit LEFT oder RIGHT angegeben wird, dann laueft der Algorithmus in eine Endlosschleife. Grund:
	 * bspw. bei LEFT Wenn das linke Element das groesste Element des Teilarrays ist, kann das Array nicht aufgeteilt
	 * werden (vorausgesetzt die Laenge ist groesser gleich 12). Loesungsidee: es muss jedes Mal geprueft werden, ob das
	 * Pivot-Element das groesste ist, wenn dann nimm naechstes Element.
	 */
	
	/* Variablen */
	private static String _pivot;
	
	/**
	 * Berechent den Pivot-Wert, als Integer, fuer ein Array und einem String
	 * @param numbers, das Array woraus der Pivot berechnet wird
	 * @param pivot, ein String welches in ein Pivot-Enum gewandelt wird
	 * @return int, der errechnete Pivot aus dem Array
	 */
	private static int getPivot(AdtArray numbers, int left, int right) {
		if(MethodPivot.valueOf(_pivot) == MethodPivot.LEFT) {
			return left;
		} else if(MethodPivot.valueOf(_pivot) == MethodPivot.RIGHT) {
			return right;
		} else if(MethodPivot.valueOf(_pivot) == MethodPivot.MEDIANOF3) {
			return (left + right) / 2;
		} else if(MethodPivot.valueOf(_pivot) == MethodPivot.RANDOM) {
			ThreadLocalRandom random = ThreadLocalRandom.current();
			if(numbers.lengthA() == 0) {
				return 0;
			} else {
				return random.nextInt(left, right + 1);
			}
		} 
		return -1;
	}
	
	/**
	 * Sortiert ein Array mit Hilfe von InsertionSort
	 * @param numbers, das, aufsteigend, zu sortierende Array
	 * @param pivot, der Pivot als String
	 * @return AdtArray, das destruktiv sortierte Array
	 */
	public static AdtArray sort(AdtArray numbers, String pivot) {
		_pivot = pivot;
		
		for(int i = 0; i < numbers.lengthA(); i++) {
			System.err.print(numbers.getA(i) + " - ");
		}
		System.err.println();
		
		recQuickSort(numbers, 0, numbers.lengthA() - 1);
//		InsertionSort.sort(numbers, 1, numbers.lengthA());
		
		System.err.println();
		for(int i = 0; i < numbers.lengthA(); i++) {
			System.err.print(numbers.getA(i) + " - ");
		}
		
		return numbers;
	}
	
	private static void recQuickSort(AdtArray numbers, int left, int right) {
		int size = right - left + 1;
		if(size < 12) {
			InsertionSort.sort(numbers, left, right + 1);
			for(int i = 0; i < numbers.lengthA(); i++) {
				System.err.print(numbers.getA(i) + " - ");
			}
			System.err.println();
		}
		else {
			int pivot = split(numbers, left, right);
			int partition = partitionIt(numbers, left, right, pivot);
			recQuickSort(numbers, left, partition - 1);
			recQuickSort(numbers, partition + 1, right);
		}
	}
	
	private static int split(AdtArray numbers, int left, int right) {
		int pivot = getPivot(numbers, left, right);
		
		if(numbers.getA(left) > numbers.getA(pivot))
			swap(numbers, left, pivot);
		
		if(numbers.getA(left) > numbers.getA(right))
			swap(numbers, left, right);
		
		if(numbers.getA(pivot) > numbers.getA(right))
			swap(numbers, pivot, right);
		
		swap(numbers, pivot, right - 1);
		
		for(int i = 0; i < numbers.lengthA(); i++) {
			System.err.print(numbers.getA(i) + " - ");
		}
		System.err.println();
		
		return numbers.getA(right - 1);
	}
	
	private static void swap(AdtArray numbers, int dex1, int dex2) {
		int temp = numbers.getA(dex1);
		numbers.setA(dex1, numbers.getA(dex2));
		numbers.setA(dex2, temp);
	}
	
	/* Schaufelt alle Elemente kleiner dem Pivot nach links vom Pivot */
	private static int partitionIt(AdtArray numbers, int left, int right, int pivot) {
		int leftPtr = left;
		int rightPtr = right - 1;
		while(true) {
			while(numbers.getA(++leftPtr) < pivot);
			while(numbers.getA(--rightPtr) > pivot);
			if(leftPtr >= rightPtr)
				break;
			else
				swap(numbers, leftPtr, rightPtr);
		}
		swap(numbers, leftPtr, right - 1);
		
		for(int i = 0; i < numbers.lengthA(); i++) {
			System.err.print(numbers.getA(i) + " - ");
		}
		System.err.println();
		
		return leftPtr;
	}
}
