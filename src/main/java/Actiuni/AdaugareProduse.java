package Actiuni;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ClaseGui.MeniuPrincipal;
import ClaseRestaurant.Main;
import ClaseRestaurant.Masa;
import ClaseRestaurant.Produs;

/**
 * Implementare actiune adaugare mese
 */
public class AdaugareProduse implements ActionListener {
	private Masa masa;
	private MeniuPrincipal meniu;
	/**
	 * Constructor
	 * @param m - masa
	 * @param meniu
	 */
	public AdaugareProduse(Masa m, MeniuPrincipal meniu) {
		this.masa = m;
		this.meniu = meniu;
	}
	/**
	 * Actiune override
	 * Se va afisa o interfata pentru a adauga produse
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame frame = new JFrame();
		frame.setSize(400, 400);
		frame.setTitle("Poduse");
		frame.setMinimumSize(new Dimension(400,400));

		frame.addWindowListener(new WindowAdapter() { // inchidere
            @Override
            public void windowClosing(WindowEvent e) {
            	frame.setVisible(false);
            	meniu.generareMasaCurenta(masa);
            }
        });

		
		JPanel produse = new JPanel();
		produse.setLayout(new BoxLayout(produse, BoxLayout.Y_AXIS));
		

		 try{
		     Statement statement = Main.connection.createStatement();
		     String queryListareProduse = "SELECT * FROM PRODUSE;"; // query selectare produse
		     ResultSet results = statement.executeQuery(queryListareProduse);
		     while(results.next()){
				Produs prodEfectiv = new Produs(results.getString(1), results.getDouble(2));
				JLabel prodLabel = new JLabel(prodEfectiv.toString());
					
					// Adaugare produse la click
					prodLabel.addMouseListener(new MouseAdapter() { // Actiune la click de a adauga un produs la o masa
		                @Override
		                public void mouseClicked(MouseEvent e) {
		                	masa.addProdus(prodEfectiv);
		                	System.out.println(prodEfectiv + " adaugat"); //log
		                	meniu.generareMasaCurenta(masa);
		                }
					}
					);
					produse.add(prodLabel);	
			}
	     	results.close();
			JScrollPane scrollProduse = new JScrollPane(produse);
			scrollProduse.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollProduse.revalidate();
			frame.setContentPane(scrollProduse);
			frame.setVisible(true);
		 }
		 catch (SQLException ex) {
		     throw new IllegalStateException("Nu se poate conecta la baza de date!", ex);
		 }	
	}
	
	
}
