package cn.zsq.ddd.demo.domain.model.order;


import cn.zsq.ddd.demo.shared.err.DemoBusinessException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * @author zhaoshengqi
 */
@Data
@NoArgsConstructor
public class Order {
    /**
     * 订单id
     */
    Long orderId;
    /**
     * 订单状态
     */
    int status;
    /**
     * 总价
     */
    Long totalPrice;
    /**
     * 订单号
     */
    String orderNo;

    /**
     * 下单时间
     */
    Date orderCreateTime;
    /**
     * 备注
     */
    String remark;

    /**
     * 配送信息
     */
    DeliveryAddress deliveryAddress;

    /**
     * 订单项
     */
    List<OrderItem> orderItemList;

    /**
     * 用户id
     */
    Long userId;

    /**
     * 库存锁定截止时间
     */
    Date lockInventoryEndTime;

    //List<Coupon> couponList;


    public void initialize() {
        this.setStatus(OrderStateEnum.INIT.getValue());
        this.setOrderCreateTime(new Date());
        calculateTotalPrice();
    }

    /**
     * todo 最好的方式是使用工厂来创建领域实体
     * 订单创建,委派给setter方法,实现自封装性
     * 创建订单
     * @param orderNo
     * @param deliveryAddress
     * @param orderItemList
     * @param userId
     * @return
     */
    public static Order create(String orderNo, DeliveryAddress deliveryAddress, List<OrderItem> orderItemList, Long userId) {
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setDeliveryAddress(deliveryAddress);
        order.setOrderItemList(orderItemList);
        order.setUserId(userId);
        order.initialize();
        return order;
    }

    public void changeItemCount(long itemId, int count) {
        if (this.status >= OrderStateEnum.PAID.ordinal()) {
            throw new DemoBusinessException("order confirmed,can not be changed:" + this.orderId);
        }

        OrderItem orderItem = retrieveItem(itemId);
        orderItem.updateCount(count);
        calculateTotalPrice();
    }

    public OrderItem retrieveItem(long itemId) {
        return orderItemList.stream()
                .filter(item -> item.getItemId() == itemId)
                .findFirst()
                .orElseThrow(() -> new DemoBusinessException("order item not found,itemId:" + itemId));
    }

    /**
     * 私有,计算逻辑应交由order本身,防止逻辑的泄露
     * 计算总价时必定是修改了订单项目或增加了打折或权益信息,这些逻辑都应该交给order去完成
     */
    private void calculateTotalPrice() {
        this.setTotalPrice(orderItemList.stream()
                .mapToLong(OrderItem::calTotalPrice)
                .sum());
    }

    public void setOrderId(Long orderId) {
        if (Objects.isNull(orderId))
            throw new DemoBusinessException("orderId can not be null");
        this.orderId = orderId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTotalPrice(Long totalPrice) {
        if (Objects.isNull(totalPrice) || totalPrice < 0)
            throw new DemoBusinessException("Illegal totalPrice");
        this.totalPrice = totalPrice;
    }

    public void setOrderNo(String orderNo) {
        if (!StringUtils.hasLength(orderNo))
            throw new DemoBusinessException("orderNo can not be null");
        this.orderNo = orderNo;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        if (Objects.isNull(deliveryAddress))
            throw new DemoBusinessException("deliveryInfo can not be null");

        this.deliveryAddress = deliveryAddress;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        if (CollectionUtils.isEmpty(orderItemList))
            throw new DemoBusinessException("orderItemList can not be empty");
        this.orderItemList = orderItemList;
    }

    public void setUserId(Long userId) {
        if (Objects.isNull(userId))
            throw new DemoBusinessException("user can not be null");
        this.userId = userId;
    }

}
