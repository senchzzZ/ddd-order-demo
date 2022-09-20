package cn.zsq.ddd.demo.domain.model.order;

import lombok.Data;

import java.util.Date;

/**
 * 锁定库存返回对象
 *
 * @author zhaoshengqi
 */
@Data
public class LockInventoryResponse {
    //库存锁定截止时间
    Date lockEndTime;
}
