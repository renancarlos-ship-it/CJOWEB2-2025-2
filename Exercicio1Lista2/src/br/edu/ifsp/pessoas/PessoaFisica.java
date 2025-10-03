package br.edu.ifsp.pessoas;

public class PessoaFisica extends Pessoa {
	private String cpf;

	public PessoaFisica(String nome, Endereco endereco, String cpf) {
		super(nome, endereco);
		this.cpf = cpf;
	
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "PessoaFisica [cpf=" + cpf + ", nome=" + nome + ", endereco=" + endereco + "]";
	}
	
	
}
