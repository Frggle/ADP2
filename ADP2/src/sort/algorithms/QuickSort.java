package sort.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import sort.AccessCount;
import sort.MethodPivot;
import adt.implementations.AdtArrayImpl;
import adt.interfaces.AdtArray;

public class QuickSort {
    /**
     * Wenn der Pivot mit LEFT oder RIGHT angegeben wird, dann laueft der
     * Algorithmus in eine Endlosschleife. Grund: bspw. bei LEFT Wenn das linke
     * Element das groesste Element des Teilarrays ist, kann das Array nicht
     * aufgeteilt werden (vorausgesetzt die Laenge ist groesser gleich 12).
     * Loesungsidee: es muss jedes Mal geprueft werden, ob das Pivot-Element das
     * groesste ist, wenn dann nimm naechstes Element.
     */

    /* Variablen */
    private static List<Long> insertionSortRuntime = new ArrayList<Long>();
    private static int readAccessCount = 0;
    private static int writeAccessCount = 0;

    /**
     * Berechent den Pivot-Wert, als Integer, fuer ein Array und einem String
     * 
     * @param numbers, das Array woraus der Pivot berechnet wird
     * @param pivot, ein String welches in ein Pivot-Enum gewandelt wird
     * @return int, der errechnete Pivot aus dem Array
     */
    private static int getPivot(AdtArray numbers, String pivot) {
        if (MethodPivot.valueOf(pivot) == MethodPivot.LEFT) {
            return numbers.getA(0);
        } else if (MethodPivot.valueOf(pivot) == MethodPivot.RIGHT) {
            return numbers.getA(numbers.lengthA() - 1);
        } else if (MethodPivot.valueOf(pivot) == MethodPivot.MEDIANOF3) {
            int left = numbers.getA(0);
            int right = numbers.getA(numbers.lengthA() - 1);

            return (left + right) / 2;
        } else {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            return numbers.getA(random.nextInt(0, numbers.lengthA()));
        }
    }

    /**
     * Sortiert ein Array mit Hilfe von InsertionSort
     * 
     * @param numbers, das, aufsteigend, zu sortierende Array 
     * @param pivot, der Pivot als String
     * @return AdtArray, das destruktiv sortierte Array
     */
    public static AdtArray sort(AdtArray numbers, String pivot) {
        AdtArray result = AdtArrayImpl.initA();
        sortRec(numbers, result, pivot);

        return result;
    }

    /**
     * Die Rekursion um ein Array aufzuteilen und ggf. sortieren zu lassen
     * 
     * @param numbers, das Array welches geteilt und sortiert wird
     * @param result, das Ergebnisarray, welches nach und nach sortiert wird
     * @param pivot, der Pivot als String
     */
    private static void sortRec(AdtArray numbers, AdtArray result, String pivot) {
        int _pivot = getPivot(numbers, pivot);

        AdtArray leftArray = AdtArrayImpl.initA();
        AdtArray rightArray = AdtArrayImpl.initA();

        for (int i = 0; i < numbers.lengthA(); i++) {
            int elem = numbers.getA(i);
            if (elem <= _pivot) {
                leftArray.setA(leftArray.lengthA(), elem);
            } else {
                rightArray.setA(rightArray.lengthA(), elem);
            }
        }
        AdtArray temp = null;
        boolean allEquals = allEquals(leftArray);
        if (leftArray.lengthA() < 12 || allEquals) {
            if (!allEquals) {
                temp = InsertionSort.sort(leftArray, 1, leftArray.lengthA());
            } else {
                temp = leftArray;
            }

            for (int i = 0; i < temp.lengthA(); i++) {
                result.setA(result.lengthA(), temp.getA(i));
            }
        } else {
            sortRec(leftArray, result, pivot);
        }
        allEquals = allEquals(rightArray);
        if (rightArray.lengthA() < 12 || allEquals) {
            if (!allEquals) {
                temp = InsertionSort.sort(rightArray, 1, rightArray.lengthA());
            } else {
                temp = rightArray;
            }

            for (int i = 0; i < temp.lengthA(); i++) {
                result.setA(result.lengthA(), temp.getA(i));
            }
        } else {
            sortRec(rightArray, result, pivot);
        }
    }

    /**
     * Sortiert ein Array mit Hilfe von InsertionSort und misst die Laufzeit, exklusiv von InsertionSort
     * 
     * @param numbers, das, aufsteigend, zu sortierende Array 
     * @param pivot, der Pivot als String
     * @return Long, die gemessene Gesamtlaufzeit, ohne InsertionSort-Anteil
     */
    public static long sortRuntime(AdtArray numbers, String pivot) {
        long runtimeStart = System.currentTimeMillis();

        AdtArray result = AdtArrayImpl.initA();
        sortRecWithRuntime(numbers, result, pivot);

        long runtime = System.currentTimeMillis() - runtimeStart;

        for (Long time : insertionSortRuntime) {
            runtime -= time;
        }

        return runtime;
    }

    /**
     * Die Rekursion um ein Array aufzuteilen und ggf. sortieren zu lassen. Misst zusaetzlich die Laufzeit
     * 
     * @param numbers, das Array welches geteilt und sortiert wird
     * @param result, das Ergebnisarray, welches nach und nach sortiert wird
     * @param pivot, der Pivot als String
     */
    private static void sortRecWithRuntime(AdtArray numbers, AdtArray result, String pivot) {
        int _pivot = getPivot(numbers, pivot);

        AdtArray leftArray = AdtArrayImpl.initA();
        AdtArray rightArray = AdtArrayImpl.initA();

        for (int i = 0; i < numbers.lengthA(); i++) {
            int elem = numbers.getA(i);
            if (elem <= _pivot) {
                leftArray.setA(leftArray.lengthA(), elem);
            } else {
                rightArray.setA(rightArray.lengthA(), elem);
            }
        }
        AdtArray temp = null;
        boolean allEquals = allEquals(leftArray);
        if (leftArray.lengthA() < 12 || allEquals) {
            if (!allEquals) {
                long start = System.currentTimeMillis();
                temp = InsertionSort.sort(leftArray, 1, leftArray.lengthA());
                insertionSortRuntime.add(System.currentTimeMillis() - start);
            } else {
                temp = leftArray;
            }

            for (int i = 0; i < temp.lengthA(); i++) {
                result.setA(result.lengthA(), temp.getA(i));
            }
        } else {
            sortRecWithRuntime(leftArray, result, pivot);
        }
        allEquals = allEquals(rightArray);
        if (rightArray.lengthA() < 12) {
            if (!allEquals) {
                long start = System.currentTimeMillis();
                temp = InsertionSort.sort(rightArray, 1, rightArray.lengthA());
                insertionSortRuntime.add(System.currentTimeMillis() - start);
            } else {
                temp = rightArray;
            }
            for (int i = 0; i < temp.lengthA(); i++) {
                result.setA(result.lengthA(), temp.getA(i));
            }
        } else {
            sortRecWithRuntime(rightArray, result, pivot);
        }
    }

    /**
     * Sortiert ein Array mit Hilfe von InsertionSort und misst die Zugriffe, exklusiv von InsertionSort
     * 
     * @param numbers, das, aufsteigend, zu sortierende Array 
     * @param pivot, der Pivot als String
     * @return AccessCount, die gezaehlten Zugriffe, ohne InsertionSort-Anteil
     */
    public static AccessCount sortAccessCount(AdtArray numbers, String pivot) {
        AdtArray result = AdtArrayImpl.initA();
        sortRecWithAccessCount(numbers, result, pivot);
        
        return new AccessCount(readAccessCount, writeAccessCount);
    }
    
    /**
     * Die Rekursion um ein Array aufzuteilen und ggf. sortieren zu lassen. Misst zusaetzlich die Zugriffe
     * 
     * @param numbers, das Array welches geteilt und sortiert wird
     * @param result, das Ergebnisarray, welches nach und nach sortiert wird
     * @param pivot, der Pivot als String
     */
    private static void sortRecWithAccessCount(AdtArray numbers, AdtArray result, String pivot) {
        int _pivot = getPivot(numbers, pivot);

        AdtArray leftArray = AdtArrayImpl.initA();
        AdtArray rightArray = AdtArrayImpl.initA();

        for (int i = 0; i < numbers.lengthA(); i++) {
            int elem = numbers.getA(i);
            readAccessCount++;
            if (elem <= _pivot) {
                leftArray.setA(leftArray.lengthA(), elem);
                writeAccessCount++;
            } else {
                rightArray.setA(rightArray.lengthA(), elem);
                writeAccessCount++;
            }
        }
        AdtArray temp = null;
        boolean allEquals = allEqualsWithAccessCount(leftArray);
        if (leftArray.lengthA() < 12 || allEquals) {
            if (!allEquals) {
                temp = InsertionSort.sort(leftArray, 1, leftArray.lengthA());
            } else {
                temp = leftArray;
            }

            for (int i = 0; i < temp.lengthA(); i++) {
                result.setA(result.lengthA(), temp.getA(i));
                writeAccessCount++;
            }
        } else {
            sortRecWithAccessCount(leftArray, result, pivot);
        }
        allEquals = allEqualsWithAccessCount(rightArray);
        if (rightArray.lengthA() < 12 || allEquals) {
            if (!allEquals) {
                temp = InsertionSort.sort(rightArray, 1, rightArray.lengthA());
            } else {
                temp = rightArray;
            }

            for (int i = 0; i < temp.lengthA(); i++) {
                result.setA(result.lengthA(), temp.getA(i));
                writeAccessCount++;
            }
        } else {
            sortRecWithAccessCount(rightArray, result, pivot);
        }
    }

    /**
     * Ueberprueft ein Array, ob alle Elemente identisch sind 
     * 
     * @param array, das Array welches kontrolliert wird
     * @return Boolean, true = alle identisch; false = unterschiedliche Elemente
     */
    private static boolean allEquals(AdtArray array) {
        int elem = array.getA(0);

        for (int i = 1; i < array.lengthA(); i++) {
            if (array.getA(i) != elem) {
                return false;
            }
        }

        return true;
    }
    
    /**
     * Ueberprueft ein Array, ob alle Elemente identisch sind und zaehlt zusaetzlich die Zugriffe
     * 
     * @param array, das Array welches kontrolliert wird
     * @return Boolean, true = alle identisch; false = unterschiedliche Elemente
     */
    private static boolean allEqualsWithAccessCount(AdtArray array) {
        int elem = array.getA(0);
        readAccessCount++;

        for (int i = 1; i < array.lengthA(); i++) {
            readAccessCount++;
            if (array.getA(i) != elem) {
                return false;
            }
        }

        return true;
    }
}
