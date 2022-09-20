package cn.zsq.ddd.demo.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author zhaoshengqi
 */
@Alias("orderItem")
@Data
public class OrderItemEntity {
    Long id;
    //订单id
    Long orderId;

    //订单号
    String orderNo;

    //数量
    Integer count;

    //单价
    Long price;

    //商品id
    Long productId;

    //商品名
    String productName;

    //折扣
    Double discount;

    //配送仓库
    Long depotId;
    //总价
    Long totalPrice;

}
