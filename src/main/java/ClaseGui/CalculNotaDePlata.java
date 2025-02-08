package ClaseGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Clase.Masa;
import Clase.Main;
import Clase.Main;

public class CalculNotaDePlata implements ActionListener{
	private Masa m;
	public CalculNotaDePlata(Masa m) {
		this.m = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(m.calculNota());
	}	
	
}
