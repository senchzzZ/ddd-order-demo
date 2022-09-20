package cn.zsq.ddd.demo.repository;

import cn.zsq.ddd.demo.convertor.OrderConvertor;
import cn.zsq.ddd.demo.convertor.OrderItemConvertor;
import cn.zsq.ddd.demo.domain.model.order.Order;
import cn.zsq.ddd.demo.domain.model.order.OrderItem;
import cn.zsq.ddd.demo.domain.infra.repository.IOrderRepository;
import cn.zsq.ddd.demo.entity.OrderEntity;
import cn.zsq.ddd.demo.entity.OrderItemEntity;
import cn.zsq.ddd.demo.repository.condition.UserItemCondition;
import cn.zsq.ddd.demo.repository.mapper.OrderItemMapper;
import cn.zsq.ddd.demo.repository.mapper.OrderMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author zhaoshengqi
 */
@Repository
public class OrderRepository implements IOrderRepository {

    @Resource
    OrderMapper orderMapper;

    @Resource
    OrderItemMapper orderItemMapper;

    /*@Resource(name = "redisBucketCacheManager")
	private CacheManager cacheManager;*/

	/*@Resource
	private RedisTemplate<String, Object> redisTemplate;*/


    public void save(Order order) {
        if (order.getOrderId() != null) {
            orderMapper.update(OrderConvertor.convertToEntity(order));
        } else {
            orderMapper.insert(OrderConvertor.convertToEntity(order));
        }
    }

    @Override
    public void saveOrderItem(OrderItem orderItem) {
        if (orderItem.getItemId() != null) {
            orderItemMapper.update(OrderItemConvertor.INSTANCT.convertToEntity(orderItem));
        } else {
            orderItemMapper.insert(OrderItemConvertor.INSTANCT.convertToEntity(orderItem));
        }
    }

    /**
     * 创建订单
     * @param order
     */
    @Override
    public void createOrder(Order order) {
        //保存订单
        OrderEntity orderEntity = OrderConvertor.convertToEntity(order);
        orderMapper.insert(orderEntity);
        order.setOrderId(orderEntity.getId());
        //保存订单项
        List<OrderItem> orderItemList = order.getOrderItemList();
        orderItemList.forEach(orderItem -> {
            orderItem.setOrderId(orderEntity.getId());
            orderItemMapper.insert(OrderItemConvertor.INSTANCT.convertToEntity(orderItem));
        });
    }

    @Override
    public void update(Order order) {
        orderMapper.update(OrderConvertor.convertToEntity(order));
    }

    public Optional<Order> findById(long orderId) {
        Order order = null;
        OrderEntity orderEntity = orderMapper.findById(orderId);
        if (orderEntity != null) {
            order = OrderConvertor.convertToDO(orderMapper.findById(orderId));
            List<OrderItemEntity> orderItemEntities = orderItemMapper.listByCondition(
                    UserItemCondition.builder().orderId(orderEntity.getId()).build()
            );
            if (!CollectionUtils.isEmpty(orderItemEntities)) {
                order.setOrderItemList(OrderItemConvertor.INSTANCT.convertToDOList(orderItemEntities));
            }
        }
        return Optional.ofNullable(order);
    }
}
