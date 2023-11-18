import java.time.*;
public class BookingDate {

    LocalDate checkIn, checkOut;

    BookingDate(){}

    BookingDate(LocalDate checkIn, LocalDate checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public LocalDate getCheckIn() {
        return this.checkIn;
    }

    public LocalDate getCheckOut() {
        return this.checkOut;
    }
}
