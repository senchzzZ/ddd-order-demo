package cn.zsq.ddd.demo.domain.model.order;


import cn.zsq.ddd.demo.domain.model.user.User;
import cn.zsq.ddd.demo.shared.event.BaseEvent;
import lombok.Data;

import java.util.List;


/**
 * @author zhaoshengqi
 */
@Data
public class OrderEvent extends BaseEvent {
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
     * 用户信息
     */
    User user;

}
