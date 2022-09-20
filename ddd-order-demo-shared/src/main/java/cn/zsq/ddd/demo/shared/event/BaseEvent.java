package cn.zsq.ddd.demo.shared.event;

import lombok.Data;

import java.util.Date;

/**
 * @author zhaoshengqi
 */
@Data
public class BaseEvent {
    String eventType;
    Date eventTime;
}
