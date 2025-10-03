package br.edu.ifsp.pessoas;

public class PessoaJuridica extends Pessoa{
	private String cnpj;

	public PessoaJuridica(String nome, Endereco endereco, String cnpj) {
		super(nome, endereco);
		this.cnpj = cnpj;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return "PessoaJuridica [cnpj=" + cnpj + ", nome=" + nome + ", endereco=" + endereco + "]";
	}
 
	
}
