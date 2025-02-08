package ClaseGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Clase.Masa;
import Clase.Main;
import Clase.Main;

public class CalculNotaDePlata implements ActionListener{
	private Masa m;
	private MeniuPrincipal meniu;
	private ArrayList<Masa> listaMese;
	public CalculNotaDePlata(Masa m, MeniuPrincipal meniu, ArrayList<Masa> listaMese) {
		this.m = m;
		this.meniu = meniu;
		this.listaMese = listaMese;
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {
		String nota =  m.calculNota() + " lei";
		System.out.println(nota);
		JOptionPane.showMessageDialog(null, nota);
		meniu.generareMese(listaMese);
	}	
	
}
