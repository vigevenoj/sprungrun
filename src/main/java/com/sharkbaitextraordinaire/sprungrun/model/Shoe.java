package com.sharkbaitextraordinaire.sprungrun.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Shoe {
	private Long shoeid;
	private String name;
	private BigDecimal mileage;
	private LocalDate purchase;
	
	public Shoe(Long shoeid, String name, BigDecimal mileage, LocalDate purchase) {
		this.shoeid = shoeid;
		this.name = name;
		this.mileage = mileage;
		this.purchase = purchase;
	}
	
	public Long getShoeid() {
		return shoeid;
	}
	public void setShoeid(Long shoeid) {
		this.shoeid = shoeid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getMileage() {
		return mileage;
	}
	public void setMileage(BigDecimal mileage) {
		this.mileage = mileage;
	}
	public LocalDate getPurchase() {
		return purchase;
	}
	public void setPurchase(LocalDate purchase) {
		this.purchase = purchase;
	}
}
