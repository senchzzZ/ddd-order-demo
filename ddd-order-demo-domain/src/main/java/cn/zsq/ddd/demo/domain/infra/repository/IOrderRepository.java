package cn.zsq.ddd.demo.domain.infra.repository;

import cn.zsq.ddd.demo.domain.model.order.Order;
import cn.zsq.ddd.demo.domain.model.order.OrderItem;

import java.util.Optional;

/**
 * @author zhaoshengqi
 */
public interface IOrderRepository {

    void save(Order order);

    Optional<Order> findById(long orderId) ;

    void update(Order order);

    void createOrder(Order order);

    void saveOrderItem(OrderItem retrieveItem);
}
