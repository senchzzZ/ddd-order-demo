package cn.zsq.ddd.demo.application.pojo.cmd.co;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author zhaoshengqi
 */
@Data
public class OrderCreateProductItemCO {
    @NotNull(message = "商品id不能为空")
    Long productId;

    @NotNull(message = "商品名称不能为空")
    String productName;

    @NotNull(message = "商品数量不能为空")
    @Min(value = 1, message = "商品数量最小为1")
    Integer count;
}
