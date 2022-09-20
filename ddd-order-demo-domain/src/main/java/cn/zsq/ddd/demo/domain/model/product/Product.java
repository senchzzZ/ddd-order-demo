package cn.zsq.ddd.demo.domain.model.product;

import lombok.Data;


/**
 *
 * @author zhaoshengqi
 */
@Data
public class Product {
    long productId;

    //商品名
    String productName;

    //库存数量
    int inventoryCount;

    //单价
    Long price;

    //商家id
    Long merchantId;
}
