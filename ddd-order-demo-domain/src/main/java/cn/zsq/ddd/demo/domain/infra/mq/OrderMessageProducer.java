package cn.zsq.ddd.demo.domain.infra.mq;

import cn.zsq.ddd.demo.domain.model.order.Order;
import cn.zsq.ddd.demo.domain.model.order.OrderEvent;
import cn.zsq.ddd.demo.domain.model.order.OrderEventTypeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 基础设施-mq
 * 订单消息生产
 * 这里是直接实现了事件行为;如果发送事件的业务很复杂,则可以只定义接口,然后独立出一个事件模块做基础设施实现(参考资源库和remote)
 * @author zhaoshengqi
 */
@Component
public class OrderMessageProducer {

    @Resource
    private KafkaTemplate<Object, Object> template;

    /**
     * 发布订单事件消息
     * @param order
     * @param eventType
     */
    public void publish(Order order, OrderEventTypeEnum eventType) {
        OrderEvent event = makeOrderEvent(order);
        event.setEventType(eventType.getEventType());
        event.setEventTime(new Date());
        template.send("topic1",event);
    }

    private OrderEvent makeOrderEvent(Order order){
        OrderEvent event = new OrderEvent();
        BeanUtils.copyProperties(order, event);
        return event;
    }
}
