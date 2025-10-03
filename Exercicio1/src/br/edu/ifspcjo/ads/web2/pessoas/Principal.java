package br.edu.ifspcjo.ads.web2.pessoas;

public class Principal {
	public static void main(String[] args) {
		Pessoas p1 = new Pessoas (31, "Renan");
		p1.setIdade(56);
		p1.setNome("Roberto");
	
		System.out.println("Nova Idade: " +p1.getIdade());
		System.out.println("Nova Nome: " +p1.getNome());
	}
}
