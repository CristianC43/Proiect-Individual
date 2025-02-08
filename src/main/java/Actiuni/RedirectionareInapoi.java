package Actiuni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import ClaseGui.MeniuPrincipal;
import ClaseRestaurant.Masa;

/**
 * Clasa pentru actiunea de intoarcere la meniul principal
 */
public class RedirectionareInapoi implements ActionListener {
	private MeniuPrincipal meniu;
	private ArrayList<Masa> listaMese;

	/**
	 * Constructor
	 * @param mm - meniu principal
	 * @param lm - lista mese
	 */
	public RedirectionareInapoi(MeniuPrincipal mm, ArrayList<Masa> lm) {
		this.meniu = mm;
		this.listaMese = lm;
	}
	
	/**
	 * Override Actiune 
	 * Se va intoarce la meniul principal
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		meniu.generareMese(listaMese);
	}
}
