package Teste;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import ClaseRestaurant.Masa;
import ClaseRestaurant.Produs;

class TestMasa {

	@Test
	void testGettereSiSettere() {
		Masa m = new Masa(1);
		assertEquals(m.getEsteOcupata(), false);
		assertEquals(m.getId(), 1);
		HashMap<Produs, Integer> hashMapGol = new HashMap<Produs, Integer>();
		assertEquals(m.getProduse(), hashMapGol);
		Produs p1 = new Produs("produs1", 10.5), p2 = new Produs("produs2", 12.5);
		m.setProdus(p1, 10);
		assertEquals(m.getEsteOcupata(), true);
		m.setProdus(p2, 50);
		
		Map<Produs, Integer> hashMapProduse = Map.of(
				p1, 10,
				p2, 50
			);
		
		assertEquals(hashMapProduse.get(p1), m.getProduse().get(p1));
		assertEquals(hashMapProduse.get(p2), m.getProduse().get(p2));
	}
	
	@Test
	void testCalculNota() {
		Masa m = new Masa(1);
		assertEquals(m.calculNota(), 0);
		Produs p1 = new Produs("produs1", 10.5), p2 = new Produs("produs2", 12.5);
		m.setProdus(p1, 10);
		m.setProdus(p2, 50);
		assertEquals(m.calculNota(), 10.5*10 +12.5*50);

	}
	
	@Test
	void testAdaugareSiStergere() {
		Masa m = new Masa(1);
		Produs p1 = new Produs("produs1", 10.5);
		m.setProdus(p1, 2);
		m.addProdus(p1);
		assertEquals((int)m.getProduse().get(p1), (int)3);
		m.deleteProd(p1);
		m.deleteProd(p1);
		assertEquals((int)m.getProduse().get(p1), (int)1);
		m.deleteProd(p1);
		assertEquals(m.getProduse().containsKey(p1), false);
	}
	
	@Test
	void testToString() {
		Masa m = new Masa(1);
		assertEquals(m.toString(), "Masa 1");
	}
}
