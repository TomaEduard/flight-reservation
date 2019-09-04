package com.example.flightreservation.entities;

public class Reservation {

	private long id;

	private Boolean checkIn;

	private int numberOfBags;

	private Passanger passanger;

	private Flight flight;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Boolean getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Boolean checkIn) {
		this.checkIn = checkIn;
	}

	public int getNumberOfBags() {
		return numberOfBags;
	}

	public void setNumberOfBags(int numberOfBags) {
		this.numberOfBags = numberOfBags;
	}

	public Passanger getPassanger() {
		return passanger;
	}

	public void setPassanger(Passanger passanger) {
		this.passanger = passanger;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

}
