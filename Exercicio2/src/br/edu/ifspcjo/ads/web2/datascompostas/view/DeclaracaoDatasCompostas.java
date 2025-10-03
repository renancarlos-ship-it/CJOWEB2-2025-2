package br.edu.ifspcjo.ads.web2.datascompostas.view;

public class DeclaracaoDatasCompostas {
	public static void main(String[] args) {
		DatasCompostas d1 = new DatasCompostas(6, 11, 1993);
		d1.setDia(22);
		d1.setMes(1);
		d1.setAno(2025);

		System.out.printf("Novo Dia: " + d1.getDia());
		System.out.printf(" Novo Mes: " + d1.getMes());
		System.out.printf(" Novo Ano: " + d1.getAno());
	}

}
