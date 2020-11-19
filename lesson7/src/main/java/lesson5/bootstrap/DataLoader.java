package lesson5.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lesson5.dao.OrderDao;
import lesson5.dao.ProductDao;
import lesson5.dao.UserDao;
import lesson5.domain.Order;
import lesson5.domain.Product;
import lesson5.domain.Role;
import lesson5.domain.User;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    public static final User USER = new User();
    public static final User MANAGER = new User();
    public static final User ADMIN = new User();

    private final OrderDao orderDao;
    private final ProductDao productDao;
    private final UserDao userDao;

    static {
        USER.setName("User");
        USER.setPassword("user");
        USER.setRole(Role.USER);

        MANAGER.setName("Manager");
        MANAGER.setPassword("manager");
        MANAGER.setRole(Role.MANAGER);

        ADMIN.setName("Admin");
        ADMIN.setPassword("admin");
        ADMIN.setRole(Role.ADMIN);
    }

    public DataLoader(OrderDao orderDao, ProductDao productDao, UserDao userDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.userDao = userDao;
    }

    @Override
    public void run(String... args) throws Exception {
        initData();
    }

    private void initData(){

        Product cheese = new Product(1,"Cheese", 450.0f);
        Product milk = new Product(2,"Milk", 55.0f);
        Product bread = new Product(3,"Bread", 34.0f);
        Product beer = new Product(4,"Beer", 65.0f);

        System.out.println("init Products");
        cheese = productDao.save(cheese);
        milk = productDao.save(milk);
        bread = productDao.save(bread);
        beer = productDao.save(beer);
        System.out.println("init orders");
        Order order1 = new Order();
        order1.setProducts(new ArrayList<>(Arrays.asList(cheese, milk, bread)));
        orderDao.save(order1);

        Order order2 = new Order();
        order2.setProducts(new ArrayList<>(Arrays.asList(cheese, beer)));
        orderDao.save(order2);

        Order order3 = new Order();
        order3.setProducts(new ArrayList<>(Arrays.asList(cheese, bread)));
        orderDao.save(order3);

        userDao.save(USER);
        userDao.save(MANAGER);
        userDao.save(ADMIN);

    }

}
