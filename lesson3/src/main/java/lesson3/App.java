package lesson3;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.List;

public class App {
    public static void main(String[] args) {

        EntityManagerFactory entityFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = entityFactory.createEntityManager();

        for (int i = 0; i < 5; i++) {
            Client client = new Client();
            client.setClient("Client " + i);
            createEntity(em, client);
        }

        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setProduct("Product " + i);
            createEntity(em, product);

            for (int j = 0; j < 2; j++) {
                ProductPrice productPrice = new ProductPrice();
                productPrice.setProduct(readEntity(em, Product.class, i + 1L));
                productPrice.setDate_from(LocalDate.of(2019 + j, 1, 1 + j));
                productPrice.setDate_to(LocalDate.of(2020 + j, 1, 1 + j));
                productPrice.setPrice(Math.random()*200);
                createEntity(em, productPrice);

                for (int k = 0; k < 3; k++) {
                    Sell sell = new Sell();
                    sell.setClient_id(readEntity(em, Client.class, (long)(Math.random()*5) + 1));
                    sell.setProduct_id(readEntity(em, Product.class, i + 1L));
                    sell.setAmount((long) (Math.random()*500));
                    sell.setDate(LocalDate.of((int) (Math.random()*2) + 2019, (int) (Math.random()*12) + 1, (int) (Math.random()*28) + 1));
                    createEntity(em, sell);
                }
            }
        }

        long clientId = 1L;
        clientHistory(em, clientId);

    }

    private static void clientHistory(EntityManager em, long id) {
        List<Sell> sells = em.createNamedQuery("Sell.findByClient", Sell.class)
                .setParameter("id", readEntity(em, Product.class, id))
                .getResultList();
        for (int i = 0; i < sells.size(); i++) {
            ProductPrice productPrice = em.createNamedQuery("ProductPrice.findPriceByDate", ProductPrice.class)
                    .setParameter("date", sells.get(i).getDate())
                    .setParameter("product_id", sells.get(i).getProduct_id())
                    .getSingleResult();
            System.out.print("Client: " + sells.get(i).getClient_id().getClient());
            System.out.print(", Product: " + sells.get(i).getProduct_id().getProduct());
            System.out.print(", Date: " + sells.get(i).getDate());
            System.out.print(", Price for each: " + productPrice.getPrice());
            System.out.print(", Amount: " + sells.get(i).getAmount());
            System.out.println(", Total price: " + productPrice.getPrice() * sells.get(i).getAmount());
        }
    }

    private static <T> void createEntity(EntityManager em, T entity){

        System.out.println("Creating entity");
        //open transaction
        em.getTransaction().begin();
        //put person into persist area of Hibernate
        em.persist(entity);
        //commit/close transaction
        em.getTransaction().commit();

        System.out.println("Creating finished");

    }

    private static <T> T readEntity(EntityManager em, Class<T> clazz, long id){
        System.out.println("Start reading");

        em.getTransaction().begin();
        T person = em.find(clazz, id);
        em.getTransaction().commit();

        System.out.println("Reading completed->" + person);
        return person;
    }

    private static <T> T saveEntity(EntityManager em, T entity){
        System.out.println("Start saving");

        em.getTransaction().begin();
        T savedEntity = em.merge(entity);
        em.getTransaction().commit();

        System.out.println("Saving completed->" + savedEntity);

        return savedEntity;
    }

}
