package cn.zsq.ddd.demo.repository.condition;

import lombok.Builder;
import lombok.Data;

/**
 * @author zhaoshengqi
 */
@Builder
@Data
public class UserItemCondition {
    Long orderId;
    String productName;
    Long price;
    String orderNo;
}
