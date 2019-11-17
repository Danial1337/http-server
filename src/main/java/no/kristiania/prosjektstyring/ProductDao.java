package no.kristiania.prosjektstyring;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ProductDao {
    private DataSource dataSource;
   public ProductDao(DataSource dataSource){
    this.dataSource = dataSource;
    }

    public void insertProduct(String productName) throws SQLException{

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(
                    "insert into products (name) values (?)"
            );
            statement.setString(1, productName);
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> listAll() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
        try (PreparedStatement statement = connection.prepareStatement(
                "select * from products"
        )) {
            try (ResultSet rs = statement.executeQuery()) {
                List<String> result = new ArrayList<>();

                while (rs.next()) {
                    result.add(rs.getString("name"));
                }
                return result;
            }
        }

    }

}

    public static void main(String[] args) throws SQLException, IOException {
        System.out.println("Enter a product name to insert: ");
        String productName = new Scanner(System.in).nextLine();

        Properties properties = new Properties();
        properties.load(new FileReader("webshop.properties"));

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUser("postgres");
        dataSource.setPassword(properties.getProperty("dataSource.password"));
        ProductDao productDao = new ProductDao(dataSource);
        productDao.insertProduct(productName);

        System.out.println(productDao.listAll());
    }
}//PUSH TEST Laast
