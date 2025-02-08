package ClaseGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import Clase.Masa;
import Clase.Produs;

public class StergereProduse  implements ActionListener{
	
	private Masa m;
	private MeniuPrincipal meniu;
	public StergereProduse(Masa m, MeniuPrincipal meniu){
		this.m = m;
		this.meniu = meniu;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) 	{
		JFrame f = new JFrame();
		f.setSize(400,400);
		
		JPanel produseInitiale = new JPanel();
		produseInitiale.setLayout(new BoxLayout(produseInitiale, BoxLayout.Y_AXIS));
		
	    for(Entry<Produs, Integer> e: m.getProduse().entrySet()) {
	    	JLabel prod = new JLabel(e.getKey().toString());  
	    	Produs p = new Produs(e.getKey().getNume( ), e.getKey().getPret());
	    	prod.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent ev) {
                	m.deleteProd(p);
                	System.out.println(prod.getText());
                	meniu.generareMasaCurenta(m);
                }
			});
	    	produseInitiale.add(prod);
	    }
		
	    JScrollPane scrollProduse = new JScrollPane(produseInitiale);
		scrollProduse.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollProduse.revalidate();
		f.setContentPane(scrollProduse);
		f.setVisible(true);
	}
}
