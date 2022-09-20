package cn.zsq.ddd.demo.domain.infra.remote;

import cn.zsq.ddd.demo.domain.model.order.LockInventoryResponse;
import cn.zsq.ddd.demo.domain.model.order.Order;

import java.util.Optional;

/**
 * 基础设施-调度中心远程服务
 * @author zhaoshengqi
 */
public interface IScheduleRemoteService{

    /**
     * 锁定库存
     * @param order
     * @return
     */
    Optional<LockInventoryResponse> lockInventory(Order order);
}
