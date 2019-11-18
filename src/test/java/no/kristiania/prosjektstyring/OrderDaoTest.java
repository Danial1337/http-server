package no.kristiania.prosjektstyring;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;


import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderDaoTest {

    private DataSource dataSource;

    @Test
    void shouldFindSavedOrders() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:myTestDatabase");

        dataSource.getConnection().createStatement().executeUpdate(
          "create table ORDERS (id serial primary key, name varchar(1000) not null)"
        );

        Order order = new Order();
        order.setName("Test");
        OrderDao dao = new OrderDao(dataSource);

        dao.insert(order);
        assertThat(dao.listAll()).contains(order);

    }
}

