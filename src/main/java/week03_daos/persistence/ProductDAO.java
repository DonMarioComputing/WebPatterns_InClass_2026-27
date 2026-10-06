package week03_daos.persistence;

import week02_databases.entities.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDAO {

    private String url = "jdbc:mysql://127.0.0.1:3306/classicmodels";
    private String username = "root";
    private String password = "";

    public void AddProduct(Product product) {

        String sql = "INSERT INTO products " +
                "(productCode, productName, productLine, productScale, " +
                "productVendor, productDescription, quantityInStock, " +
                "buyPrice, MSRP) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, product.getProductCode());
            ps.setString(2, product.getProductName());
            ps.setString(3, product.getProductLine());
            ps.setString(4, product.getProductScale());
            ps.setString(5, product.getProductVendor());
            ps.setString(6, product.getProductDescription());
            ps.setInt(7, product.getQuantityInStock());
            ps.setDouble(8, product.getBuyPrice());
            ps.setDouble(9, product.getMSRP());

            ps.executeUpdate();

            System.out.println("Product added successfully.");

        } catch (SQLException e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }
}