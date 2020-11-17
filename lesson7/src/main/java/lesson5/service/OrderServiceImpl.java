package lesson5.service;

import org.springframework.stereotype.Service;
import lesson5.dao.OrderDao;
import lesson5.domain.Order;
import lesson5.dto.OrderDto;
import lesson5.mapper.OrderMapper;

import java.util.List;

@Service
public class OrderServiceImpl implements lesson5.service.OrderService {

    private final OrderMapper mapper = OrderMapper.MAPPER;

    private final OrderDao dao;

    public OrderServiceImpl(OrderDao dao) {
        this.dao = dao;
    }

    @Override
    public OrderDto findById(Integer id) {
        return mapper.fromOrder(dao.getOne(id));
    }

    @Override
    public List<OrderDto> findAll() {
        return mapper.fromOrderList(dao.findAll());
    }

    @Override
    public OrderDto save(OrderDto dto) {
        Order entity = mapper.toOrder(dto);
        Order savedEntity = dao.save(entity);
        return mapper.fromOrder(savedEntity);
    }
}
