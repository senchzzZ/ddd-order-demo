package cn.zsq.ddd.demo.domain.model.order;

import cn.zsq.ddd.demo.shared.event.BaseEvent;
import lombok.Data;

/**
 * @author zhaoshengqi
 */
@Data
public class OrderDomainEvent extends BaseEvent {
    Order order;
}
