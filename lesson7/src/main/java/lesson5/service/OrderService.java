package lesson5.service;

import lesson5.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto findById(Integer id);

    List<OrderDto> findAll();

    OrderDto save(OrderDto dto);
}
