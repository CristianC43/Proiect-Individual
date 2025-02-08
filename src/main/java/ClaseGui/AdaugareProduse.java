package ClaseGui;

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

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		f.setSize(500, 500);
		f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	f.setVisible(false);
            	meniu.generareMasaCurenta(m);
            }
        });

		
		JPanel produse = new JPanel();
		produse.setLayout(new BoxLayout(produse, BoxLayout.Y_AXIS));
		// Citire csv
		File file = new File("C:\\Users\\craci\\Desktop\\p3-proiect-gr1-CristianC43\\src\\main\\resources\\produse.csv");
		FileReader r = null;
		try {
			r = new FileReader(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		CSVReader reader = new CSVReader(r);
		for(int i = 0; i < 5; i++) { // hardcodat 5 produse
			
			try {
				String[] prodCsv = reader.readNext();
				Produs prodEfectiv = new Produs(prodCsv[0], Double.valueOf(prodCsv[1]));
				JLabel prod = new JLabel(prodCsv[0] + " " + prodCsv[1]);
				
				// Adaugare produse la click
				prod.addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                	m.addProdus(prodEfectiv);
	                	System.out.println(prod.getText());
	                }
				}
				);
				produse.add(prod);
			} catch (CsvValidationException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		// Adaugare produse din csv
		JScrollPane scrollProduse = new JScrollPane(produse);
		f.setContentPane(scrollProduse);
		f.setVisible(true);
	}
	
}
