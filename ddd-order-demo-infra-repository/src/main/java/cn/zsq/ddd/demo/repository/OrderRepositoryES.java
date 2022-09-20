package cn.zsq.ddd.demo.repository;

import cn.zsq.ddd.demo.domain.infra.repository.IOrderRepository;
import cn.zsq.ddd.demo.domain.model.order.Order;
import cn.zsq.ddd.demo.domain.model.order.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author zhaoshengqi
 */
@Repository
public class OrderRepositoryES implements IOrderRepository {


    @Override
    public void save(Order order) {
        //
    }

    @Override
    public Optional<Order> findById(long orderId) {
        return Optional.empty();
    }

    @Override
    public void update(Order order) {
        //
    }

    @Override
    public void createOrder(Order order) {
        //
    }

    @Override
    public void saveOrderItem(OrderItem retrieveItem) {
        //
    }
}
