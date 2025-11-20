package br.edu.ifspcjo.ads.web2.ifitness.dto;

import java.time.LocalDate;

public class ActivityByDay {

	private LocalDate date;
	private Long totalDistance;

	public ActivityByDay(LocalDate date, Long totalDistance) {
		this.date = date;
		this.totalDistance = totalDistance;
	}

	public ActivityByDay() {
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Long getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(Long totalDistance) {
		this.totalDistance = totalDistance;
	}
	
}