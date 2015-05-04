package grupo8.TPAnual;

import static org.junit.Assert.*;
import grupo8.TPAnual.Usuario;

import org.junit.Before;
import org.junit.Test;

public class TestIMCDeLosDesarrolladores {
	private Usuario felipe;
	
	@Before
	public void init() {
		felipe = new Usuario(55.1, 1.69);
	}
	  
	@Test
	public void IMCdeFelipe() {
	Double imcDeFelipe = felipe.calcularIMC();
	
	assertEquals(imcDeFelipe, (Double) 19.292, 0.001);
	}

}
