package br.edu.ifsp.principal;

import br.edu.ifsp.instrumentos.Bateria;
import br.edu.ifsp.instrumentos.Guitarra;
import br.edu.ifsp.instrumentos.Piano;

public class Principal {
	public static void main(String[] args) {
		Bateria b1 = new Bateria();
		b1.tocar();
		Guitarra g1 = new Guitarra();
		g1.tocar();
		Piano p1 = new Piano();
		p1.tocar();
		
				
	}
}
