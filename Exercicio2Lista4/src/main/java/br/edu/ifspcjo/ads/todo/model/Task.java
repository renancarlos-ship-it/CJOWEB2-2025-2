package br.edu.ifspcjo.ads.todo.model;

import java.time.LocalDate;

public class Task {

	private int id;
	private String description;
	private LocalDate date;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setDate(String string) {
		// TODO Auto-generated method stub
		
	}
	
	
}