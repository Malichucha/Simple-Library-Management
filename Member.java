//AMALIA SORFINA BINTI MAHDZIR (1211111891)(TC1L)(TT4L)
//Implement borrow and return for Library Items
public class Member extends Actor {
    public Member() {
        this.role = "Member";
    }

    public void borrowItem(LibraryItem item) {
        item.setStatus("Borrowed");
        item.setBorrowedDate(java.time.LocalDate.now());
        System.out.println("Borrowed item: " + item.getTitle());
    }

    public double returnItem(LibraryItem item, int daysLate) {
        double lateFee = item.calculateLateFee(daysLate);
        item.setStatus("Available");
        item.setBorrowedDate(null);
        System.out.println("Returned item: " + item.getTitle() + ". Late fee: $" + lateFee);
        return lateFee;
    }
}
