package ClaseGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Clase.Masa;

/**
 * Clasa pentru actiunea de back
 */
public class RedirectionareInapoi implements ActionListener {
	private MeniuPrincipal mm;
	private ArrayList<Masa> lm;

	/**
	 * Constructor
	 * @param mm - meniu principal
	 * @param lm - lista mese
	 */
	public RedirectionareInapoi(MeniuPrincipal mm, ArrayList<Masa> lm) {
		this.mm = mm;
		this.lm = lm;
	}
	
	/**
	 * Actiune back
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		mm.generareMese(lm);
	}
}
