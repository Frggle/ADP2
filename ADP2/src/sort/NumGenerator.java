package sort;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import adt.implementations.AdtArrayImpl;
import adt.interfaces.AdtArray;

public class NumGenerator {
	
	// TODO: entfernen, weil Methoden static?!
	public NumGenerator() {
	}
	
	// TODO: nach dem implementieren entfernen. Nur zum Testen
	public static void main(String args[]) {
		NumGenerator gen = new NumGenerator();
		gen.sortNum("test", 10);
		
		AdtArray array =gen.readNum("test");
		System.err.println(array.getA(0));
		System.err.println(array.getA(array.lengthA()-1));
		System.err.println(array.lengthA());
	}
	
	/**
	 * Erzeugt eine Datei mit der angegebenen Anzahl an zufaelligen Zahlen. Die Zahlen werden hintereinander weg
	 * geschrieben und durch ein Leerzeichen getrennt. Die Range der zufaelligen Zahlen ist 1..(quantity * 5)
	 * @param filename, Dateiname (Endung *.dat wird automatisch ergaenzt)
	 * @param quantity, die Anzahl der zufaellig generierten Zahlen
	 */
	public void sortNum(String filename, int quantity) {
		
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
	
	public void sortNum(String filename, int quantity, boolean desc) {
		
	}
	
	/**
	 * Liest die Datei ein und fuegt die Zahlen dem ADTArray Objekt hinzu.
	 * Die Datei muss mit WhiteSpaces geschrieben sein -> zwischen zwei Integer 
	 * @param filename, der Dateiname (die Endung *.dat wurde automatisch ergaenzt)
	 * @return AdtArray, ein ADTArray Objekt
	 */
	public AdtArray readNum(String filename) {
		
		AdtArray array = AdtArrayImpl.initA();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileReader(filename + ".dat"));
			scanner.useDelimiter(" ");
			while(scanner.hasNext()) {
				array.setA(array.lengthA(), Integer.parseInt(scanner.next()));
			}
		} catch(FileNotFoundException e) {
			System.err.println("Die Datei " + filename + " wurde nicht gefunden!");
		} finally {
			scanner.close();
		}
		
		return array;
	}
}
