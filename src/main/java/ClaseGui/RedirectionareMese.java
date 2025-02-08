package ClaseGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Clase.Masa;
import Clase.Main;

/**
 * Clasa pt actiunea de redirectare
 */
public class RedirectionareMese implements ActionListener {
	private MeniuPrincipal mm;
	private Masa m;

	/**
	 * Constructor
	 * @param mm - meniu principal
	 * @param m - masa
	 */
	public RedirectionareMese(MeniuPrincipal mm, Masa m) {
		this.mm = mm;
		this.m = m;
	}
	
	/**
	 * Schimbare meniu
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		mm.generareMasaCurenta(m);
	}
}
