package br.edu.ifspcjo.ads.web2.pessoas;
public class Pessoas {
	
	private int idade; 
	private String nome;
	
	
	public Pessoas(int idade, String nome) {
		this.idade=idade;
		this.nome=nome;
	}
	public int getIdade() {
		return idade;
	}
	public String getNome() {
		return nome;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}

