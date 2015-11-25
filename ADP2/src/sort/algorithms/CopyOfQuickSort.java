package sort.algorithms;

import java.util.concurrent.ThreadLocalRandom;
import sort.MethodPivot;
import adt.implementations.AdtArrayImpl;
import adt.interfaces.AdtArray;

public class CopyOfQuickSort {
    /**
     * Wenn der Pivot mit LEFT oder RIGHT angegeben wird, dann laueft der
     * Algorithmus in eine Endlosschleife. Grund: bspw. bei LEFT Wenn das linke
     * Element das groesste Element des Teilarrays ist, kann das Array nicht
     * aufgeteilt werden (vorausgesetzt die Laenge ist groesser gleich 12).
     * Loesungsidee: es muss jedes Mal geprueft werden, ob das Pivot-Element das
     * groesste ist, wenn dann nimm naechstes Element.
     */

  
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
            if(numbers.lengthA() == 0) {
                return 0;
            } else {
                return numbers.getA(random.nextInt(0, numbers.lengthA()));
            }
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
            if(!allEquals && leftArray.lengthA() > 0) {
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
            if (!allEquals && rightArray.lengthA() > 0) {
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
}
