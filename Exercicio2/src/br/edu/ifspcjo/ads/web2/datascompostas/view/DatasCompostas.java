package br.edu.ifspcjo.ads.web2.datascompostas.view;

public class DatasCompostas {

	private int dia;
	private int mes;
	private int ano;

	public DatasCompostas(int dia, int mes, int ano) {
		setDia(dia);
		setMes(mes);
		setAno(ano);
	}

	public void setDia(int dia) {
		if (dia < 1 || dia > 31) {
			this.dia = 1;
		} else {
			this.dia = dia;
		}
	}

	public void setMes(int mes) {
		if (mes < 1 || mes > 12) {
			this.mes = 1;
		} else {
			this.mes = mes;
		}
	}

	public void setAno(int ano) {
		if (ano <= 1900) {
			this.ano = 1;
		} else {
			this.ano = ano;
		}
	}

	public int getDia() {
		return dia;
	}

	public int getMes() {
		return mes;
	}

	public int getAno() {
		return ano;
	}

}
