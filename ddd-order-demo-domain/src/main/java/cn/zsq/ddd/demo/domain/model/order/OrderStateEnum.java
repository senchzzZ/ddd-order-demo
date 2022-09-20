package cn.zsq.ddd.demo.domain.model.order;

import lombok.Getter;

/**
 * @author zhaoshengqi
 */
public enum OrderStateEnum {
    INIT(0),
    PAID(1),
    CONFIRM(2),
    SHIPPED(3),
    DONE(4);

    OrderStateEnum(int value) {
        this.value = value;
    }
    @Getter
    private final int value;
}
