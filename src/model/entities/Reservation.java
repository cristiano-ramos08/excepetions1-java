package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	//static para que não seja instanciado um novo simpleDateFormat para cada objeto "Reservation" que a aplicação tiver.
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException {
		if (!checkOut.after(checkIn)) {//se a data checkout não for posterior a data de checkin
			throw new DomainException("Check-out date must be after check-in date.");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();//desse jeito pega a dferença em milisegundos.
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	public void updateDates(Date checkIn, Date checkOut) throws DomainException {
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)){
			throw new DomainException("Reservation dates for update must be future dates");
		}
		if (!checkOut.after(checkIn)) {//se a data checkout não for posterior a data de checkin
			throw new DomainException("Check-out date must be after check-in date.");
		}	
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		
	}
	@Override 
	public String toString() {
		return "room "
			+ roomNumber
			+", check-in: "
			+ sdf.format(checkIn)
			+ ", check-out: "			
			+ sdf.format(checkOut)
			+ ", "
			+ duration()
			+ " nights";
		
	}

}
