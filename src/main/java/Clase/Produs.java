package Clase;

import java.io.Serializable;

/**
 * Clasa ce implementeaza un produs
 */
public class Produs implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nume;
	private double pret;

	/**
	 * Constructor produs
	 * @param nume - String
	 * @param pret - double
	 */
	public Produs(String nume, double pret) {
		this.nume = nume;
		this.pret = pret;
	}
	
	/**
	 * Getter pret
	 * @return pret
	 */
	public double getPret() {
		return this.pret;
	}
	
	/**
	 * Override to string
	 */
	@Override
	public String toString() {
		return this.nume + " " + this.pret + " lei";
	}
}
