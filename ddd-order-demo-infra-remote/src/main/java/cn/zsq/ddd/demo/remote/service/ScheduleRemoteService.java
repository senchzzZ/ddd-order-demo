package cn.zsq.ddd.demo.remote.service;

import cn.zsq.ddd.demo.domain.infra.remote.IScheduleRemoteService;
import cn.zsq.ddd.demo.remote.client.request.LockInventoryRequest;
import cn.zsq.ddd.demo.domain.model.order.Order;
import cn.zsq.ddd.demo.remote.client.response.BaseRemoteResponse;
import cn.zsq.ddd.demo.domain.model.order.LockInventoryResponse;
import cn.zsq.ddd.demo.remote.client.ScheduleApiClient;
import cn.zsq.ddd.demo.remote.convertor.OrderConvertor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 远程订单调度服务
 *
 * @author zhaoshengqi
 */
@Component
@Slf4j
public class ScheduleRemoteService implements IScheduleRemoteService {

    @Autowired
    ScheduleApiClient scheduleApiClient;

    /**
     * 锁定库存
     * @param order
     * @return
     */
    @Override
    public Optional<LockInventoryResponse> lockInventory(Order order) {
        LockInventoryRequest request = OrderConvertor.convertToLockInventoryRequest(order);

        BaseRemoteResponse<LockInventoryResponse> response = scheduleApiClient.lockInventory(request);
        if (response == null || response.failed()) {
            log.error("lock inventory error,request:{},response:{}", request, response);
            return Optional.empty();
        }
        //other...
        //todo 这里是直接把第三方返回的模型当做领域模型来用,如果业务较为复杂,
        // 可以把第三方服务的返回的模型和业务领域模型区分开,但需要额外做一次模型转换;
        // 建议:如非必要,可以直接把第三方模型当做领域模型使用,架构层面上是允许这么做的
        return Optional.ofNullable(response.getData());
    }
}
