//AMALIA SORFINA BINTI MAHDZIR (1211111891)(TC1L)(TT4L)
//Subclass Magazine to implemented calcualteLateFee for magazine inherit from LibraryItem
public class Magazine extends LibraryItem {
    //Construct to initialize DVD object by pass value to superclass constructor
    public Magazine(String id, String title, String status) {
        super(id, title, status);//call superclass to initialize inherited properties
    }
    //Override the abstract method from LibraryItem to calculate late fee for magazines
    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 0.5; // RM0.5 per day for magazines
    }
}
