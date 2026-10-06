package week03_databases;

import week02_databases.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SelectProductsByLine {

    public static void main(String[] args) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/classicmodels";
        String username = "root";
        String password = "";

        ArrayList<Product> products = new ArrayList<>();


        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product line: ");
        String productLine = scanner.nextLine();

        try {
            Class.forName(driver);

            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                String sql = "SELECT * FROM products WHERE productLine = ?";

                try (PreparedStatement ps = conn.prepareStatement(sql)) {

                    ps.setString(1, productLine);

                    try (ResultSet rs = ps.executeQuery()) {

                        while (rs.next()) {

                            Product product = new Product(
                                    rs.getString("productCode"),
                                    rs.getString("productName"),
                                    rs.getString("productLine"),
                                    rs.getString("productScale"),
                                    rs.getString("productVendor"),
                                    rs.getString("productDescription"),
                                    rs.getInt("quantityInStock"),
                                    rs.getDouble("buyPrice"),
                                    rs.getDouble("MSRP")
                            );

                            products.add(product);
                        }
                    }
                }
            }

        } catch (ClassNotFoundException e) {

            System.out.println("Driver cannot be found.");
            System.out.println(e.getMessage());

        } catch (SQLException e) {

            System.out.println("Database error.");
            System.out.println(e.getMessage());
        }

        Collections.sort(products);

        System.out.println();
        System.out.println("----- PRODUCTS -----");

        for (Product product : products) {
            System.out.println(product);
        }

        System.out.println("--------------------");
        System.out.println("Number of products: " + products.size());

        scanner.close();
    }
}
