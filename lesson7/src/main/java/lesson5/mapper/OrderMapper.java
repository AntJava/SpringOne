package lesson5.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import lesson5.domain.Order;
import lesson5.dto.OrderDto;

import java.util.List;

@Mapper(uses = {lesson5.mapper.ProductMapper.class})
public interface OrderMapper {
    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "orderedProducts", target = "products")
    Order toOrder(OrderDto dto);

    List<Order> toOrderList(List<OrderDto> orders);

    @InheritInverseConfiguration
    OrderDto fromOrder(Order order);

    List<OrderDto> fromOrderList(List<Order> orders);

}
