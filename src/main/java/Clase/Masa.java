package Clase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clasa ce implementeaza o masa 
 * Clasa este serializabila
 */
public class Masa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private boolean esteOcupata;
	private ArrayList<Produs> produse;

	/**
	 * Constructor
	 * @param id - id ul mesei
	 */
	public Masa(int id) {
		this.id = id;
		this.esteOcupata = false;
		this.produse = new ArrayList<Produs>();
	}
	
	/**
	 * @return true daca masa e ocupata, altfel false
	 */
	public boolean getEsteOcupata() {
		return this.esteOcupata;
	}

	/**
	 * Getter id
	 * @return id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Getter produse
	 * @return Produse
	 */
	public ArrayList<Produs> getProduse(){
		return this.produse;
	}

	/**
	 * Adaugarea unui produs in lista de produse a mesei curente
	 * @param p - produs de adaugat
	 */
	public void addProdus(Produs p) {
		this.produse.add(p);
		this.esteOcupata = true;
	}
	
	/**
	 * Calcul nota masa curenta
	 * @return Suma tuturor preturilor produselor
	 */
	public double calculNota() {
		double suma = 0;
		for(Produs p: this.produse) { 
			suma += p.getPret();
		}
		this.produse.clear();
		this.esteOcupata = false;
		return suma;
	}
	
	/**
	 * Override toString
	 */
	@Override
	public String toString() {
		return "Masa " + this.id;
	}
}
