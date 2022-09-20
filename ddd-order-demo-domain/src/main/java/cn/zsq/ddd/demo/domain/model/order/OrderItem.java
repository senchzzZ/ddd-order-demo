package cn.zsq.ddd.demo.domain.model.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaoshengqi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    //订单项ID
    Long itemId;

    //订单id
    Long orderId;

    //订单号
    String orderNo;

    //商品名
    String productName;

    //商品ID
    Long productId;

    //项目数量
    Integer count;

    //单价
    Long price;

    //折扣
    Double discount;

    //配送仓库
    Long depotId;

    //总价
    Long totalPrice;

    public OrderItem(String orderNo, Long productId, Integer count) {
        this.setOrderNo(orderNo);
        this.setProductId(productId);
        this.setCount(count);
    }

    public void updateCount(int count) {
        this.count = count;
        this.totalPrice = calTotalPrice();
    }

    public long calTotalPrice() {
        return count * price;
    }

}
