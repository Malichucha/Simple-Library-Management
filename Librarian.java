//AMALIA SORFINA BINTI MAHDZIR (1211111891)(TC1L)(TT4L)
//Allow the librarian to view library items and Status
public class Librarian extends Actor {
    public Librarian() {
        this.role = "Librarian";
    }

    public void viewItems(java.util.ArrayList<LibraryItem> items) {
        for (LibraryItem item : items) {
            System.out.println("Item ID: " + item.getId() + ", Title: " + item.getTitle() + ", Status: " + item.getStatus());
        }
    }
}
