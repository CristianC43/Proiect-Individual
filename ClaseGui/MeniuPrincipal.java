package ClaseGui;
import javax.swing.*;

import Actiuni.AdaugareProduse;
import Actiuni.CalculNotaDePlata;
import Actiuni.RedirectionareInapoi;
import Actiuni.RedirectionareMese;
import Actiuni.StergereProduse;
import ClaseRestaurant.Main;
import ClaseRestaurant.Masa;
import ClaseRestaurant.Produs;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map.Entry;

/**
 * Clasa interfata meniu principal
 */
public class MeniuPrincipal{
	private JFrame frame = new JFrame();
	private ArrayList<JButton> butoane = new ArrayList<JButton>();
	private ArrayList<Masa> listaMese;
	
	/**
	 * Constructor
	 * @param listaMese
	 */
	public MeniuPrincipal(ArrayList<Masa> listaMese) {
		// Comenzi apelate la iesire
		// Salvare bd
		
		this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ev) { // actiune inchidere fereastra
            	try {
	            	Statement statement = Main.connection.createStatement();
					String query = "DELETE FROM COMENZI WHERE 1=1;"; // query ce sterge toate comenzile
					statement.executeUpdate(query);
					
					query = "INSERT INTO COMENZI VALUES(?,?,?);"; // query ce insereaza o comanda 
					PreparedStatement statementPrepared = Main.connection.prepareStatement(query);
					for(Masa m: listaMese) {
						statementPrepared.setInt(1, m.getId());
						for(Entry<Produs,Integer> entry: m.getProduse().entrySet()) {
							statementPrepared.setString(2, entry.getKey().getNume());
							statementPrepared.setInt(3, entry.getValue());
							statementPrepared.executeUpdate();
						}
					}
					System.out.println("Datele au fost salvate"); // log
	            	System.exit(0); 
            	} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }); 
		
		// Specificatii
		this.frame.setMinimumSize(new Dimension(550,500));
		this.frame.setVisible(true);
		this.listaMese = listaMese;
	}

	/**
	 *  Metoda de generare a meniului de mese
	 * @param listaMese
	 */
	public void generareMese(ArrayList<Masa> listaMese) {
		JPanel listaMeseGui = new JPanel();
		listaMeseGui.setLayout(new GridLayout(3,3,50,50));
		for(Masa m: listaMese) { // iterare fiecare masa
			JButton buton = new JButton(m.toString());
			// Verificare status masa
			if(!m.getEsteOcupata()) 
				buton.setBackground(Color.GREEN);
			else 
				buton.setBackground(Color.RED);
			buton.addActionListener(new RedirectionareMese(this,m));
			this.butoane.add(buton);
			listaMeseGui.add(buton); 
		}
		// Update
		this.frame.setTitle("Lista Mese");
		this.frame.setContentPane(listaMeseGui);
        this.frame.revalidate();
        this.frame.repaint();
	}

	/**
	 * Metoda de generare a mesei curente
	 * @param m - masa curenta
	 */
	public void generareMasaCurenta(Masa m) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.GRAY);
		JPanel produsePanel = new JPanel();
		JPanel butoane = new JPanel();
		
		// Butoane functionalitati
		JButton butonAdd = new JButton("Adauga produse");
		JButton butonPlata = new JButton("Realizeaza nota de plata");
		JButton butonStergere = new JButton("Stergere produse");
		JButton butonBack = new JButton("Inapoi");
		
		// Adaugare actiuni
		butonBack.addActionListener(new RedirectionareInapoi(this, listaMese));
		butonAdd.addActionListener(new AdaugareProduse(m, this)); // adaugare produs
		butonPlata.addActionListener(new CalculNotaDePlata(m, this, listaMese));
		butonStergere.addActionListener(new StergereProduse(m, this));
		
		// Plasare butoane
		butoane.setLayout(new FlowLayout());
		butoane.setBackground(Color.GRAY);
		butoane.add(butonBack);
		butoane.add(butonAdd);
		butoane.add(butonPlata);
		butoane.add(butonStergere);
		
		
        produsePanel.setLayout(new BoxLayout(produsePanel, BoxLayout.Y_AXIS));
        
        // Listare produse
	    for(Entry<Produs, Integer> entry: m.getProduse().entrySet()) {
	    	JLabel prodLabel = new JLabel(entry.getKey().toString() + "   x " + String.valueOf(entry.getValue()));  
	    	prodLabel.setFont(prodLabel.getFont().deriveFont(20f));
	    	produsePanel.add(prodLabel);
	    }
	        
	    // wrap jpanel in jscrollpanel
        JScrollPane produseMasa = new JScrollPane(produsePanel);
        produseMasa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        produseMasa.setPreferredSize(new Dimension(400, 300));

        
        panel.add(produseMasa, BorderLayout.CENTER);		
		panel.add(butoane, BorderLayout.SOUTH);

		// Update
		
		this.frame.setTitle("Masa " + m.getId());
		this.frame.setContentPane(panel);
		this.frame.repaint();
		this.frame.revalidate();
	}
	
	
	/**
	 * Getter lista mese
	 * @return listaMese
	 */
	public ArrayList<Masa> getListaMese() {
		return this.listaMese;
	}
}
