package cn.zsq.ddd.demo.application.pojo.cmd;

import lombok.Data;


/**
 * CQRS,写命令
 * @author zhaoshengqi
 */
@Data
public class OrderChangeItemCmd {
    Long orderId;

    Long itemId;

    Integer count;
}
