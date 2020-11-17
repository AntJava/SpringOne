package lesson5.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import lesson5.domain.Order;

public interface OrderDao extends JpaRepository<Order, Integer> {
}
