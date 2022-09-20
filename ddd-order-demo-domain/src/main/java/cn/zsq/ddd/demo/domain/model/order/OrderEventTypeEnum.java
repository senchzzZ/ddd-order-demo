package cn.zsq.ddd.demo.domain.model.order;

import lombok.Getter;

/**
 * @author zhaoshengqi
 */
public enum OrderEventTypeEnum {
    INIT("order create"),
    PAID("order paid"),
    CONFIRM("order confirm"),
    SHIPPED("order shipped"),
    DONE("order done");

    OrderEventTypeEnum(String eventType) {
        this.eventType = eventType;
    }
    @Getter
    private final String eventType;
}
