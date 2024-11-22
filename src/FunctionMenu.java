import java.sql.*;
import java.util.Scanner;
public class FunctionMenu {
    protected int m;


    protected void showFunctionMenu() {
        System.out.println("1. Filter books by author");
        System.out.println("2. Filter books by category");
        System.out.println("3. Sort books by publication year in descending order");
        System.out.println("4. Sort books by descending price");
        System.out.println("0. Back menu");
        System.out.print("Choose a function: ");
    }

    protected String jdbc = "jdbc:mysql://localhost:3306/book";
    protected String username = "root";
    protected String password = "";
    protected Connection connection;
    // Tạo trường hợp ngoại lệ
    protected FunctionMenu() throws SQLException {
        //tạo kết nối
        connection = DriverManager.getConnection(jdbc, username, password);
        // Tạo truy vấn
        Statement statement = connection.createStatement();

    }

    //tìm theo tên tác giả
    protected void filterBooksByAuthor(Scanner input) {
        System.out.print("Enter the author name: ");
        input.nextLine();
        String author_name = input.nextLine();

        String query = "SELECT * FROM books WHERE author LIKE '%" + author_name + "%'";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("Filtered books by author:");
            System.out.printf("%-5s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s\n","IBNS_code","book_title",
                    "author","genre","selling_price","publication_year","Publisher","page_number");
            while (resultSet.next()) {
                int IBNS_code = resultSet.getInt("IBNS_code");
                String book_title = resultSet.getString("book_title");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("Genre");
                int selling_price = resultSet.getInt("selling_price");
                int publication_year = resultSet.getInt("publication_year");
                String publisher = resultSet.getString("Publisher");
                int page_number = resultSet.getInt("page_number");

                System.out.printf("%-9s | %-10s | %-10s | %-10s | %-13s | %-16s | %-10s | %-10s\n",IBNS_code,book_title,author,
                        genre,selling_price,publication_year,publisher,page_number);
            }
        } catch (SQLException e) {
            System.out.println("Error filtering books by author: " + e.getMessage());
        }
    }




    protected void filterBooksByCategory(Scanner input) {
        System.out.print("Enter the genre: ");
        input.nextLine();
        String Genre = input.nextLine();

        String query = "SELECT * FROM books WHERE genre  LIKE '%" + Genre + "%'";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("Filtered books by category:");
            System.out.printf("%-5s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s\n","IBNS_code","book_title",
                    "author","genre","selling_price","publication_year","Publisher","page_number");
            while (resultSet.next()) {
                int IBNS_code = resultSet.getInt("IBNS_code");
                String book_title = resultSet.getString("book_title");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("Genre");
                int selling_price = resultSet.getInt("selling_price");
                int publication_year = resultSet.getInt("publication_year");
                String publisher = resultSet.getString("Publisher");
                int page_number = resultSet.getInt("page_number");

                System.out.printf("%-9s | %-10s | %-10s | %-10s | %-13s | %-16s | %-10s | %-10s\n",IBNS_code,book_title,author,
                        genre,selling_price,publication_year,publisher,page_number);
            }
        } catch (SQLException e) {
            System.out.println("Error filtering books by category: " + e.getMessage());
        }
    }




    protected void sortBooksByPublicationYearDescending() {
        String query = "SELECT * FROM books ORDER BY publication_year DESC";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("Books sorted by publication year in descending order:");
            System.out.printf("%-5s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s\n","IBNS_code","book_title",
                    "author","genre","selling_price","publication_year","Publisher","page_number");
            while (resultSet.next()) {
                int IBNS_code = resultSet.getInt("IBNS_code");
                String book_title = resultSet.getString("book_title");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("Genre");
                int selling_price = resultSet.getInt("selling_price");
                int publication_year = resultSet.getInt("publication_year");
                String publisher = resultSet.getString("Publisher");
                int page_number = resultSet.getInt("page_number");

                System.out.printf("%-9s | %-10s | %-10s | %-10s | %-13s | %-16s | %-10s | %-10s\n",IBNS_code,book_title,author,
                        genre,selling_price,publication_year,publisher,page_number);
            }
        } catch (SQLException e) {
            System.out.println("Error sorting books by publication year: " + e.getMessage());
        }
    }



    protected void sortBooksByDescendingPrice() {
        String query = "SELECT * FROM books ORDER BY selling_price DESC";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("Books sorted by price in descending order:");
            System.out.printf("%-5s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s\n","IBNS_code","book_title",
                    "author","genre","selling_price","publication_year","Publisher","page_number");
            while (resultSet.next()) {
                int IBNS_code = resultSet.getInt("IBNS_code");
                String book_title = resultSet.getString("book_title");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("Genre");
                int selling_price = resultSet.getInt("selling_price");
                int publication_year = resultSet.getInt("publication_year");
                String publisher = resultSet.getString("Publisher");
                int page_number = resultSet.getInt("page_number");

                System.out.printf("%-9s | %-10s | %-10s | %-10s | %-13s | %-16s | %-10s | %-10s\n",IBNS_code,book_title,author,
                        genre,selling_price,publication_year,publisher,page_number);
            }
        } catch (SQLException e) {
            System.out.println("Error sorting books by price: " + e.getMessage());
        }
    }





    protected void checkM(int m, Scanner input) {
        while(m < 0 || m > 4) {
            System.out.print("Function not yet developed, choose again: ");
            m = input.nextInt();
        }
        this.m = m;
    }
    protected void function(int m, Scanner input) {
        switch(m) {
            case 1:
                filterBooksByAuthor(input);
                break;
            case 2:
                filterBooksByCategory(input);
                break;
            case 3:
                sortBooksByPublicationYearDescending();
                break;
            case 4:
                sortBooksByDescendingPrice();
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
    protected void returnFunctionMenu(Scanner input) {
        do{
            showFunctionMenu();
            m = input.nextInt();
            checkM(m, input);
            function(m, input);
        }while(m != 0);
    }
}
