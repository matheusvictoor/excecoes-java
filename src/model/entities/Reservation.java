package model.entities;

import model.entities.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.SimpleFormatter;

public class Reservation {

    private Integer roomNumber;
    private Date CheckIn;
    private Date CheckOut;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException {
        if (!checkOut.after(checkIn))
            throw new DomainException("Check-out date must be after check-in date");

        this.roomNumber = roomNumber;
        CheckIn = checkIn;
        CheckOut = checkOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckIn() {
        return CheckIn;
    }

    public Date getCheckOut() {
        return CheckOut;
    }

    public Long duration(){
        Long diff = getCheckOut().getTime() - getCheckIn().getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MICROSECONDS);
    }

    public void updateDates(Date checkIn, Date checkOut) throws DomainException {
        Date now = new Date();
        if (checkIn.before(now) || checkOut.before(now))
            throw new IllegalArgumentException("Error in reservation: Reservation dates update must be future dates");
        if (!checkOut.after(checkIn))
            throw new IllegalArgumentException("Error in reservation: Check-out date must after check-in date");

        this.CheckIn = checkIn;
        this.CheckOut = checkOut;
    }

    @Override
    public String toString() {
        return "Room "
                + roomNumber +
                ", Check-in: "
                + sdf.format(CheckIn)
                + ", check-out: "
                + ", "
                + duration()
                + " nights";
    }
}
