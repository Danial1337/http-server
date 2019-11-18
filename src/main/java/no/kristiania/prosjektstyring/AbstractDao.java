package no.kristiania.prosjektstyring;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> {
    protected DataSource dataSource;

    public AbstractDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public long insert(T product, String sql) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try(PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
                insertObject(product, stmt);
                stmt.executeUpdate();

                ResultSet generatedKeys = stmt.getGeneratedKeys();
                generatedKeys.next();
                return generatedKeys.getLong(1);
            }
        }
    }

    protected abstract void insertObject(T product, PreparedStatement stmt) throws SQLException;


    public List<T> listAll(String sql) throws SQLException{
        try(Connection connection = dataSource.getConnection()){
            try(PreparedStatement stmt = connection.prepareStatement(sql)){
                try(ResultSet rs = stmt.executeQuery()){
                    List<T> products = new ArrayList<>();
                    while(rs.next()){
                        products.add(readObject(rs));
                    }
                    return products;
                }
            }
        }
    }

    protected abstract T readObject(ResultSet rs) throws SQLException;
}
