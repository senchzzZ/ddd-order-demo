package cn.zsq.ddd.demo.remote.client;

import cn.zsq.ddd.demo.remote.client.response.BaseRemoteResponse;
import cn.zsq.ddd.demo.domain.model.product.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 外部服务-商品服务
 * @author zhaoshengqi
 */
@FeignClient(name = "demo-fake-product-api", path = "/product-api", fallback = ProductApiClient.Fallback.class)
public interface ProductApiClient {

    @RequestMapping(value = "/product/getProductByIds", method = RequestMethod.POST)
    BaseRemoteResponse<List<Product>> getProductInfos(List<Long> productIds);


    @Component
    class Fallback implements ProductApiClient {

        @Override
        public BaseRemoteResponse<List<Product>> getProductInfos(List<Long> productIds) {
            return null;
        }
    }
}
