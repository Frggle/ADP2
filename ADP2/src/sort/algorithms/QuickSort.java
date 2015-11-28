package sort.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import sort.AccessCount;
import sort.MethodPivot;
import adt.interfaces.AdtArray;

public class QuickSort {
	
	/* destruktives ADTArray und der PIVOT */
	private static AdtArray _numbers;
	private static String _pivot;
	
	/* Zugriffszaehler */
	private static int writeAccessCount = 0;
	private static int readAccessCount = 0;
	
	/* List mit den Runtime-Messungen vom InsertionSort */
	private static List<Long> runtimeInsertionSort;
	
	/**
	 * Setzt das Pivot-Element nach rechts (innerhalb des aktuellen Bereichs) und gibt das Pivot-Element zurueck.
	 * @param ilinks, der StartIndex vom aktuellen Bereich
	 * @param irechts, der EndIndex vom aktuellen Bereich
	 * @return das Pivot-Element
	 */
	private static int setPivot(int ilinks, int irechts) {
		if(MethodPivot.valueOf(_pivot) == MethodPivot.LEFT) {
			swap(ilinks, irechts);
		} else if(MethodPivot.valueOf(_pivot) == MethodPivot.RANDOM) {
			ThreadLocalRandom random = ThreadLocalRandom.current();
			int i = random.nextInt(ilinks, irechts + 1);
			swap(i, irechts);
		} else if(MethodPivot.valueOf(_pivot) == MethodPivot.MEDIANOF3) {
			List<Integer> list = new ArrayList<Integer>();
			int links = _numbers.getA(ilinks);
			list.add(links);
			int rechts = _numbers.getA(irechts);
			list.add(rechts);
			int tmp = (irechts + ilinks) / 2;
			int mid = _numbers.getA(tmp);
			list.add(mid);
			Collections.sort(list);
			int tmpValue = list.get(1);
			if(tmpValue == links) {
				swap(ilinks, irechts);
			} else if(tmpValue == mid) {
				swap(tmp, irechts);
			}
		}
		return _numbers.getA(irechts);
	}
	
	/**
	 * Setzt das Pivot-Element nach rechts (innerhalb des aktuellen Bereichs) und gibt das Pivot-Element zurueck.
	 * Zusaetzlich werden die Lese- und Schreibzugriffe gezaehlt.
	 * @param ilinks, der StartIndex vom aktuellen Bereich
	 * @param irechts, der EndIndex vom aktuellen Bereich
	 * @return das Pivot-Element
	 */
	private static int setPivotWithAccessCount(int ilinks, int irechts) {
		if(MethodPivot.valueOf(_pivot) == MethodPivot.LEFT) {
			swapWithAccessCount(ilinks, irechts);
		} else if(MethodPivot.valueOf(_pivot) == MethodPivot.RANDOM) {
			ThreadLocalRandom random = ThreadLocalRandom.current();
			int i = random.nextInt(ilinks, irechts + 1);
			swapWithAccessCount(i, irechts);
		} else if(MethodPivot.valueOf(_pivot) == MethodPivot.MEDIANOF3) {
			List<Integer> list = new ArrayList<Integer>();
			int links = _numbers.getA(ilinks);
			readAccessCount++;
			list.add(links);
			int rechts = _numbers.getA(irechts);
			readAccessCount++;
			list.add(rechts);
			int tmp = (irechts + ilinks) / 2;
			int mid = _numbers.getA(tmp);
			readAccessCount++;
			list.add(mid);
			Collections.sort(list);
			int tmpValue = list.get(1);
			readAccessCount++;
			if(tmpValue == links) {
				swapWithAccessCount(ilinks, irechts);
			} else if(tmpValue == mid) {
				swapWithAccessCount(tmp, irechts);
			}
		}
		readAccessCount++;
		return _numbers.getA(irechts);
	}
	
	/**
	 * Tauscht die beiden Elemente die sich im Index i und j befinden.
	 * @param i, der eine Index, der mit j getauscht wird
	 * @param j, der andere Index, der mit i getauscht wird
	 */
	private static void swap(int i, int j) {
		int temp = _numbers.getA(i);
		_numbers.setA(i, _numbers.getA(j));
		_numbers.setA(j, temp);
	}
	
	/**
	 * Tauscht die beiden Elemente die sich im Index i und j befinden. Zusaetzlich werden die Lese- und Schreibzugriffe
	 * gezaehlt.
	 * @param i, der eine Index, der mit j getauscht wird
	 * @param j, der andere Index, der mit i getauscht wird
	 */
	private static void swapWithAccessCount(int i, int j) {
		int temp = _numbers.getA(i);
		readAccessCount++;
		_numbers.setA(i, _numbers.getA(j));
		readAccessCount++;
		writeAccessCount++;
		_numbers.setA(j, temp);
		writeAccessCount++;
	}
	
	/**
	 * Die Eingangsmethode um das Array via QuickSort aufsteigend zu sortieren. Zur Unterstuetzung kommt InsertionSort
	 * zum Einsatz.
	 * @param numbers, das destruktiv zu sortierende Array
	 * @param pivot, welches Vergleichsobjekt benutzt wird - ENUM {LEFT, RIGHT, MEDIANOF3, RANDOM}
	 */
	public static void sort(AdtArray numbers, String pivot) {
		_numbers = numbers;
		_pivot = pivot;
		
		quickSort(0, _numbers.lengthA() - 1);
	}
	
	/**
	 * Die Rekursion, in dem ein aktueller Bereich verkleinert oder bei weniger als 12 Elementen sortiert wird.
	 * @param ilinks, der StartIndex vom aktuellen Bereich
	 * @param irechts, der EndIndex vom aktuellen Bereich
	 */
	private static void quickSort(int ilinks, int irechts) {
		int size = irechts - ilinks + 1;
		if(size < 12) {
			InsertionSort.sort(_numbers, ilinks, irechts);
		} else {
			int i = quickSwap(ilinks, irechts);
			quickSort(ilinks, i - 1);
			quickSort(i + 1, irechts);
		}
	}
	
	/**
	 * Nimmt eine erste "Sortierung" vor. Groessere Elemente als Pivot kommen nach rechts und kleinere nach links.
	 * @param ilinks, der StartIndex vom aktuellen Bereich
	 * @param irechts, der EndIndex vom aktuellen Bereich
	 * @return der Index vom Pivot
	 */
	private static int quickSwap(int ilinks, int irechts) {
		int i = ilinks;
		int j = irechts - 1;
		int pivot = setPivot(ilinks, irechts);
		
		while(i <= j) {
			while((_numbers.getA(i) <= pivot) && (i < irechts))
				i++;
			while((ilinks <= j) && (_numbers.getA(j) > pivot))
				j--;
			if(i < j)
				swap(i, j);
		}
		swap(i, irechts);
		return i;
	}
	
	/**
	 * Die Eingangsmethode um das Array via QuickSort aufsteigend zu sortieren. Zur Unterstuetzung kommt InsertionSort
	 * zum Einsatz. Zusaetzlich wird die Laufzeit gemessen, der InsertionSort Anteil wird nicht beachtet.
	 * @param numbers, das destruktiv zu sortierende Array
	 * @param pivot, welches Vergleichsobjekt benutzt wird - ENUM {LEFT, RIGHT, MEDIANOF3, RANDOM}
	 * @return die gemessene Laufzeit, exklusiv InsertionSort-Anteil, in Millisec
	 */
	public static long sortWithRuntime(AdtArray numbers, String pivot) {
		runtimeInsertionSort = new ArrayList<Long>();
		long startTime = System.currentTimeMillis();
		_numbers = numbers;
		_pivot = pivot;
		
		quickSortWithRuntime(0, _numbers.lengthA() - 1);
		
		long runtime = System.currentTimeMillis() - startTime;
		for(long l : runtimeInsertionSort) {
			runtime -= l;
		}
		
		return runtime;
	}
	
	/**
	 * Die Rekursion, in dem ein aktueller Bereich verkleinert oder bei weniger als 12 Elementen sortiert wird.
	 * Zusaetzlich wird die Laufzeit gemessen, der InsertionSort Anteil wird nicht beachtet.
	 * @param ilinks, der StartIndex vom aktuellen Bereich
	 * @param irechts, der EndIndex vom aktuellen Bereich
	 */
	private static void quickSortWithRuntime(int ilinks, int irechts) {
		int size = irechts - ilinks + 1;
		if(size < 12) {
			long startTime = System.nanoTime();
			InsertionSort.sort(_numbers, ilinks, irechts);
			runtimeInsertionSort.add(System.nanoTime() - startTime);
		} else {
			int i = quickSwap(ilinks, irechts);
			quickSortWithRuntime(ilinks, i - 1);
			quickSortWithRuntime(i + 1, irechts);
		}
	}
	
	/**
	 * Die Eingangsmethode um das Array via QuickSort aufsteigend zu sortieren. Zur Unterstuetzung kommt InsertionSort
	 * zum Einsatz. Zusaetzlich werden die Lese- und Schreibzugriffe gezaehlt.
	 * @param numbers, das destruktiv zu sortierende Array
	 * @param pivot, welches Vergleichsobjekt benutzt wird - ENUM {LEFT, RIGHT, MEDIANOF3, RANDOM}
	 * @return ein AccessCount-Objekt welche die Zugriffe haelt
	 */
	public static AccessCount sortWithAccessCount(AdtArray numbers, String pivot) {
		_numbers = numbers;
		_pivot = pivot;
		
		quickSortWithAccessCount(0, _numbers.lengthA() - 1);
		
		return new AccessCount(readAccessCount, writeAccessCount);
	}
	
	/**
	 * Die Rekursion, in dem ein aktueller Bereich verkleinert oder bei weniger als 12 Elementen sortiert wird.
	 * Zusaetzlich werden die Lese- und Schreibzugriffe gezaehlt.
	 * @param ilinks, der StartIndex vom aktuellen Bereich
	 * @param irechts, der EndIndex vom aktuellen Bereich
	 */
	private static void quickSortWithAccessCount(int ilinks, int irechts) {
		int size = irechts - ilinks + 1;
		if(size < 12) {
			InsertionSort.sort(_numbers, ilinks, irechts);
		} else {
			int i = quickSwapWithAccessCount(ilinks, irechts);
			quickSortWithAccessCount(ilinks, i - 1);
			quickSortWithAccessCount(i + 1, irechts);
		}
	}
	
	/**
	 * Nimmt eine erste "Sortierung" vor. Groessere Elemente als Pivot kommen nach rechts und kleinere nach links.
	 * Zusaetzlich werden die Lese- und Schreibzugriffe gezaehlt.
	 * @param ilinks, der StartIndex vom aktuellen Bereich
	 * @param irechts, der EndIndex vom aktuellen Bereich
	 * @return der Index vom Pivot
	 */
	private static int quickSwapWithAccessCount(int ilinks, int irechts) {
		int i = ilinks;
		int j = irechts - 1;
		int pivot = setPivotWithAccessCount(ilinks, irechts);
		
		while(i <= j) {
			while((_numbers.getA(i) <= pivot) && (i < irechts))
				readAccessCount++;
			i++;
			while((ilinks <= j) && (_numbers.getA(j) > pivot))
				readAccessCount++;
			j--;
			if(i < j)
				swapWithAccessCount(i, j);
		}
		swapWithAccessCount(i, irechts);
		return i;
	}
}
