package Actiuni;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ClaseGui.MeniuPrincipal;
import ClaseRestaurant.Masa;

/**
 * Implementare actiune calcul nota de plata
 */
public class CalculNotaDePlata implements ActionListener{
	private Masa masa;
	private MeniuPrincipal meniu;
	private ArrayList<Masa> listaMese;
	/**
	 * Constructor
	 * @param m - masa curenta
	 * @param meniu - meniul
	 * @param listaMese - lista tuturor meselor
	 */
	public CalculNotaDePlata(Masa m, MeniuPrincipal meniu, ArrayList<Masa> listaMese) {
		this.masa = m;
		this.meniu = meniu;
		this.listaMese = listaMese;
	}
	/**
	 * Override actiune
	 * Se va afisa o alerta cu nota de plata
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String nota =  masa.calculNota() + " lei";
		System.out.println(nota); // log
		JOptionPane.showMessageDialog(null, nota);
		meniu.generareMese(listaMese);
	}	
	
}
