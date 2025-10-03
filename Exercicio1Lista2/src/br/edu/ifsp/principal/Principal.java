package br.edu.ifsp.principal;

import br.edu.ifsp.pessoas.Endereco;
import br.edu.ifsp.pessoas.Pessoa;
import br.edu.ifsp.pessoas.PessoaFisica;
import br.edu.ifsp.pessoas.PessoaJuridica;

public class Principal {
	public static void main(String[] args) {
		Endereco e1 = new Endereco("Rua do Cristo", 1313, "Jardim Embaixador", "Campos do Jordão");
	Pessoa p1 = new PessoaFisica("Renan", e1, "123.456.789.00");
	System.out.println(p1);
		Endereco e2 = new Endereco("Rua da ADS", 1432, "Abernéssia", "Campos do Jordão");
	Pessoa p2 = new PessoaJuridica("IFSPCJO", e2, "133.2332.2323");
	System.out.println(p2);
	}
}
