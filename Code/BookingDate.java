import java.time.*;
public class BookingDate {

    LocalDate checkIn, checkOut;

    /**Default constructor for Hotel objects
    */
    BookingDate(){}

    /**Constructor with parameters for BookingDate objects
     * @param checkIn the date the user will check into the hotel
     * @param checkOut the date the user will check out of the hotel
    */
    BookingDate(LocalDate checkIn, LocalDate checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    
    /**Changes the check in date 
     * @param checkIn The check in date you want to be set as
     */
    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    
    /**Changes the check out date 
     * @param checkOut The check out date you want to be set as
     */
    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    
    /**Returns the check in date
     * @return LocalDate object containing the check in date
     */
    public LocalDate getCheckIn() {
        return this.checkIn;
    }

    
    /**Returns the check out date
     * @return LocalDate object containing the checkout date
     */
    public LocalDate getCheckOut() {
        return this.checkOut;
    }
}
