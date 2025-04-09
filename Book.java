//AMALIA SORFINA BINTI MAHDZIR (1211111891)(TC1L)(TT4L)
//Subclass Book to implemented calcualteLateFee for books inherit from LibraryItem
public class Book extends LibraryItem {
    //Construct to initialize Book object by pass value to superclass constructor
    public Book(String id, String title, String status) {
        super(id, title, status); //call superclass to initialize inherited properties
    }
    //Override the abstract method from LibraryItem to calculate late fee for books
    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 1.0; // RM1 per day for books
    }
}
