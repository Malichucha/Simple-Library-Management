//AMALIA SORFINA BINTI MAHDZIR (1211111891)(TC1L)(TT4L)
import java.time.LocalDate;

//Abstract class for LibraryItem to represent common attributes and behaviors foa all Library items.
public abstract class LibraryItem {
    private String id;
    private String title;
    private String status;
    private LocalDate borrowedDate;
//Constructor to initialize LibraryIten
    public LibraryItem(String id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    // Abstract method to calculate late fee, implemented by subclasses
    public abstract double calculateLateFee(int daysLate);
}
