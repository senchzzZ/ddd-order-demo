package cn.zsq.ddd.demo.application.pojo.cmd;

import cn.zsq.ddd.demo.application.pojo.cmd.co.OrderCreateProductItemCO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zhaoshengqi
 */
@Data
public class OrderCreateCmd {
    @NotNull(message = "用户ID不能为空")
    Long userId;

    @NotNull(message = "未选择商品")
    List<OrderCreateProductItemCO> productItems;

    @NotNull(message = "配送信息不能为空")
    Long deliveryAddressId;

    /**
     * 优惠券
     */
    List<Long> couponId;

    String remark;
}
