package Clase;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import ClaseGui.MeniuPrincipal;

/**
 * Clasa main
 */
public class Main {
	/**
	 * Metoda de start a programului
	 * @param args - nu este folosit
	 */
	public static void main(String[] args) {
		ArrayList<Masa> listaMese = new ArrayList<Masa>();		
		
		// Deserializare 
		try (FileInputStream file = new FileInputStream("src/main/resources/data.ser")) {
			ObjectInputStream reader = new ObjectInputStream(file);
			listaMese = (ArrayList<Masa>) reader.readObject();
	
			reader.close();
			file.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
		}
		
		// Initializare interfata
		MeniuPrincipal menu = new MeniuPrincipal(listaMese);
		menu.generareMese(listaMese);
		
	}
	
//	private static void initializareMese(ArrayList<Masa> listaMese){
//		int i;
//		for( i = 0; i < 12; i++)  // Hardcodat 12
//			listaMese.add(new Masa(i));
//		for( i = 0; i < 2; i++)
//			listaMese.get(3).addProdus(new Produs("Apa plata", 3)); // temp
//		for( i = 0; i < 12; i++)
//			listaMese.get(7).addProdus(new Produs("Apa minerala", 3.5)); // temp
//		
//	}
	
}
