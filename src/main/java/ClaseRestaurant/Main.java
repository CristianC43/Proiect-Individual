package ClaseRestaurant;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import ClaseGui.MeniuPrincipal;

public class Main {
    public static Connection connection; // folosit ca variabila globala
    public static void main(String[] args) {
		ArrayList<Masa> listaMese = new ArrayList<Masa>(); // lista mese

		// citire fisier json pentru autentificare
		File file = new File("src/main/resources/confidential.json");     
		StringBuilder jsonContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = reader.readLine()) != null) { // citesc pana la finalul fisierului
		    	jsonContent.append(line);
		    }
        } catch (IOException e) {
        	e.printStackTrace();

        }
        
        // parsare date
        String url=null, username=null, password=null;  
		try {
            JSONObject js = new JSONObject(jsonContent.toString());
		    url = js.getString("url");
            username = js.getString("username");
		    password = js.getString("password");
		} catch (JSONException e) {
            e.printStackTrace();		        
		}
		 

		 System.out.println("Se conecteaza la baza de date..."); // log

		 // conectare la baza de date si preluare de informatii
		 try {
			 connection = DriverManager.getConnection(url, username, password);
			 System.out.println("Conexiune realizata!");
		     Statement stm = connection.createStatement();
		     String query = "SELECT * FROM MESE;"; // query preluare mese
		     ResultSet results = stm.executeQuery(query);
		     while(results.next()) { // parcurg toate randurile din tabel si setez mesele
		    	 listaMese.add(new Masa(results.getInt(1)));
		     }
		     // query preluare comenzi + preturi produse
		     query = "SELECT ID, COMENZI.NUME, PRET, CANTITATE FROM COMENZI JOIN PRODUSE ON COMENZI.NUME = PRODUSE.NUME;"; 
		     results = stm.executeQuery(query);
		     while(results.next()) { // parcurg toate randurile din tabel si setez comenzile
		    	 listaMese.get(results.getInt(1)-1).setProdus(new Produs(results.getString(2),results.getDouble(3)), results.getInt(4));
		     }
		 }	
		 catch (SQLException e) {
		     throw new IllegalStateException("Eroare la conexiune!", e);
		 }	

		// Initializare interfata
		MeniuPrincipal menu = new MeniuPrincipal(listaMese);
		menu.generareMese(listaMese);
		
	}

}
