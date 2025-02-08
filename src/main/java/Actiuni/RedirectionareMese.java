package Actiuni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ClaseGui.MeniuPrincipal;
import ClaseRestaurant.Masa;


/**
 * Clasa pt actiunea de redirectionare la o masa specifica
 */
public class RedirectionareMese implements ActionListener {
	private MeniuPrincipal meniu;
	private Masa masa;

	/**
	 * Constructor
	 * @param mm - meniu principal
	 * @param m - masa
	 */
	public RedirectionareMese(MeniuPrincipal mm, Masa m) {
		this.meniu = mm;
		this.masa = m;
	}
	
	/**
	 * Override actiune
	 * Se va schimba din meniul principal la meniul unei singure mese
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		meniu.generareMasaCurenta(masa);
	}
}
