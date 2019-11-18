package no.kristiania.prosjektstyring;

import javax.sql.DataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDao extends AbstractDao<String>{

    public ProductDao(DataSource dataSource){
        super(dataSource);
    }

    @Override
    protected void insertObject(String product, PreparedStatement stmt) throws SQLException {
        stmt.setString(1,product);
    }

  @Override
    protected String readObject(ResultSet rs) throws SQLException{
        return rs.getString("name");
    }

    public void insert(String product) throws SQLException {
        insert(product, "insert into products (name) values (?)");
    }

    public List<String> listAll() throws SQLException {
    return listAll("select * from products");
    }
}
