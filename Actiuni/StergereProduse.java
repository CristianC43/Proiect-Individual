package Actiuni;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ClaseGui.MeniuPrincipal;
import ClaseRestaurant.Masa;
import ClaseRestaurant.Produs;

/**
 * Clasa pentru actiunea de stergere a produselor
 */
public class StergereProduse  implements ActionListener{
	
	private Masa masa;
	private MeniuPrincipal meniu;
	/**
	 * Constructor
	 * @param m - masa curenta
	 * @param meniu - meniu
	 */
	public StergereProduse(Masa m, MeniuPrincipal meniu){
		this.masa = m;
		this.meniu = meniu;
	}
	
	/**
	 * Override actiune
	 * Se va afisa o interfata ce lasa utilizatorul sa stearge produse
	 */
	@Override
	public void actionPerformed(ActionEvent ev) 	{
		JFrame frame = new JFrame();
		frame.setSize(400,400);
		frame.setTitle("Produse curente");
		frame.setMinimumSize(new Dimension(400,400));

		
		JPanel produseInitiale = new JPanel();
		produseInitiale.setLayout(new BoxLayout(produseInitiale, BoxLayout.Y_AXIS));
		
		// Listare produse de la masa curenta
	    for(Entry<Produs, Integer> entry: masa.getProduse().entrySet()) {
	    	JLabel prodLabel = new JLabel(entry.getKey().toString());  
	    	Produs produs = new Produs(entry.getKey().getNume( ), entry.getKey().getPret());
	    	prodLabel.addMouseListener(new MouseAdapter() { // Actiune de stergere la click
                @Override
                public void mouseClicked(MouseEvent ev) {
                	masa.deleteProd(produs);
                	System.out.println(prodLabel.getText() + " sters"); // log
                	meniu.generareMasaCurenta(masa);
                }
			});
	    	produseInitiale.add(prodLabel);
	    }
		
	    JScrollPane scrollProduse = new JScrollPane(produseInitiale);
		scrollProduse.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// Update
		scrollProduse.revalidate();
		frame.setContentPane(scrollProduse);
		frame.setVisible(true);
	}
}
