package br.edu.ifspcjo.ads.web2.ifitness.model;

public enum ActivityType {
	
	CAMINHADA("Caminhada"),
	CICLISMO("Ciclismo"),
	CORRIDA("Corrida"),
	NATACAO("Natação");
	
	private String type;
	
	ActivityType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

}
