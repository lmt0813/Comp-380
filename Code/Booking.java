public class Booking {
    protected String bookingID;
    protected double price;
    protected int hotelID;
    protected String checkInDate;
    protected String checkOutDate;
    protected String paymentInformation;

    public Booking(double price, int hotelID, String checkInDate, String checkOutDate, String paymentInformation) {
        this.bookingID = "aaaa";
        this.price = price;
        this.hotelID = hotelID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.paymentInformation = paymentInformation;
    }


    public static void main(String[] args) {
        String x = "The_Hilton_Inn";
        x = x.replace('_', ' ');
        x = x.toLowerCase();
        System.out.println(x);
        if(x.contains("hilton")) {
            System.out.println("Yes");
        }
    }
}