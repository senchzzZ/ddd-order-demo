package cn.zsq.ddd.demo.remote.client.request;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 *
 * @author zhaoshengqi
 */
@Data
public class LockInventoryRequest {
    /**
     * 订单号
     */
    String orderNo;

    /**
     * 下单时间
     */
    Date orderTime;

    /**
     * 订单项
     */
    List<OrderItemData> orderItemDataList;

    @Data
    public static class OrderItemData {
        //商品ID
        Long productId;

        //项目数量
        Integer count;
    }
}
