import java.sql.*;
import java.util.Scanner;
public class BookMenu extends FunctionMenu {
    //Tạo thuộc tính kết nối CSDL
    protected String jbtc = "jdbc:mysql://localhost:3306/book";
    protected String username = "root";
    protected String password = "";
    protected Connection connection;

    //Tạo trường hợp ngoại lệ
    protected BookMenu() throws SQLException {
        //Tạo kết nối CSDL
        connection = DriverManager.getConnection(jbtc, username, password);
        // Tạo truy vấn
        Statement statement = connection.createStatement();
    }

    protected void showBookMenu() {
        System.out.println("1. Add new book");
        System.out.println("2. Update book infomation");
        System.out.println("3. Delete book");
        System.out.println("4. Show all the book");
        System.out.println("0. Back menu");
        System.out.print("Choose a function: ");
    }

    //thêm bản ghi vào csdl
    protected void insertBook(int IBNS_code, String book_title, String author,String genre, int selling_price,
                              int publication_year, int page_number, String publisher) {
        String sql = "INSERT INTO books " +
                "(IBNS_code, book_title, author, genre, selling_price, publication_year, page_number, publisher)" +
                "VALUES" +
                "(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, IBNS_code);
            statement.setString(2, book_title);
            statement.setString(3, author);
            statement.setString(4,genre);
            statement.setInt(5, selling_price);
            statement.setInt(6, publication_year);
            statement.setInt(7,page_number);
            statement.setString(8,publisher);



            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void addbook(Scanner input) {
        System.out.print("Typing sum of book: ");
        int sum = input.nextInt();
        input.nextLine();

        for (int i = 0; i < sum; i++) {
            System.out.println("Infomation of book " + (i + 1));
            System.out.println("--------------------------------");

            System.out.println("IBNS code: ");
            int IBNS_code = input.nextInt();
            input.nextLine();

            System.out.println("Book title: ");
            String book_title = input.nextLine();

            System.out.println("Author: ");
            String author = input.nextLine();

            System.out.println("Genre: ");
            String genre = input.nextLine();

            System.out.println("Selling price: ");
            int selling_price = input.nextInt();
            input.nextLine();

            System.out.println("Publication year: ");
            int publication_year = input.nextInt();
            input.nextLine();

            System.out.println("Page number: ");
            int page_number = input.nextInt();
            input.nextLine();

            System.out.println("Publisher: ");
            String publisher = input.nextLine();

            insertBook(IBNS_code, book_title, author, genre, selling_price, publication_year, page_number,publisher);
            System.out.println("Insert new book successfully");
        }
    }


    protected void updateBook(Scanner input) {
        input.nextLine();
        System.out.print("Enter IBNS code to update: ");
        int IBNS_code = input.nextInt();
        if (checkBookExistence(IBNS_code)) {
            input.nextLine(); // Consume the newline character
            System.out.println("Enter new information for the book:");
            System.out.println("Enter new book title: ");
            String tieuDeSachMoi = input.nextLine();
            System.out.println("Enter new author: ");
            String tacGiaMoi = input.nextLine();
            System.out.println("Enter new genre: ");
            String theLoaiMoi = input.nextLine();
            System.out.println("Enter new selling price: ");
            int giaBanMoi = input.nextInt();
            System.out.println("Enter new publication year: ");
            int namXuatBanMoi = input.nextInt();
            input.nextLine(); // Consume the newline character
            System.out.println("Enter new publisher: ");
            String nhaXuatBan = input.nextLine();
            System.out.println("Enter new page number: ");
            int soTrang = input.nextInt();
            try {
                String sql = "UPDATE books SET book_title = ?, author = ?, genre = ?, selling_price = ?, " +
                        "publication_year = ?, publisher = ?, page_number = ? WHERE IBNS_code = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, tieuDeSachMoi);
                statement.setString(2, tacGiaMoi);
                statement.setString(3, theLoaiMoi);
                statement.setInt(4, giaBanMoi);
                statement.setInt(5, namXuatBanMoi);
                statement.setString(6, nhaXuatBan);
                statement.setInt(7, soTrang);
                statement.setInt(8, IBNS_code); // Set the IBNS_code parameter
                statement.executeUpdate();
                System.out.println("Book information updated successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Book does not exist.");
        }
    }
    private boolean checkBookExistence(int IBNS_code) {
        String sql = "SELECT * FROM books WHERE IBNS_code = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, IBNS_code);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    protected void deteleBook(Scanner input) throws SQLException {
        input.nextLine();
        System.out.println("Enter the book code to delete: ");
        String maSach = input.nextLine();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE IBNS_code = ?");
        statement.setString(1, maSach);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) {
            System.out.println("Books don't exist!");
            return;
        }
        // Xóa sách
        statement = connection.prepareStatement("DELETE FROM books WHERE IBNS_code = ?");
        statement.setString(1, maSach);
        statement.executeUpdate();

        // Thông báo cho người dùng
        System.out.println("Successfully deleted books!");

    }


    protected void showListbook() {
        String sql = "SELECT * FROM books";
        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(sql);
            System.out.println("-----List of books-----");
            System.out.printf("%-5s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s\n","IBNS_code","book_title",
                    "author","genre","selling_price","publication_year","Publisher","page_number");
            while (query.next()) {
                int IBNS_code = query.getInt("IBNS_code");
                String book_title = query.getString("book_title");
                String author = query.getString("author");
                String genre = query.getString("Genre");
                int selling_price = query.getInt("selling_price");
                int publication_year = query.getInt("publication_year");
                String publisher = query.getString("Publisher");
                int page_number = query.getInt("page_number");

                System.out.printf("%-9s | %-10s | %-10s | %-10s | %-13s | %-16s | %-10s | %-10s\n",IBNS_code,book_title,author,
                        genre,selling_price,publication_year,publisher,page_number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void book(int m, Scanner input) throws SQLException {
        switch(m) {
            case 1:
                addbook(input);
                break;
            case 2:
                updateBook(input);
                break;
            case 3:
                deteleBook(input);
                break;
            case 4:
                showListbook();
                break;
        }
        if(m != 0) {
            System.out.print("Click 1 return menu, click 0 back to main menu: ");
            m = input.nextInt();
            while(m < 0 || m > 1) {
                System.out.print("Click 1 return menu, click 0 back to main menu: ");
                m = input.nextInt();
            }
            this.m = m;
        }
    }
    protected void returnBookMenu(Scanner input) throws SQLException {
        do{
            showBookMenu();
            m = input.nextInt();
            checkM(m, input);
            book(m, input);
        }while(m != 0);
    }
}