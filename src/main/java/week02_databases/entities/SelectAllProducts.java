package week02_databases.entities;

import java.sql.*;
import java.util.ArrayList;

public class SelectAllProducts {

public static void main(String[] args) {


    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://127.0.0.1:3306/classicmodels";
    String username = "root";
    String password = "";


    ArrayList<Product> products = new ArrayList<>();

    try {
        Class.forName(driver);

        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "SELECT * FROM products";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {

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


    System.out.println("----- ALL PRODUCTS -----");

    for (Product product : products) {
        System.out.println(product);
    }

    System.out.println("------------------------");
    System.out.println("Number of products: " + products.size());
}
}
