package cn.zsq.ddd.demo.repository;

import cn.zsq.ddd.demo.convertor.OrderConvertor;
import cn.zsq.ddd.demo.domain.infra.repository.IOrderRepository;
import cn.zsq.ddd.demo.domain.model.order.Order;
import cn.zsq.ddd.demo.domain.model.order.OrderItem;
import cn.zsq.ddd.demo.entity.OrderEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author zhaoshengqi
 */
@Repository
public class OrderRepositoryRedis implements IOrderRepository {

    @Resource
    RedisTemplate<Long, OrderEntity> redisTemplate;

    @Override
    public void save(Order order) {
        //
    }

    @Override
    public Optional<Order> findById(long orderId) {
        return Optional.of(OrderConvertor.convertToDO(redisTemplate.opsForValue().get(orderId)));
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
