package br.edu.ifspcjo.ads.web2.LocalCenter.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class RentalCar implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Client client;
	private Car car;
	private LocalDate withdrawDate;
	private LocalDate returnDate;
	private Double dailyValue;
	private Double totalValue;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public LocalDate getWithdrawDate() {
		return withdrawDate;
	}
	public void setWithdrawDate(LocalDate withdrawDate) {
		this.withdrawDate = withdrawDate;
	}
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	public Double getDailyValue() {
		return dailyValue;
	}
	public void setDailyValue(Double dailyValue) {
		this.dailyValue = dailyValue;
	}
	public Double getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RentalCar other = (RentalCar) obj;
		return Objects.equals(id, other.id);
	}	
}
