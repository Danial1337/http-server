package no.kristiania.prosjektstyring;

import org.postgresql.ds.PGSimpleDataSource;

import java.io.*;
import java.sql.SQLException;
import java.util.Properties;

public class ProsjektStyring {
    private final OrderDao orderDao;
    private final ProductDao productDao;
    private PGSimpleDataSource dataSource;
private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public ProsjektStyring() throws IOException{
    Properties properties = new Properties();
   properties.load(new FileReader("webshop.properties"));

   dataSource = new PGSimpleDataSource();

   dataSource.setUrl("jdbc:postgresql://localhost:5432/webshop");
   dataSource.setUser("postgres");
   dataSource.setPassword(properties.getProperty("dataSource.password"));


     productDao = new ProductDao(dataSource);
     orderDao = new OrderDao(dataSource);
}


    public static void main(String[]args) throws IOException, SQLException{
        new ProsjektStyring().run();
    }


    private void run() throws IOException, SQLException {
        System.out.println("Choose Action: Create [product][create][order]");
        String action = input.readLine();

    switch (action.toLowerCase()){
        case "product:":
            insertProduct();
            break;
        case "order":
                insertOrder();
            break;
    }

        System.out.println("Current projects :" + productDao.listAll());
    }

    private void insertOrder() throws IOException, SQLException{
        System.out.println("Please type the name of a new order");
        Order order = new Order();
        order.setName(input.readLine());
        orderDao.insert(order);
    }

    private void insertProduct() throws IOException, SQLException {
        System.out.println("Please type the name of new product:");
        String productName = input.readLine();
        productDao.insert(productName);
    }


}
