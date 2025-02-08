package Teste;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ClaseRestaurant.Produs;

class TestProdus {

	@Test
	void testGettere() {
		Produs produs = new Produs("produs test", 10.5);
		assertEquals(produs.getNume(), "produs test");
		assertEquals(produs.getPret(), 10.5);
	}
	
	@Test
	void testEquals() {
		Produs p1 = new Produs("produs test 1", 10.5);
		Produs p2 = new Produs("produs test 2", 10.5);
		Produs p3 = new Produs("produs test 1", 12.5);
		Produs p4 = new Produs("produs test 1", 10.5);
		
		assertEquals(p1.equals(p2), false);
		assertEquals(p1.equals(p3), false);
		assertEquals(p1.equals(p4), true);
	}
	
	@Test
	void testToString() {
		Produs p1 = new Produs("produs test 1", 10.5);
		String p1String = p1.toString();
		assertEquals(p1String, "produs test 1 10.5 lei");
	}

	
	
}
