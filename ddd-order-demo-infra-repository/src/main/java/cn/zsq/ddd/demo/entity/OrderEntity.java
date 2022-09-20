package cn.zsq.ddd.demo.entity;


import lombok.Data;
import org.apache.ibatis.type.Alias;


/**
 * @author zhaoshengqi
 */
@Alias("order")
@Data
public class OrderEntity {

    /**
     * id
     */
    Long id;

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
     * 用户id
     */
    Long userId;

    /**
     * 配送地址
     */
    String deliveryAddress;

    /**
     * 备注
     */
    String remark;
}
