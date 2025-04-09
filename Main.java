//AMALIA SORFINA BINTI MAHDZIR (1211111891)(TC1L)(TT4L)
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.time.*;

//Main Library class with GUI to handle user interface and interaction logic
public class Main extends JFrame implements ActionListener {
    private JLabel labelRole, labelBookId, labelBookTitle, labelDaysLate, labelBorrowBook, labelLateFee, labelSelectType;
    private JTextField textFieldDaysLate;
    private JButton loginButton, returnButton, borrowButton, viewButton, clearButton, exitButton, backButton;
    private JComboBox<String> roleComboBox, itemTypeComboBox, bookTitleComboBox;
    private JLabel bookTitleLabel, bookIdLabel; 
    private JPanel cards;
    private JPanel loginPage, memberPage, librarianPage;
    private ArrayList<LibraryItem> items = new ArrayList<>();
    private Actor currentActor;
    private JTable table;
    
    //Constructor initialize the library system with default items and sets up GUI components
    public Main() {
        setTitle("Library Management System");
        setSize(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize library items (ID, Title, Status)
        items.add(new Book("1011", "The Nightingale", "Available"));
        items.add(new Book("2022", "Maryposa", "Available"));
        items.add(new Magazine("3033", "Ujang", "Available"));
        items.add(new Magazine("4044", "Ana Muslim", "Available"));
        items.add(new DVD("5055", "SuperSonic", "Available"));
        items.add(new DVD("6066", "Mario", "Available"));

        // Initialize components for the login page
        labelRole = new JLabel("Select Role");
        roleComboBox = new JComboBox<>(new String[]{"Member", "Librarian"});
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        loginPage = new JPanel();
        loginPage.add(labelRole);
        loginPage.add(roleComboBox);
        loginPage.add(loginButton);

        // Initialize components for the member page
        labelSelectType = new JLabel("Select Item Type:");
        itemTypeComboBox = new JComboBox<>(new String[]{"Select Type", "Book", "Magazine", "DVD"});
        itemTypeComboBox.addActionListener(this);
        labelBookTitle = new JLabel("Select Book Title:");
        bookTitleComboBox = new JComboBox<>();
        bookTitleComboBox.addActionListener(this);
        bookTitleLabel = new JLabel("No book selected");
        labelBookId = new JLabel("Item ID:");
        bookIdLabel = new JLabel("Select a Title to Autofill");
        labelDaysLate = new JLabel("Enter Days Late");
        textFieldDaysLate = new JTextField(10);
        returnButton = new JButton("Return Item");
        returnButton.addActionListener(this);
        borrowButton = new JButton("Borrow Item");
        borrowButton.addActionListener(this);
        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this); 
        backButton = new JButton("Back to Login");
        backButton.addActionListener(this);

        labelLateFee = new JLabel("Late Fee: RM0.00");
        
        //Set up member page layout
        memberPage = new JPanel(new GridLayout(12, 2));
        memberPage.add(labelSelectType);
        memberPage.add(itemTypeComboBox);
        memberPage.add(labelBookTitle);
        memberPage.add(bookTitleComboBox);
        memberPage.add(labelBookId);
        memberPage.add(bookIdLabel);
        memberPage.add(labelDaysLate);
        memberPage.add(textFieldDaysLate);
        memberPage.add(borrowButton);
        memberPage.add(returnButton);
        memberPage.add(clearButton);
        memberPage.add(exitButton); 
        memberPage.add(labelLateFee); 
        memberPage.add(backButton); 

        // Librarian page with a table for all books
        viewButton = new JButton("View All Books");
        viewButton.addActionListener(this);
        librarianPage = new JPanel();
        librarianPage.add(viewButton);
        librarianPage.add(backButton);
        //JLable indicate can update status to borrowed or available
        JLabel descriptionLabel = new JLabel("Update: Borrowed or Available");
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        librarianPage.add(descriptionLabel);
        

        // JTable setup to show available and borrowed items
        String[] columnNames = {"ID", "Title", "Type", "Status"};
        Object[][] data = getTableData("All");
        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        librarianPage.add(scrollPane);

        // CardLayout setup to navigate between member and librarian pages
        cards = new JPanel(new CardLayout());
        cards.add(loginPage, "Login");
        cards.add(memberPage, "Member");
        cards.add(librarianPage, "Librarian");

        add(cards);
        setVisible(true);
    }
    //Method to gather data using table based on selected item type
    private Object[][] getTableData(String type) {
        ArrayList<LibraryItem> filteredItems = new ArrayList<>();
        for (LibraryItem item : items) {
            //Filter library items by item type (Book, Megazines and DVD)
            if (type.equals("All") || item.getClass().getSimpleName().equalsIgnoreCase(type)) {
                filteredItems.add(item);
            }
        }

        Object[][] data = new Object[filteredItems.size()][4];
        for (int i = 0; i < filteredItems.size(); i++) {
            LibraryItem item = filteredItems.get(i);
            data[i][0] = item.getId();
            data[i][1] = item.getTitle();
            data[i][2] = item.getClass().getSimpleName();
            data[i][3] = item.getStatus();
        }
        return data;
    }

    //ActionListener for handling user actions
    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle login button action
        if (e.getSource() == loginButton) {
            //Login functionality : member or librarian
            String selectedRole = (String) roleComboBox.getSelectedItem();
            if (selectedRole.equals("Member")) {
                currentActor = new Member(); //Polymorphism in which actor Member is instantiated
                showMemberPage();
            } else if (selectedRole.equals("Librarian")) {
                currentActor = new Librarian(); //Polymorphism in which actor Librarian is instantiated
                showLibrarianPage();
            }
        }
        // Handle item type selection (Book, Magazine, DVD)
        else if (e.getSource() == itemTypeComboBox) {
            String selectedType = (String) itemTypeComboBox.getSelectedItem();
            if (selectedType.equals("Book")) {
                // Populate Book titles
                bookTitleComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"The Nightingale", "Maryposa"}));
            } else if (selectedType.equals("Magazine")) {
                // Populate Magazine titles
                bookTitleComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Ujang", "Ana Muslim"}));
            } else if (selectedType.equals("DVD")) {
                // Populate DVD titles
                bookTitleComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"SuperSonic", "Mario"}));
            }
        }
        // Handle title selection to display item ID
        else if (e.getSource() == bookTitleComboBox) {
            String selectedTitle = (String) bookTitleComboBox.getSelectedItem();
            LibraryItem selectedItem = null;
            for (LibraryItem item : items) {
                if (item.getTitle().equals(selectedTitle)) {
                    selectedItem = item;
                    break;
                }
            }
            if (selectedItem != null) {
                bookIdLabel.setText(selectedItem.getId()); // Auto-fill Item ID based on selected title
            }
        }
        // Handle borrow book button for member
        else if (e.getSource() == borrowButton) {
            String selectedId = bookIdLabel.getText(); // Auto-filled Item ID
            LibraryItem itemToBorrow = null;
            for (LibraryItem item : items) {
                if (item.getId().equals(selectedId) && item.getStatus().equals("Available")) {
                    itemToBorrow = item;
                    break;
                }
            }

            if (itemToBorrow == null) {
                JOptionPane.showMessageDialog(this, "Item not available or already borrowed.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                //Polymorphism which member actor borrow the item
                ((Member) currentActor).borrowItem(itemToBorrow);
                JOptionPane.showMessageDialog(this, itemToBorrow.getTitle() + " borrowed successfully!");
            }
        }
        // Handle return book button for member
        else if (e.getSource() == returnButton) {
            if (!(currentActor instanceof Member)) {
                JOptionPane.showMessageDialog(this, "Only Members can return books.");
                return;
            }

            String bookId = bookIdLabel.getText(); // Auto-filled Item ID
            int daysLate;
            try {
                daysLate = Integer.parseInt(textFieldDaysLate.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number of days late.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LibraryItem itemToReturn = null;
            for (LibraryItem item : items) {
                if (item.getId().equals(bookId) && item.getStatus().equals("Borrowed")) {
                    itemToReturn = item;
                    break;
                }
            }

            if (itemToReturn == null) {
                JOptionPane.showMessageDialog(this, "Item not found or already returned.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                //Polymorphism which member actor returns the item and calculate late fee
                double lateFee = ((Member) currentActor).returnItem(itemToReturn, daysLate);
                labelLateFee.setText("Late Fee: RM" + lateFee); // Update the late fee label
                JOptionPane.showMessageDialog(this, "Book returned successfully. Late fee: RM" + lateFee);
            }
        }
        // Handle view button for librarian
        else if (e.getSource() == viewButton) {
            // Refresh the table with the selected item type
            String selectedType = (String) itemTypeComboBox.getSelectedItem();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setDataVector(getTableData(selectedType), new String[]{"ID", "Title", "Type", "Status"});
        }
        // Handle clear button for member
        else if (e.getSource() == clearButton) {
            // Clear all selections and reset to default values
            itemTypeComboBox.setSelectedIndex(0); // Reset Item Type ComboBox
             bookTitleComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Select a Title"})); // Reset Book Title ComboBox
            bookIdLabel.setText("Select a Title to Autofill"); // Reset Item ID label
            textFieldDaysLate.setText(""); // Clear Days Late text field
            labelLateFee.setText("Late Fee: RM0.00"); // Reset Late Fee label
        }
        // Handle exit button (now returns to login page)
        else if (e.getSource() == exitButton) {
            showLoginPage();
        }
        // Handle back button to go back to login page
        else if (e.getSource() == backButton) {
            showLoginPage();
        }
    }
    //Login page component 
    private void showMemberPage() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, "Member");
    }

    private void showLibrarianPage() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, "Librarian");
    }

    private void showLoginPage() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, "Login");
    }
    //Main method to run Library system
    public static void main(String[] args) {
        new Main();
    }
}
