package cn.zsq.ddd.demo.remote.client;

import cn.zsq.ddd.demo.remote.client.response.BaseRemoteResponse;
import cn.zsq.ddd.demo.domain.model.order.LockInventoryResponse;
import cn.zsq.ddd.demo.remote.client.request.LockInventoryRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author zhaoshengqi
 */
@FeignClient(name = "demo-fake-schedule-api", path = "/schedule-api", fallback = ProductApiClient.Fallback.class)
public interface ScheduleApiClient {

    @RequestMapping(value = "/schedule/lockInventory", method = RequestMethod.POST)
    BaseRemoteResponse<LockInventoryResponse> lockInventory(LockInventoryRequest request);

    @Component
    class ScheduleApiClientFallback implements ScheduleApiClient{
        @Override
        public BaseRemoteResponse<LockInventoryResponse> lockInventory(LockInventoryRequest request) {
            return null;
        }
    }
}
