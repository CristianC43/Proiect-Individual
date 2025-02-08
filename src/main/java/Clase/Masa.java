package Clase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

/**
 * Clasa ce implementeaza o masa 
 * Clasa este serializabila
 */
public class Masa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private boolean esteOcupata;
	private HashMap<Produs, Integer> produse;
  
	/**
	 * Constructor
	 * @param id - id ul mesei
	 */
	public Masa(int id) {
		this.id = id;
		this.esteOcupata = false;
		this.produse = new HashMap<Produs, Integer>();
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
	public HashMap<Produs, Integer> getProduse(){
		return this.produse;
	}

	/**
	 * Adaugarea unui produs in lista de produse a mesei curente
	 * @param p - produs de adaugat
	 */
	public void addProdus(Produs p) {
		if(!this.produse.containsKey(p)) this.produse.put(p, 1);
		else this.produse.put(p, this.produse.get(p)+1);
		this.esteOcupata = true;
	}
	
	public void deleteProd(Produs p) {
		if(this.produse.containsKey(p)) {
			if(this.produse.get(p) == 1) this.produse.remove(p);
			else this.produse.put(p,this.produse.get(p)-1);
		} else {
			System.out.println("Nu exista produsul " + p);;
			JOptionPane.showMessageDialog(null, "Nu exista produsul " + p);
		}
		if(this.produse.isEmpty()) this.esteOcupata = false;
	}
	
	/**
	 * Calcul nota masa curenta
	 * @return Suma tuturor preturilor produselor
	 */
	public double calculNota() {
		double suma = 0;
		for(Produs k: this.produse.keySet()) { 
			suma += k.getPret() * this.produse.get(k); // Produs x Nr produse
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
