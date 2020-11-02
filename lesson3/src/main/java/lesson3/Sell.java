package lesson3;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sell")
@NamedQueries({
        @NamedQuery(name = "Sell.findByClient", query = "SELECT a FROM Sell a WHERE a.client = :id")
})
public class Sell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "amount")
    private Long amount;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Sell() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient_id() {
        return client;
    }

    public void setClient_id(Client client) {
        this.client = client;
    }

    public Product getProduct_id() {
        return product;
    }

    public void setProduct_id(Product product) { this.product = product; }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
