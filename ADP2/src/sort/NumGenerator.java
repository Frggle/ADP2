package sort;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import adt.implementations.AdtArrayImpl;
import adt.interfaces.AdtArray;

public class NumGenerator {
	/**
	 * Marc
	 * InsertionSort mit zahlen.dat = 1.530.204 ms
	 * QuickSort mit zahlen.dat (mit InsertionSort Messung) = XY ms
	 * QuickSort mit zahlen.dat (ohne InsertionSort Messung) = XY ms
	 * 
	 * Constantin
	 * InsertionSort mit zahlen.dat = 622.092 ms
     * QuickSort mit zahlen.dat (mit InsertionSort Messung) = XY ms
     * QuickSort mit zahlen.dat (ohne InsertionSort Messung) = XY ms
     * 
	 */
		
	
	// TODO: nach dem implementieren entfernen. Nur zum Testen
	public static void main(String args[]) {
	
	 NumGenerator.sortNum("testAlreadySortedDesc_30", 30, true);
	}
	
	// TODO: sortNum Methoden haben zu viel Redundanz -> ueberarbeiten
	
	/**
	 * Erzeugt eine Datei mit der angegebenen Anzahl an zufaelligen Zahlen. Die Zahlen werden hintereinander weg
	 * geschrieben und durch ein Leerzeichen getrennt. Die Range der zufaelligen Zahlen ist 1..(quantity * 5)
	 * @param filename, Dateiname (Endung *.dat wird automatisch ergaenzt)
	 * @param quantity, die Anzahl der zufaellig generierten Zahlen
	 */
	public static void sortNum(String filename, int quantity) {
		
		File file = new File(filename + ".dat");
		if(file.exists()) {
			System.err.println("Die Datei existiert bereits");
		} else {
			try {
				file.createNewFile();
			} catch(IOException e) {
				e.printStackTrace();
			}
			BufferedWriter bw = null;
			try {
				FileWriter fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
				
				ThreadLocalRandom random = ThreadLocalRandom.current();
				
				for(int i = 1; i < quantity; i++) {
					bw.write(String.valueOf(random.nextInt(1, quantity * 5)) + " ");
				}
				bw.write(String.valueOf(random.nextInt(1, quantity * 5)));	// nach der letzten Zahl folgt kein Space
				System.err.println("Die Datei " + filename + ".dat wurde erfolgreich erstellt");
			} catch(IOException e) {
				e.printStackTrace();
			} finally {
				try {
					bw.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Erzeugt eine Datei mit der angegebenen Anzahl in sortierter Reihenfolge. Die Zahlen werden hintereinander weg
	 * geschrieben und durch ein Leerzeichen getrennt. Die Range der zufaellig generierten Zahlen ist 1..(quantity * 5)
	 * @param filename, Dateiname (Endung *.dat wird automatisch ergaenzt)
	 * @param quantity, die Anzahl der zufaellig generierten Zahlen
	 * @param desc, true = absteigend sortiert; false = aufsteigend sortiert
	 */
	public static void sortNum(String filename, int quantity, boolean desc) {
		
		File file = new File(filename + ".dat");
		if(file.exists()) {
			System.err.println("Die Datei existiert bereits");
		} else {
			try {
				file.createNewFile();
			} catch(IOException e) {
				e.printStackTrace();
			}
			BufferedWriter bw = null;
			try {
				FileWriter fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
				
				ThreadLocalRandom random = ThreadLocalRandom.current();
				List<Integer> list = new ArrayList<Integer>();
				for(int i = 1; i <= quantity; i++) {
					list.add(random.nextInt(1, quantity * 5));
				}
				
				Collections.sort(list);
				
				if(desc) {
					Collections.reverse(list);
					for(int i = 1; i < list.size(); i++) {
						bw.write(String.valueOf(list.get(i)) + " ");
					}
					bw.write(String.valueOf(list.get(list.size() - 1)));
				} else {
					for(int i = 1; i < list.size(); i++) {
						bw.write(String.valueOf(list.get(i)) + " ");
					}
					bw.write(String.valueOf(list.get(list.size() - 1)));
				}
				
				System.err.println("Die Datei " + filename + ".dat wurde erfolgreich erstellt");
			} catch(IOException e) {
				e.printStackTrace();
			} finally {
				try {
					bw.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Liest die Datei ein und fuegt die Zahlen dem ADTArray Objekt hinzu. Die Datei muss mit WhiteSpaces geschrieben
	 * sein -> zwischen zwei Integer.
	 * @param filename, der Dateiname (die Endung *.dat wurde automatisch ergaenzt)
	 * @return AdtArray, ein ADTArray Objekt
	 */
	public static AdtArray readNum(String filename) {
		
		AdtArray array = AdtArrayImpl.initA();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileReader(filename + ".dat"));
			while(scanner.hasNextInt()) {
				array.setA(array.lengthA(), scanner.nextInt());
			}
		} catch(FileNotFoundException e) {
			System.err.println("Die Datei " + filename + " wurde nicht gefunden!");
		} finally {
			scanner.close();
		}
		return array;
	}
}
