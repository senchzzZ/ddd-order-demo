package cn.zsq.ddd.demo.convertor;

import cn.zsq.ddd.demo.domain.model.order.Order;
import cn.zsq.ddd.demo.entity.OrderEntity;

import java.util.Objects;

/**
 * @author zhaoshengqi
 */
public class OrderConvertor {

    private OrderConvertor() {
    }

    public static Order convertToDO(OrderEntity entity) {
        if (entity == null)
            return null;
        Order order = new Order();
        order.setOrderId(entity.getId());
        order.setOrderNo(entity.getOrderNo());
        order.setStatus(entity.getStatus());
        order.setRemark(entity.getRemark());
        order.setTotalPrice(entity.getTotalPrice());
        order.setUserId(entity.getUserId());
        return order;
    }

    public static OrderEntity convertToEntity(Order order) {
        if (order == null)
            return null;
        OrderEntity entity = new OrderEntity();
        if (Objects.nonNull(order.getOrderId())) {
            entity.setId(order.getOrderId());
        }
        entity.setUserId(order.getUserId());
        entity.setOrderNo(order.getOrderNo());
        entity.setRemark(order.getRemark());
        entity.setStatus(order.getStatus());
        entity.setTotalPrice(entity.getTotalPrice());
        entity.setDeliveryAddress(order.getDeliveryAddress().getAddressDetail());
        return entity;
    }


}
