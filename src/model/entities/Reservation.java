package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.SimpleFormatter;

public class Reservation {

    private Integer roomNumber;
    private Date CheckIn;
    private Date CheckOut;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
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

    public String updateDates(Date checkIn, Date checkOut){
        Date now = new Date();
        if (checkIn.before(now) || checkOut.before(now))
            return "Error in reservation: Reservation dates update must be future dates";
        if (!checkOut.after(checkIn))
            return "Error in reservation: Check-out date must after check-in date";

        this.CheckIn = checkIn;
        this.CheckOut = checkOut;

        return null;
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
