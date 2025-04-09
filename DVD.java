//AMALIA SORFINA BINTI MAHDZIR (1211111891)(TC1L)(TT4L)
//Subclass DVD to implemented calcualteLateFee for dvd inherit from LibraryItem
public class DVD extends LibraryItem {
    //Construct to initialize DVD object by pass value to superclass constructor
    public DVD(String id, String title, String status) {
        super(id, title, status);//call superclass to initialize inherited properties
    }
    //Override the abstract method from LibraryItem to calculate late fee for DVD
    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 2.0; // RM2 per day for DVDs
    }
}
