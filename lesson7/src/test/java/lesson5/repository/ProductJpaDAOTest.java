package lesson5.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import lesson5.domain.Product;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class ProductJpaDAOTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private ProductJpaDAO productDAO;

    @Test
    public void checkFindByPriceBetween(){

        //have
        Product milk = new Product(null, "Milk", 54.0f);
        Product cheese = new Product(null, "Cheese", 450.0f);
        Product bread = new Product(null, "Bread", 45.0f);
        Product water = new Product(null, "Water", 15.0f);
        List<String> expectedTitles = Arrays.asList(milk.getTitle(), bread.getTitle());

        em.persist(milk);
        em.persist(cheese);
        em.persist(bread);
        em.persist(water);
        em.flush();

        //execute
        List<Product> byPrice = productDAO.findAllByPriceBetween(30.0f, 100.0f);

        //check
        Assert.assertNotNull(byPrice);
        Assert.assertEquals(2, byPrice.size());
        Assert.assertTrue(expectedTitles.contains(byPrice.get(0).getTitle()));
        Assert.assertTrue(expectedTitles.contains(byPrice.get(1).getTitle()));
    }

}