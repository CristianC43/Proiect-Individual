package ClaseGui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import Clase.Masa;
import Clase.Produs;

/**
 * Implementare actiune adaugare mese
 */
public class AdaugareProduse implements ActionListener {
	private Masa m;
	private MeniuPrincipal meniu;
	/**
	 * Constructor
	 * @param m - masa
	 * @param meniu
	 */
	public AdaugareProduse(Masa m, MeniuPrincipal meniu) {
		this.m = m;
		this.meniu = meniu;
	}
	/**
	 * Actiune
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame f = new JFrame();
		f.setSize(400, 400);
		f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	f.setVisible(false);
            	meniu.generareMasaCurenta(m);
            }
        });

		
		JPanel produse = new JPanel();
		produse.setLayout(new BoxLayout(produse, BoxLayout.Y_AXIS));
		
		File file = new File("src/main/resources/produse.csv");
		FileReader r = null;
		try {
			r = new FileReader(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		
		 String url = "jdbc:mysql://localhost:3306/RESTAURANT";
		 String username = "angajat";
		 String password = "1234";

		 System.out.println("Connecting database ...");

		 try (Connection connection = DriverManager.getConnection(url, username, password)) {
		     System.out.println("Database connected!");
		     Statement stm = connection.createStatement();
		     String queryListareProduse = "SELECT * FROM PRODUSE;";
		     ResultSet results = stm.executeQuery(queryListareProduse);
		     while(results.next()){
				Produs prodEfectiv = new Produs(results.getString(1), results.getDouble(2));
				JLabel prodLabel = new JLabel(prodEfectiv.toString());
					
					// Adaugare produse la click
					prodLabel.addMouseListener(new MouseAdapter() {
		                @Override
		                public void mouseClicked(MouseEvent e) {
		                	m.addProdus(prodEfectiv);
		                	System.out.println(prodEfectiv);
		                	meniu.generareMasaCurenta(m);
		                }
					}
					);
					produse.add(prodLabel);	
			}
	     	results.close();
			JScrollPane scrollProduse = new JScrollPane(produse);
			scrollProduse.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollProduse.revalidate();
			f.setContentPane(scrollProduse);
			f.setVisible(true);
		 }
		 catch (SQLException ec) {
		     throw new IllegalStateException("Cannot connect the database!", ec);
		 }	
	}
	
	
}
