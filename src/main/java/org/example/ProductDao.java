package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private Connection connection;

    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    public ProductRecord findById(int id) {
        final var SQL = "SELECT * FROM products WHERE id = ?";

        ProductRecord productRecord = null;

        try  {
            PreparedStatement stmt = this.connection.prepareStatement(SQL);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                productRecord
                        = new ProductRecord(rs.getInt("id"), rs.getString("name"), rs.getInt("price"));
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (productRecord == null) {
            return null;
        }
        return productRecord;
    }

    public List<ProductRecord> findByName(String name) {
        final var SQL = "SELECT * FROM products WHERE name LIKE ?";

        var list = new ArrayList<ProductRecord>();

        try  {
            PreparedStatement stmt = this.connection.prepareStatement(SQL);
            stmt.setString(1,"%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                var productRecord
                        = new ProductRecord(rs.getInt("id"), rs.getString("name"), rs.getInt("price"));
                list.add(productRecord);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (list.size() == 0) {
            System.out.println("ヒットしませんでした。");
            return list;
        }
        return list;
    }

    public int insert(ProductRecord productRecord) {
        final var SQL = "INSERT INTO products VALUES (?, ?, ?)";
        try  {
            PreparedStatement stmt = this.connection.prepareStatement(SQL);
            stmt.setInt(1,productRecord.id());
            stmt.setString(2,productRecord.name());
            stmt.setInt(3,productRecord.price());
            var result = stmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int update(ProductRecord productRecord) {
        final var SQL = "UPDATE products SET name = ?, price = ? WHERE id = ?";
        try  {
            PreparedStatement stmt = this.connection.prepareStatement(SQL);
            stmt.setString(1,productRecord.name());
            stmt.setInt(2,productRecord.price());
            stmt.setInt(3,productRecord.id());
            var result = stmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(int id) {
        final var SQL = "DELETE FROM products WHERE id = ?";
        try  {
            PreparedStatement stmt = this.connection.prepareStatement(SQL);
            stmt.setInt(1, id);
            var result = stmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
